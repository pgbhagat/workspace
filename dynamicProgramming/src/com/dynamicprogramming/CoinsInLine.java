package com.dynamicprogramming;

public class CoinsInLine {

	public static int moveRecursive(int[] coins, int start, int end) {
		int value = 0;
		if (start == end) {
			value = coins[start];
		} else if (start < end) {
			int startValue = coins[start]
					+ Math.min(moveRecursive(coins, start + 2, end), moveRecursive(coins, start + 1, end - 1));
			int endValue = coins[end]
					+ Math.min(moveRecursive(coins, start + 1, end - 1), moveRecursive(coins, start, end - 2));
			value = Math.max(startValue, endValue);
		}
		return value;

	}

	// https://www.youtube.com/watch?v=WxpIHvsu1RI
	public static int dynamicWay(int[] coins) {
		class GameEntry {
			int firstPlayerCoins = 0;
			int secondPlayerCoins = 0;
		}

		GameEntry solution[][] = new GameEntry[coins.length][coins.length];
		for (int i = 0; i < solution.length; i++) {
			GameEntry entry = new GameEntry();
			entry.firstPlayerCoins = coins[i];
			entry.secondPlayerCoins = 0;
			solution[i][i] = entry;
		}

		for (int totalCoins = 2; totalCoins <= coins.length; totalCoins++) {
			for (int i = 0; i <= coins.length - totalCoins; i++) {
				int leftCoins = coins[i] + solution[i + 1][i + totalCoins - 1].secondPlayerCoins;
				int rightCoins = coins[i + totalCoins - 1] + solution[i][i + totalCoins - 2].secondPlayerCoins;
				int maxValue = Math.max(leftCoins, rightCoins);
				GameEntry entry = new GameEntry();
				entry.firstPlayerCoins = maxValue;
				entry.secondPlayerCoins = (maxValue == leftCoins ? solution[i + 1][i + totalCoins - 1].firstPlayerCoins
						: solution[i][i + totalCoins - 2].firstPlayerCoins);
				solution[i][i + totalCoins - 1] = entry;
			}
		}
		return solution[0][solution.length - 1].firstPlayerCoins;

	}

	public static void main(String[] args) {
		int[] coins = { 6, 9, 1, 2, 16, 8 };
		System.out.println("Alice best move recursion - " + moveRecursive(coins, 0, coins.length - 1));
		System.out.println("Alice best move dynamic programming - " + dynamicWay(coins));
	}
}
