package com.revature.project03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.project03.entities.LoginRoute;
import com.revature.project03.repository.LoginRouteRepository;

@Service
public class LoginRouteService {
	
	@Autowired
	private LoginRouteRepository repository;

	public LoginRoute findbyEmail(String email) {
		return repository.findByUserEmail(email);
	}
}
