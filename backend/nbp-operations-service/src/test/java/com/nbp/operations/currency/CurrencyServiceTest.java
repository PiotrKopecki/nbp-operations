package com.nbp.operations.currency;

import com.nbp.operations.api.NbpApiService;
import com.nbp.operations.api.dto.AverageExchangeRateDto;
import com.nbp.operations.api.dto.BuySellExchangeRateDto;
import com.nbp.operations.api.dto.RateBuySellDto;
import com.nbp.operations.api.dto.RateDto;
import com.nbp.operations.currency.dto.MinMaxExchangeRateDto;

import com.nbp.operations.exception.BusinessException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CurrencyServiceTest {
	@Autowired
	private CurrencyService currencyService;

	@MockBean
	private NbpApiService nbpApiService;

	@Test
	public void givenCurrencyCodeAndTopCount_whenCollectMinMaxAverageExchangeRate_thenOk() {
		RateDto rateDto = new RateDto();
		rateDto.setMid(3.14);
		AverageExchangeRateDto averageExchangeRateDto = new AverageExchangeRateDto();
		averageExchangeRateDto.setRates(List.of(rateDto));
		when(nbpApiService.getLastAverageExchangeRates(any(), any(), any())).thenReturn(averageExchangeRateDto);

		MinMaxExchangeRateDto minMaxExchangeRateDto = currencyService.collectMinMaxAverageExchangeRate("EUR", 5);

		assertThat(minMaxExchangeRateDto.getMax()).isEqualTo(3.14);
		assertThat(minMaxExchangeRateDto.getMin()).isEqualTo(3.14);
	}

	@Test
	public void givenWrongCurrencyCode_whenCollectMinMaxAverageExchangeRate_thenThrowsException() {
		BusinessException e = assertThrows(BusinessException.class, () -> currencyService.collectMinMaxAverageExchangeRate("wrong", 5));
		assertThat(e.getHttpStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
		assertThat(e.getMessage()).isEqualTo("Incorrect currency code");
	}

	@Test
	public void givenCurrencyCodeAndTopCount_whenCollectMajorDiffrence_thenOk() {
		RateBuySellDto rateBuySellDto = new RateBuySellDto();
		RateBuySellDto rateBuySellDto2 = new RateBuySellDto();
		rateBuySellDto.setAsk(1.0);
		rateBuySellDto.setBid(0.5);
		rateBuySellDto2.setAsk(2.0);
		rateBuySellDto2.setBid(0.5);
		BuySellExchangeRateDto buySellExchangeRateDto = new BuySellExchangeRateDto();
		buySellExchangeRateDto.setRates(List.of(rateBuySellDto, rateBuySellDto2));

		when(nbpApiService.getLastExchangeRates(any(), any(), any())).thenReturn(buySellExchangeRateDto);

		Double majorDiffrence = currencyService.collectMajorDiffrenceBetweenExRates("EUR", 5);

		assertThat(majorDiffrence).isEqualTo(1.5);
	}
}
