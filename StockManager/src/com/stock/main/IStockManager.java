package com.stock.main;

import java.math.BigDecimal;

import com.stock.fileio.StockDetailsPojo;

public interface IStockManager {

	public String getStockDetailsAsString(String stockSymbol);

	public StockDetailsPojo getStockDetails(String stockSymbol);

	public BigDecimal getStockPrice(String stockSymbol);

	public BigDecimal getStockYearTargetPrice(String stockSymbol);

	public BigDecimal getStockYearLow(String stockSymbol);

	public BigDecimal getStockYearHigh(String stockSymbol);
}
