package com.coinmill.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.coinmill.dto.ExchangeRateDto;
import com.coinmill.entity.ExchangeRate;
import com.coinmill.service.ExchangeRateService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class ExchangeRateServiceImplTest {
	
	@Autowired
	ExchangeRateService exchangeRateService;	
	
	List<ExchangeRate> listExchangeRate = new ArrayList<>();
	ExchangeRateDto exchangeRateUsd = new ExchangeRateDto();
	ExchangeRateDto exchangeRateGpb = new ExchangeRateDto();
	ExchangeRateDto exchangeRateEur = new ExchangeRateDto();
	ExchangeRateDto exchangeRateJpd = new ExchangeRateDto();
	
	/*
	//列出所有資料
    List<ExchangeRate> listAllExchangeRate();

    //新增資料
    ExchangeRate createExchangeRate(ExchangeRateDto exchangeRateDto);

    //刪除資料
    int deleteExchangeRate(Long id);

    //列出第N頁資料, 並列出該頁筆數
    List<ExchangeRate> listExchangeRateByPage(int pageNum, int pageSize);
    
    //依照chartName, currencyCode抓取資料
    List<ExchangeRate> listExchangeRateByCode(String chartName, String currencyCode);
    
    //依照chartName, currencyName抓取資料
    List<ExchangeRate> listExchangeRateByName(String chartName, String currencyName);    

    //依照id抓取資料
    ExchangeRate getExchangeRate(Long id);	
	*/
	

	@BeforeEach
	void setUp() throws Exception {
		Date date = new Date();
		
		exchangeRateUsd.setChartName("Bitcoin");
		exchangeRateUsd.setCurrencyCode("USD");
		exchangeRateUsd.setCurrencyName("美金");
		exchangeRateUsd.setCurrencyRate(18697.5742);
		exchangeRateUsd.setRateTime(date);
		exchangeRateUsd.setUpdated("Sep 26, 2022 06:48:00 UTC");
		exchangeRateUsd.setUpdatedIso("2022-09-26T06:48:00+00:00");
		exchangeRateUsd.setUpdateduk("Sep 26, 2022 at 07:48 BST");

		exchangeRateGpb.setChartName("Bitcoin");
		exchangeRateGpb.setCurrencyCode("GBP");
		exchangeRateGpb.setCurrencyName("英鎊");
		exchangeRateGpb.setCurrencyRate(15623.5434);
		exchangeRateGpb.setRateTime(date);
		exchangeRateGpb.setUpdated("Sep 26, 2022 06:48:00 UTC");
		exchangeRateGpb.setUpdatedIso("2022-09-26T06:48:00+00:00");
		exchangeRateGpb.setUpdateduk("Sep 26, 2022 at 07:48 BST");
		
		exchangeRateGpb.setChartName("Bitcoin");
		exchangeRateGpb.setCurrencyCode("EUR");
		exchangeRateGpb.setCurrencyName("歐元");
		exchangeRateGpb.setCurrencyRate(18214.1671);
		exchangeRateGpb.setRateTime(date);
		exchangeRateGpb.setUpdated("Sep 26, 2022 06:48:00 UTC");
		exchangeRateGpb.setUpdatedIso("2022-09-26T06:48:00+00:00");
		exchangeRateGpb.setUpdateduk("Sep 26, 2022 at 07:48 BST");
		
		
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testListAllExchangeRate() {

		exchangeRateService.createExchangeRate(exchangeRateUsd);
		List<ExchangeRate> listAllExchangeRate = exchangeRateService.listAllExchangeRate();
		Assertions.assertEquals(1, listAllExchangeRate.size());
		
	}

	@Test
	void testCreateExchangeRate() {
		
		ExchangeRate saveExchangeRate = exchangeRateService.createExchangeRate(exchangeRateUsd);
		Assertions.assertEquals("USD", saveExchangeRate.getCurrencyCode());
	}

	@Test
	void testDeleteExchangeRate() {
		exchangeRateService.createExchangeRate(exchangeRateUsd);
		exchangeRateService.createExchangeRate(exchangeRateGpb);
		int i = exchangeRateService.deleteExchangeRate(1L);
		Assertions.assertEquals(1, i);
	}

	@Test
	void testListExchangeRateByCode() {
		exchangeRateService.createExchangeRate(exchangeRateUsd);
		List<ExchangeRate> listAllExchangeRate = exchangeRateService.listExchangeRateByCode("Bitcoin", "USD");
		Assertions.assertEquals("USD", listAllExchangeRate.get(0).getCurrencyCode());
	}

	@Test
	void testListExchangeRateByName() {
		exchangeRateService.createExchangeRate(exchangeRateUsd);
		List<ExchangeRate> listAllExchangeRate = exchangeRateService.listExchangeRateByName("Bitcoin", "美金");
		Assertions.assertEquals("美金", listAllExchangeRate.get(0).getCurrencyName());
	}

	@Test
	void testGetExchangeRate() {
		exchangeRateService.createExchangeRate(exchangeRateUsd);
		ExchangeRate exchangeRate = exchangeRateService.getExchangeRate(1L);
	}

}
