package com.ternion.RFO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ternion.RFO.entity.AccountData;
import com.ternion.RFO.entity.UserData;

public interface AccountRepo extends JpaRepository<AccountData, Integer> {
	public AccountData findByUsername(String username);
}