package com.stock.test;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.stock.cache.StockCacheManager;
import com.stock.fileio.StockDetailsPojo;

import junit.framework.TestCase;

public class TestStockCacheManager extends TestCase {

	@Test
	public void testStockCacheManagerGet() {
		StockDetailsPojo entry = new StockDetailsPojo();
		entry.setSymbol("Goog");
		entry.setLastTradePriceOnly(new BigDecimal(23.5f));
		entry.setOneyrTargetPrice(new BigDecimal(30.0f));
		entry.setYearHigh(new BigDecimal(25.5f));
		entry.setYearLow(new BigDecimal(13.5f));

		StockCacheManager.getInstance().put(entry);
		Assert.assertNotNull(StockCacheManager.getInstance().get(entry.getStockSymbol()));
		Assert.assertEquals(StockCacheManager.getInstance().get(entry.getStockSymbol()).getLastTradePriceOnly(),
				entry.getLastTradePriceOnly());
	}

}
