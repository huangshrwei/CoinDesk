package com.coinmill.service;


import java.util.List;

import com.coinmill.dto.ExchangePriceDto;
import com.coinmill.dto.ExchangeRateDto;

public interface CoindeskService {

	List<ExchangeRateDto> getCoindesk(String url) throws Exception;
	
	String getCoinName(String coinCode);
	
	Double getCoinPrice(String coinCode, Double codePrice);	
	
	boolean checkUrl(String url);
	
	ExchangePriceDto exchangePriceDto(String coinCode, Double codePrice);
	
	//Optional<CurrencyRate> createCoinRate(CoindeskDto coindeskDto);
	
}
