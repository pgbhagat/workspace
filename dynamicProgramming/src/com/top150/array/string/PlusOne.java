package com.top150.array.string;

import java.util.Arrays;

public class PlusOne {

    public static int[] plusOne(int[] digits) {
        int[] result;
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        result = new int[digits.length + 1];
        result[0] = 1;
        for (int i = 0; i < digits.length; i++) {
            result[i + 1] = digits[i];
        }
        return result;
    }

    public static void main(String... args) {
        Arrays.stream(plusOne(new int[]{1, 2, 4, 5, 9})).forEach(n -> System.out.print(n + " "));
        System.out.println();
        Arrays.stream(plusOne(new int[]{9, 9})).forEach(n -> System.out.print(n + " "));
    }
}
