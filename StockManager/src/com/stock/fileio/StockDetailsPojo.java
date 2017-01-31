package com.stock.fileio;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StockDetailsPojo {

	// naming conversion should match exactly to that one json result.. else
	// json mapper throws error..
	@JsonProperty
	private String symbol;
	@JsonProperty
	private BigDecimal LastTradePriceOnly;
	@JsonProperty
	private BigDecimal OneyrTargetPrice;
	@JsonProperty
	private BigDecimal YearHigh;
	@JsonProperty
	private BigDecimal YearLow;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public BigDecimal getLastTradePriceOnly() {
		return LastTradePriceOnly;
	}

	public void setLastTradePriceOnly(BigDecimal lastTradePriceOnly) {
		this.LastTradePriceOnly = lastTradePriceOnly;
	}

	public BigDecimal getOneyrTargetPrice() {
		return OneyrTargetPrice;
	}

	public void setOneyrTargetPrice(BigDecimal oneyrTargetPrice) {
		this.OneyrTargetPrice = oneyrTargetPrice;
	}

	public BigDecimal getYearHigh() {
		return YearHigh;
	}

	public void setYearHigh(BigDecimal yearHigh) {
		this.YearHigh = yearHigh;
	}

	public BigDecimal getYearLow() {
		return YearLow;
	}

	public void setYearLow(BigDecimal yearLow) {
		this.YearLow = yearLow;
	}

	private BigDecimal getYearTargetPrice() {
		return OneyrTargetPrice;
	}

	private BigDecimal getCurrentPrice() {
		return LastTradePriceOnly;
	}

	public String getStockSymbol() {
		return symbol;
	}

	public String toCSVString() {
		return this.symbol + "," +

		(this.LastTradePriceOnly != null ? this.LastTradePriceOnly : "<Not Available>") + ","

		+ (this.OneyrTargetPrice != null ? this.OneyrTargetPrice : "<Not Available>") + "," +

		(this.YearHigh != null ? this.YearHigh : "<Not Available>") + ","

		+ (this.YearLow != null ? this.YearLow : "<Not Available>");
	}

	public String toString() {
		return "Symbol - " + this.symbol

		+ ", current price $" + (this.LastTradePriceOnly != null ? this.LastTradePriceOnly : "<Not Available>")

		+ ", one year target price $" + (this.OneyrTargetPrice != null ? this.OneyrTargetPrice : "<Not Available>")

		+ ", year high $" + (this.YearHigh != null ? this.YearHigh : "<Not Available>")

		+ ", year low $" + (this.YearLow != null ? this.YearLow : "<Not Available>");
	}

	public boolean isValidQuote() {
		if (this.getCurrentPrice() != null || this.getYearHigh() != null || this.getYearHigh() != null
				|| this.getYearLow() != null || this.getYearTargetPrice() != null) {
			return true;
		}
		return false;
	}

}
