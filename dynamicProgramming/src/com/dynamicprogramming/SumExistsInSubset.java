package com.dynamicprogramming;

public class SumExistsInSubset {

	public static void sumExistsRecursion(int index, int sum, int[] elements, int[] solution) {
		if (sum == 0) {
			// print solution
			System.out.println("set - ");
			for (int i = 0; i < solution.length; i++) {
				if (solution[i] == 1) {
					System.out.print(" " + elements[i]);
				}
			}
			System.out.println();
		} else {
			if (index <= elements.length - 1 && sum > 0) {
				solution[index] = 1;
				sumExistsRecursion(index + 1, sum - elements[index], elements, solution);
				solution[index] = 0;
				sumExistsRecursion(index + 1, sum, elements, solution);
			}
		}
	}

	public static boolean[][] sumExists(int[] in, int sum) {
		int[] input = new int[in.length + 1];
		input[0] = 0;
		for (int i = 0; i < in.length; i++) {
			input[i + 1] = in[i];
		}

		boolean[][] sol = new boolean[input.length][sum + 1];

		for (int i = sol.length - 1; i >= 0; i--) {
			sol[i][0] = true;
		}

		for (int i = 1; i < sol.length; i++) {
			for (int j = 1; j < sol[0].length; j++) {
				sol[i][j] = sol[i - 1][j] || (j == input[i]);
				if (sol[i][j] == false && j > input[i] && sol[i - 1][j - input[i]]) {
					//sol[i][j] = sol[i - 1][j - input[i]];
					sol[i][j] = true;
				}

			}
		}
		return sol;

	}

	public static void main(String[] args) {
		int[] input = { 3, 2, 1, 5, 7 };
		boolean[][] sol = sumExists(input, 6);
		for (boolean[] b : sol) {
			for (boolean v : b) {
				System.out.print(v + " ");
			}
			System.out.println();
		}
		sumExistsRecursion(0, 6, input, new int[input.length]);
	}

}
