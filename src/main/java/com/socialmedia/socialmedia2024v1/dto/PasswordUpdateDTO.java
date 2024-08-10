package com.socialmedia.socialmedia2024v1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PasswordUpdateDTO {
	@Pattern(regexp="[A-Za-z]+[0-9]+")
	private String userId;
	
	@NotBlank
	//@Pattern(regexp="[A-Z]+[a-z]+[@|_|-|&]+[0-9]+[A-Za-z]*")
	@Size(min=5, max=20)
	private String oldPassword;
	
	@NotBlank
	//@Pattern(regexp="[A-Z]+[a-z]+[@|_|-|&]+[0-9]+[A-Za-z]*")
	@Size(min=5, max=20)
	private String newPassword;
}
