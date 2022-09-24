package com.coinmill.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coinmill.dto.CoindeskDto;
//import com.coinmill.entity.CurrencyRate;
import com.coinmill.service.CoindeskService;

@Service
public class CoindeskServiceImpl implements CoindeskService{
	
    @Override	
    public CoindeskDto<?> getCoindesk(String url){
    	CoindeskDto coindeskDto = null;
    	return coindeskDto;
    }
	
    /*
    @Override
    public Optional<CurrencyRate> createCoinRate(CoindeskDto coindeskDto){
    	Optional<CurrencyRate> optCurrencyRate = null;
    	return optCurrencyRate;
    }
    */	
	
}
