package com.socialmedia.socialmedia2024v1.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddUserEntityDTO {
	@NotEmpty
	@Size(min=5, max=20)
	@Pattern(regexp="[A-Za-z]+[0-9]+")
	private String userId;
	
	@NotEmpty
	private String password;
	
	@NotEmpty
	@Pattern(regexp="[A-Za-z]+( [A-Za-z]+)*")
	@Size(max=50)
	private String name;
}
