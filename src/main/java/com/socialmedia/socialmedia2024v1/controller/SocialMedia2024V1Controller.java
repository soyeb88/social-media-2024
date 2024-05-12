package com.socialmedia.socialmedia2024v1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class SocialMedia2024V1Controller {
	
	@GetMapping("/test")
	public String testEndPoint() {
		return "Hello";
	}
	
}
