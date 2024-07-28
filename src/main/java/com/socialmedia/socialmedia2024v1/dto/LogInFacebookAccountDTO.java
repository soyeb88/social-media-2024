package com.socialmedia.socialmedia2024v1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LogInFacebookAccountDTO {

	private String email;
	private String phone;
	private String password;
}
