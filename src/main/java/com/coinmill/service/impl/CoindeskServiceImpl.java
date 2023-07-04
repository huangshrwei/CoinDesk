package com.coinmill.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.coinmill.api.CoindeskUtil;
import com.coinmill.dto.CoindeskDto;
import com.coinmill.dto.ExchangePriceDto;
import com.coinmill.dto.ExchangeRateDto;
import com.coinmill.entity.CurrencySet;
import com.coinmill.entity.ExchangeRate;
//import com.coinmill.entity.CurrencyRate;
import com.coinmill.service.CoindeskService;
import com.coinmill.service.CurrencySetService;
import com.coinmill.service.ExchangeRateService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CoindeskServiceImpl implements CoindeskService{
	
	@Autowired
	CurrencySetService currencySetService;
	
	@Autowired
	ExchangeRateService exchangeRateService; 	
	
	@Lazy
	List<CurrencySet> listCurrencySet = new ArrayList<>();
	
	List<ExchangeRateDto> listExchangeRateDto = new ArrayList<>();
	
    private void getAll(){
    	log.info("CurrencySetService init....");
    	listCurrencySet = currencySetService.listAllCurrencySet();
    	if (listExchangeRateDto.size() == 0) {
    		List<ExchangeRate> listExchangeRate = exchangeRateService.listAllExchangeRate();
    		for (int i = 0; i < listExchangeRate.size(); i ++) {
    			ExchangeRateDto exchangeRateDto = new ExchangeRateDto();
    			exchangeRateDto.setChartName(listExchangeRate.get(i).getChartName());
    			exchangeRateDto.setCurrencyCode(listExchangeRate.get(i).getCurrencyCode());
    			exchangeRateDto.setCurrencyName(listExchangeRate.get(i).getChartName());
    			exchangeRateDto.setCurrencyRate(listExchangeRate.get(i).getCurrencyRate());
    			listExchangeRateDto.add(exchangeRateDto);
    		}
    	}
    }
	
    @Override
    public boolean checkUrl(String url) {
		String regexp = "((http|ftp|https)://)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?";;
		
		boolean isMatch = Pattern.matches(regexp, url);
    	return isMatch;
    }
    
    @Override	
    public List<ExchangeRateDto> getCoindesk(String url) throws Exception {    	    
    	
    	try {
    		String json =  CoindeskUtil.stream(url);
    		//log.info("json: "+json);
    		CoindeskDto<CoindeskDto> coindeskDto = CoindeskUtil.convert(json, CoindeskDto.class);
    		//log.info("coindeskDto: "+json);
    		ExchangeRateDto exchangeRateUsd = new ExchangeRateDto();
    		ExchangeRateDto exchangeRateEur = new ExchangeRateDto();
    		ExchangeRateDto exchangeRateGbp = new ExchangeRateDto();
    		
    		if (coindeskDto.getTime().getUpdated().length()>0) {
    			String dateString = coindeskDto.getTime().getUpdated().replace(" UTC", "");
            	SimpleDateFormat sdf = new SimpleDateFormat ("MMM dd, yyyy HH:mm:ss", Locale.ENGLISH); 
            	Date t = new Date(); 
            	try { 
            		t = sdf.parse(dateString);
            		exchangeRateUsd.setRateTime(t);
            		exchangeRateGbp.setRateTime(t);
            		exchangeRateEur.setRateTime(t);
            	} catch (Exception e) { 
            		log.info("Unparseable using " + sdf); 
            	}
    		}
    		
    		
    		//usd
    		exchangeRateUsd.setChartName(coindeskDto.getChartName());
    		exchangeRateUsd.setCurrencyCode(coindeskDto.getBpi().getUsd().getCode());
    		exchangeRateUsd.setCurrencyName(getCoinName(coindeskDto.getBpi().getUsd().getCode()));
    		exchangeRateUsd.setCurrencyRate(Double.valueOf(coindeskDto.getBpi().getUsd().getRate_float()));
    		exchangeRateUsd.setDisclaimer(coindeskDto.getDisclaimer());
    		exchangeRateUsd.setUpdated(coindeskDto.getTime().getUpdated());
    		exchangeRateUsd.setUpdatedIso(coindeskDto.getTime().getUpdatedISO());
    		exchangeRateUsd.setUpdateduk(coindeskDto.getTime().getUpdateduk());

    		//gbp
    		exchangeRateGbp.setChartName(coindeskDto.getChartName());
    		exchangeRateGbp.setCurrencyCode(coindeskDto.getBpi().getGbp().getCode());
    		exchangeRateGbp.setCurrencyName(getCoinName(coindeskDto.getBpi().getGbp().getCode()));
    		exchangeRateGbp.setCurrencyRate(Double.valueOf(coindeskDto.getBpi().getGbp().getRate_float()));
    		exchangeRateGbp.setDisclaimer(coindeskDto.getDisclaimer());
    		exchangeRateGbp.setUpdated(coindeskDto.getTime().getUpdated());
    		exchangeRateGbp.setUpdatedIso(coindeskDto.getTime().getUpdatedISO());
    		exchangeRateGbp.setUpdateduk(coindeskDto.getTime().getUpdateduk());
    		
    		//eur
    		exchangeRateEur.setChartName(coindeskDto.getChartName());
    		exchangeRateEur.setCurrencyCode(coindeskDto.getBpi().getEur().getCode());
    		exchangeRateEur.setCurrencyName(getCoinName(coindeskDto.getBpi().getEur().getCode()));
    		exchangeRateEur.setCurrencyRate(Double.valueOf(coindeskDto.getBpi().getEur().getRate_float()));
    		exchangeRateEur.setDisclaimer(coindeskDto.getDisclaimer());
    		exchangeRateEur.setUpdated(coindeskDto.getTime().getUpdated());
    		exchangeRateEur.setUpdatedIso(coindeskDto.getTime().getUpdatedISO());
    		exchangeRateEur.setUpdateduk(coindeskDto.getTime().getUpdateduk());  

    		listExchangeRateDto.add(exchangeRateGbp);
    		listExchangeRateDto.add(exchangeRateUsd);    		
    		listExchangeRateDto.add(exchangeRateEur);   
    		
    		exchangeRateService.createExchangeRate(exchangeRateGbp);
    		exchangeRateService.createExchangeRate(exchangeRateEur);
    		exchangeRateService.createExchangeRate(exchangeRateUsd);
    		
    		return listExchangeRateDto;
    		//return coindeskDto;
    	}catch (IOException e){
    		log.warn("異常:{}", e.getMessage());
    		return null;
    	}

    }
	
    
    @Override	
    public String getCoinName(String coinCode) {
    	getAll();
        List<CurrencySet> findList = listCurrencySet.stream().filter(item -> item.getCurrencyCode().equals(coinCode)).collect(Collectors.toList());
        //log.info("findList: " + findList.toString());
        if(findList.size()>0){
            return findList.get(0).getCurrencyName();
        }
        return null;    	
    }
    
    @Override
    public Double getCoinPrice(String coinCode, Double codePrice) {
    	getAll();
    	if (listExchangeRateDto.isEmpty()) {
    		return null;
    	}
    	
        List<ExchangeRateDto> findList = listExchangeRateDto.stream().filter(item -> item.getCurrencyCode().equals(coinCode)).collect(Collectors.toList());
        //log.info("findList: " + findList.toString());
        if(findList.size()>0){
        	return (codePrice/findList.get(0).getCurrencyRate());
        }
        return null;
    }
    
    @Override
    public ExchangePriceDto exchangePriceDto(String coinCode, Double codePrice) {
    	getAll();
    	ExchangePriceDto exchangePriceDto = new ExchangePriceDto();
    	
    	if (listExchangeRateDto.isEmpty()) {
    		return null;
    	}
    	
        List<ExchangeRateDto> findList = listExchangeRateDto.stream().filter(item -> item.getCurrencyCode().equals(coinCode)).collect(Collectors.toList());
        //log.info("findList: " + findList.toString());
        if(findList.size()>0){
        	
        	exchangePriceDto.setChartName(findList.get(0).getChartName());
        	exchangePriceDto.setCurrencyCode(coinCode);
        	exchangePriceDto.setCurrencyRate(findList.get(0).getCurrencyRate());
        	exchangePriceDto.setCurrencyName(findList.get(0).getCurrencyName());
        	exchangePriceDto.setOriginPrice(codePrice);
        	exchangePriceDto.setRateTime(findList.get(0).getRateTime());
        	exchangePriceDto.setExchangePrice((codePrice/findList.get(0).getCurrencyRate()));        	
        	return exchangePriceDto;
        }
        return null;    	    	

    }    
    
    /*
    @Override
    public Optional<CurrencyRate> createCoinRate(CoindeskDto coindeskDto){
    	Optional<CurrencyRate> optCurrencyRate = null;
    	return optCurrencyRate;
    }
    */	
	
}
