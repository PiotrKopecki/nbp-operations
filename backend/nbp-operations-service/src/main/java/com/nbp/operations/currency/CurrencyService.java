package com.nbp.operations.currency;

import com.nbp.operations.api.NbpApiService;
import com.nbp.operations.api.dto.AverageExchangeRateDto;
import com.nbp.operations.api.dto.BuySellExchangeRateDto;
import com.nbp.operations.currency.dto.MinMaxExchangeRateDto;
import com.nbp.operations.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CurrencyService {
	private final NbpApiService nbpApiService;

	public Double collectAverageExchangeRate(String currencyCode, String date) {
		if (!CurrencyUtils.isCurrencyValid(currencyCode)) {
			throw new BusinessException("Incorrect currency code", HttpStatus.BAD_REQUEST);
		}
		AverageExchangeRateDto averageExchangeRateDto =
				nbpApiService.getAverageExchangeRateByDate(CurrencyUtils.tableForAverageCurrency, currencyCode, date);
		if (averageExchangeRateDto == null) {
			return -1.0;
		}
		return averageExchangeRateDto.getRates().get(0).getMid();
	}

	public MinMaxExchangeRateDto collectMinMaxAverageExchangeRate(String currencyCode, Integer topCount) {
		if (!CurrencyUtils.isCurrencyValid(currencyCode)) {
			throw new BusinessException("Incorrect currency code", HttpStatus.BAD_REQUEST);
		}
		MinMaxExchangeRateDto minMaxExchangeRateDto = new MinMaxExchangeRateDto();
		AverageExchangeRateDto averageExchangeRateDto =
				nbpApiService.getLastAverageExchangeRates(CurrencyUtils.tableForAverageCurrency, currencyCode, topCount);
		if (averageExchangeRateDto == null) {
			return null;
		}

		averageExchangeRateDto.getRates().forEach(rateDto -> {
			Double mid = rateDto.getMid();
			if (mid > minMaxExchangeRateDto.getMax()) {
				minMaxExchangeRateDto.setMax(mid);
			}
			if (mid < minMaxExchangeRateDto.getMin() || minMaxExchangeRateDto.getMin().equals(0.0)) {
				minMaxExchangeRateDto.setMin(mid);
			}
		});
		return minMaxExchangeRateDto;
	}

	public Double collectMajorDiffrenceBetweenExRates(String currencyCode, Integer topCount) {
		if (!CurrencyUtils.isCurrencyValid(currencyCode)) {
			throw new BusinessException("Incorrect currency code", HttpStatus.BAD_REQUEST);
		}
		BuySellExchangeRateDto buySellExchangeRateDto =
				nbpApiService.getLastExchangeRates(CurrencyUtils.tableForBuySellCurrency, currencyCode, topCount);
		if (buySellExchangeRateDto == null) {
			return -1.0;
		}

		return buySellExchangeRateDto.getRates()
				.stream()
				.mapToDouble(rate -> Math.abs(rate.getAsk() - rate.getBid()))
				.max()
				.orElseThrow(() -> new BusinessException("There was a problem with collecting max diffrence", HttpStatus.BAD_REQUEST));
	}
}
