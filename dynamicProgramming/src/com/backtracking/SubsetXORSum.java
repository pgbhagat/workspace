package com.backtracking;

public class SubsetXORSum {
    public int subsetXORSum(int[] nums) {
        return backtrack(nums, 0, 0);
    }

    public int backtrack(int[] nums, int index, int xor) {
        if (index >= nums.length) {
            return xor;
        }
        //Do not include current element
        int exclSum = backtrack(nums, index + 1, xor);
        int inclSum = backtrack(nums, index + 1, xor ^ nums[index]);
        return exclSum + inclSum;
    }
}
