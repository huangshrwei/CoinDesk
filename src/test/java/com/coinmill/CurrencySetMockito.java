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

import com.coinmill.entity.CurrencySet;
import com.coinmill.service.CurrencySetService;
import lombok.extern.slf4j.Slf4j;

@AutoConfigureMockMvc
@Slf4j
public class CurrencySetMockito {
    @InjectMocks
    CurrencySetMockito testCurrencySetMockito;	
	
	@Mock
	CurrencySetService currencySetService;
	
	private List<CurrencySet> setList = new ArrayList<>();
	CurrencySet setUsd = new CurrencySet();
	CurrencySet setGbp = new CurrencySet();

	
    @Autowired                           
    private MockMvc mockMvc; 	

	
	@BeforeEach
	void setup() throws Exception
	{
		//
		MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(testCurrencySetMockito).build();

        //given setGbp		
		
        setGbp.setCurrencyCode("GBP");
        setGbp.setCurrencyName("GBP");
		java.util.Date date = new java.util.Date();
		setGbp.setCreationDate(date);
		setGbp.setUpdatedDate(date);		   	
		
		//given setUsd				
		setUsd.setCurrencyCode("USD");
		setUsd.setCurrencyName("USD NAME");
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
		
		setGbp.setCreationDate(null);
		setGbp.setUpdatedDate(null);
		setGbp.setCurrencyCode(null);
		setGbp.setCurrencyName(null);
		
		setUsd.setCreationDate(null);
		setUsd.setUpdatedDate(null);
		setUsd.setCurrencyCode(null);
		setUsd.setCurrencyName(null);
		
	}	

	@Test
	void Get()
	{
		//given
        Mockito.when(currencySetService.getCurrencySet("USD")).thenReturn(setUsd);
        Mockito.when(currencySetService.getCurrencySet("GBP")).thenReturn(setGbp);

        //then
        Assertions.assertEquals("USD", currencySetService.getCurrencySet("USD").getCurrencyCode()); 
	}
	
	
	@Test
	void FindAll()
	{
		// given
        Mockito.when(currencySetService.getCurrencySet("USD")).thenReturn(setUsd);
        Mockito.when(currencySetService.getCurrencySet("GBP")).thenReturn(setGbp);
        Mockito.when(currencySetService.listAllCurrencySet()).thenReturn(setList);  			
		
        //then
        Assertions.assertEquals(setList, currencySetService.listAllCurrencySet());
	}	
	
}
