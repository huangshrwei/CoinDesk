package com.coinmill.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.InvalidJpaQueryMethodException;

import com.coinmill.dao.ExchangeRateRepository;
import com.coinmill.entity.ExchangeRate;


@ExtendWith(MockitoExtension.class)
class ExchangeRateServiceImplTest {
	
	@InjectMocks
	ExchangeRateServiceImpl exchangeRateServiceImpl;
	
	@Mock
	ExchangeRateRepository exchangeRateRepository;

	List<ExchangeRate> listExchangeRate = new ArrayList<>();
	ExchangeRate exchangeRateUsd = new ExchangeRate();
	ExchangeRate exchangeRateGpb = new ExchangeRate();
	ExchangeRate exchangeRateEur = new ExchangeRate();
	ExchangeRate exchangeRateJpd = new ExchangeRate();
	
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
		
		exchangeRateEur.setChartName("Bitcoin");
		exchangeRateEur.setCurrencyCode("EUR");
		exchangeRateEur.setCurrencyName("歐元");
		exchangeRateEur.setCurrencyRate(18214.1671);
		exchangeRateEur.setRateTime(date);
		exchangeRateEur.setUpdated("Sep 26, 2022 06:48:00 UTC");
		exchangeRateEur.setUpdatedIso("2022-09-26T06:48:00+00:00");
		exchangeRateEur.setUpdateduk("Sep 26, 2022 at 07:48 BST");
		
		listExchangeRate.add(exchangeRateUsd);
		listExchangeRate.add(exchangeRateUsd);
		listExchangeRate.add(exchangeRateUsd);
		
	}
	
	@Test
	void testExchangeRateServiceWithFindAll() {
		when(exchangeRateRepository.findAll()).thenReturn(listExchangeRate);
		List<ExchangeRate> findAllList = this.exchangeRateServiceImpl.listAllExchangeRate();
		assertEquals(3, findAllList.size());
		verify(exchangeRateRepository).findAll();
	}

	@Test
	void testExchangeRateServiceWithDeleteException() {
		doThrow(new InvalidJpaQueryMethodException("errorr")).when(exchangeRateRepository).deleteById(any());
		int i = this.exchangeRateServiceImpl.deleteExchangeRate(any());
		assertEquals(i, -1);
		verify(exchangeRateRepository).deleteById(any());
	}
	
	@Test
	void testExchangeRateServiceWithGetExchangeRate() {
		when(exchangeRateRepository.findById(any())).thenReturn(Optional.of(exchangeRateUsd));
		ExchangeRate exchangeRate = this.exchangeRateServiceImpl.getExchangeRate(any());
		assertEquals("USD", exchangeRate.getCurrencyCode());
		verify(exchangeRateRepository).findById(any());
	}
	
	@Test
	void testExchangeRateServiceWithGetDataByPage() {
		when(exchangeRateRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<ExchangeRate>(listExchangeRate));
		assertDoesNotThrow(()->this.exchangeRateServiceImpl.listExchangeRateByPage(1, 1));
		verify(exchangeRateRepository).findAll(any(Pageable.class));
	}
	
}
