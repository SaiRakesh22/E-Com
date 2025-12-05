package com.example.ecom.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecom.model.Users;

@Repository
public interface UserRepo extends JpaRepository<Users,String> {

    Users findByUserName(String username);
    
}
