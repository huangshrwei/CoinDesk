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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.coinmill.entity.ExchangeRate;
import com.coinmill.service.ExchangeRateService;
import lombok.extern.slf4j.Slf4j;


@AutoConfigureMockMvc
@Slf4j
public class ExchangeRateMockito {
    @InjectMocks
    ExchangeRateMockito testExchangeRateMockito;	
	
	@Mock
	ExchangeRateService exchangeRateService;
	
	private List<ExchangeRate> setList = new ArrayList<>();
	ExchangeRate setUsd = new ExchangeRate();
	ExchangeRate setGbp = new ExchangeRate();


	
	@BeforeEach
	void setup() throws Exception
	{
		//
		MockitoAnnotations.openMocks(this);

        //given setGbp
        setGbp.setRateId(1L);
        setGbp.setChartName("Btp");
        setGbp.setDisclaimer("Btp Desc");
        setGbp.setCurrencyCode("GBP");
        setGbp.setCurrencyName("GBP Name");
		java.util.Date date = new java.util.Date();
		setGbp.setCreationDate(date);
		setGbp.setUpdatedDate(date);		   	
		
		//given setUsd
		setUsd.setRateId(2L);
		setUsd.setChartName("Btp");
		setUsd.setDisclaimer("Btp Desc");
		setUsd.setCurrencyCode("USD");
		setUsd.setCurrencyName("USD Name");
		date = new java.util.Date();
		setUsd.setCreationDate(date);
		setUsd.setUpdatedDate(date);
		
		//given all		
		setList.add(setGbp);
		setList.add(setUsd);        
        
        log.info("@BeforeEach");
		
	}
	
	
	@AfterEach
	void tearDown() throws Exception
	{
		setList.clear();
		
        setGbp.setChartName(null);
        setGbp.setDisclaimer(null);		
		setGbp.setCreationDate(null);
		setGbp.setUpdatedDate(null);
		setGbp.setCurrencyCode(null);
		setGbp.setCurrencyName(null);
		
        setGbp.setChartName(null);
        setGbp.setDisclaimer(null);		
		setUsd.setCreationDate(null);
		setUsd.setUpdatedDate(null);
		setUsd.setCurrencyCode(null);
		setUsd.setCurrencyName(null);
		
	}	

	@Test
	void Get()
	{
		//given
        Mockito.when(exchangeRateService.getExchangeRate(1L)).thenReturn(setGbp);

        //then
        Assertions.assertEquals("GBP", exchangeRateService.getExchangeRate(1L).getCurrencyCode()); 
	}
	
	
	@Test
	void FindAll()
	{
		// given
        Mockito.when(exchangeRateService.listAllExchangeRate()).thenReturn(setList);  			
		
        //then
        Assertions.assertEquals(setList, exchangeRateService.listAllExchangeRate());
	}	
	
}
