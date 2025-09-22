package com.test;

public class Division {

    public static int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        if (dividend == Integer.MIN_VALUE && divisor == 1) {
            return Integer.MIN_VALUE;
        }
        boolean negative = (dividend < 0) ^ (divisor < 0);
        int result = 0;


        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);

        while (dividend >= divisor) {
            result++;
            dividend -= divisor;
        }
        return negative ? (-1 * result) : result;
    }

    public static void main(String... args) {
        System.out.println(divide(-2147483648, 2));
    }
}
