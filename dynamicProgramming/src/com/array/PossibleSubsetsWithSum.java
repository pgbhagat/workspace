package com.array;

public class PossibleSubsetsWithSum {

	public static void main(String[] args) {
		printAllPossibleSubSetWithSum(4, "");
	}

	private static void printAllPossibleSubSetWithSum(int sum, String result) {
		if (sum == 0) {
			System.out.println("{" + result + "}");
		} else {
			for (int i = 1; i <= sum; i++) {
				printAllPossibleSubSetWithSum(sum - i, result + (result.isEmpty() ? "" : ",") + i);
			}
		}
	}

}
