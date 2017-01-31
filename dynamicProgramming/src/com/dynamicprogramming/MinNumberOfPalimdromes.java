package com.dynamicprogramming;

public class MinNumberOfPalimdromes {

	public static void main(String[] args) {
		//String input = "xabaay";
		String input = "x";
		int minCuts = getMinCutPalimdromeCount(input);
		System.out.println("min cut required - " + minCuts);
	}

	private static int getMinCutPalimdromeCount(String input) {
		int minCut = 0;
		if (input.length() > 0 && !isPalimdrome(input)) {
			minCut = input.length();
			for (int i = 1; i < input.length(); i++) {
				minCut = Math.min(1 + getMinCutPalimdromeCount(input.substring(0, i))
						+ getMinCutPalimdromeCount(input.substring(i, input.length())), minCut);
			}
		}
		return minCut;
	}

	private static boolean isPalimdrome(String input) {
		boolean isPalim = true;
		if (input != null && !input.isEmpty()) {
			int totalSize = input.length();
			for (int i = 0; i < totalSize / 2; i++) {
				if (input.charAt(i) != input.charAt(totalSize - i - 1)) {
					isPalim = false;
					break;
				}
			}
		}
		return isPalim;
	}

}
