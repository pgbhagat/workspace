package com.dynamicprogramming;

import java.util.Arrays;
import java.util.OptionalInt;

public class MaxProductSubArray {
    public static int maxProduct(int[] nums) {
        OptionalInt maxOptional = Arrays.stream(nums).max();
        int maxProduct = maxOptional.isPresent() ? maxOptional.getAsInt() : 1;
        int currMax = 1;
        int currMin = 1;
        for (int n : nums) {
            if (n == 0) {
                currMax = 1;
                currMin = 1;
            } else {
                int tmpMax = n * currMax;
                currMax = Math.max(tmpMax, Math.max(currMin * n, n));
                currMin = Math.min(tmpMax, Math.min(currMin * n, n));
                maxProduct = Math.max(maxProduct, currMax);
            }
        }
        return maxProduct;
    }
        public static void main(String... args) {
        System.out.println(maxProduct(new int[]{2, 3, -2, 4}));
    }

}
