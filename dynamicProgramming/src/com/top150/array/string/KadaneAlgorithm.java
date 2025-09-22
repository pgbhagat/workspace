package com.top150.array.string;

public class KadaneAlgorithm {
    //https://neetcode.io/courses/advanced-algorithms/0
    public static void maxSumSubArray(int[] num) {
        int currSum = num[0];
        int maxSum = num[0];
        int maxL = 0;
        int maxR = 0;
        int l = 0;
        for (int i = 1; i < num.length; i++) {
            if (currSum < 0) {
                l = i;
                currSum = 0;
            }
            currSum += num[i];
            if (currSum > maxSum) {
                maxR = i;
                maxL = l;
                maxSum = currSum;
            }

        }
        System.out.println(String.format("max subarray index = %d to %d, with max sum=%d", maxL, maxR, maxSum));

    }

    public static void main(String... args) {
        // maxSumSubArray(new int[]{6, -2, 7});
        maxSumSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
    }
}
