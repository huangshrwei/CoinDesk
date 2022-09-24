package com.coinmill.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.coinmill.entity.ExchangeRate;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

	@Query("Select a From ExchangeRate a where a.chartName = ?1 and a.currencyCode = ?2")
	List<ExchangeRate> findByCode(String chartName, String currencyCode);
	
	@Query("Select a From ExchangeRate a where a.chartName = ?1 and a.currencyName = ?2")
	List<ExchangeRate> findByName(String chartName, String currencyName);
	
}
