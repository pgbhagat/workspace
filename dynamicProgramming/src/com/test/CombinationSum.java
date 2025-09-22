package com.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CombinationSum {

  public static void main(String[] args) {
    CombinationSum generateParanthesis = new CombinationSum();
    System.out.println(generateParanthesis.combinationSum(new int[]{2, 3, 6, 7}, 7));
  }

  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> result = new LinkedList<>();
    List<Integer> intermediateResult = new LinkedList<>();
    backtrack(target, candidates, intermediateResult, result);
    return result;
  }

  public void backtrack(int target, int[] candidates, List<Integer> intermediateResult, List<List<Integer>> result) {
    if (target == 0) {
      result.add(new LinkedList<>(intermediateResult));
      return;
    }
    if (target < 0) {
      return;
    }
    for (int i = 0; i < candidates.length; i++) {
      intermediateResult.add(candidates[i]);
      backtrack(target - candidates[i], candidates, intermediateResult, result);
      ((LinkedList) intermediateResult).removeLast();
    }
  }

}
