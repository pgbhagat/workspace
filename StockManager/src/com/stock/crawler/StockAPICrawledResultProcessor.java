package com.stock.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.stock.fileio.StockDetailsPojo;
import com.stock.log.FileLogger;
import com.stock.query.SymbolChecker;

public class StockAPICrawledResultProcessor {

	public static final String ROOT_Node = "query";
	public static final String RESULT_Node = "results";
	public static final String ARRAY_Node = "quote";

	public List<StockDetailsPojo> processJsonResult(JsonNode rawJsonResult) {
		List<StockDetailsPojo> result = new ArrayList<StockDetailsPojo>();
		if (rawJsonResult != null) {
			JsonNode rootNode = (JsonNode) rawJsonResult.get(ROOT_Node);
			if (rootNode != null) {
				JsonNode resultNode = rootNode.get(RESULT_Node);
				if (resultNode != null) {
					if (resultNode.get(ARRAY_Node) instanceof ArrayNode) {
						ArrayNode quotes = ((ArrayNode) resultNode.get(ARRAY_Node));
						ObjectMapper mapper = new ObjectMapper();
						for (int i = 0; i < quotes.size(); i++) {
							JsonNode node = quotes.get(i);
							StockDetailsPojo stockQuote;
							try {
								stockQuote = mapper.readValue(node.toString(), StockDetailsPojo.class);
								if (stockQuote.isValidQuote()) {
									result.add(stockQuote);
								} else {
									FileLogger.getInstance().log("Invalid symbol - " + stockQuote.getStockSymbol());
									SymbolChecker.getInstance().markSymbolnvalid(stockQuote.getStockSymbol());
								}
							} catch (IOException e) {
								FileLogger.getInstance()
										.log("Warn: Exception in StockAPICrawledResultProcessor, error - "
												+ e.getLocalizedMessage());
							}

						}
					} else if (resultNode.get(ARRAY_Node) instanceof ObjectNode) {
						ObjectNode quotes = (ObjectNode) resultNode.get(ARRAY_Node);
						ObjectMapper mapper = new ObjectMapper();
						StockDetailsPojo stockQuote;
						try {
							stockQuote = mapper.readValue(quotes.toString(), StockDetailsPojo.class);
							if (stockQuote.isValidQuote()) {
								result.add(stockQuote);
							} else {
								FileLogger.getInstance().log("Invalid symbol - " + stockQuote.getStockSymbol());
								SymbolChecker.getInstance().markSymbolnvalid(stockQuote.getStockSymbol());
							}
						} catch (IOException e) {
							FileLogger.getInstance().log("Warn: Exception in StockAPICrawledResultProcessor, error - "
									+ e.getLocalizedMessage());
						}
					} else {
						FileLogger.getInstance().log("Warn: Unknow json element " + resultNode.get(ARRAY_Node));
					}
				}
			}

		}
		return result;
	}
}
