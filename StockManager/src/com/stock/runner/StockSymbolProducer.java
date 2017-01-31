package com.stock.runner;

import java.io.IOException;

import com.stock.fileio.SharedListResult;
import com.stock.fileio.StockSymbolFileReader;
import com.stock.log.FileLogger;
import com.stock.main.StockManager;

public class StockSymbolProducer implements Runnable {
	SharedListResult<String> sharedSymbolList;

	public StockSymbolProducer(SharedListResult<String> sharedList) {
		this.sharedSymbolList = sharedList;
	}

	@Override
	public void run() {
		StockSymbolFileReader stockSymbolProducer = new StockSymbolFileReader(sharedSymbolList);
		try {
			stockSymbolProducer.produceStockSymbols(StockManager.STOCK_SYM_FILE);
		} catch (IOException e) {
			FileLogger.getInstance()
					.log("Warn: Exception in StockSymbolProducer, error - " + e.getLocalizedMessage());
		}
	}

}

