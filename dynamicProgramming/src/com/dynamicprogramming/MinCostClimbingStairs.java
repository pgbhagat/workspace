package com.dynamicprogramming;

public class MinCostClimbingStairs {
    public static int minCostClimbingStairs(int[] cost) {
        int[] minCost = new int[cost.length + 1];
        for (int i = 0; i < cost.length; i++) {
            minCost[i] = cost[i];
        }

        for (int i = cost.length - 2; i >= 0; i--) {
            int costOfOneJump = minCost[i] + minCost[i + 1];
            int costOfTwoJump = minCost[i] + minCost[i + 2];
            minCost[i] = Math.min(costOfOneJump, costOfTwoJump);
        }
        return Math.min(minCost[0], minCost[1]);
    }

    public static void main(String... args) {
        System.out.println(minCostClimbingStairs(new int[]{10, 15, 20}));
    }
}
