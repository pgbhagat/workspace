package com.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

public class TextJustification {

	public static List<String> greedyWay(String[] strings, int width) {
		List<String> result = new ArrayList<>();
		if (strings != null && strings.length > 0) {
			for (int i = 0; i < strings.length;) {
				StringBuilder buffer = new StringBuilder();
				int total = strings[i].length();
				int j = i;
				while ((j + 1) < strings.length && width >= (total + strings[j + 1].length() + 1)) {
					total += strings[j + 1].length() + 1;
					j++;
				}
				for (int k = i; k <= j; k++) {
					buffer.append(strings[k]).append(" ");
				}
				int remaining = width - total;
				if (remaining > 0) {
					addPaddingSpaces(buffer, remaining);
				}
				i = j + 1;
				result.add(buffer.toString());
			}
		}
		return result;

	}

	private static void addPaddingSpaces(StringBuilder buffer, int remaining) {
		for (int i = 0; i < remaining; i++) {
			buffer.append(" ");
		}
	}

	public static void main(String[] args) {
		String[] strings = { "Tushar", "likes", "to", "write", "code", "at", "free", "time" };
		List<String> justifiedText = greedyWay(strings, 12);
		for (String str : justifiedText) {
			System.out.println(">" + str + "<");
		}
	}

}
