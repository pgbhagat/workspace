package com.array;

public class SearchRangeInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        result[0] = binarySearchFirst(nums, target);
        result[1] = binarySearchLast(nums, target);
        return result;
    }

    public int binarySearchLast(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int index = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target > nums[mid]) {
                left = mid + 1;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                index = mid;
                left = mid + 1;
            }

        }
        return index;
    }

    public int binarySearchFirst(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int index = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target > nums[mid]) {
                left = mid + 1;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                index = mid;
                right = mid - 1;
            }
        }
        return index;
    }
}
