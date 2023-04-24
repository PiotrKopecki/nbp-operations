package com.nbp.operations.api;

import com.nbp.operations.api.dto.AverageExchangeRateDto;
import com.nbp.operations.api.dto.BuySellExchangeRateDto;
import com.nbp.operations.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class NbpApiService {
	private final NbpApi nbpApi;

	public boolean isValidDate(String date) { //date format: RRRR-MM-DD
		if (date.length() != 10) {
			return false;
		}
		for(int i = 0; i < date.length(); i++) {
			if (i != 4 && i != 7 ) {
				if (!Character.isDigit(date.charAt(i))) {
					return false;
				}
			}
			else if (date.charAt(i) != '-') {
				return false;
			}
		}
		return true;
	}

	public AverageExchangeRateDto getAverageExchangeRateByDate(String table, String currencyCode, String date) {
		if(!isValidDate(date)) {
			throw new BusinessException("Invalid date format", HttpStatus.BAD_REQUEST);
		}
		AverageExchangeRateDto averageExchangeRateDto;
		try{
			averageExchangeRateDto = nbpApi.getAverageExchangeRateByDate(table, currencyCode, date);
		} catch (Exception e) {
			log.warn(e.getMessage());
			return null;
		}
		return averageExchangeRateDto;
	}

	public AverageExchangeRateDto getLastAverageExchangeRates(String table, String currencyCode, Integer topCount) {
		AverageExchangeRateDto averageExchangeRateDto;
		try{
			averageExchangeRateDto = nbpApi.getLastAverageExchangeRates(table, currencyCode, topCount);
		} catch (Exception e) {
			log.warn(e.getMessage());
			return null;
		}
		return averageExchangeRateDto;
	}

	public BuySellExchangeRateDto getLastExchangeRates(String table, String currencyCode, Integer topCount) {
		BuySellExchangeRateDto buySellExchangeRateDto;
		try{
			buySellExchangeRateDto = nbpApi.getLastExchangeRates(table, currencyCode, topCount);
		} catch (Exception e) {
			log.warn(e.getMessage());
			return null;
		}
		return buySellExchangeRateDto;
	}
}
