package com.backtracking;

public class StringPermutations {

	public static void main(String[] args) {
		String str = "acbs";
		char[] chars = str.toCharArray();
		printPermutations(chars, 0);
	}

	private static void printPermutations(char[] chars, int start) {
		if (start == chars.length) {
			for (char ch : chars) {
				System.out.print(ch);
			}
			System.out.print(" ");
		} else {
			for (int j = start; j < chars.length; j++) {
				swap(chars, start, j);
				printPermutations(chars, start + 1);
				swap(chars, start, j);
			}
		}
	}

	private static void swap(char[] chars, int i, int j) {
		char temp = chars[i];
		chars[i] = chars[j];
		chars[j] = temp;
	}

}
