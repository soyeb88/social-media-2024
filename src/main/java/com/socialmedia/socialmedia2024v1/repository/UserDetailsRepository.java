package com.socialmedia.socialmedia2024v1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.socialmedia.socialmedia2024v1.model.UserDetails;
import com.socialmedia.socialmedia2024v1.util.SQL;


public interface UserDetailsRepository extends JpaRepository<UserDetails,Long>{

	@Query(value=SQL.FIND_BY_USERID, nativeQuery = true)
	String findByUserId(String userId);
	
	@Query(value = SQL.LOG_IN_FACEBOOK,  nativeQuery = true)
	String findUserIdByEmailAndPassword(String email, String password);

	@Query(value = "select firstname, lastname from socialmedia.user_details where user_id = :id",  nativeQuery = true)
	List<Object[]> findNameByUserId(String id);
}
