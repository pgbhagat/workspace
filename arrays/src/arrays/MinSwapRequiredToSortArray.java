package arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//http://www.geeksforgeeks.org/minimum-number-swaps-required-sort-array/
/**
 * 
 * Given an array of n distinct elements, find the minimum number of swaps
 * required to sort the array.
 * 
 * Examples:
 * 
 * Input : {4, 3, 2, 1} Output : 2
 * 
 * Explanation : Swap index 0 with 3 and 1 with 2 to form the sorted array {1,
 * 2, 3, 4}.
 * 
 * Input : {1, 5, 4, 3, 2} Output : 2
 * 
 * 
 * @author Prashant.Bhagat
 *
 */
public class MinSwapRequiredToSortArray {

	public static void main(String[] args) {
		//int[] input = { 4, 3, 2, 1 };
		int [] input = {1, 5, 4, 2, 3, 3, 4};
		int min = getMinSwapReqToSortArray(input);
		System.out.println(min);

	}

	static class Pair implements Comparable<Pair> {
		int value;
		int position;

		@Override
		public int compareTo(Pair o) {
			return this.value - o.value;
		}
	}

	private static int getMinSwapReqToSortArray(int[] input) {
		List<Pair> pairs = new ArrayList<>();
		for (int i = 0; i < input.length; i++) {
			Pair pair = new Pair();
			pair.value = input[i];
			pair.position = i;
			pairs.add(pair);
		}
		Collections.sort(pairs);

		int minSwap = 0;
		boolean[] visited = new boolean[input.length];
		for (int i = 0; i < input.length; i++) {
			if (visited[i] == true || pairs.get(i).position == i) {
				continue;
			}
			int currSwaps = 0;
			int j = i;
			while (visited[j] == false) {
				visited[j] = true;
				j = pairs.get(j).position;
				currSwaps++;
			}
			minSwap += (currSwaps - 1);

		}
		return minSwap;
	}

}
