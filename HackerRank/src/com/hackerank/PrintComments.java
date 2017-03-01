package com.hackerank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Prints only comments from a source code.
 * 
 * It considers single line comment, multi line comments
 * 
 * 
 * 
 * @author Prashant.Bhagat
 *
 */
public class PrintComments {

	public static void main(String... args) {
		List<String> lines = new ArrayList<>();
		try (Scanner scanner = new Scanner(System.in);) {
			while (scanner.hasNextLine()) {
				lines.add(scanner.nextLine());
			}

		}
		printsComments(lines);
	}

	private static void printsComments(List<String> lines) {
		boolean multiLineComment = false;
		for (String line : lines) {
			if (line.indexOf("//") != -1) {
				System.out.println(line.substring(line.indexOf("//")));
			} else if (line.indexOf("/*") != -1) {
				if (line.indexOf("*/") != -1) {
					multiLineComment = false;
					System.out.println(line.substring(line.indexOf("/*"), line.indexOf("*/") + "*/".length()));
				} else {
					multiLineComment = true;
					System.out.println(line.substring(line.indexOf("/*")));
				}
			} else if (line.indexOf("*/") != -1) {
				multiLineComment = false;
				System.out.println(line.substring(0, line.indexOf("/*")));
			} else if (multiLineComment) {
				System.out.println(line);
			}
		}

	}
}
