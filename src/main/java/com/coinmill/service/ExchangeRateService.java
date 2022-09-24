package com.coinmill.service;

import java.util.List;
import java.util.Optional;

import com.coinmill.dto.ExchangeRateDto;
import com.coinmill.entity.ExchangeRate;

public interface ExchangeRateService {

    List<ExchangeRate> listAllExchangeRate();

    ExchangeRate createExchangeRate(ExchangeRateDto exchangeRateDto);

    int deleteExchangeRate(Long id);

    List<ExchangeRate> listExchangeRateByPage(int pageNum, int pageSize);
    
    List<ExchangeRate> listExchangeRateByCode(String chartName, String currencyCode);
    
    List<ExchangeRate> listExchangeRateByName(String chartName, String currencyName);    

    ExchangeRate getExchangeRate(Long id);	
	
}
