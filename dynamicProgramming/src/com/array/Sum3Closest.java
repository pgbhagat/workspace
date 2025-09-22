package com.array;

import java.util.Arrays;

public class Sum3Closest {
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closest = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < nums.length - 2; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                int diff = Math.abs(target - sum);
                if(diff < Math.abs(closest - target)) {
                    closest = sum;
                }
                if (sum == target) {
                    return sum;
                } else if (sum < target) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return closest;
    }

    public static void main(String... args) {
        System.out.println(threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
    }

}
