package com.stock.query;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.stock.log.FileLogger;

public class QueryColumnCsvMapping {

	private final String fileName = "QueryColumnCsvMapping";
	private Map<String, String> queryColumnsToCsvMapping = new HashMap<>();

	QueryColumnCsvMapping() {
		queryColumnsToCsvMapping.put("symbol", "Stock Symbol");
		queryColumnsToCsvMapping.put("LastTradePriceOnly", "Current Price");
		queryColumnsToCsvMapping.put("OneyrTargetPrice", "Year Target Price");
		queryColumnsToCsvMapping.put("YearHigh", "Year High");
		queryColumnsToCsvMapping.put("YearLow", "Year Low");
		populateQueryColumnAndCsvMapping();

	}

	
	private void populateQueryColumnAndCsvMapping() {
		try (FileReader fileReader = new FileReader(getClass().getClassLoader().getResource(fileName).getFile());
				BufferedReader bufferReader = new BufferedReader(fileReader);) {
			String line = null;
			while ((line = bufferReader.readLine()) != null) {
				if (!line.trim().startsWith("#")) {
					String[] tokens = line.trim().split("=");
					queryColumnsToCsvMapping.put(tokens[0], tokens[1]);
				}
			}
		} catch (IOException e) {
			FileLogger.getInstance().log(
					"Warning: IOException during reading the custom query column to csv mapping file.. error - "
							+ e.getMessage());
		}
	}
}
