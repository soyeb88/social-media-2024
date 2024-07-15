package com.socialmedia.socialmedia2024v1.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddFacebookUserEntityDTO {
	
	@NotBlank
	//@Pattern(regexp="[A-Z]+[a-z]+[@|_|-|&]+[0-9]+[A-Za-z]*")
	@Size(min=5, max=20)
	private String password;
	
	//@Email
	//@NotBlank
	private String email;
	
	//@Pattern(regexp="[0-9]+")
	//@Size(min=4, max=15)
	private String phone;
	
	@NotBlank
	@Pattern(regexp="[A-Za-z]+")
	@Size(min=1, max=50)
	private String firstName;
	
	@Pattern(regexp="[A-Za-z]+")
	@Size(min=1, max=50)
	private String lastName;
	
	@NotNull
	//@Pattern(regexp="[fm]")
	private String gender;
	
	//@NotNull
	@Past
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dob;	
}
