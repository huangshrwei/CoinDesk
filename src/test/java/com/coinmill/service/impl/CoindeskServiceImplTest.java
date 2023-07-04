package com.coinmill.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.coinmill.api.CoindeskUtil;
import com.coinmill.dto.ExchangePriceDto;
import com.coinmill.dto.ExchangeRateDto;
import com.coinmill.entity.CurrencySet;
import com.coinmill.entity.ExchangeRate;
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
		CurrencySet currencySetGbp = new CurrencySet();
		currencySetGbp.setCurrencyCode("GBP");
		currencySetGbp.setCurrencyName("英鎊");
		currencySetGbp.setCreationDate(new Date());
		currencySetGbp.setUpdatedDate(new Date());
		List<CurrencySet> listCurrencySet = new ArrayList<>();
		listCurrencySet.add(currencySetGbp);
		when(currencySetService.listAllCurrencySet()).thenReturn(listCurrencySet);
		
		ExchangeRate exchangeRateGpb = new ExchangeRate();
		exchangeRateGpb.setChartName("Bitcoin");
		exchangeRateGpb.setCurrencyCode("GBP");
		exchangeRateGpb.setCurrencyName("英鎊");
		exchangeRateGpb.setCurrencyRate(15623.5434);
		Date date = new Date();
		exchangeRateGpb.setRateTime(date);
		exchangeRateGpb.setUpdated("Sep 26, 2022 06:48:00 UTC");
		exchangeRateGpb.setUpdatedIso("2022-09-26T06:48:00+00:00");
		exchangeRateGpb.setUpdateduk("Sep 26, 2022 at 07:48 BST");
		List<ExchangeRate> exchangeRate = new ArrayList<>();
		exchangeRate.add(exchangeRateGpb);
		when(exchangeRateService.listAllExchangeRate()).thenReturn(exchangeRate);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testExchangePriceDto() throws Exception {
		//when(currencySetService.listAllCurrencySet()).thenReturn(null);
		String getUsdCoinName = this.coindeskServiceImpl.getCoinName("美金");
		Double getUsdCoinPrice = this.coindeskServiceImpl.getCoinPrice("USD", 12.00);
		ExchangePriceDto exchangeUsdPriceDto = this.coindeskServiceImpl.exchangePriceDto("USD", 12.00);
		assertEquals(null, getUsdCoinName);
		assertEquals(null, getUsdCoinPrice);
		assertEquals(null, exchangeUsdPriceDto);
		String getGbpCoinName = this.coindeskServiceImpl.getCoinName("GBP");
		Double getGbpCoinPrice = this.coindeskServiceImpl.getCoinPrice("GBP", 12.00);
		ExchangePriceDto exchangeGbpPriceDto = this.coindeskServiceImpl.exchangePriceDto("GBP", 12.00);
		assertEquals("英鎊", getGbpCoinName);
		//assertEquals(null, getGbpCoinPrice);
		assertEquals("GBP", exchangeGbpPriceDto.getCurrencyCode());
		boolean isFalse = this.coindeskServiceImpl.checkUrl("http://123.456.789");
		boolean isTrue = this.coindeskServiceImpl.checkUrl("http://www.google.com");
		assertEquals(true, isTrue);
		assertEquals(false, isFalse);
		
		String json = "{\"time\":{\"updated\":\"Jul 4, 2023 07:28:00 UTC\",\"updatedISO\":\"2023-07-04T07:28:00+00:00\",\"updateduk\":\"Jul 4, 2023 at 08:28 BST\"},\"disclaimer\":\"This data was produced from the CoinDesk Bitcoin Price Index (USD). Non-USD currency data converted using hourly conversion rate from openexchangerates.org\",\"chartName\":\"Bitcoin\",\"bpi\":{\"USD\":{\"code\":\"USD\",\"symbol\":\"&#36;\",\"rate\":\"31,012.9324\",\"description\":\"United States Dollar\",\"rate_float\":31012.9324},\"GBP\":{\"code\":\"GBP\",\"symbol\":\"&pound;\",\"rate\":\"25,914.1582\",\"description\":\"British Pound Sterling\",\"rate_float\":25914.1582},\"EUR\":{\"code\":\"EUR\",\"symbol\":\"&euro;\",\"rate\":\"30,211.1240\",\"description\":\"Euro\",\"rate_float\":30211.124}}}";
		
		//when(CoindeskUtil.stream(any())).thenReturn(json);
		List<ExchangeRateDto> listExchangeRateDto  = this.coindeskServiceImpl.getCoindesk("https://api.coindesk.com/v1/bpi/currentprice.json");
		assertEquals(true, listExchangeRateDto.size()>0);
	}

}
