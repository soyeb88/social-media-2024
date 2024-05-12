package com.socialmedia.socialmedia2024v1.service;

import com.socialmedia.socialmedia2024v1.model.Account;
import com.socialmedia.socialmedia2024v1.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialmedia.socialmedia2024v1.dto.AddUserEntityDTO;

@Service
public class UserAccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	public void createAccount(AddUserEntityDTO addUserEntityDTO) {
		
		Account account = new Account();
		
		account.setUserId(addUserEntityDTO.getUserId());
		account.setPassword(addUserEntityDTO.getPassword());
		account.setName(addUserEntityDTO.getName());
		
		accountRepository.save(account);
		
	}

}
