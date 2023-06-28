package com.project.model;


import java.time.LocalTime;



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

    
    private String problem;
    
    private String prescription;
   
    @Column(name = "Appointment Time")
    private LocalTime time;

    private String incomingDate;
    private LocalTime incomingTime;


    private String outgoingDate;
    private LocalTime outgoingTime;

    
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

  
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    // Constructors, getters, and setters
}
