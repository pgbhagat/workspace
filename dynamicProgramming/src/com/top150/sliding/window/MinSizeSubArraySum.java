package com.top150.sliding.window;

public class MinSizeSubArraySum {
    public static int minSubArrayLen(int target, int[] nums) {
        int minSize = Integer.MAX_VALUE;
        int left = 0, right = 0;
        int currentSum = 0;
        while (left < nums.length && right < nums.length) {
            if (left == right) {
                currentSum = nums[left];
            }
            if (currentSum >= target) {
                minSize = Math.min(minSize, right - left + 1);
                currentSum = currentSum - nums[left];
                left++;
            } else if (currentSum < target) {
                right++;
                if (right < nums.length) {
                    currentSum += nums[right];
                }
            }
        }
        return minSize == Integer.MAX_VALUE ? 0 : minSize;
    }

    public static void main(String... args) {
        System.out.println(minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
        System.out.println(minSubArrayLen(4, new int[]{1, 4, 4}));
        System.out.println(minSubArrayLen(11, new int[]{1, 2, 3, 4, 5}));
    }

}
