package com.ternion.RFO.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ternion.RFO.entity.AccountData;

public interface AccountRepo extends JpaRepository<AccountData, Integer> {
	public AccountData findByUsername(String name);
}