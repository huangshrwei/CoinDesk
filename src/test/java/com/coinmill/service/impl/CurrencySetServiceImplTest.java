package com.coinmill.service.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.InvalidJpaQueryMethodException;

import com.coinmill.entity.CurrencySet;
import com.coinmill.dao.CurrencySetRepository;
import com.coinmill.dto.CurrencySetDto;
import com.coinmill.service.impl.CurrencySetServiceImplTest;


@ExtendWith(MockitoExtension.class)
class CurrencySetServiceImplTest {

	@InjectMocks
	private CurrencySetServiceImpl currencySetServiceImpl; 
	
	@Mock
	CurrencySetRepository currencySetRepository;
	
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
		
	}

	@AfterEach
	void tearDown() throws Exception {
		currencySetUsd = null;
		currencySetGbp = null;
		currencySetEur = null;		
	}

	
	@Test
	void testCurrencySetServiceWithCreateCurrencySetException() {
		doThrow(new InvalidJpaQueryMethodException("errorr")).when(currencySetRepository).save(any());
		CurrencySet currencySetResult = this.currencySetServiceImpl.createCurrencySet(currencySetUsd);
		assertEquals(null, currencySetResult.getCreationDate());
		assertEquals(null, currencySetResult.getCurrencyCode());
		assertEquals(null, currencySetResult.getCurrencyName());
		assertEquals(null, currencySetResult.getUpdatedDate());
	}
	
	@Test
	void testCurrencySetServiceWithUpdateCurrencySetException() {
		doThrow(new InvalidJpaQueryMethodException("errorr")).when(currencySetRepository).save(any());
		CurrencySet currencySetResult = this.currencySetServiceImpl.updateCurrencySet("USD", currencySetUsd);
		assertEquals(null, currencySetResult.getCreationDate());
		assertEquals(null, currencySetResult.getCurrencyCode());
		assertEquals(null, currencySetResult.getCurrencyName());
		assertEquals(null, currencySetResult.getUpdatedDate());
	}

	@Test
	void testCurrencySetServiceWithDeleteException() {
		doThrow(new InvalidJpaQueryMethodException("errorr")).when(currencySetRepository).deleteById(any());
		int i = this.currencySetServiceImpl.deleteCurrencySet(any());
		assertEquals(i, -1);
		verify(currencySetRepository).deleteById(any());
	}
	
	@Test
	void testCurrencySetServiceWithNotGetCurrencySet() {
		when(currencySetRepository.findById(any())).thenReturn(Optional.empty());
		CurrencySet currencySetRes = this.currencySetServiceImpl.getCurrencySet(any());
		assertEquals(null, currencySetRes);
		verify(currencySetRepository).findById(any());
	}
	
	@Test
	void testExchangeRateServiceWithGetDataByPage() {
		CurrencySet currencySet = new CurrencySet();
		currencySet.setCurrencyCode("USD");
		currencySet.setCurrencyName("美元");
		List<CurrencySet> currencySetList = new ArrayList<>();
		currencySetList.add(currencySet);
;		when(currencySetRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<CurrencySet>(currencySetList));
		assertDoesNotThrow(()->this.currencySetServiceImpl.listCurrencySet(1, 1));
		verify(currencySetRepository).findAll(any(Pageable.class));
	}

}
