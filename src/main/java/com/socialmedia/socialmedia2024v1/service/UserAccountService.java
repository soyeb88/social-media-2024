package com.socialmedia.socialmedia2024v1.service;

import com.socialmedia.socialmedia2024v1.model.Account;
import com.socialmedia.socialmedia2024v1.model.UserDetails;
import com.socialmedia.socialmedia2024v1.repository.AccountRepository;
import com.socialmedia.socialmedia2024v1.repository.UserDetailsRepository;

import jakarta.validation.Valid;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialmedia.socialmedia2024v1.controller.SocialMedia2024V1Controller;
import com.socialmedia.socialmedia2024v1.dto.AddFacebookUserEntityDTO;
import com.socialmedia.socialmedia2024v1.dto.AddUserEntityDTO;
import com.socialmedia.socialmedia2024v1.dto.FacebookProfileDTO;
import com.socialmedia.socialmedia2024v1.dto.LogInFacebookAccountDTO;
import com.socialmedia.socialmedia2024v1.dto.LogInUserEntityDTO;
import com.socialmedia.socialmedia2024v1.dto.UserDetailsDTO;
import com.socialmedia.socialmedia2024v1.dto.UserIdDTO;
import com.socialmedia.socialmedia2024v1.exceptions.ResourcesNotFoundExceptionHandler;

/**
 *
 * @author Soyeb Ahmed
 * 
 **/

@Service("userService")
public class UserAccountService {
	@Autowired
	private static final Logger LOGGER = LoggerFactory.getLogger(SocialMedia2024V1Controller.class);

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private UserDetailsRepository userDetailsRepository;

	/**
	 * 
	 * @param AddUserEntityDTO
	 * @return
	 **/

	public UserIdDTO createAccount(AddUserEntityDTO addUserEntityDTO) throws ResourcesNotFoundExceptionHandler {
		LOGGER.debug("Enter createAccount method on UserAccountService class... ");

		Account account = new Account();
		UserIdDTO userIdDTO = new UserIdDTO();

		account.setUserId(addUserEntityDTO.getUserId());
		account.setPassword(addUserEntityDTO.getPassword());
		account.setName(addUserEntityDTO.getName());

		LOGGER.debug("Invoking accountRepository method from createAccount method on UserAccountService class... ");
		accountRepository.save(account);

		userIdDTO.setUserId(accountRepository.findByUserId(addUserEntityDTO.getUserId()));

		LOGGER.debug("Exit createAccount method on UserAccountService class... ");
		return userIdDTO;

	}

	public Object createFacebookUserDetails(@Valid AddFacebookUserEntityDTO addFacebookUserEntityDTO) {
		LOGGER.debug("Enter createFacebookUserDetails method on UserAccountService class... ");

		UserDetails userDetails = new UserDetails();
		UserIdDTO userIdDTO = new UserIdDTO();

		String[] splitWord = addFacebookUserEntityDTO.getEmail().split("@");
		String userId = splitWord[0];
		
		userDetails.setUserId(userId); 
		userDetails.setFirstName(addFacebookUserEntityDTO.getFirstName());
		userDetails.setLastName(addFacebookUserEntityDTO.getLastName());
		userDetails.setEmail(addFacebookUserEntityDTO.getEmail());
		userDetails.setPassword(addFacebookUserEntityDTO.getPassword());
		userDetails.setMonth(addFacebookUserEntityDTO.getMonth());
		userDetails.setDay(addFacebookUserEntityDTO.getDay());
		userDetails.setYear(addFacebookUserEntityDTO.getYear());
		userDetails.setGender(addFacebookUserEntityDTO.getGender());
		

		LOGGER.debug("Invoking userDetailsRepository method from createFacebookUserDetails method on UserAccountService class... ");
		userDetailsRepository.save(userDetails);

		userIdDTO.setUserId(userDetailsRepository.findByUserId(userId));

		LOGGER.debug("Exit createAccount method on UserAccountService class... ");
		return userIdDTO;
	}

	public UserIdDTO loginAccount(LogInUserEntityDTO logInUserEntityDTO) {
		System.out.println(logInUserEntityDTO.toString());

		UserIdDTO userIdDTO = new UserIdDTO();
		userIdDTO.setUserId(accountRepository.findUserIdByUserIdAndPassword(logInUserEntityDTO.getUserId(),
				logInUserEntityDTO.getPassword()));
		return userIdDTO;
	}

	public UserIdDTO loginFacebookAccount(@Valid LogInFacebookAccountDTO logInFacebookAccountDTO) {
		System.out.println(logInFacebookAccountDTO.toString());

		UserIdDTO userIdDTO = new UserIdDTO();
		userIdDTO.setUserId(userDetailsRepository.findUserIdByEmailAndPassword(logInFacebookAccountDTO.getEmail(),
				logInFacebookAccountDTO.getPassword()));
		return userIdDTO;
	}
	
	public UserDetailsDTO getUserDetails(String id) {

		UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
		userDetailsDTO.setName(accountRepository.findNameByUserId(id));

		return userDetailsDTO;

	}

	public FacebookProfileDTO facebookProfile(String id) {
		
		FacebookProfileDTO facebookProfileDTO = new FacebookProfileDTO();
		List<Object[]> list = userDetailsRepository.findNameByUserId(id);
		
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
