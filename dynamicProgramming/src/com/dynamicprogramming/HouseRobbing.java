package com.dynamicprogramming;

//No two adjacent house can be robbed.
//Every house has some money, rob these houses so to maximize the robbery.

public class HouseRobbing {

	public static void main(String[] args) {
		int[] houses = { 4, 3, 1, 9, 7 };
		int maxRobbery = robHousesDp(houses);
		System.out.println("max robbery using dp : " + maxRobbery);
	}

	private static int robHousesDp(int[] houses) {
		if (houses == null || houses.length == 0) {
			return 0;
		}
		if (houses.length == 1) {
			return houses[0];
		}
		int[][] dp = new int[2][houses.length + 1];
		dp[0][0] = 0;
		dp[1][0] = 0;
		// initial rob amount
		int i = 1;
		for (int amount : houses) {
			dp[0][i] = 0;
			dp[1][i++] = amount;
		}

		for (i = 2; i < dp[0].length; i++) {
			int if_ithIsRobbed = dp[1][i] + ((dp[1][i - 2] > dp[0][i - 2]) ? dp[1][i - 2] : dp[0][i - 2]);
			int if_ithNotRobbed = dp[0][i] + ((dp[1][i - 1] > dp[0][i - 1]) ? dp[1][i - 1] : dp[0][i - 1]);
			dp[0][i] = if_ithNotRobbed;
			dp[1][i] = if_ithIsRobbed;
		}
		printTheHousesTobeRobbed(dp, houses);
		return dp[0][dp[0].length - 1] > dp[1][dp[0].length - 1] ? dp[0][dp[0].length - 1] : dp[1][dp[0].length - 1];
	}

	private static void printTheHousesTobeRobbed(int[][] dp, int[] houses) {
		int maxRobbery = dp[0][dp[0].length - 1] > dp[1][dp[0].length - 1] ? dp[0][dp[0].length - 1]
				: dp[1][dp[0].length - 1];

		System.out.println("rob the houses : ");
		for (int i = dp[0].length - 1; i > 0; i--) {
			if (maxRobbery == dp[1][i]) {
				System.out.print(houses[i - 1] + " ");
				maxRobbery = maxRobbery - houses[i - 1];
			}
		}
		System.out.println();
	}

}
