package com.socialmedia.socialmedia2024v1.util;

public class Service {
	public static final String GET_BY_USERID_AND_PASSWORD = "select user_id from socialmedia.account "
			+ "where user_id = :userId and password = :password";
}
