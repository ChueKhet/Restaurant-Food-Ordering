package com.ternion.RFO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ternion.RFO.entity.SaleDetailData;

public interface SaleDetailRepo extends JpaRepository<SaleDetailData, Integer>{
	@Modifying
	@Query(value = "DELETE FROM sale_detail_data where header_id = ?", nativeQuery = true)
	void deleteAllByHeaderId(int id);
}