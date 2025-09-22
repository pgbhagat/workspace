package com.dynamicprogramming;

public class NthTribonacci {
    public static int tribonacci(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            int minus = 0;
            if (i >= 4) {
                minus = dp[i - 4];
            }
            dp[i] = dp[i - 1] * 2 - minus;
        }
        return dp[n];
    }

    public static int tribonacciOpt(int n) {
        int[] dp = new int[3];

        dp[0] = 0;
        dp[1] = dp[2] = 1;
        if (n < 3) {
            return dp[n];
        }
        for (int i = 3; i <= n; i++) {
            int sum = dp[0] + dp[1] + dp[2];
            dp[0] = dp[1];
            dp[1] = dp[2];
            dp[2] = sum;
        }
        return dp[2];

    }

    public static void main(String... args) {
        System.out.println(tribonacci(4));
        System.out.println(tribonacciOpt(4));
    }

}
