package com.socialmedia.socialmedia2024v1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddUserEntityDTO {
	private String userId;
	private String password;
	private String name;
}
