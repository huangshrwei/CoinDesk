package com.coinmill.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

//for json data
@Data
public class CointimeDto {
	
	@JsonProperty("updated")
	private String updated;
	
	@JsonProperty("updatedISO")
	private String updatedISO;
	
	@JsonProperty("updateduk")
	private String updateduk;
	
}
