package com.socialmedia.socialmedia2024v1.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddFacebookUserEntityDTO {
	
	@NotBlank
	private String password;
	
	@Email
	private String email;
	
	private String phone;
	
	@NotBlank
	@Pattern(regexp="[A-Za-z]+")
	private String firstName;
	
	@Pattern(regexp="[A-Za-z]+")
	private String lastName;
	
	@NotNull
	//@Pattern(regexp="[fm]")
	private Character gender;
	
	@NotNull
	@Past
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dob;	
}
