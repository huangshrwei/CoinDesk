package com.coinmill.service;

import java.util.List;
import java.util.Optional;

import com.coinmill.dto.ExchangeRateDto;
import com.coinmill.entity.ExchangeRate;

public interface ExchangeRateService {
	
	//列出所有資料
    List<ExchangeRate> listAllExchangeRate();

    //新增資料
    ExchangeRate createExchangeRate(ExchangeRateDto exchangeRateDto);

    //刪除資料
    int deleteExchangeRate(Long id);

    //列出第N頁資料, 並列出該頁筆數
    List<ExchangeRate> listExchangeRateByPage(int pageNum, int pageSize);
    
    //依照chartName, currencyCode抓取資料
    List<ExchangeRate> listExchangeRateByCode(String chartName, String currencyCode);
    
    //依照chartName, currencyName抓取資料
    List<ExchangeRate> listExchangeRateByName(String chartName, String currencyName);    

    //依照id抓取資料
    ExchangeRate getExchangeRate(Long id);	
	
}
