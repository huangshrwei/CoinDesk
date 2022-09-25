package com.coinmill.controller;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.coinmill.api.CommonResult;
import com.coinmill.domain.CoindeskUtil;
import com.coinmill.dto.CoindeskDto;
import com.coinmill.dto.ExchangeRateDto;
import com.coinmill.entity.CurrencySet;
import com.coinmill.service.CoindeskService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@Api(tags = "CoindeskController")
@Tag(name = "CoindeskController", description = "匯率資料")
@RequestMapping("/coindesk")
@Slf4j
public class CoindeskController {

	@Autowired
	CoindeskService coindeskService;
	
    @ApiOperation("抓取匯率資料")
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<List<ExchangeRateDto>> createProducts(
			  @ApiParam(
	    			    name = "url",
	    			    type = "String",
	    			    value = "網址",
	    			    defaultValue = "",
	    			    required = true)
	    	  @RequestParam String url) throws Exception {    
    //public CommonResult<List<ExchangeRateDto>> createProducts() throws Exception {          	
    	
    	//String url = "https://api.coindesk.com/v1/bpi/currentprice.json"; //just a string
    	List<ExchangeRateDto> listExchangeRateDto = coindeskService.getCoindesk(url);    	
    	
    	CommonResult commonResult = null;
    	
    	if (listExchangeRateDto!=null) {
    		return commonResult.success(listExchangeRateDto);
    	}else {
    		return commonResult.failed();
    	}
    	
    	
        //String url = "https://api.coindesk.com/v1/bpi/currentprice.json"; //just a string
        
        //URL url = new URL("https://api.coindesk.com/v1/bpi/currentprice.json");    
        
    	//String urlString = "https://api.coindesk.com/v1/bpi/currentprice.json";
		
		//coindeskService.getCoindesk(urlString);
		
		//String json =  CoindeskUtil.stream(urlString);
				
		//CoindeskDto<CoindeskDto> coindeskDto = CoindeskUtil.convert(json, CoindeskDto.class);		
    }
	
    
    
}
