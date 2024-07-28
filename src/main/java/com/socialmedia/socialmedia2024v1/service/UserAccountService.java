package com.socialmedia.socialmedia2024v1.service;

import java.text.ParseException;

import com.socialmedia.socialmedia2024v1.dto.AddFacebookUserEntityDTO;
import com.socialmedia.socialmedia2024v1.dto.LogInFacebookAccountDTO;
import com.socialmedia.socialmedia2024v1.dto.PasswordUpdateDTO;
import com.socialmedia.socialmedia2024v1.dto.ResponseDTO;

import jakarta.validation.Valid;

/**
 *
 * @author Soyeb Ahmed
 * 
 **/


public interface UserAccountService {
	
	/**
	 * 
	 * @param AddFacebookUserEntityDTO
	 * @return ResponseDTO
	 * @throws ParseException 
	 * 
	 **/
	public ResponseDTO createFacebookUserDetails(@Valid AddFacebookUserEntityDTO addFacebookUserEntityDTO) throws ParseException;
	
	/**
	 * 
	 * @param logInFacebookAccountDTO
	 * @return ResponseDTO
	 * 
	 **/
	public ResponseDTO loginFacebookAccount(@Valid LogInFacebookAccountDTO logInFacebookAccountDTO);
	
	/**
	 * 
	 * @param PasswordUpdateDTO
	 * @return ResponseDTO
	 * 
	 **/
	public ResponseDTO updateUserPassword(@Valid PasswordUpdateDTO passwordUpdateDTO);
	
	/**
	 * 
	 * @param userId
	 * @return ResponseDTO
	 * 
	 **/
	public ResponseDTO deleteUserAccount(String userId);
}
