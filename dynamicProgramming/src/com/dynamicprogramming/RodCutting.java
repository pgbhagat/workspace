package com.dynamicprogramming;

public class RodCutting {

	public static int rodCutMaxProfit(int rodLen, int[] rodPrices) {
		int maxProfit = 0;
		if (rodLen > 0) {
			int max = -1;
			for (int i = 1; i <= rodLen; i++) {
				max = Math.max(max, rodPrices[i] + rodCutMaxProfit(rodLen - i, rodPrices));
			}
			maxProfit = max;
		}
		return maxProfit;
	}

	public static int rodCutMaxProfitWithMemorization(int rodLen, int[] rodPrices, int[] memory) {
		if (memory[rodLen] > 0) {
			return memory[rodLen];
		}
		int maxProfit = 0;
		if (rodLen > 0) {
			int max = -1;
			for (int i = 1; i <= rodLen; i++) {
				max = Math.max(max, rodPrices[i] + rodCutMaxProfit(rodLen - i, rodPrices));
			}
			maxProfit = max;
		}
		memory[rodLen] = maxProfit;
		return maxProfit;
	}

	public static void main(String[] args) {
		int rodLength = 33;
		int[] rodPrices = new int[] { 0, 2, 3, 7, 8, 9, 11, 16, 28, 89, 100, 2, 3, 7, 8, 9, 11, 16, 28, 89, 100, 2, 3,
				7, 8, 9, 11, 16, 28, 89, 100, 2, 3, 7, 8, 9, 11, 16, 28, 89, 100, 2, 3, 7, 8, 9, 11, 16, 28, 89, 100, 2,
				3, 7, 8, 9, 11, 16, 28, 89, 100 };
		long startTime = System.currentTimeMillis();
		System.out.println("max rod cut profit - " + rodCutMaxProfit(rodLength, rodPrices));
		long endTime = System.currentTimeMillis();
		System.out.println("Time taken - " + (endTime - startTime));

		int[] mem = new int[rodLength + 1];
		startTime = System.currentTimeMillis();
		System.out.println(
				"max rod cut profit with memory - " + rodCutMaxProfitWithMemorization(rodLength, rodPrices, mem));
		endTime = System.currentTimeMillis();
		System.out.println("Time taken with mem- " + (endTime - startTime));
	}

}
