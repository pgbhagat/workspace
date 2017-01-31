package com.dynamicprogramming;

public class LongestCommonSubsequence {

	public static int find(char[] strOne, char[] strTwo) {
		if (strOne == null || strOne.length == 0 || strTwo == null || strTwo.length == 0) {
			return 0;
		}
		int[][] longestCommonSub = new int[strOne.length + 1][strTwo.length + 1];
		String[][] solution = new String[strOne.length + 1][strTwo.length + 1];
		// if A is null then LCS of A, B =0
		for (int i = 0; i <= strTwo.length; i++) {
			longestCommonSub[0][i] = 0;
			solution[0][i] = "0";
		}

		// if B is null then LCS of A, B =0
		for (int i = 0; i <= strOne.length; i++) {
			longestCommonSub[i][0] = 0;
			solution[i][0] = "0";
		}

		for (int i = 1; i <= strOne.length; i++) {
			for (int j = 1; j <= strTwo.length; j++) {
				if (strOne[i - 1] == strTwo[j - 1]) {
					longestCommonSub[i][j] = longestCommonSub[i - 1][j - 1] + 1;
					solution[i][j] = "diagonal";
				} else {
					longestCommonSub[i][j] = Math.max(longestCommonSub[i - 1][j], longestCommonSub[i][j - 1]);
					if (longestCommonSub[i][j] == longestCommonSub[i - 1][j]) {
						solution[i][j] = "top";
					} else {
						solution[i][j] = "left";
					}
				}
			}
		}
		// below code is to just print the result
		String x = solution[strOne.length][strTwo.length];
		String answer = "";
		int a = strOne.length;
		int b = strTwo.length;
		while (x != "0") {
			if (solution[a][b] == "diagonal") {
				answer = strOne[a - 1] + answer;
				a--;
				b--;
			} else if (solution[a][b] == "left") {
				b--;
			} else if (solution[a][b] == "top") {
				a--;
			}
			x = solution[a][b];
		}
		System.out.println(answer);

		for (int i = 0; i <= strOne.length; i++) {
			for (int j = 0; j <= strTwo.length; j++) {
				System.out.print(" " + longestCommonSub[i][j]);
			}
			System.out.println();
		}
		return longestCommonSub[strOne.length][strTwo.length];
	}

	public static void main(String[] args) {
		String A = "ACBDEA";
		String B = "ABCDA";
		System.out.println("LCS :" + find(A.toCharArray(), B.toCharArray()));
	}
}