package com.ternion.RFO.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ternion.RFO.entity.SaleDetailData;

public interface SaleDetailRepo extends JpaRepository<SaleDetailData, Integer>{
	@Query(value = "select * from sale_detail_data where header_id = ?", nativeQuery = true)
    List<String> findByHeaderId(int header_id);

}
