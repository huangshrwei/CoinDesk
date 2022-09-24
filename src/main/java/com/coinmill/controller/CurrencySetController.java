package com.coinmill.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.coinmill.api.CommonResult;
import com.coinmill.dto.CurrencySetDto;
import com.coinmill.entity.CurrencySet;
import com.coinmill.service.CurrencySetService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@Api(tags = "CurrencySetController")
@Tag(name = "CurrencySetController", description = "幣別設定")
@RequestMapping("/currencySet")
@Slf4j
public class CurrencySetController {

	    @Autowired
	    private CurrencySetService currencySetService;    

	    @ApiOperation(value = "取得幣別設定資料", notes = "列出所有幣別設定資料")
	    @ResponseStatus(HttpStatus.OK)
	    @GetMapping(value = "/listAll")
	    public List<CurrencySet> getAll() {
	        return currencySetService.listAllCurrencySet();
	    }  	
		
	    @ApiOperation("新增幣別設定")
	    @RequestMapping(value = "/create", method = RequestMethod.POST)
	    @ResponseBody
	    public CommonResult createCurrencySet(@RequestBody CurrencySetDto currencySetDto) {
	        CommonResult commonResult;
	        CurrencySet newCurrencySet = new CurrencySet();
	        newCurrencySet = currencySetService.createCurrencySet(currencySetDto);
	        if (newCurrencySet != null) {
	            commonResult = CommonResult.success(newCurrencySet);
	            log.debug("createCurrencySet success:{}", newCurrencySet);
	        } else {
	            commonResult = CommonResult.failed("操作失败");
	            log.debug("createCurrencySet failed:{}", currencySetDto);
	        }
	        return commonResult;
	    }

	    @ApiOperation("更新指定id幣別設定")
	    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	    @ResponseBody
	    public CommonResult updateCurrencySet(@PathVariable("id") String id, @RequestBody CurrencySetDto currencySetDto, BindingResult result) {
	        CommonResult commonResult;
	        CurrencySet updCurrencySet = new CurrencySet();
	        updCurrencySet = currencySetService.updateCurrencySet(id, currencySetDto);
	        if (updCurrencySet != null) {
	            commonResult = CommonResult.success(currencySetDto);
	            log.debug("updateCurrencySet success:{}", currencySetDto);
	        } else {
	            commonResult = CommonResult.failed("操作失败");
	            log.debug("updateCurrencySet failed:{}", currencySetDto);
	        }
	        return commonResult;
	    }

	    @ApiOperation("删除指定id的幣別設定")
	    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	    @ResponseBody
	    public CommonResult deleteCurrencySet(@PathVariable("id") String id) {
	        int count = currencySetService.deleteCurrencySet(id);
	        if (count == 1) {
	            log.debug("deleteCurrencySet success :id={}", id);
	            return CommonResult.success(null);
	        } else {
	            log.debug("deleteCurrencySet failed :id={}", id);
	            return CommonResult.failed("操作失败");
	        }
	    }

	    @ApiOperation("分页查询幣別設定資料")
	    @RequestMapping(value = "/list", method = RequestMethod.GET)
	    @ResponseBody
	    public CommonResult<List<CurrencySet>> listProducts(@RequestParam(value = "pageNum", defaultValue = "1")
	                                                        @ApiParam("頁碼") Integer pageNum,
	                                                        @RequestParam(value = "pageSize", defaultValue = "3")
	                                                        @ApiParam("每頁數量") Integer pageSize) {
	        List<CurrencySet> currencySetList = currencySetService.listCurrencySet(pageNum, pageSize);
	        return CommonResult.success(currencySetList);
	    }

	    @ApiOperation("取得指定id幣別設定資料")
	    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	    @ResponseBody
	    public CommonResult<CurrencySet> products(@PathVariable("id") String id) {
	        return CommonResult.success(currencySetService.getCurrencySet(id));
	    }    
	       
	
}
