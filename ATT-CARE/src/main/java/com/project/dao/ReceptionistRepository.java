package com.project.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.model.Doctor;
import com.project.model.Patient;
import com.project.model.Receptionist;

@Repository
public interface ReceptionistRepository extends JpaRepository<Receptionist, Integer> {

	Optional<Receptionist> findById(Long id);

	Optional<Receptionist> findByEmail(String email);

	void deleteById(Long id);

	
}
