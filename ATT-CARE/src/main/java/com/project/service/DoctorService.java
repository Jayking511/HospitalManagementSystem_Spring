package com.project.service;

import java.util.List;


import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.auth.AuthenticationRequest;
import com.project.auth.AuthenticationResponse;
import com.project.controller.NotFoundException;
import com.project.dao.DoctorRepository;
import com.project.model.Doctor;
import com.project.model.Patient;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

   
    public ResponseEntity<Doctor> getDoctorById(Long id) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        return optionalDoctor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public Doctor createDoctor(Doctor doctor) {
        // Perform any necessary validation or business logic
    	Doctor doctor1=doctorRepository.save(doctor);
        return doctor1;
    }

   
    public ResponseEntity<Doctor> updateDoctor(Long id,  Doctor updatedDoctor) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();
           doctor.setName(updatedDoctor.getName());
           doctor.setEmail(updatedDoctor.getEmail());
           doctor.setPassword(updatedDoctor.getPassword());
           doctor.setSpecialization(updatedDoctor.getSpecialization());
           doctor.setGender(updatedDoctor.getGender());
           doctor.setMobile(updatedDoctor.getMobile());
           doctorRepository.save(doctor);
            return ResponseEntity.ok(doctor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    public ResponseEntity<Doctor> updateDoctorByEmail(String email,  Doctor updatedDoctor) {
        Optional<Doctor> optionalDoctor = doctorRepository.findByEmail(email);
        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();
           doctor.setName(updatedDoctor.getName());
           doctor.setEmail(updatedDoctor.getEmail());
           doctor.setPassword(updatedDoctor.getPassword());
           doctor.setSpecialization(updatedDoctor.getSpecialization());
           doctor.setGender(updatedDoctor.getGender());
           doctor.setMobile(updatedDoctor.getMobile());
           doctorRepository.save(doctor);
            return ResponseEntity.ok(doctor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
   
	
	  public void deleteDoctor( Long id) throws NotFoundException{ Optional<Doctor>
	  optionalDoctor = doctorRepository.findById(id); if
	  (optionalDoctor.isPresent()) { doctorRepository.deleteById(id);
	  
	  } else { new NotFoundException("doctor not found with id:"+id); } }
	 
    
    
    // Add more service methods as needed
}
