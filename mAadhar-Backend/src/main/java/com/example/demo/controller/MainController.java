package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Admin;
import com.example.demo.model.Citizen;
import com.example.demo.repository.AdminRepo;
import com.example.demo.repository.CitizensRepo;
import com.examples.demo.api.exception.AdminNotFoundException;
import com.examples.demo.api.exception.CitizenNotFoundException;


@RestController
public class MainController {

	@Autowired
	private AdminRepo adminRepo;

	@Autowired
	private CitizensRepo citizenRepo;

	// Admin
	@CrossOrigin(origins = "http://localhost")
	@GetMapping("/admin")
	public Iterable<Admin> getAllAdmin() {
		return adminRepo.findAll();
	}

	@CrossOrigin(origins = "http://localhost")
	@GetMapping("/admin/{id}")
	public Admin getAdmin(@PathVariable("id") Integer id) {
		Optional<Admin> optAdmin = adminRepo.findById(id);
		if (optAdmin.isEmpty()) {
			throw new AdminNotFoundException(id);
		}
		return optAdmin.get();
	}

	@CrossOrigin(origins = "http://localhost")
	@PostMapping("/admin")
	public Admin createAdmin(@RequestBody Admin admin) {
		return adminRepo.save(admin);
	}

	@CrossOrigin(origins = "http://localhost")
	@PutMapping("/admin/{id}")
	public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin,@PathVariable("id")Integer id) {
		 Optional<Admin> optionalAdmin = adminRepo.findById(id);

	        if (optionalAdmin.isPresent()) {
	            Admin existingAdmin = optionalAdmin.get();


	            existingAdmin.setAdminName(admin.getAdminName());
	            existingAdmin.setEmail(admin.getEmail());
	            existingAdmin.setPassword(admin.getPassword());
	            adminRepo.save(existingAdmin);

	            
	            return ResponseEntity.ok(existingAdmin);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	}

	@CrossOrigin(origins = "http://localhost")
	@DeleteMapping("/admin/{id}")
	public void deleteAdmin(@PathVariable("id") Integer id) {
		adminRepo.deleteById(id);
	}
	
	@CrossOrigin(origins = "http://localhost")
	@GetMapping("/admin/byEmail/{email}")
	public int getAdminIdByEmail(@PathVariable("email") String email) {
		Admin admin=adminRepo.findIdByEmail(email);
		return admin.getId();
	}
	
	
    //----------------------------------------------------

	
	// Citizens
	@CrossOrigin(origins = "http://localhost")
	@GetMapping("/citizen")
	public Iterable<Citizen> getAllCitizens() {
		return citizenRepo.findAll();
	}

	@CrossOrigin(origins = "http://localhost")
	@GetMapping("/citizen/{id}")
	public Citizen getCitizen(@PathVariable("id") Integer id) {
		Optional<Citizen> optCiti = citizenRepo.findById(id);
		if (optCiti.isEmpty()) {
			throw new CitizenNotFoundException(id);
		}
		return optCiti.get();
	}
	
	@CrossOrigin(origins = "http://localhost")
	@PostMapping("/citizen")
	public Citizen createCitizen(@RequestBody Citizen citi) {
		return citizenRepo.save(citi);
	}
	
	@CrossOrigin(origins = "http://localhost")
	@PutMapping("/citizen/{id}")
	public ResponseEntity<Citizen> updateCitizen(@RequestBody Citizen citi,@PathVariable("id") Integer id) {
		Optional<Citizen> optionalCitizen = citizenRepo.findById(id);
	    
	    if (optionalCitizen.isPresent()) {
	        Citizen existingCitizen = optionalCitizen.get();
	        
	        existingCitizen.setName(citi.getName());
	        existingCitizen.setDob(citi.getDob());
	        existingCitizen.setEmail(citi.getEmail());
	        existingCitizen.setMobileno(citi.getMobileno());
	        existingCitizen.setGender(citi.getGender());
	        existingCitizen.setAddress(citi.getAddress());
	        existingCitizen.setUID(citi.getUID());
	        existingCitizen.setIssueDate(citi.getIssueDate());
	        existingCitizen.setDuplicate(citi.getDuplicate());
	        
	        citizenRepo.save(existingCitizen);
	        return ResponseEntity.ok(existingCitizen);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@CrossOrigin(origins = "http://localhost")
	@DeleteMapping("/citizen/{id}")
	public void deleteCitizen(@PathVariable("id") Integer id) {
		citizenRepo.deleteById(id);
	}
	
	@CrossOrigin(origins = "http://localhost")
	@GetMapping("/citizen/byEmail/{email}")
	public int getIdByEmail(@PathVariable("email") String email) {
		Citizen citizen= citizenRepo.findIdByEmail(email);
		return citizen.getCitizenId();
	}

}
	
