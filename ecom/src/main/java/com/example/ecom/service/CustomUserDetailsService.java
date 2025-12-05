package com.example.ecom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.ecom.model.AllUserDetails;
import com.example.ecom.model.Users;
import com.example.ecom.repo.UserRepo;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired 
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user1 = userRepo.findByUserName(username); 
        if(user1==null){
            throw new UsernameNotFoundException("User not found");
        }
        return new AllUserDetails(user1);
    }
    
}
