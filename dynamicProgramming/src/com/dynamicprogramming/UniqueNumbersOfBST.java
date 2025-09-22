package com.dynamicprogramming;

public class UniqueNumbersOfBST {

    public int uniqueNumberOfBST(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            int count = 0;
            for (int j = 1; j <= i; j++) {
                int left = j - 1;
                int right = i - j;
                count += (dp[left] * dp[right]);
            }
            dp[i] = count;
        }
        return dp[n];
    }
}
