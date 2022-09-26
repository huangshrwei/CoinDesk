package com.coinmill.service;


import java.util.List;

import com.coinmill.dto.ExchangePriceDto;
import com.coinmill.dto.ExchangeRateDto;

public interface CoindeskService {

	//將網頁內容放入List<ExchangeRateDto>中
	List<ExchangeRateDto> getCoindesk(String url) throws Exception;
	
	//use coincode get coinname
	String getCoinName(String coinCode);
	
	//抓取幣別匯率
	Double getCoinPrice(String coinCode, Double codePrice);	
	
	//檢查網址格式
	boolean checkUrl(String url);
	
	//匯率換算
	ExchangePriceDto exchangePriceDto(String coinCode, Double codePrice);

	
}
