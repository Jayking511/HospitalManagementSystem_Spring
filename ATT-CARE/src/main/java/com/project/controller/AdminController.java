package com.project.controller;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.auth.AuthenticationResponse;
import com.project.auth.AuthenticationService;
import com.project.auth.RegisterRequest;
import com.project.model.Doctor;
import com.project.model.Receptionist;
import com.project.service.DoctorService;
import com.project.service.ReceptionistService;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")

public class AdminController {
	private final AuthenticationService service;	
    private final DoctorService doctorService;
    private final ReceptionistService receptionistService;
    public AdminController(AuthenticationService service,DoctorService doctorService,ReceptionistService receptionistService) {
    	this.service=service;
    	this.doctorService=doctorService;
    	this.receptionistService=receptionistService;
    }
    
	  @PostMapping("/register")
	  public ResponseEntity<AuthenticationResponse> register(
	      @RequestBody RegisterRequest request
	  ) {
	    return ResponseEntity.ok(service.register(request));
	  }
	  
	  @PostMapping("/doctor-register")
	  public ResponseEntity<AuthenticationResponse> doctorRegister(
	      @RequestBody RegisterRequest request
	  ) {
		  String email=request.getEmail();
		  String password=request.getPassword();
		  Doctor doctor=new Doctor();
		  doctor.setEmail(email);
		  doctor.setPassword(password);
		  doctorService.createDoctor(doctor);
	    return ResponseEntity.ok(service.register(request));
	  }
	  
	
	 @PostMapping("/receptionist-register")
	  public ResponseEntity<AuthenticationResponse> registerReceptionist(
	      @RequestBody RegisterRequest request
	  ) {
		 String email=request.getEmail();
		  String password=request.getPassword();
		  Receptionist receptionist=new Receptionist();
		  receptionist.setEmail(email);
		  receptionist.setPassword(password);
		  receptionistService.createReceptionist(receptionist);
	    return ResponseEntity.ok(service.register(request));
	  }

		/*
		 * @GetMapping
		 * 
		 * @PreAuthorize("hasAuthority('admin:read')") public String get() { return
		 * "GET:: admin controller"; }
		 * 
		 * @PostMapping
		 * 
		 * @PreAuthorize("hasAuthority('admin:create')")
		 * 
		 * @Hidden public String post() { return "POST:: admin controller"; }
		 * 
		 * @PutMapping
		 * 
		 * @PreAuthorize("hasAuthority('admin:update')")
		 * 
		 * @Hidden public String put() { return "PUT:: admin controller"; }
		 * 
		 * @DeleteMapping
		 * 
		 * @PreAuthorize("hasAuthority('admin:delete')")
		 * 
		 * @Hidden public String delete() { return "DELETE:: admin controller"; }
		 */
}
