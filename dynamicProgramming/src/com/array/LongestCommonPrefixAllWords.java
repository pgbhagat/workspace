package com.array;

public class LongestCommonPrefixAllWords {

	public static void main(String[] args) {
		String inputStr = "Sumit Summation Summit Sum";
		String[] inputStrArr = inputStr.split(" ");
		System.out.println("longest common prefix amongsts all words - " + findCommonPrefix(inputStrArr));

	}

	private static String findCommonPrefix(String[] inputStrArr) {
		int longestCommonPrefix = 0;
		if (inputStrArr != null && inputStrArr.length > 0) {
			longestCommonPrefix = inputStrArr[0].length();
			for (int i = 1; i < inputStrArr.length; i++) {
				int currLen = 0;
				while (currLen < longestCommonPrefix && currLen < inputStrArr[i].length()
						&& inputStrArr[0].charAt(currLen) == inputStrArr[i].charAt(currLen)) {
					currLen++;
				}
				longestCommonPrefix = currLen;

			}
			return inputStrArr[0].substring(0, longestCommonPrefix);
		}
		return null;
	}

}
