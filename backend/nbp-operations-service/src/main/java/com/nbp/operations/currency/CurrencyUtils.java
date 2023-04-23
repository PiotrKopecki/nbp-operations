package com.nbp.operations.currency;

import java.util.List;

public class CurrencyUtils {
	public static List<String> currencyCodes = List.of(
			"AUD","THB","BRL","BGN","CAD","CLP","CZK","DKK","EUR","HUF","HKD","UAH",
			"ISK","INR","MYR","MXN","ILS","NZD","NOK","PHP","GBP","ZAR","RON",
			"IDR","SGD","SEK","CHF","TRY","USD","KRW","JPY","CNY","XDR"
	);
	public static String tableForAverageCurrency = "a"; //from task description
	public static String tableForBuySellCurrency = "c";

	public static boolean isCurrencyValid(String currencyCode) {
		return currencyCodes.contains(currencyCode.toUpperCase());
	}
}
