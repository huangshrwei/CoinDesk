package com.coinmill;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import com.coinmill.dto.ExchangePriceDto;
import com.coinmill.dto.ExchangeRateDto;
import com.coinmill.service.CoindeskService;

import lombok.extern.slf4j.Slf4j;

@AutoConfigureMockMvc
@Slf4j
public class CoindeskMockito {
	
    @InjectMocks
    CoindeskMockito testCoindeskMockito;	
	
	@Mock
	CoindeskService coindeskService;
	
    @Autowired                           
    private MockMvc mockMvc; 		        
	
	@BeforeEach
	void setup() throws Exception
	{
		MockitoAnnotations.openMocks(this);		
	}
	
	@AfterEach
	void tearDown() throws Exception
	{
	}	
	
	
	//將網頁內容放入List<ExchangeRateDto>中
	@Test
	void GetCoindesk() throws Exception
	{		
	    //given
	    ExchangeRateDto exchangeRateUsd = new ExchangeRateDto();   
	    exchangeRateUsd.setChartName("bpi");
	    exchangeRateUsd.setCurrencyCode("USD");
	    exchangeRateUsd.setCurrencyName("US Dollar");
	    exchangeRateUsd.setCurrencyRate(1.1);
	    exchangeRateUsd.setDisclaimer("彼特幣");
	    
	    ExchangeRateDto exchangeRateGpb = new ExchangeRateDto();	    
	    exchangeRateGpb.setChartName("bpi");
	    exchangeRateGpb.setCurrencyCode("GPB");
	    exchangeRateGpb.setCurrencyName("UK Dollar");
	    exchangeRateGpb.setCurrencyRate(1.2);
	    exchangeRateGpb.setDisclaimer("彼特幣");
	    
	    List<ExchangeRateDto> listExchangeRateDto = new ArrayList<>(); 
	    listExchangeRateDto.add(exchangeRateGpb);
	    listExchangeRateDto.add(exchangeRateUsd);
	    
	    //when
	    Mockito.when(coindeskService.getCoindesk("http://localhost")).thenReturn(listExchangeRateDto);
	    
	    //then
	    Assertions.assertEquals(listExchangeRateDto, coindeskService.getCoindesk("http://localhost")); 
	}	
	
	
	//use coincode get coinname
	@Test
	void GetCoinName() throws Exception
	{		
	    //when
	    Mockito.when(coindeskService.getCoinName("USD")).thenReturn("US Dollar");
	    
	    //then
	    Assertions.assertEquals("US Dollar", coindeskService.getCoinName("USD")); 
	}		
	
	
	//抓取幣別匯率
	@Test
	void GetCoinPrice() throws Exception
	{
	    //when
	    Mockito.when(coindeskService.getCoinPrice("USD",1000.00)).thenReturn(1.1);
	    
	    //then
	    Assertions.assertEquals(1.1, coindeskService.getCoinPrice("USD",1000.00)); 
	}
	
	
	//檢查網址格式
	@Test
	void CheckUrl() throws Exception
	{		
	    //when
	    Mockito.when(coindeskService.checkUrl("http://localhost")).thenReturn(true);
	    
	    //then
	    Assertions.assertEquals(true, coindeskService.checkUrl("http://localhost")); 
	}	
	
	//匯率換算
	@Test
	void ExchangePrice() throws Exception
	{
		//give
		ExchangePriceDto exchangePriceDto = new ExchangePriceDto();
		exchangePriceDto.setChartName("bpi");
		exchangePriceDto.setCurrencyCode("USD");
		exchangePriceDto.setCurrencyName("US Dollar");
		exchangePriceDto.setCurrencyRate(1.11);
		exchangePriceDto.setOriginPrice(10000.00);		
		
	    //when
	    Mockito.when(coindeskService.exchangePriceDto("USD",10000.00)).thenReturn(exchangePriceDto);
	    
	    //then
	    Assertions.assertEquals(exchangePriceDto, coindeskService.exchangePriceDto("USD",10000.00)); 
	}	
	
}
