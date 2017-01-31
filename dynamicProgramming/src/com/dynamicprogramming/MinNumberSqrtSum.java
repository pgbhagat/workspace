package com.dynamicprogramming;

public class MinNumberSqrtSum {

	public static int getMinSquares(int n) {
		// base cases
		if (n <= 3) {
			return n;
		}

		int minSquareNums = n; // Maximum squares required is n (1*1 + 1*1 + ..)
		for (int i = 1; i <= n; i++) {
			int temp = i * i;
			if (temp > n) {
				break;
			} else {
				minSquareNums = Math.min(minSquareNums, 1 + getMinSquares(n - temp));
			}
		}
		return minSquareNums;
	}

	public static int getMinSquaresDynamicProgramming(int n) {

		int dp[] = new int[n + 1];

		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 3;

		for (int i = 4; i <= n; i++) {
			// max value is i as i can always be represented
			// as 1*1 + 1*1 + ...
			dp[i] = i;

			for (int j = 1; j <= i; j++) {
				int temp = j * j;
				if (temp > i)
					break;
				else
					dp[i] = Math.min(dp[i], 1 + dp[i - temp]);
			}
		}

		return dp[n];
	}

	public static void main(String[] args) {
		System.out.println(getMinSquares(6));
		System.out.println(getMinSquares(13));
	}

}
