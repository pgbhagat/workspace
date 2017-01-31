package com.stock.runner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.stock.cache.StockCacheManager;
import com.stock.fileio.CSVWriter;
import com.stock.fileio.StockDetailsPojo;
import com.stock.log.FileLogger;
import com.stock.main.StockManager;

public class StockCrawlerConsumer implements Runnable {

	CrawledSubResult subResult;
	List<StockDetailsPojo> tmpList = new ArrayList<StockDetailsPojo>();

	public StockCrawlerConsumer(CrawledSubResult subResult) {
		this.subResult = subResult;
	}

	@Override
	public void run() {

		synchronized (subResult) {
			while (!subResult.isDone) {
				try {
					if (subResult.list.isEmpty()) {
						subResult.wait();
					}
					tmpList.addAll(subResult.list);
					subResult.list.clear();
					subResult.notifyAll();
				} catch (InterruptedException e) {
					FileLogger.getInstance()
							.log("Warn: Exception in StockCrawlerConsumer, error - " + e.getLocalizedMessage());
				}
				StockCacheManager.getInstance().putAll(tmpList);
			}
			if (!subResult.list.isEmpty()) {
				tmpList.addAll(subResult.list);
				subResult.list.clear();
				subResult.notifyAll();
				StockCacheManager.getInstance().putAll(tmpList);
			}
		}

		try {
			FileLogger.getInstance().log("Total stock details to be dumped in file " + tmpList.size());
			CSVWriter.dumpStockDetailsAsCSV(tmpList, StockManager.CSV_FILE);
		} catch (IOException e) {
			FileLogger.getInstance().log("Warn: Exception in StockCrawlerConsumer.dumpStockDetailsAsCSV, error - "
					+ e.getLocalizedMessage());
		}

	}

}