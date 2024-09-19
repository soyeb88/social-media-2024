package com.socialmedia.socialmedia2024v1.integration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import com.socialmedia.socialmedia2024v1.dto.AddFacebookUserEntityDTO;
import com.socialmedia.socialmedia2024v1.dto.ResponseDTO;
import com.socialmedia.socialmedia2024v1.repository.UserDetailsRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SignUpIntegrationTest {

	@LocalServerPort
	private int port;

	private String baseUrl = "http://localhost:";

	private static RestTemplate restTemplate;

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@BeforeAll
	public static void init() {
		restTemplate = new RestTemplate();
	}

	@BeforeEach
	public void setUp() {
		baseUrl = baseUrl.concat(port + "").concat("/facebook/signup");
	}

	@Test
	@Sql(statements="delete from socialmedia.user_details where email='test1@test.com'", 
		executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void createUserAccount() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

		ResponseDTO signUpResponseDTO = new ResponseDTO();
		signUpResponseDTO.setResponseMessage("User Account Created Successfully!");
		signUpResponseDTO.setStatusCode(201);
		signUpResponseDTO.setUserId("test1");

		AddFacebookUserEntityDTO addFacebookUserEntityDTO = AddFacebookUserEntityDTO.builder().firstName("Soyeb").password("Dhaka_866")
				.lastName("Ahmed").email("test1@test.com").phone(null).gender("m").dob(LocalDate.parse("22 Dec 1994", formatter))
				.build();
		
		ResponseDTO responseDTO = restTemplate.postForObject(baseUrl, addFacebookUserEntityDTO, signUpResponseDTO.getClass());
		
		Assertions.assertEquals(signUpResponseDTO,responseDTO);
	}

}
