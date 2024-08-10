package com.socialmedia.socialmedia2024v1.unit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.when;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.socialmedia.socialmedia2024v1.dto.AddFacebookUserEntityDTO;
import com.socialmedia.socialmedia2024v1.dto.FacebookProfileDTO;
import com.socialmedia.socialmedia2024v1.dto.ResponseDTO;
import com.socialmedia.socialmedia2024v1.model.UserDetails;
import com.socialmedia.socialmedia2024v1.repository.UserDetailsRepository;
import com.socialmedia.socialmedia2024v1.service.UserAccountService;

/**
 * <h1>UserAccountServiceTest</h1> program implements an application that
 * <p>
 * <b>Note:</b>Simply Junit test on CRUD operation to the standard output.
 * </p>
 *
 * @author Soyeb Ahmed
 * @version 1.0
 * @since 2024-08-08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FacebookUnitTest {

	@Autowired
	private UserAccountService userAccountService;

	@MockBean
	private UserDetailsRepository userDetailsRepository;
	

	@Test
	public void givenAddUserAccountObject_createFacebookUserDetails_thenReturn_SuccessResponse() throws Exception {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
		
		
		
		ResponseDTO signUpResponseDTO = new ResponseDTO();
		signUpResponseDTO.setResponseMessage("User Account Created Successfully!");
		signUpResponseDTO.setStatusCode(201);
		signUpResponseDTO.setUserId("soyeb88");
		
		
		AddFacebookUserEntityDTO addFacebookUserEntityDTO = AddFacebookUserEntityDTO.builder().firstName("Soyeb").lastName("Ahmed").email("soyeb88@gmail.com")
		.gender("m").dob(LocalDate.parse("22 Dec 1994", formatter)).build();
		
		UserDetails userDetails = UserDetails.builder().userId("soyeb88").password(addFacebookUserEntityDTO.getPassword())
				.email("soyeb888@gmail.com").phone(null).firstName(addFacebookUserEntityDTO.getFirstName())
				.lastName(addFacebookUserEntityDTO.getLastName()).gender(addFacebookUserEntityDTO.getGender())
				.dob(addFacebookUserEntityDTO.getDob()).build();

		
		when(userDetailsRepository.save(userDetails))
		.thenReturn(userDetails);
		
		assertEquals(signUpResponseDTO,userAccountService.createFacebookUserDetails(addFacebookUserEntityDTO));

	}

}

