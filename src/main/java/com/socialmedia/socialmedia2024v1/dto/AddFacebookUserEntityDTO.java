package com.socialmedia.socialmedia2024v1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddFacebookUserEntityDTO {

	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String password;
	private String gender;
	private String month;
	private String day;
	private String year;
}
