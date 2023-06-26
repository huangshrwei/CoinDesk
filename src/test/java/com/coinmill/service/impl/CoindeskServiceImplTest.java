package com.coinmill.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.coinmill.dto.ExchangePriceDto;
import com.coinmill.entity.CurrencySet;
import com.coinmill.service.CurrencySetService;
import com.coinmill.service.ExchangeRateService;


@ExtendWith(MockitoExtension.class)
class CoindeskServiceImplTest {
	@InjectMocks
	CoindeskServiceImpl coindeskServiceImpl;
	
	@Mock
	CurrencySetService currencySetService;
	
	@Mock
	ExchangeRateService exchangeRateService; 

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testExchangePriceDto() throws Exception {
		//when(currencySetService.listAllCurrencySet()).thenReturn(null);
		String getCoinName = this.coindeskServiceImpl.getCoinName("美金");
		Double getCoinPrice = this.coindeskServiceImpl.getCoinPrice("USD", 12.00);
		ExchangePriceDto exchangePriceDto = this.coindeskServiceImpl.exchangePriceDto("美金", 12.00);
		assertEquals(null, getCoinName);
		assertEquals(null, getCoinPrice);
		assertEquals(null, exchangePriceDto);
	}
	
	@Test
	void testInin() {
		CurrencySet currencySetGbp = new CurrencySet();
		currencySetGbp.setCurrencyCode("GBP");
		currencySetGbp.setCurrencyName("英鎊");
		currencySetGbp.setCreationDate(new Date());
		currencySetGbp.setUpdatedDate(new Date());
		List<CurrencySet> listCurrencySet = new ArrayList<>();
		listCurrencySet.add(currencySetGbp);
		when(currencySetService.listAllCurrencySet()).thenReturn(listCurrencySet);
		
	}

}
