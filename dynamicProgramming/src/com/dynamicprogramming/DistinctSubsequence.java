package com.dynamicprogramming;

/**
 * 
 * Given a string S and a string T, count the number of distinct subsequences of
 * T in S.
 * 
 * A subsequence of a string is a new string which is formed from the original
 * string by deleting some (can be none) of the characters without disturbing
 * the relative positions of the remaining characters. (ie, “ACE” is a
 * subsequence of “ABCDE” while “AEC” is not).
 * 
 * Subsequence "ACE" is present in string "ABACEBCBE" twice...
 * 
 * @author Prashant.Bhagat
 *
 */
public class DistinctSubsequence {

	public static void main(String[] args) {
		String mainString = "ABACEBCBE";
		String subSeqToFind = "AABE";
		int count = countDistinctSubsequenceRecurse(mainString, subSeqToFind);
		System.out.println("Recurse : " + count);
		count = countDistinctSubSeqDynamic(mainString, subSeqToFind);
		System.out.println("dp : " + count);
	}

	private static int countDistinctSubSeqDynamic(String mainString, String subSeqToFind) {
		if (mainString == null || mainString.length() == 0 || subSeqToFind == null || subSeqToFind.length() == 0)
			return 0;

		int dp[][] = new int[subSeqToFind.length()][mainString.length() + 1];

		for (int i = 1; i < dp[0].length; i++) {
			if (subSeqToFind.charAt(0) == mainString.charAt(i - 1)) {
				dp[0][i] = dp[0][i - 1] + 1;
			} else {
				dp[0][i] = dp[0][i - 1];
			}
		}
		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[0].length; j++) {
				if (mainString.charAt(j - 1) == subSeqToFind.charAt(i)) {
					dp[i][j] = Math.min(dp[i][j - 1] + 1, dp[i - 1][j - 1]);
				} else {
					dp[i][j] = dp[i][j - 1];
				}
			}
		}
		return dp[dp.length - 1][dp[0].length - 1];
	}

	private static int countDistinctSubsequenceRecurse(String mainString, String subSeqToFind) {
		if (mainString == null || mainString.length() == 0 || subSeqToFind == null || subSeqToFind.length() == 0)
			return 0;
		int count = 0;
		boolean[] flag = new boolean[mainString.length()];
		StringBuilder mainStringBuilder = new StringBuilder();
		StringBuilder subSeqBuilder = new StringBuilder(subSeqToFind);
		for (int i = 0, j = 0; i < mainString.length(); i++) {
			if (mainString.charAt(i) == subSeqBuilder.charAt(j)) {
				j++;
				if (j == subSeqBuilder.length()) {
					count++;
					j = 0;
				}
				flag[i] = true;
			}
		}
		int i = 0;
		if (count > 0) {
			for (boolean f : flag) {
				if (!f) {
					mainStringBuilder.append(mainString.charAt(i));
				}
				i++;
			}
		}
		count = count + countDistinctSubsequenceRecurse(mainStringBuilder.toString(), subSeqToFind);
		return count;
	}

}
