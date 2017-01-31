package com.stock.main;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.stock.cache.StockCacheManager;
import com.stock.fileio.StockDetailsPojo;
import com.stock.log.FileLogger;
import com.stock.query.SymbolChecker;

public class StockManager implements IStockManager {
	public static final String STOCK_SYM_FILE = ".\\resource\\stocks.txt";
	public static final String CSV_FILE = ".\\result\\stock_details.txt";
	public static final int CRAWL_INTERVAL_MILLIS = 10_000;
	private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

	public static void main(String[] args) {
		StockManager manager = new StockManager();
		manager.scheduleStockCrawler(CRAWL_INTERVAL_MILLIS);

		boolean exit = false;
		Scanner scanner = new Scanner(System.in);
		try {
			do {
				manager.showOptions();
				String strOption = scanner.nextLine();
				String symbol;
				try {
					int intOption = Integer.parseInt(strOption);
					switch (intOption) {
					case 1:
						manager.askSumbolInput();
						symbol = scanner.nextLine();
						if (!SymbolChecker.getInstance().isSymbolValid(symbol)) {
							System.out.println("Symbol " + symbol + " is invalid.");
						} else {
							String details = manager.getStockDetailsAsString(symbol);
							System.out.println();
							System.out.println(details);
						}
						break;
					case 2:
						manager.askSumbolInput();
						symbol = scanner.nextLine();
						if (!SymbolChecker.getInstance().isSymbolValid(symbol)) {
							System.out.println("Symbol + " + symbol + " is invalid.");
						} else {
							BigDecimal price = manager.getStockPrice(symbol);
							System.out.println();
							System.out.println("Price is $" + price);
						}
						break;
					case 3:
						manager.askSumbolInput();
						symbol = scanner.nextLine();
						if (!SymbolChecker.getInstance().isSymbolValid(symbol)) {
							System.out.println("Symbol + " + symbol + " is invalid.");
						} else {
							BigDecimal yearEndTarget = manager.getStockYearTargetPrice(symbol);
							System.out.println();
							System.out.println("Year end target is $" + yearEndTarget);
						}
						break;
					case 4:
						manager.askSumbolInput();
						symbol = scanner.nextLine();
						if (!SymbolChecker.getInstance().isSymbolValid(symbol)) {
							System.out.println("Symbol + " + symbol + " is invalid.");
						} else {
							BigDecimal yearLow = manager.getStockYearLow(symbol);
							System.out.println();
							System.out.println("Year low $" + yearLow);
						}
						break;
					case 5:
						symbol = scanner.nextLine();
						if (!SymbolChecker.getInstance().isSymbolValid(symbol)) {
							System.out.println("Symbol + " + symbol + " is invalid.");
						} else {
							BigDecimal yearHigh = manager.getStockYearHigh(symbol);
							System.out.println();
							System.out.println("Year high $" + yearHigh);
						}
						break;
					case 6:
						exit = true;
						break;
					default: {
						System.out.println("Invalid choice.. valid choices: 1 to 6");
						continue;
					}
					}
				} catch (NumberFormatException e) {
					System.out.println("Invalid choice.. valid choices: 1 to 6");
					continue;
				}
				System.out.println();
			} while (exit == false);
		} finally {
			scanner.close();
		}
		manager.shutDown();
		FileLogger.getInstance().closeLogger();
	}

	private void shutDown() {
		System.out.println("Shutting all the executor.. wait");
		executor.shutdown();
		try {
			executor.awaitTermination(60, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
		}
		System.out.println("Shutdown completed");
	}

	public void askSumbolInput() {
		System.out.println("Enter the stock symbol: ");
		System.out.println("");
	}

	public void showOptions() {
		System.out.println("Options:");
		System.out.println("1. Print stock full details");
		System.out.println("2. Get stock current price");
		System.out.println("3. Get stock year end target price");
		System.out.println("4. Get stock year low price");
		System.out.println("5. Get stock year high price");
		System.out.println("6. Exit");
		System.out.println("");

	}

	private void scheduleStockCrawler(long intervalMillis) {
		executor.scheduleAtFixedRate(new StockParallelCrawlerTask(), 0, intervalMillis, TimeUnit.MILLISECONDS);
	}

	@Override
	public String getStockDetailsAsString(String stockSymbol) {
		StockDetailsPojo stockDetails = StockCacheManager.getInstance().get(stockSymbol);
		return stockDetails != null ? stockDetails.toString() : "Stock price is not yet available, try some time later";
	}

	@Override
	public StockDetailsPojo getStockDetails(String stockSymbol) {
		return StockCacheManager.getInstance().get(stockSymbol);
	}

	@Override
	public BigDecimal getStockPrice(String stockSymbol) {
		StockDetailsPojo stockDetails = StockCacheManager.getInstance().get(stockSymbol);
		return stockDetails != null ? stockDetails.getLastTradePriceOnly() : new BigDecimal(0.0f);
	}

	@Override
	public BigDecimal getStockYearTargetPrice(String stockSymbol) {
		StockDetailsPojo stockDetails = StockCacheManager.getInstance().get(stockSymbol);
		return stockDetails != null ? stockDetails.getOneyrTargetPrice() : new BigDecimal(0.0f);
	}

	@Override
	public BigDecimal getStockYearLow(String stockSymbol) {
		StockDetailsPojo stockDetails = StockCacheManager.getInstance().get(stockSymbol);
		return stockDetails != null ? stockDetails.getYearLow() : new BigDecimal(0.0f);
	}

	@Override
	public BigDecimal getStockYearHigh(String stockSymbol) {
		StockDetailsPojo stockDetails = StockCacheManager.getInstance().get(stockSymbol);
		return stockDetails != null ? stockDetails.getYearHigh() : new BigDecimal(0.0f);
	}

}
