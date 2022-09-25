package com.coinmill.service;


import java.util.List;

import com.coinmill.dto.ExchangeRateDto;

public interface CoindeskService {

	List<ExchangeRateDto> getCoindesk(String url) throws Exception;
	
	String getCoinName(String coinCode);
	
	//Optional<CurrencyRate> createCoinRate(CoindeskDto coindeskDto);
	
}
