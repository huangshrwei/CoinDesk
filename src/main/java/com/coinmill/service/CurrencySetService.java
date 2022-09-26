package com.coinmill.service;

import java.util.List;

import com.coinmill.dto.CurrencySetDto;
import com.coinmill.entity.CurrencySet;

public interface CurrencySetService {
	
	//列出所有資料
    List<CurrencySet> listAllCurrencySet();

    //新增資料
    CurrencySet createCurrencySet(CurrencySetDto currencySetDto);

    //更新資料
    CurrencySet updateCurrencySet(String id, CurrencySetDto currencySetDto);

    //刪除資料
    int deleteCurrencySet(String id);

    //列出第N頁資料, 並列出該頁筆數
    List<CurrencySet> listCurrencySet(int pageNum, int pageSize);

    //依照id抓取資料
    CurrencySet getCurrencySet(String id);

}
