package com.dynamicprogramming;

public class DivisorGame {

    public static boolean divisorGame(int n) {
        boolean[] dp = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            //for (int j = 1; j <= i/2; j++) {
            for (int j = 1; j < i; j++) {
                if (i % j == 0 && dp[i - j] == false) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

    public static void main(String... args) {
        System.out.println(divisorGame(3));
    }
}
