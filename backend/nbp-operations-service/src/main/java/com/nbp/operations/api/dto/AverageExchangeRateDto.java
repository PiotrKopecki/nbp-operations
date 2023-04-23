package com.nbp.operations.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AverageExchangeRateDto {
	private String table;
	private String currency;
	private String code;
	private List<RateDto> rates;
}
