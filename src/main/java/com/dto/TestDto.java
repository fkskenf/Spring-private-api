package com.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TestDto {
	
	private long no;
	private String firstName;
	private String secondName;
	
//	@JsonProperty("pluse_map")
	private String pluseMap; // JSON Object를 stringify한 값
}
