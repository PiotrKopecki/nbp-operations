package com.nbp.operations.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class NbpApiServiceTest {

	@Autowired
	private NbpApiService nbpApiService;

	@MockBean
	private NbpApi nbpApi;

	@Test
	public void givenValidDate_whenIsValidDate_thenTrue() {
		boolean result = nbpApiService.isValidDate("2022-12-12");
		assertThat(result).isEqualTo(true);
	}

	@Test
	public void givenInvalidDate_whenIsValidDate_thenFalse() {
		boolean result = nbpApiService.isValidDate("xxx");
		assertThat(result).isEqualTo(false);
	}
}
