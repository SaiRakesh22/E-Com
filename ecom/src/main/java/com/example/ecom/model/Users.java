package com.example.ecom.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
public class Users {
    @Id
    private int id;
    private String username;
}
