package com.revature.project03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.project03.entities.Patient;
import com.revature.project03.repository.PatientRepository;
@Service
public class PatientService {

	@Autowired
	private PatientRepository repository;
	
	public Patient findbyEmail(String email) {
		return repository.findByEmail(email);
	}
}
