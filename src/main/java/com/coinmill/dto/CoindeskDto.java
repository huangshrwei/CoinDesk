package com.coinmill.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

//for json data
@Data
public class CoindeskDto<T> {

	@JsonProperty("time")
	private CointimeDto time;
	
	@JsonProperty("disclaimer")
	private String disclaimer;
	
	@JsonProperty("chartName")
	private String chartName;
	
	@JsonProperty("bpi")
	private CoinbpiDto<T> bpi;
}
