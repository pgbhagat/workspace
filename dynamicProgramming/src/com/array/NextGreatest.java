package com.array;

/**
 * 
 * Replace every element with the next greatest
 * 
 * if the array is {6, 7, 4, 3, 5, 2}, output {7, 5, 5, 5, 2, 0}
 * 
 * @author Prashant.Bhagat
 *
 */
public class NextGreatest {

	public static void main(String[] args) {
		// int[] input = new int[] { 2, 5, 6, 7, 2, 3, 4, 8, 3, 5 };
		int[] input = new int[] { 6, 7, 4, 3, 5, 2 };
		int[] output = replaceNextGreatest(input);
		for (int ele : output) {
			System.out.print(ele + " ");
		}
	}

	private static int[] replaceNextGreatest(int[] input) {
		if (input == null || input.length < 2) {
			return input;
		} else {
			int greatestTillNow = input[input.length - 1];
			input[input.length - 1] = 0;
			for (int i = input.length - 2; i >= 0; i--) {
				int tmp = input[i];
				input[i] = greatestTillNow;
				if (tmp > greatestTillNow)
					greatestTillNow = tmp;
			}
			return input;
		}
	}

}
