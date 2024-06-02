package com.socialmedia.socialmedia2024v1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PasswordUpdateDTO {
	private String userId;
	private String oldPassword;
	private String newPassword;
}
