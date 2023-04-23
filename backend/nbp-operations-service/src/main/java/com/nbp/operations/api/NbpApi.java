package com.nbp.operations.api;

import com.nbp.operations.api.dto.AverageExchangeRateDto;
import com.nbp.operations.api.dto.BuySellExchangeRateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "NbpApi", url = "${nbp.api.url}")
public interface NbpApi {

	@GetMapping("exchangerates/rates/{table}/{currencyCode}/{date}/")
	AverageExchangeRateDto getAverageExchangeRateByDate(@PathVariable String table, @PathVariable String currencyCode, @PathVariable String date);

	@GetMapping("exchangerates/rates/{table}/{currencyCode}/last/{topCount}/")
	AverageExchangeRateDto getLastAverageExchangeRates(@PathVariable String table, @PathVariable String currencyCode, @PathVariable Integer topCount);

	@GetMapping("exchangerates/rates/{table}/{currencyCode}/last/{topCount}/")
	BuySellExchangeRateDto getLastExchangeRates(@PathVariable String table, @PathVariable String currencyCode, @PathVariable Integer topCount);
}
