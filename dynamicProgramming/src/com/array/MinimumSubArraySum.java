package com.array;

import java.util.Arrays;

public class MinimumSubArraySum {

	public static void main(String[] args) {
		int[] input = { 1, 5, 20, 70, 8 };
		int[] result = minSubArrSum(input, 95);
		print(result);
	}

	private static void print(int[] result) {
		for (int ele : result) {
			System.out.print(ele + " ");
		}
	}

	private static int[] minSubArrSum(int[] input, int sum) {
		int start = 0;
		int minSubArrLen = input.length;
		int currSum = 0;
		int startIndex = 0;
		int endIndex = 0;
		Arrays.sort(input);
		for (int i = 0; i <= input.length; i++) {
			while (currSum > sum) {
				currSum = currSum - input[start];
				start++;
				if (currSum > sum && i - start < minSubArrLen) {
					minSubArrLen = i - start;
					startIndex = i;
					endIndex = start;
				}
			}
			if (i < input.length) {
				currSum = currSum + input[i];
			}
		}
		int i = 0, j = 0;
		if (startIndex < endIndex) {
			i = startIndex;
			j = endIndex;
		} else {
			j = startIndex;
			i = endIndex;
		}
		return Arrays.copyOfRange(input, i, j);
	}

}
