package com.stock.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.List;

import com.stock.crawler.StockAPICrawledResultProcessor;
import com.stock.crawler.StockAPICrawlerImpl;
import com.stock.fileio.StockDetailsPojo;
import com.stock.log.FileLogger;
import com.stock.main.StockManager;
import com.stock.query.QueryFormatter;
import com.stock.query.SymbolChecker;

public class StockCacheManager {

	LRUCacheImpl<String, StockDetailsPojo> cache;
	private static final Integer CACHE_SIZE = 300;
	private String fileCache;
	private static StockCacheManager instance = new StockCacheManager(CACHE_SIZE, StockManager.CSV_FILE);

	private StockCacheManager(int cacheSize, String cachedfileName) {
		cache = new LRUCacheImpl<String, StockDetailsPojo>(cacheSize);
		fileCache = cachedfileName;
	}

	public static StockCacheManager getInstance() {
		return instance;
	}

	public synchronized void putAll(List<StockDetailsPojo> stockDetails) {
		for (StockDetailsPojo stock : stockDetails) {
			cache.put(stock.getStockSymbol().toLowerCase(), stock);
		}
	}

	public synchronized void put(StockDetailsPojo stock) {
		cache.put(stock.getStockSymbol().toLowerCase(), stock);
	}

	public StockDetailsPojo get(String symbol) {
		StockDetailsPojo stock = cache.get(symbol.toLowerCase());
		if (stock == null) {
			// tryLoadingFromFileCache(symbol);
			SymbolChecker.getInstance().markSymbolValid(symbol);
			QueryFormatter formatter = new QueryFormatter();
			StockAPICrawlerImpl crawler = new StockAPICrawlerImpl();
			StockAPICrawledResultProcessor processor = new StockAPICrawledResultProcessor();
			String query = formatter.getStockQuery(symbol);
			List<StockDetailsPojo> result;
			try {
				result = processor.processJsonResult(crawler.getHttp(query));
				StockCacheManager.getInstance().putAll(result);
			} catch (IOException e) {
				FileLogger.getInstance()
						.log("Error while getting respounse for stock - " + symbol + ", error - " + e.getMessage());
			}
		}
		return cache.get(symbol.toLowerCase());
	}

	@SuppressWarnings("unused")
	private void tryLoadingFromFileCache(String symbol) {
		FileLock lock = null;
		try (RandomAccessFile file = new RandomAccessFile(fileCache, "rw");
				FileChannel channel = file.getChannel();
				FileReader reader = new FileReader(fileCache);
				BufferedReader br = new BufferedReader(reader)) {
			lock = channel.lock();
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] tokens = line.trim().split(",");
				if (tokens[0].equalsIgnoreCase(symbol) && tokens.length == 4) {
					StockDetailsPojo cacheEntry = new StockDetailsPojo();
					cacheEntry.setSymbol(symbol);
					try {
						cacheEntry.setLastTradePriceOnly(new BigDecimal(tokens[1]));
					} catch (NumberFormatException e) {
						cacheEntry.setLastTradePriceOnly(null);
					}
					try {
						cacheEntry.setOneyrTargetPrice(new BigDecimal(tokens[2]));
					} catch (NumberFormatException e) {
						cacheEntry.setOneyrTargetPrice(null);
					}
					try {
						cacheEntry.setYearHigh(new BigDecimal(tokens[3]));
					} catch (NumberFormatException e) {
						cacheEntry.setYearHigh(null);
					}
					try {
						cacheEntry.setYearLow(new BigDecimal(tokens[4]));
					} catch (NumberFormatException e) {
						cacheEntry.setYearLow(null);
					}
					put(cacheEntry);
					break;
				}
			}
		} catch (IOException e) {
			FileLogger.getInstance().log("Warn: error while reading entry from cache file, error - " + e.getMessage()
					+ ", cache file - " + fileCache);
		} finally {
			try {
				if (lock != null) {
					lock.release();
				}
			} catch (IOException e) {
			}
		}

	}
}
