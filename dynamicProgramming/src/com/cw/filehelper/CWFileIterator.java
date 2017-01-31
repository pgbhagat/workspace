package com.cw.filehelper;

import java.io.File;

public class CWFileIterator {

	private static CWFileIterator instance = null;
	private static String[] files;
	private static int currIndex = 0;
	private static String currentFile;

	private CWFileIterator(String dir) {
		File file = new File(dir);
		if (file.isDirectory()) {
			files = file.list();
		}
	}

	public String getNextFilename() {
		if (currentFile == null) {
			currentFile = files[0];
		} else if (currIndex < files.length - 1) {
			currIndex++;
		}
		currentFile = files[currIndex];
		return currentFile;
	}

	public String getPrevFilename() {
		if (currentFile == null) {
			currentFile = files[0];
		} else if (currIndex > 0) {
			currIndex--;
		}
		currentFile = files[currIndex];
		return currentFile;
	}

	public static CWFileIterator getInstance(String dir) {
		if (instance == null) {
			synchronized (CWFileIterator.class) {
				if (instance == null) {
					instance = new CWFileIterator(dir);
				}
			}
		}
		return instance;
	}

	public static void main(String[] args) {
		System.out.println(CWFileIterator.getInstance("C:\\Windows\\System32\\drivers\\etc").getNextFilename());
		System.out.println(CWFileIterator.getInstance("C:\\Windows\\System32\\drivers\\etc").getNextFilename());
		System.out.println(CWFileIterator.getInstance("C:\\Windows\\System32\\drivers\\etc").getPrevFilename());
		System.out.println(CWFileIterator.getInstance("C:\\Windows\\System32\\drivers\\etc").getPrevFilename());
		System.out.println(CWFileIterator.getInstance("C:\\Windows\\System32\\drivers\\etc").getPrevFilename());
		System.out.println(CWFileIterator.getInstance("C:\\Windows\\System32\\drivers\\etc").getNextFilename());
		System.out.println(CWFileIterator.getInstance("C:\\Windows\\System32\\drivers\\etc").getNextFilename());
		System.out.println(CWFileIterator.getInstance("C:\\Windows\\System32\\drivers\\etc").getNextFilename());
		System.out.println(CWFileIterator.getInstance("C:\\Windows\\System32\\drivers\\etc").getNextFilename());
		System.out.println(CWFileIterator.getInstance("C:\\Windows\\System32\\drivers\\etc").getNextFilename());

	}

}
