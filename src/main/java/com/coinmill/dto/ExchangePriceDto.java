package com.coinmill.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ExchangePriceDto {

    private String chartName;
    
    private Date rateTime;        
    
    private String currencyCode;
    
    private String currencyName;       
        
    private Double currencyRate;
    
    private Double originPrice;    
    
    private Double exchangePrice;    
	
}
