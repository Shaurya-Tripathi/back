package com.project.epfm.Controller;



import com.project.epfm.DTO.EmployeeReportDTO;
import com.project.epfm.service.EmployeeReportService;
import com.project.epfm.service.PdfGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@PreAuthorize("hasRole('HR')")
@RequestMapping("/hr")
public class PdfController {

    private final PdfGeneratorService pdfGeneratorService;
    private final EmployeeReportService employeeReportService;

    @Autowired
    public PdfController(PdfGeneratorService pdfGeneratorService, EmployeeReportService employeeReportService) {
        this.pdfGeneratorService = pdfGeneratorService;
        this.employeeReportService = employeeReportService;
    }

    @GetMapping("/download-pdf/{employeeId}")
    public ResponseEntity<InputStreamResource> downloadPdf(@PathVariable Long employeeId) {
        try {
            EmployeeReportDTO reportDTO = employeeReportService.getEmployeeReport(employeeId);
            byte[] pdfBytes = pdfGeneratorService.generateEmployeeReport(reportDTO);

            ByteArrayInputStream bis = new ByteArrayInputStream(pdfBytes);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=Employee_Report_" + employeeId + ".pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(pdfBytes.length)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(bis));

        } catch (IOException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

