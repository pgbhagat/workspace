package com.array;

import java.util.Arrays;

public class NextHighestPermutation {

    public static void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j > i && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
            reverse(nums, i + 1);
        }
    }

    private static void reverse(int[] nums, int i) {
        int right = nums.length - 1;
        int left = i;
        while (right > left) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String... args) {
        int[] nums = new int[]{-1, 2, 1, -4};
        nextPermutation(nums);
        Arrays.stream(nums).forEach(a -> System.out.print(a + " "));

        nums = new int[]{3, 2, 1};
        nextPermutation(nums);
        Arrays.stream(nums).forEach(a -> System.out.print(a + " "));
    }

}
