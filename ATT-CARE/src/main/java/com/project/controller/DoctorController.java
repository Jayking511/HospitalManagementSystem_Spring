package com.project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.auth.AuthenticationResponse;
import com.project.auth.AuthenticationService;
import com.project.auth.RegisterRequest;
import com.project.model.Doctor;
import com.project.service.DoctorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/doctors")
@Tag(name = "Doctor")
@RequiredArgsConstructor
public class DoctorController {
	
	@Autowired
	private final DoctorService doctorService;

	/*
	 * @Operation( description = "Get endpoint for manager", summary =
	 * "This is a summary for management get endpoint", responses = {
	 * 
	 * @ApiResponse( description = "Success", responseCode = "200" ),
	 * 
	 * @ApiResponse( description = "Unauthorized / Invalid Token", responseCode =
	 * "403" ) }
	 * 
	 * )
	 */
	 	@GetMapping
	    public List<Doctor> getAllDoctors() {
	        return doctorService.getAllDoctors();
	    }

	   @GetMapping("/{id}")
	    public ResponseEntity<Doctor> getDoctorById(@PathVariable  Long id) {
	       
	        return doctorService.getDoctorById(id);
	    }

	   @PostMapping
	   public String createDoctor(@RequestBody Doctor doctor) {
	        // Perform any necessary validation or business logic

	        doctorService.createDoctor(doctor);
	        
	        return "Doctor registered successfully";
	    }

		/*
		 * @PutMapping("/{id}") public ResponseEntity<Doctor> updateDoctor(@PathVariable
		 * Long id, @RequestBody Doctor updatedDoctor) { return
		 * doctorService.updateDoctor(id, updatedDoctor); }
		 */
	   
	   
	   
	   @PutMapping("/{email}")
	    public ResponseEntity<Doctor> updateDoctorByEmail(@PathVariable String email, @RequestBody Doctor updatedDoctor) {
	       return doctorService.updateDoctorByEmail(email, updatedDoctor);
	    }
	   

	   @DeleteMapping("/{id}")
	    public void deleteDoctor(@PathVariable Long id) throws NotFoundException{
	      doctorService.deleteDoctor(id);
	   }

}
