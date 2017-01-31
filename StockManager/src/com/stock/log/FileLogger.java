package com.stock.log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Simple file logger
 * 
 * @author Prashant.Bhagat
 *
 */
public class FileLogger {
	private final String file = ".\\logs\\log.txt";

	private BufferedWriter writer;
	private static final FileLogger instance = new FileLogger();

	private FileLogger() {
		try {
			writer = new BufferedWriter(new FileWriter(new File(file)));
		} catch (IOException e) {
			System.out.println("Warning: failed to init logger, error- " + e.getMessage()
					+ ", logging will not be available for this run.");
		}
	}

	public static FileLogger getInstance() {
		return instance;
	}

	public synchronized void closeLogger() {
		if (writer != null) {
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
			}
		}
	}

	public synchronized void log(String message) {
		if (writer != null) {
			try {
				writer.write(message);
				writer.write("\n\r");
			} catch (IOException e) {
			}
		}
	}
}
