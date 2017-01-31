package com.stock.runner;

import java.util.ArrayList;
import java.util.List;

import com.stock.fileio.StockDetailsPojo;

public class CrawledSubResult {
	boolean isDone = false;
	List<StockDetailsPojo> list = new ArrayList<StockDetailsPojo>();
}
