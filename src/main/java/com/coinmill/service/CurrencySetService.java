package com.coinmill.service;

import java.util.List;

import com.coinmill.dto.CurrencySetDto;
import com.coinmill.entity.CurrencySet;

public interface CurrencySetService {
	
    List<CurrencySet> listAllCurrencySet();

    CurrencySet createCurrencySet(CurrencySetDto currencySetDto);

    CurrencySet updateCurrencySet(String id, CurrencySetDto currencySetDto);

    int deleteCurrencySet(String id);

    List<CurrencySet> listCurrencySet(int pageNum, int pageSize);

    CurrencySet getCurrencySet(String id);

}
