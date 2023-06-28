package com.project.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dao.AppointmentRepository;
import com.project.dao.DoctorRepository;
import com.project.dao.PatientRepository;
import com.project.model.Appointment;
import com.project.model.Patient;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public AppointmentController(AppointmentRepository appointmentRepository, PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository=doctorRepository;
    }

    @GetMapping("/{patientId}")
    public List<Appointment> getAppointmentsByPatientId(@PathVariable Long patientId) {
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();
            return appointmentRepository.findByPatientId(patientId);
        } else {
            throw new NotFoundException("Patient not found");
        }
    }

    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment) {
    	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    	 
    	 LocalDate parsedDate = LocalDate.parse(appointment.getDate(), formatter);
         appointment.setDate(parsedDate.toString());
         
         if(!appointment.getIncomingDate().isBlank()) {
         LocalDate parsedIncomingDate = LocalDate.parse(appointment.getIncomingDate(), formatter);
         appointment.setIncomingDate(parsedIncomingDate.toString());
         }
       
         if(!appointment.getOutgoingDate().isBlank()) {
        LocalDate parsedOutgoingDate = LocalDate.parse(appointment.getOutgoingDate(), formatter);
        appointment.setOutgoingDate(parsedOutgoingDate.toString());
         }
       Long patientId = appointment.getPatient().getId();
       
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();
            appointment.setPatient(patient);
            return appointmentRepository.save(appointment);
        } else {
            throw new NotFoundException("Patient not found");
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody Appointment updatedAppointment) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        if (optionalAppointment.isPresent()) {
            Appointment appointment = optionalAppointment.get();
            appointment.setDate(updatedAppointment.getDate());
            appointment.setTime(updatedAppointment.getTime());
            appointment.setProblem(updatedAppointment.getProblem());
            appointment.setPrescription(updatedAppointment.getPrescription());
            appointment.setIncomingDate(updatedAppointment.getIncomingDate());
            appointment.setIncomingTime(updatedAppointment.getIncomingTime());
            appointment.setOutgoingDate(updatedAppointment.getOutgoingDate());
            appointment.setOutgoingTime(updatedAppointment.getOutgoingTime());
            
            Long patientId = updatedAppointment.getPatient().getId();
            Optional<Patient> optionalPatient = patientRepository.findById(patientId);
            if (optionalPatient.isPresent()) {
                Patient patient = optionalPatient.get();
                appointment.setPatient(patient);
                appointmentRepository.save(appointment);
                return ResponseEntity.ok(appointment);
            } else {
                throw new NotFoundException("Patient not found");
            }
            
         
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        if (optionalAppointment.isPresent()) {
            appointmentRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
