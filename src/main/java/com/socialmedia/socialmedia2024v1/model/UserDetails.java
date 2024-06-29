package com.socialmedia.socialmedia2024v1.model;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user_details")
public class UserDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private long id;
	
	@Column(name="user_id", nullable = false,  unique = true)	
	private String userId;
	
	@Column(name="password", nullable = false)
	private String password;
	
	
	@Column(name="email", unique = true)
	private String email;
	
	@Column(name="phone", unique = true)
	private String phone;
	
	@Column(name="firstname", nullable = false)
	private String firstName;
	
	@Column(name="lastname")
	private String lastName;

	@Column(name="gender", nullable = false)
	private Character gender;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="dob", nullable = false)
	private LocalDate dob;

}
