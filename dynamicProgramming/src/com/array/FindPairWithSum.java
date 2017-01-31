package com.array;

import java.util.Arrays;

public class FindPairWithSum {

	public static void main(String[] args) {
		int[] input = new int[] { 777, 1, 2, 1, 2, 4, 6, 3, 2, 45, 6, 23, 56, 342, 76, 21, 19 };
		findPairWithSum(input, 781);
		findPairWithSum(input, 782);
	}

	private static void findPairWithSum(int[] input, int sum) {
		Arrays.sort(input);
		int low = 0;
		int high = input.length - 1;

		while (low < high) {
			int tmpSum = input[low] + input[high];
			if (tmpSum == sum) {
				System.out.println("Pair - {" + input[low] + ", " + input[high] + "} with sum - " + sum);
				break;
			} else if (tmpSum > sum) {
				high--;
			} else {
				low++;
			}
		}
	}
}
