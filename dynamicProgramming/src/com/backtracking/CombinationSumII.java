package com.backtracking;

import java.util.*;
import java.util.stream.Collectors;

public class CombinationSumII {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new LinkedList<>();
        backtrack(candidates, 0, target, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] candidates, int index, int target, ArrayList<Integer> tmpResult, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(tmpResult));
        } else if (target > 0) {
            for (int i = index; i < candidates.length; i++) {
                tmpResult.add(candidates[i]);
                backtrack(candidates, i + 1, target - candidates[i], tmpResult, result);
                tmpResult.remove(tmpResult.size() - 1);
            }
        }
    }
}
