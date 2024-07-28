package com.socialmedia.socialmedia2024v1.service.impl;

import com.socialmedia.socialmedia2024v1.model.UserDetails;
import com.socialmedia.socialmedia2024v1.repository.AccountRepository;
import com.socialmedia.socialmedia2024v1.repository.UserDetailsRepository;

import jakarta.validation.Valid;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.socialmedia.socialmedia2024v1.service.UserAccountService;
import com.socialmedia.socialmedia2024v1.config.JasyptEncryptorConfig;
import com.socialmedia.socialmedia2024v1.controller.SocialMedia2024V1Controller;
import com.socialmedia.socialmedia2024v1.dto.AddFacebookUserEntityDTO;
import com.socialmedia.socialmedia2024v1.dto.FacebookProfileDTO;
import com.socialmedia.socialmedia2024v1.dto.LogInFacebookAccountDTO;
import com.socialmedia.socialmedia2024v1.dto.LogInUserEntityDTO;
import com.socialmedia.socialmedia2024v1.dto.PasswordUpdateDTO;
import com.socialmedia.socialmedia2024v1.dto.ResponseDTO;
import com.socialmedia.socialmedia2024v1.util.Util;

/**
 *
 * @author Soyeb Ahmed
 * 
 **/

@Service("userService")
@Transactional
public class UserAccountServiceImpl implements UserAccountService {
	@Autowired
	private static final Logger LOGGER = LoggerFactory.getLogger(SocialMedia2024V1Controller.class);

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@Autowired
	private JasyptEncryptorConfig jasyptEncryptorConfig;

	public ResponseDTO createFacebookUserDetails(@Valid AddFacebookUserEntityDTO addFacebookUserEntityDTO)
			throws ParseException {
		LOGGER.debug("Enter createFacebookUserDetails method on UserAccountService class... ");
		
		
		ResponseDTO signUpResponseDTO = new ResponseDTO();
		
		if(addFacebookUserEntityDTO.getEmail() == null && addFacebookUserEntityDTO.getPhone() == null) {
			System.out.println("Worked");
			signUpResponseDTO.setResponseMessage("Both Email and Phone number could not be null value!");
			signUpResponseDTO.setStatusCode(400);
			signUpResponseDTO.setUserId(null);
			return signUpResponseDTO;
		}
		
		signUpResponseDTO.setUserId(userDetailsRepository.findUserIdByEmailOrPhone(addFacebookUserEntityDTO.getEmail(), 
				addFacebookUserEntityDTO.getPhone())); 

		
		if(signUpResponseDTO.getUserId() != null) {
			//System.out.println("Worked");
			signUpResponseDTO.setResponseMessage("Your Account is already exists");
			signUpResponseDTO.setStatusCode(409);
			return signUpResponseDTO;
		}
		
		
		LOGGER.debug("Invoke Util class getUserId method to create and return userId... ");
		String userId = Util.getUserId(addFacebookUserEntityDTO);
		userId = checkUserIdUnique(userId, addFacebookUserEntityDTO.getFirstName());

		UserDetails userDetails = new UserDetails();

		userDetails.setUserId(userId);
		/*userDetails
				.setPassword(jasyptEncryptorConfig.passwordEncryptor().encrypt(addFacebookUserEntityDTO.getPassword()));
		*/
		
		userDetails.setPassword(addFacebookUserEntityDTO.getPassword());
		userDetails.setEmail(addFacebookUserEntityDTO.getEmail());
		userDetails.setPhone(addFacebookUserEntityDTO.getPhone());
		userDetails.setFirstName(addFacebookUserEntityDTO.getFirstName());
		userDetails.setLastName(addFacebookUserEntityDTO.getLastName());
		userDetails.setGender(addFacebookUserEntityDTO.getGender());
		userDetails.setDob(addFacebookUserEntityDTO.getDob());

		LOGGER.debug("Invoking userDetailsRepository method to create user account...");
		userDetailsRepository.save(userDetails);

		LOGGER.debug("Invoking userDetailsRepository method to find userId...");
		signUpResponseDTO.setResponseMessage("User Account Created Successfully!");
		signUpResponseDTO.setStatusCode(201);
		signUpResponseDTO.setUserId(userDetailsRepository.findByUserId(userId));

		LOGGER.debug("Exit createFacebookUserDetails method on UserAccountService class... ");
		return signUpResponseDTO;
	}

