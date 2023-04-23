package com.nbp.operations.currency;

import com.nbp.operations.currency.dto.MinMaxExchangeRateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/currency")
public class CurrencyController {
	private final CurrencyService currencyService;

	@GetMapping("/{currencyCode}/average/exchange-rate")
	public ResponseEntity<Double> getAverageExchangeRate(@PathVariable String currencyCode, @RequestParam String date) {
		Double midValue = currencyService.collectAverageExchangeRate(currencyCode, date);
		return new ResponseEntity<>(midValue, HttpStatus.OK);
	}

	@GetMapping("/{currencyCode}/top/{size}/average/exchange-rate")
	public ResponseEntity<MinMaxExchangeRateDto> getMinMaxAverageExchangeRate(@PathVariable String currencyCode, @PathVariable Integer size) {
		MinMaxExchangeRateDto minMaxExchangeRateDto = currencyService.collectMinMaxAverageExchangeRate(currencyCode, size);
		return new ResponseEntity<>(minMaxExchangeRateDto, HttpStatus.OK);
	}

	@GetMapping("/{currencyCode}/top/{size}/exchange-rate")
	public ResponseEntity<Double> getMajorDiffrence(@PathVariable String currencyCode, @PathVariable Integer size) {
		Double majorDiffrence = currencyService.collectMajorDiffrenceBetweenExRates(currencyCode, size);
		return new ResponseEntity<>(majorDiffrence, HttpStatus.OK);
	}
}
