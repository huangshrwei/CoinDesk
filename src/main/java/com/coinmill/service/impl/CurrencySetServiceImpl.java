package com.coinmill.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coinmill.dao.CurrencySetRepository;
import com.coinmill.dto.CurrencySetDto;
import com.coinmill.entity.CurrencySet;
import com.coinmill.service.CurrencySetService;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class CurrencySetServiceImpl implements CurrencySetService{
	
	@Autowired
	private CurrencySetRepository currencySetRepository;

	@Override
    public List<CurrencySet> listAllCurrencySet(){
    	List<CurrencySet> currencySetList = new ArrayList<>();
    	currencySetList = currencySetRepository.findAll();    	
    	return currencySetList;
    }

	@Override
	@Transactional
    public CurrencySet createCurrencySet(CurrencySetDto currencySetDto) {
    	CurrencySet currencySet = new CurrencySet();
    	CurrencySet currencySetResult = new CurrencySet();
    	currencySet.setCurrencyCode(currencySetDto.getCurrencyCode());
    	currencySet.setCurrencyName(currencySetDto.getCurrencyName());
    	try {
    		currencySetResult = currencySetRepository.save(currencySet);   	
    	}catch (Exception e) {
    		log.info("createCurrencySet Error: " + e.getMessage());
    	}
    	return currencySetResult;
	}

	@Override
	@Transactional
    public CurrencySet updateCurrencySet(String id, CurrencySetDto currencySetDto) {
    	CurrencySet currencySet = new CurrencySet();
    	CurrencySet currencySetResult = new CurrencySet();    	
    	currencySet.setCurrencyCode(id);
    	currencySet.setCurrencyName(currencySetDto.getCurrencyName());
    	try {
    		currencySetResult = currencySetRepository.save(currencySet);    	
    	}catch (Exception e) {
    		log.info("updateCurrencySet Error: " + e.getMessage());
    	}
    	return currencySetResult;
	}

	@Override
	@Transactional
    public int deleteCurrencySet(String id) {
    	int i = 0;
    	try {
    		currencySetRepository.deleteById(id);
    		i = 1;
    	}catch (Exception e) {
    		log.debug("deleteCurrencySet: " + e.getMessage());
    		i = -1;
    	}
        return i;
	};

	@Override
    public List<CurrencySet> listCurrencySet(int pageNum, int pageSize){
    	Pageable pageable = PageRequest.of(pageNum-1, pageSize);    	 
    	Page<CurrencySet> page = currencySetRepository.findAll(pageable);
    	return page.getContent();
	}

	@Override
    public CurrencySet getCurrencySet(String id) {
    	Optional<CurrencySet> optCurrencySet = currencySetRepository.findById(id);
    	if (!optCurrencySet.isEmpty()) {
    		CurrencySet currencySet = optCurrencySet.get();
    		return currencySet;
    	}else {
    		return null;
    	}
	}	
	
}
