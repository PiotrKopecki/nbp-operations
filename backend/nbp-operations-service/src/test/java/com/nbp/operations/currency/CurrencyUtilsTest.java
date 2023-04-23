package com.nbp.operations.currency;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CurrencyUtilsTest {

	@Test
	public void givenValidCurrencyCode_whenIsCurrencyValid_thenTrue() {
		assertThat(CurrencyUtils.isCurrencyValid("eur")).isEqualTo(true);
		assertThat(CurrencyUtils.isCurrencyValid("KRW")).isEqualTo(true);
	}

	@Test
	public void givenInvalidCurrencyCode_whenIsCurrencyValid_thenFalse() {
		assertThat(CurrencyUtils.isCurrencyValid("123")).isEqualTo(false);
		assertThat(CurrencyUtils.isCurrencyValid("kko")).isEqualTo(false);
	}
}
