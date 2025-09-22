package com.top150.hashmap;

public class HappyNumber {
    public static boolean isHappy(int n) {
        if (n == 1) return true;
        if (n < 10) return false;

        int sum = 0;
        while (n > 0) {
            int tmp = n % 10;
            sum += (tmp * tmp);
            n = n / 10;
        }
        return isHappy(sum);
    }

    public static void main(String... args) {
        System.out.println(isHappy(19));
    }
}
