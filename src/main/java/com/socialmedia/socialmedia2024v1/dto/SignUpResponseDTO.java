package com.socialmedia.socialmedia2024v1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignUpResponseDTO {
	private String responseMessage;
	private Integer statusCode;
	private String userId;	
}
