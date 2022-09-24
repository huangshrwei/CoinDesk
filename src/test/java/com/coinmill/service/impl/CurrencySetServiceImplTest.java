package com.coinmill.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.coinmill.entity.CurrencySet;
import com.coinmill.dto.CurrencySetDto;
import com.coinmill.service.CurrencySetService;
import com.coinmill.service.impl.CurrencySetServiceImplTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@EnableJpaAuditing
class CurrencySetServiceImplTest {

	@Autowired
	private CurrencySetService currencySetService; 
	
	CurrencySetDto currencySetUsd = new CurrencySetDto(); 
	
	CurrencySetDto currencySetGbp = new CurrencySetDto();
	
	CurrencySetDto currencySetEur = new CurrencySetDto();		
	
	@BeforeEach
	void setUp() throws Exception {
		
		currencySetUsd.setCurrencyCode("USD");
		currencySetUsd.setCurrencyName("美元");
		
		currencySetGbp.setCurrencyCode("GBP");
		currencySetGbp.setCurrencyName("英鎊");
		
		currencySetEur.setCurrencyCode("EUR");
		currencySetEur.setCurrencyName("歐元");
		
		//given
		currencySetService.createCurrencySet(currencySetUsd);		
		
	}

	@AfterEach
	void tearDown() throws Exception {
		currencySetUsd = null;
		currencySetGbp = null;
		currencySetEur = null;		
	}

	
	@Test
	void testCreateCurrencySet() {
		currencySetService.createCurrencySet(currencySetGbp);	
	}	
	
	@Test
	void testGetCurrencySet() {
		//when
		CurrencySet currencySet = new CurrencySet(); 
		currencySet = currencySetService.getCurrencySet("USD");
		
		//then
		Assertions.assertEquals("USD", currencySet.getCurrencyCode());
	}	

	
	
	@Test
	void testListAllCurrencySet() {
		
		//when
		List<CurrencySet> listCurrencySet = new ArrayList<>(); 
		listCurrencySet = currencySetService.listAllCurrencySet();
		
		//then
		Assertions.assertEquals(2, listCurrencySet.size());
		
	}
	
	@Test
	void testUpdateCurrencySet() {
		//given
		currencySetUsd.setCurrencyCode("USD");
		currencySetUsd.setCurrencyName("USD");		
		
		//when
		CurrencySet currencySet = new CurrencySet(); 
		currencySet = currencySetService.updateCurrencySet("USD", currencySetUsd);
		
		//then
		Assertions.assertEquals("USD", currencySet.getCurrencyName());		
	}

	@Test
	void testDeleteCurrencySet() {
						
		//given
		currencySetService.createCurrencySet(currencySetEur);	
		List<CurrencySet> listCurrencySet = new ArrayList<>();
		listCurrencySet = currencySetService.listAllCurrencySet();
		int i = listCurrencySet.size();
		
		//when
		currencySetService.deleteCurrencySet("EUR");		
		listCurrencySet = currencySetService.listAllCurrencySet();
		
		//then
		Assertions.assertEquals(i-1, listCurrencySet.size());
	
	}



}
