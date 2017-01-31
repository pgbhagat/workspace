package com.stock.test;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.stock.crawler.StockAPICrawledResultProcessor;
import com.stock.crawler.StockAPICrawlerImpl;
import com.stock.fileio.StockDetailsPojo;

import junit.framework.TestCase;

public class TestStockAPICrawlerImpl extends TestCase {

	@Test
	public void testGetHttp() {
		String url = "https://query.yahooapis.com/v1/public/yql?q=select%20symbol,LastTradePriceOnly,OneyrTargetPrice,YearLow,YearHigh%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22GOOG,ACP,TXSSDDS%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
		StockAPICrawlerImpl crawler = new StockAPICrawlerImpl();
		try {
			JsonNode node = crawler.getHttp(url);
			Assert.assertNotNull(node);
			StockAPICrawledResultProcessor processor = new StockAPICrawledResultProcessor();
			List<StockDetailsPojo> list = processor.processJsonResult(node);
			Assert.assertNotNull(list);
			
		} catch (IOException e) {
			Assert.fail(e.getMessage());
		}
	}
}
