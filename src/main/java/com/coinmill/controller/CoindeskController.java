package com.coinmill.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.coinmill.api.CommonResult;
import com.coinmill.api.ResultCode;
import com.coinmill.api.CoindeskUtil;
import com.coinmill.dto.CoindeskDto;
import com.coinmill.dto.ExchangePriceDto;
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
		
	List<ExchangeRateDto> listExchangeRateDto = new ArrayList<>();
	
    @ApiOperation("抓取匯率資料")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
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
    	listExchangeRateDto = coindeskService.getCoindesk(url);    	
    	
    	CommonResult commonResult = null;
    	
    	if (!coindeskService.checkUrl(url)) {
    		return commonResult.failed(ResultCode.HTTP_FAILED);
    	}    	
    	
    	if (listExchangeRateDto!=null) {
    		return commonResult.success(listExchangeRateDto);
    	}else {
    		return commonResult.failed();
    	}
    	           	
    }
    
    @ApiOperation("幣別匯率轉換")
    @RequestMapping(value = "/exchange", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<ExchangePriceDto> listExchangePriceDto(
			  @ApiParam(
	    			    name = "code",
	    			    type = "String",
	    			    value = "幣別代碼",
	    			    defaultValue = "USD",
	    			    required = true)
	    	  @RequestParam String code,
			  @ApiParam(
	    			    name = "codePrice",
	    			    type = "Double",
	    			    value = "幣別金額",
	    			    defaultValue = "0.00",
	    			    required = true)
	    	  @RequestParam Double codePrice) 
    {
    	if (listExchangeRateDto.isEmpty()) {
    		return CommonResult.failed("請先抓取匯率資料");    		
    	}
    	
        ExchangePriceDto exchangePriceDto = coindeskService.exchangePriceDto(code, codePrice);
        return CommonResult.success(exchangePriceDto);
    }    
    
}
