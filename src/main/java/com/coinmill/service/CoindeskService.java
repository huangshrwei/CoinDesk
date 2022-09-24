package com.coinmill.service;

import java.util.Optional;

import com.coinmill.dto.CoindeskDto;
//import com.coinmill.entity.CurrencyRate;

public interface CoindeskService {

	CoindeskDto<?> getCoindesk(String url);
	
	//Optional<CurrencyRate> createCoinRate(CoindeskDto coindeskDto);
	
}
