package com.socialmedia.socialmedia2024v1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication
@EnableEncryptableProperties
public class SocialMedia2024V1Application {

	public static void main(String[] args) {
		SpringApplication.run(SocialMedia2024V1Application.class, args);
	}

}
