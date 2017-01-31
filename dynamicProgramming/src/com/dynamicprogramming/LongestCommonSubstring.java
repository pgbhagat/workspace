package com.dynamicprogramming;

public class LongestCommonSubstring {

	public static void main(String[] args) {
		String str1 = "suhsusma";
		String str2 = "sus";
		printLongestCommonSubstring(str1, str2);
	}

	private static void printLongestCommonSubstring(String str1, String str2) {
		String s1 = str1.length() > str2.length() ? str2 : str1;
		String s2 = s1 == str1 ? str2 : str1;
		int[][] solution = new int[s1.length() + 1][s2.length() + 1];
		for (int i = 1; i < solution.length; i++) {
			for (int j = 1; j < solution[0].length; j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					solution[i][j] = 1 + solution[i - 1][j - 1];
				} else {
					//solution[i][j] = solution[i - 1][j - 1];
					solution[i][j] = 0;
				}
			}
		}

		for (int i = 0; i < solution.length; i++) {
			for (int j = 0; j < solution[0].length; j++) {
				System.out.print(" " + solution[i][j]);
			}
			System.out.println();
		}
	}

}
