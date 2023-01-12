package com.ternion.RFO.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ternion.RFO.entity.SaleHeaderData;


public interface KitchenRepo extends JpaRepository<SaleHeaderData, Integer> {
//	@Query(value = "select * from sale_detail_data where header_id = ?", nativeQuery = true)
//    List<String> findByHeaderId(int header_id);
	
	@Query(value="select * from sale_header_data s where s.created_at=CURDATE() and s.order_status=1", nativeQuery=true)
	List<SaleHeaderData> findSlipAndTableNo();
}