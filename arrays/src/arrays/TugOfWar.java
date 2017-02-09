package arrays;

import java.util.Arrays;

/**
 * 
 * Given a set of n integers, divide the set in two subsets of n/2 sizes each
 * such that the difference of the sum of two subsets is as minimum as possible.
 * If n is even, then sizes of two subsets must be strictly n/2 and if n is odd,
 * then size of one subset must be (n-1)/2 and size of other subset must be
 * (n+1)/2.
 * 
 * For example, let given set be {3, 4, 5, -3, 100, 1, 89, 54, 23, 20}, the size
 * of set is 10. Output for this set should be {4, 100, 1, 23, 20} and {3, 5,
 * -3, 89, 54}. Both output subsets are of size 5 and sum of elements in both
 * subsets is same (148 and 148). Let us consider another example where n is
 * odd. Let given set be {23, 45, -34, 12, 0, 98, -99, 4, 189, -1, 4}. The
 * output subsets should be {45, -34, 12, 98, -1} and {23, 0, -99, 4, 189, 4}.
 * The sums of elements in two subsets are 120 and 121 respectively.
 * 
 * 
 * http://www.geeksforgeeks.org/tug-of-war/
 * 
 * 
 * @author Prashant.Bhagat
 *
 */
public class TugOfWar {

	static boolean[] solution;
	static int minSum = Integer.MAX_VALUE;

	public static void main(String[] args) {
		//int[] input = { 3, 4, 5, -3, 100, 1, 89, 54, 23, 20 };
		int [] input = {23, 45, -34, 12, 0, 98, -99, 4, 189, -1, 4};
		int mid = input.length / 2;
		boolean[] used = new boolean[input.length];
		solution = new boolean[input.length];
		tugOfWar(input, mid, 0, 0, used);
		printSolution(input);
	}

	private static void printSolution(int[] input) {
		System.out.println("first half");
		int oneSum = 0;
		int twoSum = 0;
		for (int i = 0; i < input.length; i++) {
			if (solution[i] == true) {
				System.out.print(input[i] + ",");
				oneSum += input[i];
			}
		}

		System.out.println();
		System.out.println("second half");
		for (int i = 0; i < input.length; i++) {
			if (solution[i] == false) {
				System.out.print(input[i] + ",");
				twoSum += input[i];
			}
		}
		System.out.println();
		System.out.println("first half sum - " + oneSum + ", second half sum - " + twoSum);
	}

	private static void tugOfWar(int[] input, int size, int curr_size, int index, boolean[] used) {
		if (curr_size == size) {
			int oneSum = 0;
			int twoSum = 0;
			for (int i = 0; i < input.length; i++) {
				if (used[i] == true) {
					oneSum += input[i];
				} else {
					twoSum += input[i];
				}
			}
			if (Math.abs(twoSum - oneSum) < minSum) {
				solution = Arrays.copyOf(used, used.length);
				minSum = Math.abs(twoSum - oneSum);
			}
		}
		if (index == input.length) {
			return;
		}
		used[index] = true;
		tugOfWar(input, size, curr_size + 1, index + 1, used);
		used[index] = false;
		tugOfWar(input, size, curr_size, index + 1, used);
	}

}
