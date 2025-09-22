package com.dynamicprogramming;

public class InterleavingString {

    public static boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null && s2 == null && s3 == null) {
            return true;
        }
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }
        if (s1.isEmpty() && s2.isEmpty() && s3.isEmpty()) {
            return true;
        }
        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        dp[0][0] = true;
        //populate 0 row, s1 is empty and s2 is exact match of s3
        for (int i = 1; i <= s2.length(); i++) {
            dp[0][i] = dp[0][i - 1] && s2.charAt(i - 1) == s3.charAt(i - 1);
        }
        //populate 0 column, s2 is empty and s1 is exact match of s3
        for (int i = 1; i <= s1.length(); i++) {
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                boolean s1Match = s3.charAt(i + j - 1) == s1.charAt(i - 1) && dp[i - 1][j];
                boolean s2Match = s3.charAt(i + j - 1) == s2.charAt(j - 1) && dp[i][j - 1];
                dp[i][j] = s1Match || s2Match;
            }
        }
        return dp[s1.length()][s2.length()];
    }

    public static void main(String... args) {
        System.out.println(isInterleave("", "", ""));
        System.out.println(isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        System.out.println(isInterleave("aabcc", "dbbca", "aadbbcbaac"));
    }
}
