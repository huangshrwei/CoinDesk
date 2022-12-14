package com.coinmill.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ExchangeRateDto {

    private String chartName;

    private String disclaimer;
    
    private Date rateTime;      
    
    private String updated;
    
    private String updatedIso;
    
    private String updateduk;    
    
    private String currencyCode;
    
    private String currencyName;       
        
    private Double currencyRate;  	
	
}
