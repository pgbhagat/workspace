package com.array;

public class AllPermulationOfAString {

	public static void main(String[] args) {
		String str = "abc";
		printAllPerm(str.toCharArray(), 0, str.length());
	}

	private static void printAllPerm(char[] str, int left, int size) {
		if (left == size) {
			for (char ch : str) {
				System.out.print(ch);
			}
			System.out.println();
		} else {
			for (int i = left; i < size; i++) {
				swap(str, left, i);
				printAllPerm(str, left + 1, size);
				swap(str, left, i);
			}
		}
	}

	private static void swap(char[] str, int left, int i) {
		char tmp = str[left];
		str[left] = str[i];
		str[i] = tmp;

	}

}
