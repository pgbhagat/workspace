package com.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class AllSubsets {
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        boolean[] visited = new boolean[nums.length];
        backtrack(nums, 0, new ArrayList<>(), result, visited);
        return result;
    }

    private static void backtrack(int[] nums, int index, ArrayList<Integer> tmpResult, List<List<Integer>> result, boolean[] visited) {
        if (tmpResult.size() <= nums.length) {
            result.add(new ArrayList<>(tmpResult));
            for (int i = index; i < nums.length; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    tmpResult.add(nums[i]);
                    backtrack(nums, i + 1, tmpResult, result, visited);
                    tmpResult.remove(tmpResult.size() - 1);
                    visited[i] = false;
                }
            }
        }
    }

    public static void main(String... args) {
        System.out.println(subsets(new int[]{1, 2, 3}));
    }
}