package com.socialmedia.socialmedia2024v1.util;

public class SQL {
	public static final String FIND_BY_USERID = "select user_id from socialmedia.user_details "
			+ "where user_id = :userId";
	
	public static final String GET_BY_USERID_AND_PASSWORD = "select user_id from socialmedia.account "
			+ "where user_id = :userId and password = :password";
	
	public static final String LOG_IN_FACEBOOK = "select user_id from socialmedia.user_details "
			+ "where (email = :email or phone = :phone) and password = :password";
	
	public static final String EXISTS_EMAIL_OR_PHONE_FACEBOOK = "select user_id from socialmedia.user_details "
			+ "where email = :email or phone = :phone";
	
	public static final String UPDATE_PASSWORD = "update socialmedia.user_details "
			+ "set password = :newPassword where user_id = :userId and password= :oldPassword";
	
	public static final String DELETE_PASSWORD = "delete from socialmedia.user_details "
			+ "where user_id = :userId";
}
