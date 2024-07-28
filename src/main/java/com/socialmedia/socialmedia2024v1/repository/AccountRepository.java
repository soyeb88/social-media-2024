package com.socialmedia.socialmedia2024v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.socialmedia.socialmedia2024v1.model.Account;
import com.socialmedia.socialmedia2024v1.util.SQL;

public interface AccountRepository extends JpaRepository<Account,Long>{

	@Query(value="select user_id from socialmedia.account where user_id = :userId", nativeQuery = true)
	String findByUserId(String userId);

}
