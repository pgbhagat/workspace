package com.dynamicprogramming;

import java.util.Arrays;

public class CountingBits {

    public static int[] countBits(int n) {
        int[] dp = new int[n + 1];
        int offset = 1;
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            if (offset * 2 == i) {
                offset = i;
            }
            dp[i] = 1 + dp[i - offset];
        }
        return dp;
    }

    public static int[] countBitsSimple(int n) {
        int[] result = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            int count = 0;
            int number = i;
            while (number > 0) {
                count += number % 2;
                number /= 2;
            }
            result[i] = count;
        }
        return result;
    }

    public static void main(String... args) {
        System.out.println(Arrays.toString(countBits(156)));
    }
}
