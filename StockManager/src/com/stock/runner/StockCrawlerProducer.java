package com.stock.runner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.JsonNode;
import com.stock.crawler.StockAPICrawledResultProcessor;
import com.stock.crawler.StockAPICrawlerImpl;
import com.stock.fileio.SharedListResult;
import com.stock.fileio.StockDetailsPojo;
import com.stock.log.FileLogger;
import com.stock.query.QueryFormatter;
import com.stock.query.SymbolChecker;

public class StockCrawlerProducer implements Runnable {
	int chunk_size = 5;
	CrawledSubResult subResult;
	SharedListResult<String> sharedSymbolList;
	List<String> myStockSymbols = new ArrayList<String>();

	final int MAX_CRAWLER = 5;

	ExecutorService executor;

	public boolean isDone() {
		synchronized (subResult) {
			return subResult.isDone;
		}
	}

	public StockCrawlerProducer(CrawledSubResult subResult, SharedListResult<String> sharedSymbolList) {
		this.subResult = subResult;
		this.sharedSymbolList = sharedSymbolList;
		this.executor = Executors.newFixedThreadPool(MAX_CRAWLER);
	}

	@Override
	public void run() {
		synchronized (sharedSymbolList) {
			while (!sharedSymbolList.isDone()) {
				if (sharedSymbolList.getSharedList().isEmpty()) {
					try {
						sharedSymbolList.wait();
					} catch (InterruptedException e) {
						FileLogger.getInstance()
								.log("Warn: Exception in StockCrawlerProducer, error - " + e.getLocalizedMessage());
					}
				}
				synchronized (myStockSymbols) {
					myStockSymbols.addAll(sharedSymbolList.getSharedList());
				}
				sharedSymbolList.getSharedList().clear();
				sharedSymbolList.notifyAll();

				Thread crawlerThread = new Thread(new Runnable() {
					@Override
					public void run() {
						List<String> localSymbols = new ArrayList<String>();
						synchronized (myStockSymbols) {
							localSymbols.addAll(myStockSymbols);
							myStockSymbols.clear();
						}
						if (localSymbols.isEmpty()) {
							return;
						}
						for (String symbol : localSymbols) {
							SymbolChecker.getInstance().markSymbolValid(symbol);
						}
						int startIndex = 0;
						int endIndex = localSymbols.size();
						JsonNode result = null;
						QueryFormatter formatter = new QueryFormatter();
						StockAPICrawlerImpl crawler = new StockAPICrawlerImpl();
						StockAPICrawledResultProcessor processor = new StockAPICrawledResultProcessor();
						if (localSymbols.size() < chunk_size) {
							chunk_size = localSymbols.size();
						}
						for (int i = startIndex; i < endIndex; i += chunk_size) {
							int tmpEnd = i + chunk_size;
							if (tmpEnd > endIndex)
								tmpEnd = endIndex;
							String query = formatter.getStockQuery(localSymbols.subList(i, tmpEnd));
							try {
								result = crawler.getHttp(query);
								List<StockDetailsPojo> tmpResult = processor.processJsonResult(result);
								synchronized (subResult) {
									subResult.list.addAll(tmpResult);
									subResult.notifyAll();
								}
							} catch (IOException e) {
								FileLogger.getInstance()
										.log("Warn: Exception in crawlerThread, error - " + e.getMessage());
							}
						}
					}
				});
				executor.submit(crawlerThread);
			}

		}
		try {
			// Max wait for this much time to all task complete the work...
			executor.shutdown();
			executor.awaitTermination(60, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			FileLogger.getInstance()
					.log("Warn: Exception in executor.awaitTermination, error - " + e.getLocalizedMessage());
		}
		synchronized (subResult) {
			subResult.isDone = true;
			subResult.notifyAll();
		}
	}

}