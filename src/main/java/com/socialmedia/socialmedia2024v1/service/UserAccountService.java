package com.socialmedia.socialmedia2024v1.service;

import com.socialmedia.socialmedia2024v1.model.Account;
import com.socialmedia.socialmedia2024v1.repository.AccountRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialmedia.socialmedia2024v1.controller.SocialMedia2024V1Controller;
import com.socialmedia.socialmedia2024v1.dto.AddUserEntityDTO;
import com.socialmedia.socialmedia2024v1.exceptions.ResourcesExceptionHandler;

@Service("userService")
public class UserAccountService {
	@Autowired
	private static final Logger logger = LoggerFactory.getLogger(SocialMedia2024V1Controller.class);
	
	@Autowired
	private AccountRepository accountRepository;
	
	public String createAccount(AddUserEntityDTO addUserEntityDTO) throws ResourcesExceptionHandler {
		logger.debug("Enter createAccount method on UserAccountService class.");
		
		Account account = new Account();
		
		account.setUserId(addUserEntityDTO.getUserId());
		account.setPassword(addUserEntityDTO.getPassword());
		account.setName(addUserEntityDTO.getName());
		
		accountRepository.save(account);
		return accountRepository.findByUserId(addUserEntityDTO.getUserId());
		
	}

}
