package com.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
