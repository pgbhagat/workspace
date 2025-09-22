package com.dynamicprogramming;

public class MaxRepeatingSubString {
    public static int maxRepeating(String sequence, String word) {
        int count = 0;
        String tmp = word;
        while (sequence.indexOf(tmp) != -1) {
            count++;
            tmp += tmp;
        }
        return count;
    }

    public static void main(String... args) {
        System.out.println(maxRepeating("aaabaaaabaaabaaaabaaaabaaaabaaaaba", "aaaba"));

    }
}
