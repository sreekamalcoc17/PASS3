package com.revature.project03.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.project03.entities.Doctor;
import com.revature.project03.model.Login;
import com.revature.project03.service.DoctorService;
import com.revature.project03.service.GeneratePasswordService;
@RestController
@RequestMapping("/doctorController")
@CrossOrigin(origins = "*")
public class DoctorController {
	@Autowired
    private DoctorService service;
	private GeneratePasswordService passGen;

    @PostMapping("/addDoctor")
    public Doctor addDoctor(@RequestBody Doctor doctor) {
    	String newPass = passGen.generateRandomPassword();
    	doctor.setPassword(newPass);
        return service.saveDoctor(doctor);
    }

    @PostMapping("/addDoctors")
    public List<Doctor> addDoctors(@RequestBody List<Doctor> doctors) {
        return service.saveDoctors(doctors);
    }

    @GetMapping("/doctors")
    public List<Doctor> findAllDoctors() {
        return service.getDoctors();
    }

    @GetMapping("/doctorById/{id}")
    public Doctor findDoctorById(@PathVariable int id) {
        return service.getDoctorById(id);
    }
    @GetMapping("/doctorByEmail/{email}")
    public Doctor findDoctorByEmail(@PathVariable String email) {
        return service.getDoctorByEmail(email);
    }
    
    @PutMapping("/updateDoctor")
    public Doctor updateDoctor(@RequestBody Doctor doctor) {
        return service.updateDoctor(doctor);
    }

    @DeleteMapping("/deleteDoctor/{id}")
    public String deleteDoctor(@PathVariable int id) {
        return service.deleteDoctor(id);
    }
    
    @PostMapping("/loginDoctor")
    public Doctor login(@RequestBody Login login) {
    	System.out.println(login);
    	Doctor curr_Doctor = service.getDoctorByEmail(login.getEmail());
    	System.out.println(curr_Doctor);
    	String enteredPass = login.getPassword();
    	System.out.println(enteredPass);
    	String userPass = curr_Doctor.getPassword();
    	System.out.println(userPass);
    	if(enteredPass.equals(userPass)) {
    		System.out.println("if loop entered");
    		return service.getDoctorByEmail(curr_Doctor.getEmail());
    	}
    	else {
    		return null;
    	}
    	
    }

}
