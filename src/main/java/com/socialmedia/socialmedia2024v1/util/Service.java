package com.socialmedia.socialmedia2024v1.util;

public class Service {
	public static final String GET_BY_USERID_AND_PASSWORD = "select user_id from socialmedia.account "
			+ "where user_id = :userId and password = :password";
	
	public static final String LOG_IN_FACEBOOK = "select user_id from socialmedia.user_details "
			+ "where email = :email and password = :password";
}
