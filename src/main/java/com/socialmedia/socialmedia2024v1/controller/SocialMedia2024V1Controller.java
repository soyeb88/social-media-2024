package com.socialmedia.socialmedia2024v1.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialmedia.socialmedia2024v1.dto.AddUserEntityDTO;
import com.socialmedia.socialmedia2024v1.dto.UserDetailsDTO;
import com.socialmedia.socialmedia2024v1.exceptions.ResourcesExceptionHandler;
import com.socialmedia.socialmedia2024v1.service.UserAccountService;

@CrossOrigin(origins="http://localhost:3000/")

@RestController
@RequestMapping
public class SocialMedia2024V1Controller {
	
	@Autowired
	private UserAccountService userAccountService;
	
	@Autowired
	private static final Logger logger = LoggerFactory.getLogger(SocialMedia2024V1Controller.class);

	
	@GetMapping("/test")
	public String testEndPoint() {
		return "Hello Sadia";
	}
	
	@PostMapping("/user")
	public ResponseEntity<Object> createUserAccount(@RequestBody AddUserEntityDTO addUserEntityDTO) throws ResourcesExceptionHandler {
		logger.info("Enter createUserAccount method: ");
		
		UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
		
		String name = userAccountService.createAccount(addUserEntityDTO);
		userDetailsDTO.setName(name);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userDetailsDTO);
	}
	
	@GetMapping("/user/{name}")
	public ResponseEntity<String> getUserAccount() {
		
		//userAccountService.createAccount(addUserEntityDTO);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
