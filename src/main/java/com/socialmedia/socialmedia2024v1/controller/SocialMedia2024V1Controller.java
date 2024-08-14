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
import com.socialmedia.socialmedia2024v1.dto.AddFacebookUserEntityDTO;
import com.socialmedia.socialmedia2024v1.dto.LogInFacebookAccountDTO;
import com.socialmedia.socialmedia2024v1.dto.PasswordUpdateDTO;
import com.socialmedia.socialmedia2024v1.dto.ResponseDTO;
import com.socialmedia.socialmedia2024v1.exceptions.ResourcesNotFoundExceptionHandler;
import com.socialmedia.socialmedia2024v1.service.impl.UserAccountServiceImpl;
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
	private UserAccountServiceImpl userAccountService;
	
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
	 * @throws ResourcesNotFoundExceptionHandler  
	 * @throws ParseException 
	 * 
	 **/
	@PostMapping(Service.SignUp)
	public ResponseEntity<Object> createFacebookUserAccount(@Valid @RequestBody AddFacebookUserEntityDTO addFacebookUserEntityDTO) throws ResourcesNotFoundExceptionHandler, JsonProcessingException, ParseException {
		LOGGER.debug("Enter createFacebookUserAccount method from SocialMedia2024V1Controller class... ");
		
		//@Configuaration annotation 
		//need to fix DOB format 
		//System.out.println(new JsonSchemaCofig().jsonSchema(addFacebookUserEntityDTO));
		
		ResponseDTO signUpResponseDTO = new ResponseDTO();
		signUpResponseDTO = userAccountService.createFacebookUserDetails(addFacebookUserEntityDTO);

		
		LOGGER.debug("Invoking userAccountService method from createUserAccount in SocialMedia2024V1Controller class.");
		
		
		LOGGER.debug("Exit createFacebookUserAccount method from SocialMedia2024V1Controller class and return... ");
		if(signUpResponseDTO.getStatusCode() == 400) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Util.ObjectWriterMapping(signUpResponseDTO));
		}
		else if(signUpResponseDTO.getStatusCode() == 409) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(Util.ObjectWriterMapping(signUpResponseDTO));
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(Util.ObjectWriterMapping(signUpResponseDTO)); 
	}
	
	
	/**
	 * 
	 * @param LogInFacebookAccountDTO
	 * @return ResponseEntity<String>
	 * @throws JsonProcessingException 
	 * @throws ResourcesNotFoundExceptionHandler
	 * 
	 **/
	@PostMapping("/facebook/login")
	public ResponseEntity<String> logInFacebookAccount(@Valid @RequestBody LogInFacebookAccountDTO logInFacebookAccountDTO)  throws ResourcesNotFoundExceptionHandler, JsonProcessingException {
		LOGGER.debug("Enter logInFacebookAccount method from SocialMedia2024V1Controller class... ");
		
		ResponseDTO logInResponseDTO = new ResponseDTO();
		
		logInResponseDTO = userAccountService.loginFacebookAccount(logInFacebookAccountDTO);

		if(logInResponseDTO.getStatusCode() == 401) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Util.ObjectWriterMapping(logInResponseDTO));
		}
		
		if(logInResponseDTO.getStatusCode() == 404) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Util.ObjectWriterMapping(logInResponseDTO));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(Util.ObjectWriterMapping(logInResponseDTO));
	}
	
	/**
	 * 
	 * @param id
	 * @return ResponseEntity<String>
	 * @throws JsonProcessingException 
	 * @throws ResourcesNotFoundExceptionHandler
	 * 
	 **/
	@GetMapping("/facebook/profile/{id}")
	public ResponseEntity<String> getFacebookProfileByUserId(@PathVariable String id)  throws ResourcesNotFoundExceptionHandler, JsonProcessingException {
		LOGGER.debug("Enter getFacebookProfileByUserId method from SocialMedia2024V1Controller class... ");
		
		return ResponseEntity.status(HttpStatus.OK).body(Util.ObjectWriterMapping(userAccountService.facebookProfile(id)));
	}
	
	/**
	 * 
	 * @param PasswordUpdateDTO
	 * @return ResponseEntity<String>
	 * @throws JsonProcessingException 
	 * @throws ResourcesNotFoundExceptionHandler
	 * 
	 **/
	@PutMapping("/facebook/update")
	public ResponseEntity<String> updateUserPasswordByUserId(@Valid @RequestBody PasswordUpdateDTO passwordUpdateDTO)  throws ResourcesNotFoundExceptionHandler, JsonProcessingException {
		LOGGER.debug("Enter updateUserPasswordByUserId method from SocialMedia2024V1Controller class... ");
		
		ResponseDTO updatePasswordResponseDTO = new ResponseDTO();
		
		updatePasswordResponseDTO = userAccountService.updateUserPassword(passwordUpdateDTO);
		
		if(updatePasswordResponseDTO.getStatusCode() == 404) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Util.ObjectWriterMapping(updatePasswordResponseDTO));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(Util.ObjectWriterMapping(updatePasswordResponseDTO));
	}
	
	/**
	 * 
	 * @param userId
	 * @return ResponseEntity<String>
	 * @throws JsonProcessingException 
	 * @throws ResourcesNotFoundExceptionHandler
	 * 
	 **/
	@DeleteMapping("/facebook/{userId}")
	public ResponseEntity<String> deleteUserAccountByUserId(@PathVariable String userId)  throws ResourcesNotFoundExceptionHandler, JsonProcessingException {
		LOGGER.debug("Enter deleteUserAccountByUserId method from SocialMedia2024V1Controller class... ");
		
		ResponseDTO deleteAccountResponseDTO = new ResponseDTO();
		
		deleteAccountResponseDTO = userAccountService.deleteUserAccount(userId);

		if(deleteAccountResponseDTO.getStatusCode() == 404) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Util.ObjectWriterMapping(deleteAccountResponseDTO));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(Util.ObjectWriterMapping(deleteAccountResponseDTO));
	}
	
}
