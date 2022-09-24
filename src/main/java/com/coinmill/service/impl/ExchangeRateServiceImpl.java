package com.coinmill.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coinmill.dao.ExchangeRateRepository;
import com.coinmill.dto.ExchangeRateDto;
import com.coinmill.entity.ExchangeRate;
import com.coinmill.service.ExchangeRateService;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class ExchangeRateServiceImpl implements ExchangeRateService{

	@Autowired
	ExchangeRateRepository exchangeRateRepository;
	
	@Override
    public List<ExchangeRate> listAllExchangeRate(){
		List<ExchangeRate> listExchangeRate = new ArrayList<>();
		listExchangeRate = exchangeRateRepository.findAll();
		return listExchangeRate;
    }

	@SuppressWarnings("null")
	@Override
	@Transactional
    public ExchangeRate createExchangeRate(ExchangeRateDto exchangeRateDto) {
		ExchangeRate createExchangeRate = null;
		
		createExchangeRate.setChartName(exchangeRateDto.getChartName());
		createExchangeRate.setCurrencyCode(exchangeRateDto.getCurrencyCode());
		createExchangeRate.setCurrencyName(exchangeRateDto.getCurrencyName());
		createExchangeRate.setCurrencyRate(exchangeRateDto.getCurrencyRate());		
		createExchangeRate.setDisclaimer(exchangeRateDto.getDisclaimer());
		createExchangeRate.setUpdated(exchangeRateDto.getUpdated());
		createExchangeRate.setUpdatedIso(exchangeRateDto.getUpdatedIso());
		createExchangeRate.setUpdateduk(exchangeRateDto.getUpdateduk());
		
		
		if (exchangeRateDto.getUpdated().length()>0) {
			String dateString = exchangeRateDto.getUpdated().replace(" UTC", "");
        	SimpleDateFormat sdf = new SimpleDateFormat ("MMM dd, yyyyy HH:mm:ss"); 
        	Date t; 
        	try { 
        		t = sdf.parse(dateString); 
        		createExchangeRate.setRateTime(t);
        	} catch (Exception e) { 
        		log.info("Unparseable using " + sdf); 
        	}
		}
		ExchangeRate saveExchangeRate = exchangeRateRepository.save(createExchangeRate);			
		
		return saveExchangeRate;		
	}

	@Override
	@Transactional
    public int deleteExchangeRate(Long id) {;
    	int i = 0;
    	try {
    		exchangeRateRepository.deleteById(id);
    		i = 1;
    	}catch (Exception e) {
    		log.debug("deleteExchangeRate: " + e.getMessage());
    		i = -1;
    	}
        return i;		
	}

	@Override
    public List<ExchangeRate> listExchangeRateByPage(int pageNum, int pageSize){
    	Pageable pageable = PageRequest.of(pageNum-1, pageSize);    	 
    	Page<ExchangeRate> page = exchangeRateRepository.findAll(pageable);
    	return page.getContent();		
	}
    
	@Override
    public List<ExchangeRate> listExchangeRateByCode(String chartName, String currencyCode){
		List<ExchangeRate> listExchangeRate = new ArrayList<>();
		listExchangeRate = exchangeRateRepository.findByCode(chartName, currencyCode);
		return listExchangeRate;		
	}
    
	@Override
    public List<ExchangeRate> listExchangeRateByName(String chartName, String currencyName){
		List<ExchangeRate> listExchangeRate = new ArrayList<>();
		listExchangeRate = exchangeRateRepository.findByName(chartName, currencyName);
		return listExchangeRate;		
	}

	@Override
    public ExchangeRate getExchangeRate(Long id) {
		Optional<ExchangeRate> optExchangeRate = exchangeRateRepository.findById(id);
    	if (!optExchangeRate.isEmpty()) {
    		ExchangeRate exchangeRate = optExchangeRate.get();
    		return exchangeRate;
    	}else {
    		return null;
    	}	
	}
	
}
