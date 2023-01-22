package com.ternion.RFO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ternion.RFO.entity.AccountData;

public interface AccountRepo extends JpaRepository<AccountData, Integer> {
	public AccountData findByUserid(String userid);
	
	@Query(value = "SELECT CASE WHEN (MAX(CAST(SUBSTRING(userid, 3, 6) AS UNSIGNED)) + 1) IS NULL THEN 1 "
			+ "ELSE (MAX(CAST(SUBSTRING(userid, 3, 6) AS UNSIGNED)) + 1) END AS Max_ID FROM Account_data "
			+ "WHERE userid <> 'admin'", nativeQuery = true)
	public int getMaxId();
	
	@Query(value = "SELECT * FROM account_data WHERE parent_id=?", nativeQuery = true)
	public AccountData findByParentId(int parentid);
}