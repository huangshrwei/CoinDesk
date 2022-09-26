package com.coinmill.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.coinmill.dto.ExchangePriceDto;
import com.coinmill.dto.ExchangeRateDto;
import com.coinmill.service.CoindeskService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class CoindeskServiceImplTest {
	
	@Autowired
	CoindeskService coindeskService;	

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCheckUrl() {
		String corrUrl = "http://aaa.aa.aa";
		String errUrl = "AA";
		
		Assertions.assertEquals(true, coindeskService.checkUrl(corrUrl));
		Assertions.assertEquals(false, coindeskService.checkUrl(errUrl));
	}

	@Test
	void testGetCoindesk() throws Exception {
		
		List<ExchangeRateDto> listExchangeRateDto = coindeskService.getCoindesk("https://api.coindesk.com/v1/bpi/currentprice.json");
		Assertions.assertEquals(false, listExchangeRateDto.isEmpty());
	}

	@Test
	void testGetCoinName() {
		String coinName = coindeskService.getCoinName("USD");
		Assertions.assertEquals("美金", coinName);
	}

	@Test
	void testGetCoinPrice() throws Exception {
		
		List<ExchangeRateDto> listExchangeRateDto = coindeskService.getCoindesk("https://api.coindesk.com/v1/bpi/currentprice.json");
		Double price = coindeskService.getCoinPrice("USD", 100000.00);
		List<ExchangeRateDto> findList = listExchangeRateDto.stream().filter(item -> item.getCurrencyCode().equals("USD")).collect(Collectors.toList());
		Assertions.assertEquals(100000.00/findList.get(0).getCurrencyRate(), price);
	}

	@Test
	void testExchangePriceDto() throws Exception {
		
		List<ExchangeRateDto> listExchangeRateDto = coindeskService.getCoindesk("https://api.coindesk.com/v1/bpi/currentprice.json");
		ExchangePriceDto exchangePriceDto= coindeskService.exchangePriceDto("USD", 100000.00);
		Assertions.assertEquals("USD",exchangePriceDto.getCurrencyCode());
	}

}
