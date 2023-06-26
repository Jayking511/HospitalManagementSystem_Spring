package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Appointment Id")
    private Long id;
    

   
    @Column(name = "Appointment Date")
    private String date;

   
    @Column(name = "Appointment Time")
    private LocalTime time;

    private String incomingDate;
    private LocalTime incomingTime;


    private String outgoingDate;
    private LocalTime outgoingTime;

    
    
    private String description;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    // Constructors, getters, and setters
}
