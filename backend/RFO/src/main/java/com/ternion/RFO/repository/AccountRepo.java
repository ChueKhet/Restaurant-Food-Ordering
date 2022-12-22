package com.ternion.RFO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ternion.RFO.entity.AccountData;
import com.ternion.RFO.entity.UserData;

public interface AccountRepo extends JpaRepository<AccountData, Integer> {
	@Query(value = "select * from user_data where username = ?", nativeQuery = true)
	public AccountData findByUserName(String name);
}