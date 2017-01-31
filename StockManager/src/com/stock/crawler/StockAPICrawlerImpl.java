package com.stock.crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.log.FileLogger;

public class StockAPICrawlerImpl implements IStockAPICrawler {

	private static final int MAX_WAIT = 10_000;// in millis

	@Override
	public JsonNode getHttp(String url) throws IOException {
		URL getURL = null;
		try {
			getURL = new URL(url);
		} catch (MalformedURLException e) {
			FileLogger.getInstance().log("Invalid url [" + url + "], error - " + e.getMessage());
			throw e;
		}
		HttpsURLConnection con = null;
		try {
			con = (HttpsURLConnection) getURL.openConnection();
			con.setReadTimeout(MAX_WAIT);
			try {
				con.connect();
			} catch (SocketTimeoutException e) {
				FileLogger.getInstance()
						.log("Time out while getting response from [" + url + "], error - " + e.getMessage());
				throw e;
			}

		} catch (IOException e) {
			FileLogger.getInstance().log("Error while getting response from [" + url + "], error - " + e.getMessage());
			throw e;
		}
		try (InputStream ins = con.getInputStream();
				InputStreamReader isr = new InputStreamReader(ins);
				BufferedReader in = new BufferedReader(isr);) {
			StringBuilder builder = new StringBuilder();
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				//FileLogger.getInstance().log(inputLine);
				builder.append(inputLine);
			}
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(builder.toString());
			return node;
		} catch (IOException e) {
			FileLogger.getInstance().log("exception while processing response..error - " + e.getMessage());
			throw e;
		}

	}

	@Override
	public boolean postHttp(String url, JsonNode postBody) {
		// TODO not implemented..
		return false;
	}

}
