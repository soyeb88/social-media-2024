package com.socialmedia.socialmedia2024v1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.socialmedia.socialmedia2024v1.dto.AddUserEntityDTO;
import com.socialmedia.socialmedia2024v1.dto.LogInUserEntityDTO;
import com.socialmedia.socialmedia2024v1.dto.UserDetailsDTO;
import com.socialmedia.socialmedia2024v1.exceptions.ResourcesNotFoundExceptionHandler;
import com.socialmedia.socialmedia2024v1.service.UserAccountService;


/**
 *
 * @author Soyeb Ahmed
 * 
 **/

@CrossOrigin(origins="http://localhost:3000/")

@RestController
@RequestMapping
public class SocialMedia2024V1Controller {
	
	@Autowired
	private UserAccountService userAccountService;
	
	@Autowired
	private static final Logger LOGGER = LoggerFactory.getLogger(SocialMedia2024V1Controller.class);

	
	@GetMapping("/test")
	public String testEndPoint() {
		return "Hello Sadia";
	}
	
	/**
	 * 
	 * @param AddUserEntityDTO
	 * @return
	 * @throws JsonProcessingException 
	 **/
	
	@PostMapping("/user")
	public ResponseEntity<Object> createUserAccount(@Valid @RequestBody AddUserEntityDTO addUserEntityDTO) throws ResourcesNotFoundExceptionHandler, JsonProcessingException {
		LOGGER.debug("Enter createUserAccount method from SocialMedia2024V1Controller class... ");
		
		LOGGER.debug("Invoking userAccountService method from createUserAccount in SocialMedia2024V1Controller class.");
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(userAccountService.createAccount(addUserEntityDTO));
		
		LOGGER.debug("Exit createUserAccount method from SocialMedia2024V1Controller class and return... ");
		return ResponseEntity.status(HttpStatus.CREATED).body(json);
	}
	
	@PostMapping("/user/login")
	public ResponseEntity<String> getUserAccount(@Valid @RequestBody LogInUserEntityDTO logInUserEntityDTO)  throws ResourcesNotFoundExceptionHandler, JsonProcessingException {
		
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(userAccountService.loginAccount(logInUserEntityDTO));	
		return ResponseEntity.status(HttpStatus.OK).body(json);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<String> getUserDetailsByUserId(@PathVariable String id)  throws ResourcesNotFoundExceptionHandler, JsonProcessingException {
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(userAccountService.getUserDetails(id));
		
		//userAccountService.getUserDetails(id);
		return ResponseEntity.status(HttpStatus.OK).body(json);
	}
	
}
