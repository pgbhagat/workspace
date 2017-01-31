package com.cw.filehelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Properties;

public class MyPropertyFile {

	public static void main(String[] args) throws IOException {

		Properties prop = new Properties();
		prop.put("source", "c:\\doc\\sample.pdf");
		OutputStream out = new FileOutputStream(new File("c:\\test.txt"));
		Writer writer = new OutputStreamWriter(out, "UTF-16");
		prop.store(writer, "");
		out.flush();
		out.close();
		
		
	}

}
