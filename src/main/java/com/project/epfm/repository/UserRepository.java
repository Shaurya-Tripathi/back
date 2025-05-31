package com.project.epfm.repository;

import com.project.epfm.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<Users,Long> {

    Users findByUserName(String userName);
    Users findByEmail(String email);
}
