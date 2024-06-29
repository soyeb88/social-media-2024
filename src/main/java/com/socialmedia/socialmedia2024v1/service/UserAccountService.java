package com.socialmedia.socialmedia2024v1.service;

import java.text.ParseException;

import com.socialmedia.socialmedia2024v1.dto.AddFacebookUserEntityDTO;

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
	 * @return Object
	 * @throws ParseException 
	 * 
	 **/
	public Object createFacebookUserDetails(@Valid AddFacebookUserEntityDTO addFacebookUserEntityDTO) throws ParseException;
}
