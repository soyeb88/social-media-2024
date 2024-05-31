package com.socialmedia.socialmedia2024v1.service;

import com.socialmedia.socialmedia2024v1.model.Account;
import com.socialmedia.socialmedia2024v1.repository.AccountRepository;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialmedia.socialmedia2024v1.controller.SocialMedia2024V1Controller;
import com.socialmedia.socialmedia2024v1.dto.AddUserEntityDTO;
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

	public UserIdDTO loginAccount(LogInUserEntityDTO logInUserEntityDTO) {
		System.out.println(logInUserEntityDTO.toString());
		
		UserIdDTO userIdDTO = new UserIdDTO();
		userIdDTO.setUserId(accountRepository.findUserIdByUserIdAndPassword(logInUserEntityDTO.getUserId(), 
				logInUserEntityDTO.getPassword()));
		return userIdDTO;
	}

	public UserDetailsDTO getUserDetails(String id) {
		
		UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
		userDetailsDTO.setName(accountRepository.findNameByUserId(id));
		
		return userDetailsDTO; 
		
	}

}
