package com.socialmedia.socialmedia2024v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialmedia.socialmedia2024v1.dto.AddUserEntityDTO;
import com.socialmedia.socialmedia2024v1.service.UserAccountService;

@CrossOrigin(origins="http://localhost:3000/")

@RestController
@RequestMapping
public class SocialMedia2024V1Controller {
	
	@Autowired
	private UserAccountService userAccountService;
	
	@GetMapping("/test")
	public String testEndPoint() {
		return "Hello";
	}
	
	@PostMapping("/createUserAccount")
	public String createUserAccount(@RequestBody AddUserEntityDTO addUserEntityDTO) {
		
		userAccountService.createAccount(addUserEntityDTO);
		
		return addUserEntityDTO.toString();
	}
	
}
