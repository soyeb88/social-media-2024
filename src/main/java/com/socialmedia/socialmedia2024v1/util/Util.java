package com.socialmedia.socialmedia2024v1.util;

import java.util.Random;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import com.socialmedia.socialmedia2024v1.dto.AddFacebookUserEntityDTO;


public class Util {

	
	public static String ObjectWriterMapping(Object object) throws JsonProcessingException{
	
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(object);
		return json;
	}
	
	public static String getUserId(AddFacebookUserEntityDTO addFacebookUserEntityDTO){
		
		if(addFacebookUserEntityDTO.getEmail() == null) {
			return null;
		}
		
		String[] splitWord = addFacebookUserEntityDTO.getEmail().split("@");
		String userId = splitWord[0];
		return userId;
	}

	public static String createRandomUserId(String userId) {				
		return userId + new Random().nextInt(9);
	}

}
