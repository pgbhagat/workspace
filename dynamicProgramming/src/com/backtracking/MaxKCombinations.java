package com.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MaxKCombinations {
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new LinkedList<>();
        backtrack(n, 1, k, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int end, int index, int max, ArrayList<Integer> tmpResult, List<List<Integer>> result) {
        if (tmpResult.size() == max) {
            result.add(new ArrayList<>(tmpResult));
        }
        if (tmpResult.size() < max) {
            for (int i = index; i <= end; i++) {
                tmpResult.add(i);
                backtrack(end, i + 1, max, tmpResult, result);
                tmpResult.remove(tmpResult.size() - 1);
            }
        }
    }

    public static void main(String... args) {
        System.out.println(combine(3, 2));
    }

}
