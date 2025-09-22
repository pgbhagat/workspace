package com.top150.array.string;

import java.util.Arrays;

public class ProductOfArrayExceptSelf {

    public static int[] productExceptSelf(int[] nums) {
        int product = 1;
        int[] answer = new int[nums.length];
        int countOfZero = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                countOfZero++;
            } else {
                product *= nums[i];
            }
        }
        if (countOfZero == 1) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 0)
                    answer[i] = product;
            }
        } else if (countOfZero == 0) {
            answer[0] = product / nums[0];
            for (int i = 1; i < nums.length; i++) {
                answer[i] = answer[i - 1] * nums[i - 1] / nums[i];
            }
        }
        return answer;
    }

    public static void main(String... args) {
        System.out.println(Arrays.toString(productExceptSelf(new int[]{1, 2, 3, 4})));
        System.out.println(Arrays.toString(productExceptSelf(new int[]{-1, 1, 0, -3, 3})));
        System.out.println(Arrays.toString(productExceptSelf(new int[]{4,3,2,1,2})));
    }
}
