package com.nbp.operations.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BuySellExchangeRateDto {
	private String table;
	private String currency;
	private String code;
	private List<RateBuySellDto> rates;
}
