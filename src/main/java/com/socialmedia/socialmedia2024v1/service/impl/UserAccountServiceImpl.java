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

import com.socialmedia.socialmedia2024v1.service.UserAccountService;
import com.socialmedia.socialmedia2024v1.config.JasyptEncryptorConfig;
import com.socialmedia.socialmedia2024v1.controller.SocialMedia2024V1Controller;
import com.socialmedia.socialmedia2024v1.dto.AddFacebookUserEntityDTO;
import com.socialmedia.socialmedia2024v1.dto.FacebookProfileDTO;
import com.socialmedia.socialmedia2024v1.dto.LogInFacebookAccountDTO;
import com.socialmedia.socialmedia2024v1.dto.LogInUserEntityDTO;
import com.socialmedia.socialmedia2024v1.dto.UserDetailsDTO;
import com.socialmedia.socialmedia2024v1.dto.SignUpResponseDTO;
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

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@Autowired
	private JasyptEncryptorConfig jasyptEncryptorConfig;

	public Object createFacebookUserDetails(@Valid AddFacebookUserEntityDTO addFacebookUserEntityDTO)
			throws ParseException {
		LOGGER.debug("Enter createFacebookUserDetails method on UserAccountService class... ");
		
		SignUpResponseDTO signUpResponseDTO = new SignUpResponseDTO();
		
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

	public SignUpResponseDTO loginAccount(LogInUserEntityDTO logInUserEntityDTO) {
		System.out.println(logInUserEntityDTO.toString());

		SignUpResponseDTO userIdDTO = new SignUpResponseDTO();
		userIdDTO.setUserId(accountRepository.findUserIdByUserIdAndPassword(logInUserEntityDTO.getUserId(),
				logInUserEntityDTO.getPassword()));
		return userIdDTO;
	}

	public SignUpResponseDTO loginFacebookAccount(@Valid LogInFacebookAccountDTO logInFacebookAccountDTO) {
		System.out.println(logInFacebookAccountDTO.toString());

		SignUpResponseDTO userIdDTO = new SignUpResponseDTO();
		userIdDTO.setUserId(userDetailsRepository.findUserIdByEmailAndPassword(logInFacebookAccountDTO.getEmail(),
				logInFacebookAccountDTO.getPassword()));
		return userIdDTO;
	}

	public UserDetailsDTO getUserDetails(String id) {

		UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
		userDetailsDTO.setName(accountRepository.findNameByUserId(id));

		return userDetailsDTO;

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

}
