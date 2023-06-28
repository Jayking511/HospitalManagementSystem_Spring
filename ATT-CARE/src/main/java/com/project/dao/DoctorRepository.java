package com.project.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.model.Doctor;
import com.project.model.Patient;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

	Optional<Doctor> findById(Long id);
	/*
	 * Optional<Doctor> findById(Long id); Optional<Doctor> deleteById(Long id);
	 * Optional<Doctor> findByEmail(String email);
	 */

	Optional<Doctor> findByEmail(String email);

	void deleteById(Long id);
}
