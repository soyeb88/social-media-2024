package com.socialmedia.socialmedia2024v1.controller;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.socialmedia.socialmedia2024v1.dto.AddFacebookUserEntityDTO;
import com.socialmedia.socialmedia2024v1.dto.AddUserEntityDTO;
import com.socialmedia.socialmedia2024v1.dto.LogInFacebookAccountDTO;
import com.socialmedia.socialmedia2024v1.dto.LogInUserEntityDTO;
import com.socialmedia.socialmedia2024v1.dto.PasswordUpdateDTO;
import com.socialmedia.socialmedia2024v1.dto.UserDetailsDTO;
import com.socialmedia.socialmedia2024v1.exceptions.ResourcesNotFoundExceptionHandler;
import com.socialmedia.socialmedia2024v1.service.UserAccountService;
import com.socialmedia.socialmedia2024v1.util.Util;
import com.socialmedia.socialmedia2024v1.util.Service;


/**
 *
 * @author Soyeb Ahmed
 * 
 **/

@CrossOrigin(origins="http://localhost:80/")
//@CrossOrigin(origins="http://localhost:3000/")

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
	 * @param AddFacebookUserEntityDTO
	 * @return ResponseEntity<Object>
	 * @throws JsonProcessingException 
	 * @throws ParseException 
	 * 
	 **/
	@PostMapping(Service.SignUp)
	public ResponseEntity<Object> createFacebookUserAccount(@Valid @RequestBody AddFacebookUserEntityDTO addFacebookUserEntityDTO) throws ResourcesNotFoundExceptionHandler, JsonProcessingException, ParseException {
		LOGGER.debug("Enter createFacebookUserAccount method from SocialMedia2024V1Controller class... ");
		
		LOGGER.debug("Invoking userAccountService method from createUserAccount in SocialMedia2024V1Controller class.");
			
		LOGGER.debug("Exit createFacebookUserAccount method from SocialMedia2024V1Controller class and return... ");
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(Util.ObjectWriterMapping(userAccountService.createFacebookUserDetails(addFacebookUserEntityDTO))); 
	}
	
	/*
	@PostMapping("/facebook/login")
	public ResponseEntity<String> logInFacebookAccount(@Valid @RequestBody LogInFacebookAccountDTO logInFacebookAccountDTO)  throws ResourcesNotFoundExceptionHandler, JsonProcessingException {
		
		System.out.println(logInFacebookAccountDTO.toString());
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		//String json = ow.writeValueAsString(userAccountService.loginFacebookAccount(logInFacebookAccountDTO));	
		return ResponseEntity.status(HttpStatus.OK).body("work");
	}
	
	
	@GetMapping("/facebook/profile/{id}")
	public ResponseEntity<String> getFacebookProfileByUserId(@PathVariable String id)  throws ResourcesNotFoundExceptionHandler, JsonProcessingException {
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		//String json = ow.writeValueAsString(userAccountService.facebookProfile(id));
		
		//userAccountService.getUserDetails(id);
		return ResponseEntity.status(HttpStatus.OK).body("work");
	}
	
	@PutMapping("/user/update")
	public ResponseEntity<String> updateUserPasswordByUserId(@Valid @RequestBody PasswordUpdateDTO passwordUpdateDTO)  throws ResourcesNotFoundExceptionHandler, JsonProcessingException {
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		//String json = ow.writeValueAsString(userAccountService.getUserDetails(id));
		String json = ow.writeValueAsString(passwordUpdateDTO);
		return ResponseEntity.status(HttpStatus.OK).body(json);
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<String> deleteUserAccountByUserId(@PathVariable String id)  throws ResourcesNotFoundExceptionHandler, JsonProcessingException {
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		//String json = ow.writeValueAsString(userAccountService.getUserDetails(id));
		String json = ow.writeValueAsString(id);
		return ResponseEntity.status(HttpStatus.OK).body(json);
	}
	*/
}
