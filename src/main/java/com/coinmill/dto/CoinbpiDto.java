package com.coinmill.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

//for json data
@Data
public class CoinbpiDto<T> {

	@JsonProperty("USD") 
	private CodemillDto usd;
	
	@JsonProperty("GBP")
	private CodemillDto gbp;
	
	@JsonProperty("EUR")
	private CodemillDto eur;
	
}
