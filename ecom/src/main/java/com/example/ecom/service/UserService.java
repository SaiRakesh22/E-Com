package com.example.ecom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecom.model.Users;
import com.example.ecom.repo.UserRepo;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;  
    
    public void saveUser(Users user) {
        userRepo.save(user);
        // Logic to save user to the database
    }
}
