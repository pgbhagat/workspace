package com.dynamicprogramming;

public class LongestPalindromicSubsequence {

	public static int findPalindromeRecursion(String str) {
		int len = 0;
		if (str.length() == 0) {
			len = 0;
		} else if (str.length() == 1) {
			len = 1;
		} else {
			if (str.charAt(0) == str.charAt(str.length() - 1)) {
				len = 2 + findPalindromeRecursion(str.substring(1, str.length() - 1));
			} else {
				len = Math.max(findPalindromeRecursion(str.substring(1)),
						findPalindromeRecursion(str.substring(0, str.length() - 1)));
			}
		}
		return len;
	}

	public int findPalindrome(String str) {
		char[] chars = str.toCharArray();
		int[][] solutionMatrix = new int[chars.length][chars.length];
		for (int i = 0; i < chars.length; i++) {
			solutionMatrix[i][i] = 1;
		}
		for (int sublen = 2; sublen <= chars.length; sublen++) {
			for (int i = 0; i <= solutionMatrix.length - sublen; i++) {
				int j = i + sublen - 1;
				if (chars[i] == chars[j] && sublen == 2) {
					solutionMatrix[i][j] = 2;
				} else if (chars[i] == chars[j]) {
					solutionMatrix[i][j] = solutionMatrix[i + 1][j - 1] + 2;
				} else {
					solutionMatrix[i][j] = Math.max(solutionMatrix[i + 1][j], solutionMatrix[i][j - 1]);
				}
			}
		}
		printMatrix(solutionMatrix);
		return solutionMatrix[0][solutionMatrix.length - 1];

	}

	public void printMatrix(int[][] LP) {
		for (int i = 0; i < LP.length; i++) {
			for (int j = 0; j < LP.length; j++) {
				System.out.print("  " + LP[i][j]);
			}
			System.out.println("");
		}
	}

	public static void main(String arg[]) {
		String strA = "AABCDEBAZ";
		// String strA = "AABCD";
		LongestPalindromicSubsequence lps = new LongestPalindromicSubsequence();
		int x = lps.findPalindrome(strA);
		System.out.println("Length of Longest Palindrome in '" + strA + "' is- " + x);
		x = findPalindromeRecursion(strA);
		System.out.println("Length of Longest Palindrome in '" + strA + "' is- " + x);

	}
}