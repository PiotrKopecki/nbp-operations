package com.nbp.operations.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RateBuySellDto {
	private String no;
	private String effectiveDate;
	private Double bid;
	private Double ask;
}
