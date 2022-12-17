package com.ternion.RFO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ternion.RFO.entity.UserData;

public interface AccountRepo extends JpaRepository<UserData, Integer> {
	@Query(value = "select * from user_data where username = ?", nativeQuery = true)
	public UserData findByUserName(String name);
}