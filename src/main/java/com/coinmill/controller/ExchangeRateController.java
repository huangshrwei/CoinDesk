package com.coinmill.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.coinmill.entity.ExchangeRate;
import com.coinmill.service.ExchangeRateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@Api(tags = "ExchangeRateController")
@Tag(name = "ExchangeRateController", description = "轉入資料查詢")
@RequestMapping("/exchangeRate")
@Slf4j
public class ExchangeRateController {

    @Autowired
    private ExchangeRateService exchangeRateService;    

    @ApiOperation(value = "取得轉入資料", notes = "列出所有轉入資料")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/listAll")
    public List<ExchangeRate> getAll() {
        return exchangeRateService.listAllExchangeRate();
    }  		
	
}
