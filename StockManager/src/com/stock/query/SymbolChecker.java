package com.stock.query;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SymbolChecker {
	private Set<String> validSymbols = new HashSet<String>();
	private Set<String> inValidSymbols = new HashSet<String>();
	private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private static SymbolChecker instance = new SymbolChecker();

	private SymbolChecker() {
	}

	public static SymbolChecker getInstance() {
		return instance;
	}

	public void markSymbolValid(String symbol) {
		if (symbol == null || symbol.trim().isEmpty()) {
			return;
		}
		try {
			readWriteLock.writeLock().lock();
			validSymbols.add(symbol.trim().toLowerCase());
			inValidSymbols.remove(symbol.trim().toLowerCase());
		} finally {
			readWriteLock.writeLock().unlock();
		}
	}

	public void markSymbolnvalid(String symbol) {
		if (symbol == null || symbol.trim().isEmpty()) {
			return;
		}
		try {
			readWriteLock.writeLock().lock();
			validSymbols.remove(symbol.trim().toLowerCase());
			inValidSymbols.add(symbol.trim().toLowerCase());
		} finally {
			readWriteLock.writeLock().unlock();
		}
	}

	public boolean isSymbolValid(String symbol) {
		boolean isValid = false;
		if (symbol == null || symbol.trim().isEmpty()) {
			isValid = false;
		}
		try {
			readWriteLock.readLock().lock();
			isValid = validSymbols.contains(symbol.trim().toLowerCase());
		} finally {
			readWriteLock.readLock().unlock();
		}
		return isValid;
	}

	public String getInvalidSymbols() {
		return inValidSymbols.toString();
	}

}
