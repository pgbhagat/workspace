package com.stock.fileio;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.stock.log.FileLogger;

public class StockSymbolFileReader {

	private SharedListResult<String> sharedList;
	private static final int read_chunk_size = 5;

	public StockSymbolFileReader(SharedListResult<String> sharedList) {
		this.sharedList = sharedList;
	}

	public void produceStockSymbols(String stockSymbolfile) throws IOException {
		List<String> symbols = new ArrayList<String>();
		try (FileReader fileReader = new FileReader(stockSymbolfile);
				BufferedReader bufferReader = new BufferedReader(fileReader);) {
			String line = null;
			try {
				while ((line = bufferReader.readLine()) != null) {
					symbols.add(line.trim());
					if (symbols.size() == read_chunk_size) {
						synchronized (this.sharedList) {
							this.sharedList.getSharedList().addAll(symbols);
							this.sharedList.notifyAll();
						}
						symbols.clear();
					}
				}
				synchronized (this.sharedList) {
					if (!symbols.isEmpty()) {
						this.sharedList.getSharedList().addAll(symbols);
					}
					this.sharedList.setDone(true);
					this.sharedList.notifyAll();
				}
				symbols.clear();
			} catch (IOException e) {
				System.out
						.println("Error while reading the file [ " + stockSymbolfile + "], error - " + e.getMessage());
				throw e;
			}
		} catch (FileNotFoundException e) {
			FileLogger.getInstance().log("Invalid file to read stock symbols, error - " + e.getMessage());
			throw e;
		}
	}
}
