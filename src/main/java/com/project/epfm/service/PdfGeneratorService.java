package com.project.epfm.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.project.epfm.Entity.Employee;
import com.project.epfm.Entity.Feedbacks;
import com.project.epfm.Entity.PerformanceReview;
import com.project.epfm.Entity.Goals;
import com.project.epfm.DTO.EmployeeReportDTO;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class PdfGeneratorService {

    public byte[] generateEmployeeReport(EmployeeReportDTO reportDTO) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);


        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String formattedDateTime = now.format(dtf);
        document.add(new Paragraph(formattedDateTime).setTextAlignment(TextAlignment.RIGHT));

        // Employee Details
        Employee employee = reportDTO.getEmployee();
        document.add(new Paragraph("Employee Report").setBold().setFontSize(18));
        document.add(new Paragraph("Name: " + employee.getName()));
        document.add(new Paragraph("Department: " + employee.getDepartment()));
        document.add(new Paragraph("Role: " + employee.getRole()));
        document.add(new Paragraph("Contact: " + employee.getContactDetails()));

        // Feedbacks Section
        document.add(new Paragraph("\nFeedbacks:").setBold());
        if (reportDTO.getFeedbacksList() == null || reportDTO.getFeedbacksList().isEmpty()) {
            document.add(new Paragraph("No Feedback given to employee"));
        } else {
            for (Feedbacks feedback : reportDTO.getFeedbacksList()) {
                document.add(new Paragraph("From: " + feedback.getFromEmployee().getName() +
                        " | Type: " + feedback.getFeedbackType() +
                        " | Comment: " + feedback.getComment()));
            }
        }

        // Performance Reviews Section
        document.add(new Paragraph("\nPerformance Reviews:").setBold());
        if (reportDTO.getPerformanceReviews() == null || reportDTO.getPerformanceReviews().isEmpty()) {
            document.add(new Paragraph("No Performace review given to employee"));
        } else {
            for (PerformanceReview review : reportDTO.getPerformanceReviews()) {
                document.add(new Paragraph("Date: " + review.getDate() +
                        " | Score: " + review.getPerformanceScore() +
                        " | Reviewer: " + review.getManager().getName() +
                        " | Feedback: " + review.getFeedback()));
            }
        }

        // Goals Section
        document.add(new Paragraph("\nGoals:").setBold());
        if (reportDTO.getGoals() == null || reportDTO.getGoals().isEmpty()) {
            document.add(new Paragraph("No golas assigned for the employee"));
        } else {
            for (Goals goal : reportDTO.getGoals()) {
                document.add(new Paragraph("Goal: " + goal.getGoalDescription() +
                        " | Status: " + goal.getProgressStatus() +
                        " | Target Date: " + goal.getFormattedTargetDate()));
            }
        }

        document.close();
        return baos.toByteArray();
    }
}
