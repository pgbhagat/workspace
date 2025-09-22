package com.dynamicprogramming;

public class CountNumbersWithUniqueDigits {

    public static int numberCount(int a, int b) {
        int count = 0;
        for (int i = a; i <= b; i++) {
            if (hasUniqueDigits(i)) {
                count++;
            }
        }
        return count;
    }

    private static boolean hasUniqueDigits(int i) {
        boolean[] digits = new boolean[10];
        if (i == 0) {
            return true;
        }
        while (i > 0) {
            int digit = i % 10;
            if (digits[digit]) {
                return false;
            }
            digits[digit] = true;
            i = i / 10;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(numberCount(10, 20));
    }
}
