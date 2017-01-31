package com.stock.main;

import com.stock.fileio.SharedListResult;
import com.stock.log.FileLogger;
import com.stock.runner.CrawledSubResult;
import com.stock.runner.StockCrawlerConsumer;
import com.stock.runner.StockCrawlerProducer;
import com.stock.runner.StockSymbolProducer;

public class StockParallelCrawlerTask implements Runnable {

	/*
	 * public static void main(String... args) { new Thread(new
	 * StockParallelCrawlerTask()).start(); }
	 */

	CrawledSubResult subResult = new CrawledSubResult();

	StockParallelCrawlerTask() {
	}

	@Override
	public void run() {
		SharedListResult<String> sharedList = new SharedListResult<String>();
		StockSymbolProducer stockSymbolProducer = new StockSymbolProducer(sharedList);
		StockCrawlerProducer producer = new StockCrawlerProducer(subResult, sharedList);
		StockCrawlerConsumer consumer = new StockCrawlerConsumer(subResult);
		Thread symbolProducerThread = new Thread(stockSymbolProducer);
		Thread crawlerProderThread = new Thread(producer);
		Thread crawlerConsumerThread = new Thread(consumer);
		crawlerConsumerThread.start();
		crawlerProderThread.start();
		symbolProducerThread.start();
		try {
			symbolProducerThread.join();
			crawlerProderThread.join();
			crawlerConsumerThread.join();
		} catch (InterruptedException e) {
			FileLogger.getInstance()
					.log("Warn: Exception in StockParallerCrawlerTask, error - " + e.getLocalizedMessage());
		}
	}

}
