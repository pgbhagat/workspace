package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Triplets {

	public static void main(String[] args) {

		Integer[] numbers = new Integer[] { -1, 2, -1, 4, 5, 6, 7, 8, 9, 0 };
		List<List<Integer>> triplets = findTripletsHavingSum(numbers, 4);
		System.out.println(triplets);
	}

	private static List<List<Integer>> findTripletsHavingSum(Integer[] numbers, int sum) {
		List<List<Integer>> triplets = new ArrayList<List<Integer>>();
		boolean[] consumed = new boolean[numbers.length];
		if (numbers.length > 3) {
			Arrays.sort(numbers);
			for (int i = 0; i < numbers.length - 2; i++) {
				int resultToLookFor = sum - numbers[i];
				int start = i + 1;
				int end = numbers.length - 1;
				while (start < end) {
					if (numbers[start] + numbers[end] == resultToLookFor && (consumed[i] == false
							|| consumed[start] == false || consumed[end] == false)) {
						List<Integer> triple = new ArrayList<Integer>(3);
						triple.add(numbers[i]);
						triple.add(numbers[start]);
						triple.add(numbers[end]);
						consumed[i] = true;
						consumed[start] = true;
						consumed[end] = true;
						triplets.add(triple);
						start++;
						end--;
					} else if (numbers[start] + numbers[end] < resultToLookFor) {
						start++;
					} else {
						end--;
					}
				}
			}
		}

		return triplets;
	}

}
