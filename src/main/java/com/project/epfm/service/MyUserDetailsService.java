package com.project.epfm.service;


import com.project.epfm.Entity.UserPrincipal;
import com.project.epfm.Entity.Users;
import com.project.epfm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class MyUserDetailsService implements UserDetailsService {


    private UserRepository repo;
    @Autowired
    public MyUserDetailsService(UserRepository repo){
        this.repo =repo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = repo.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found for email: " + email);
        }

        return new UserPrincipal(user);
    }




}
