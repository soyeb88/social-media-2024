package com.socialmedia.socialmedia2024v1.service.impl;

import com.socialmedia.socialmedia2024v1.model.UserDetails;
import com.socialmedia.socialmedia2024v1.repository.UserDetailsRepository;

import jakarta.validation.Valid;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialmedia.socialmedia2024v1.service.UserAccountService;
import com.socialmedia.socialmedia2024v1.config.JasyptEncryptorConfig;
import com.socialmedia.socialmedia2024v1.controller.SocialMedia2024V1Controller;
import com.socialmedia.socialmedia2024v1.dto.AddFacebookUserEntityDTO;
import com.socialmedia.socialmedia2024v1.dto.FacebookProfileDTO;
import com.socialmedia.socialmedia2024v1.dto.LogInFacebookAccountDTO;
import com.socialmedia.socialmedia2024v1.dto.LogInUserEntityDTO;
import com.socialmedia.socialmedia2024v1.dto.UserDetailsDTO;
import com.socialmedia.socialmedia2024v1.dto.UserIdDTO;
import com.socialmedia.socialmedia2024v1.util.Util;

/**
 *
 * @author Soyeb Ahmed
 * 
 **/

@Service("userService")
public class UserAccountServiceImpl implements UserAccountService {
	@Autowired
	private static final Logger LOGGER = LoggerFactory.getLogger(SocialMedia2024V1Controller.class);

	// @Autowired
	// private AccountRepository accountRepository;

	@Autowired
	private UserDetailsRepository userDetailsRepository;
	
	@Autowired
	private JasyptEncryptorConfig jasyptEncryptorConfig;

	public Object createFacebookUserDetails(@Valid AddFacebookUserEntityDTO addFacebookUserEntityDTO)
			throws ParseException {
		LOGGER.debug("Enter createFacebookUserDetails method on UserAccountService class... ");

		LOGGER.debug("Invoke Util class getUserId method to create and return userId... ");
		String userId = Util.getUserId(addFacebookUserEntityDTO);		
		userId = checkUserIdUnique(userId);
		
		UserIdDTO userIdDTO = new UserIdDTO();
		UserDetails userDetails = new UserDetails();
		
		userDetails.setUserId(userId);
		userDetails.setPassword(jasyptEncryptorConfig.passwordEncryptor().encrypt(addFacebookUserEntityDTO.getPassword()));
		userDetails.setEmail(addFacebookUserEntityDTO.getEmail());
		userDetails.setFirstName(addFacebookUserEntityDTO.getFirstName());
		userDetails.setLastName(addFacebookUserEntityDTO.getLastName());
		userDetails.setGender(addFacebookUserEntityDTO.getGender());
		userDetails.setDob(addFacebookUserEntityDTO.getDob());
		
		LOGGER.debug("Invoking userDetailsRepository method to create user account...");
		userDetailsRepository.save(userDetails);

		LOGGER.debug("Invoking userDetailsRepository method to find userId...");
		userIdDTO.setUserId(userDetailsRepository.findByUserId(userId));

		LOGGER.debug("Exit createFacebookUserDetails method on UserAccountService class... ");
		return userIdDTO;
	}

	private String checkUserIdUnique(String userId) {

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
	

	/*
	 * public UserIdDTO loginAccount(LogInUserEntityDTO logInUserEntityDTO) {
	 * System.out.println(logInUserEntityDTO.toString());
	 * 
	 * UserIdDTO userIdDTO = new UserIdDTO();
	 * userIdDTO.setUserId(accountRepository.findUserIdByUserIdAndPassword(
	 * logInUserEntityDTO.getUserId(), logInUserEntityDTO.getPassword())); return
	 * userIdDTO; }
	 * 
	 * public UserIdDTO loginFacebookAccount(@Valid LogInFacebookAccountDTO
	 * logInFacebookAccountDTO) {
	 * System.out.println(logInFacebookAccountDTO.toString());
	 * 
	 * UserIdDTO userIdDTO = new UserIdDTO();
	 * userIdDTO.setUserId(userDetailsRepository.findUserIdByEmailAndPassword(
	 * logInFacebookAccountDTO.getEmail(), logInFacebookAccountDTO.getPassword()));
	 * return userIdDTO; }
	 * 
	 * public UserDetailsDTO getUserDetails(String id) {
	 * 
	 * UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
	 * userDetailsDTO.setName(accountRepository.findNameByUserId(id));
	 * 
	 * return userDetailsDTO;
	 * 
	 * }
	 * 
	 * public FacebookProfileDTO facebookProfile(String id) {
	 * 
	 * FacebookProfileDTO facebookProfileDTO = new FacebookProfileDTO();
	 * List<Object[]> list = userDetailsRepository.findNameByUserId(id);
	 * 
	 * String firstName = ""; String lastName = "";
	 * 
	 * for (Object[] obj : list) { firstName = (String) obj[0]; lastName = (String)
	 * obj[1]; }
	 * 
	 * facebookProfileDTO.setFirstName(firstName);
	 * facebookProfileDTO.setLastName(lastName); return facebookProfileDTO; }
	 */
}
