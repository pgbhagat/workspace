package com.stock.query;

import java.util.List;

public class QueryFormatter {

	private static final String base_url = "https://query.yahooapis.com/v1/public/yql";
	private static final String query_start_part = "?q=select%20symbol,LastTradePriceOnly,OneyrTargetPrice,YearLow,YearHigh%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22";
	private static final String query_end_part = "%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";

	public String getStockQuery(List<String> stockSymbols) {
		StringBuilder inClause = new StringBuilder();
		for (String symbol : stockSymbols) {
			inClause.append(symbol).append(",");
		}
		if (inClause.toString().isEmpty()) {
			return null;
		} else {
			return base_url + query_start_part + inClause.toString() + query_end_part;
		}
	}

	public String getStockQuery(String symbol) {
		return base_url + query_start_part + symbol + query_end_part;
	}
}
