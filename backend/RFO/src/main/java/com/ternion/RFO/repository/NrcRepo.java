package com.ternion.RFO.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ternion.RFO.entity.NrcData;

public interface NrcRepo extends JpaRepository<NrcData, Integer> {
	
	@Query(value = "select nrc_code from nrc_data group by nrc_code", nativeQuery = true)
    List<String> findGroupByNrcCode();

	@Query(value = "select township_names from nrc_data where nrc_code = ?", nativeQuery = true)
    List<String> findByNrcCode(int nrc_code);
}