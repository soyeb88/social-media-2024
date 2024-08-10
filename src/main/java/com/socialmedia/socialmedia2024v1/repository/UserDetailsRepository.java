package com.socialmedia.socialmedia2024v1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.socialmedia.socialmedia2024v1.dto.PasswordUpdateDTO;
import com.socialmedia.socialmedia2024v1.model.UserDetails;
import com.socialmedia.socialmedia2024v1.util.SQL;

import jakarta.validation.Valid;


public interface UserDetailsRepository extends JpaRepository<UserDetails,Long>{

	@Query(value=SQL.FIND_BY_USERID, nativeQuery = true)
	String findByUserId(String userId);
	
	
	@Query(value = SQL.LOG_IN_FACEBOOK,  nativeQuery = true)
	String findUserIdByEmailOrPhoneAndPassword(String email, String phone, String password);
	
	
	@Query(value = SQL.EXISTS_EMAIL_OR_PHONE_FACEBOOK,  nativeQuery = true)
	String findUserIdByEmailOrPhone(String email, String phone);
	
	
	@Query(value = "select firstname, lastname from socialmedia.user_details where user_id = :userId",  nativeQuery = true)
	List<Object[]> findNameByUserId(String userId);

	@Modifying
	@Query(value = SQL.UPDATE_PASSWORD,  nativeQuery = true)
	Integer updatePassword(String oldPassword, String newPassword, String userId);

	@Modifying
	@Query(value = SQL.DELETE_PASSWORD,  nativeQuery = true)
	Integer deleteByUserId(String userId);

}