	private String checkUserIdUnique(String userId, String firstName) {

		if(userId == null) {
			return Util.createRandomUserId(firstName);
		}
		
		String fetchUserId = userDetailsRepository.findByUserId(userId);

		if (fetchUserId == null) {
			return userId;
		}

		while (fetchUserId != null) {

			fetchUserId = Util.createRandomUserId(fetchUserId);
			userId = fetchUserId;
			fetchUserId = userDetailsRepository.findByUserId(fetchUserId);
		}
		return userId;
	}
	
	
	public ResponseDTO loginFacebookAccount(@Valid LogInFacebookAccountDTO logInFacebookAccountDTO) {
		System.out.println(logInFacebookAccountDTO.toString());

		ResponseDTO logInResponseDTO = new ResponseDTO();
		
		logInResponseDTO.setUserId(userDetailsRepository.findUserIdByEmailOrPhoneAndPassword(logInFacebookAccountDTO.getEmail(),
				logInFacebookAccountDTO.getPhone(), logInFacebookAccountDTO.getPassword()));
		//System.out.println(userIdDTO.toString());
		
		if(logInResponseDTO.getUserId() == null) {
			//System.out.println("Worked");
			logInResponseDTO.setResponseMessage("Your Email or Phone number not found in our databas");
			logInResponseDTO.setStatusCode(404);
			return logInResponseDTO;
		}
		
		logInResponseDTO.setResponseMessage("User Account Log In Successfully!");
		logInResponseDTO.setStatusCode(200);
		
		return logInResponseDTO;
	}

	public FacebookProfileDTO facebookProfile(String userId) {

		FacebookProfileDTO facebookProfileDTO = new FacebookProfileDTO();
		List<Object[]> list = userDetailsRepository.findNameByUserId(userId);

		String firstName = "";
		String lastName = "";

		for (Object[] obj : list) {
			firstName = (String) obj[0];
			lastName = (String) obj[1];
		}

		facebookProfileDTO.setFirstName(firstName);
		facebookProfileDTO.setLastName(lastName);
		return facebookProfileDTO;
	}

	public ResponseDTO updateUserPassword(@Valid PasswordUpdateDTO passwordUpdateDTO) {
		
		System.out.println(passwordUpdateDTO.toString());

		ResponseDTO updatePasswordResponseDTO = new ResponseDTO();
		
		Integer updatedRow = 0;
		
		updatedRow = userDetailsRepository.updatePassword(passwordUpdateDTO.getOldPassword(), passwordUpdateDTO.getNewPassword()
				, passwordUpdateDTO.getUserId());
		
		if(updatedRow == 0) {
			
			updatePasswordResponseDTO.setResponseMessage("Password doesn't Update Successfully on User Id " + 
					passwordUpdateDTO.getUserId());
			updatePasswordResponseDTO.setStatusCode(404);
			updatePasswordResponseDTO.setUserId(passwordUpdateDTO.getUserId());
			return updatePasswordResponseDTO;
		}

		
		updatePasswordResponseDTO.setResponseMessage("Password Update Successfully on User Id " + 
				passwordUpdateDTO.getUserId());
		updatePasswordResponseDTO.setStatusCode(200);
		updatePasswordResponseDTO.setUserId(passwordUpdateDTO.getUserId());
		
		return updatePasswordResponseDTO;
	}

	public ResponseDTO deleteUserAccount(String userId) {
		
		ResponseDTO deleteAccountResponseDTO = new ResponseDTO();
		
		Integer deletedRow = 0;
		
		deletedRow = userDetailsRepository.deleteByUserId(userId);
		
		if(deletedRow == 0) {
			
			deleteAccountResponseDTO.setResponseMessage("Account doesn't delete Successfully on User Id " + 
					userId);
			deleteAccountResponseDTO.setStatusCode(404);
			deleteAccountResponseDTO.setUserId(userId);
			return deleteAccountResponseDTO;
		}
		
		deleteAccountResponseDTO.setResponseMessage("Account delete Successfully on User Id " + 
				userId);
		deleteAccountResponseDTO.setStatusCode(200);
		deleteAccountResponseDTO.setUserId(userId);
		
		return deleteAccountResponseDTO;
	}
	
	

}
