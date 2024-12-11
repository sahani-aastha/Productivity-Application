package com.example.demo.models;

import jakarta.persistence.*;


@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int userId;
    private String content;

    // Getters and Setters
}
