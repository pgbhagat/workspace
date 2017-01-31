package com.dynamicprogramming;

/**
 * @author Prashant.Bhagat
 *
 */
public class StairClimbingPuzzle {

	public static int climbStairsRecurseVersion1(int steps) {
		if (steps <= 0) {
			return 0;
		}
		return 1 + climbStairsRecurseVersion1(steps - 1) + climbStairsRecurseVersion1(steps - 2)
				+ climbStairsRecurseVersion1(steps - 3);
	}

	public static int climbStairsRecurseVersion2(int steps) {
		int ways = 1;
		if (steps <= 0) {
			ways = 0;
		} else {
			for (int i = 1; i <= 3; i++) {
				if (steps >= i) {
					ways += climbStairsRecurseVersion2(steps - i);
				}
			}
		}
		return ways;
	}

	public static int climbStairsDynamicProgramming(int steps, int[] subResults) {
		if (steps <= 0)
			return 0;
		if (subResults[steps] > 0) {
			return subResults[steps];
		}
		subResults[steps] = 1 + climbStairsDynamicProgramming(steps - 1, subResults)
				+ climbStairsDynamicProgramming(steps - 2, subResults)
				+ climbStairsDynamicProgramming(steps - 3, subResults);
		return subResults[steps];
	}

	public static void main(String[] args) {
		int steps = 5;
		System.out.println("Resursion 1- " + climbStairsRecurseVersion1(steps));
		System.out.println("Resursion 2- " + climbStairsRecurseVersion2(steps));
		int[] subResults = new int[steps + 1];
		System.out.println("DP- " + climbStairsDynamicProgramming(steps, subResults));
	}

}
