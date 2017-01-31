package com.stock.fileio;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.stock.log.FileLogger;
import com.stock.query.SymbolChecker;

public class CSVWriter {

	private static final String CSV_HEADER = "Stock Symbol,Current Price,Year Target Price,Year High,Year Low";

	public static void dumpStockDetailsAsCSV(List<StockDetailsPojo> stockDetailList, String targetFile)
			throws IOException {

		if (stockDetailList == null || stockDetailList.isEmpty()) {
			return;
		}
		try (FileWriter fileWriter = new FileWriter(targetFile);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);) {
			try {
				bufferedWriter.write(CSV_HEADER);
				bufferedWriter.write("\n\r");
				for (StockDetailsPojo stockDetail : stockDetailList) {
					bufferedWriter.write(stockDetail.toCSVString());
					bufferedWriter.write("\n\r");
				}
				bufferedWriter.flush();
				FileLogger.getInstance().log("Total invalid stock " + SymbolChecker.getInstance().getInvalidSymbols());
			} catch (IOException e) {
				FileLogger.getInstance()
						.log("Error while writing the file [ " + targetFile + "], error - " + e.getMessage());
				throw e;
			} finally {
			}
		} catch (FileNotFoundException e) {
			FileLogger.getInstance().log("Invalid file to wrtie stock details to, error - " + e.getMessage());
			throw e;
		} finally {
		}

	}

}
