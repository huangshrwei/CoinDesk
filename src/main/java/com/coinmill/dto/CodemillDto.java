package com.coinmill.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


//for json data
@Data
public class CodemillDto {

	@JsonProperty("code")
	private String code;
	
	@JsonProperty("symbol")
	private String symbol;
	
	@JsonProperty("rate")
	private String rate;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("rate_float")
	private double rate_float;
}
