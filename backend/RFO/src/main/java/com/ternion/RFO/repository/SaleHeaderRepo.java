package com.ternion.RFO.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ternion.RFO.entity.SaleHeaderData;

public interface SaleHeaderRepo extends JpaRepository<SaleHeaderData, Integer>{
	@Query(value = "select CASE WHEN MAX(slip_no) IS NULL THEN 1 ELSE (MAX(slip_no) + 1) END from sale_header_data where created_at = CURDATE()", nativeQuery = true)
    int getTodayMaxSlip();
}