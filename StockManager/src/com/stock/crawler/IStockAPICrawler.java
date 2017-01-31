package com.stock.crawler;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;

public interface IStockAPICrawler {
	JsonNode getHttp(String url) throws IOException;

	boolean postHttp(String url, JsonNode postBody);
}
