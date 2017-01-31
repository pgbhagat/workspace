package com.dynamicprogramming;

public class LongestIncreasingSubSequence {

	public static int getLongestIncreasingSubseqRecurse(int[] input, int currentIndex) {
		int maxSeq = 1;
		if (currentIndex < input.length - 1) {
			int tmpIndex = currentIndex + 1;
			while (tmpIndex <= input.length - 1) {
				int subSeq = 0;
				if (input[currentIndex] < input[tmpIndex]) {
					subSeq = 1 + getLongestIncreasingSubseqRecurse(input, tmpIndex);
					if (subSeq > maxSeq) {
						maxSeq = subSeq;
						System.out
								.println("max seq - " + maxSeq + ", current - " + currentIndex + ", tmp - " + tmpIndex);
					}
				}
				tmpIndex++;
			}
		}
		return maxSeq;
	}

	public static void getLongestIncreasingSeqDynamicProgramming(int[] input) {
		if (input == null)
			return;
		if (input.length == 1) {
			System.out.println(1);
		}
		int[] solution = new int[input.length];
		for (int i = 0; i < input.length; i++) {
			solution[i] = 1;
		}

		for (int i = 1; i < input.length; i++) {
			for (int j = 0; j < i; j++) {
				if (input[i] > input[j]) {
					if (solution[j] + 1 > solution[i]) {
						solution[i] = solution[j] + 1;
					}
				}
			}
		}

		int maxIndex = 0;
		int maxSeq = -1;
		for (int i = 0; i < solution.length; i++) {
			if (solution[i] > maxSeq) {
				maxSeq = solution[i];
				maxIndex = i;
			}
		}

		System.out.println("Max seq length - " + maxSeq);
		System.out.print("seq - " + input[maxIndex]);

		int nextValue = maxSeq - 1;
		for (int i = maxIndex - 1; i >= 0; i--) {
			if (solution[i] == nextValue) {
				System.out.print(" " + input[i]);
				nextValue--;
			}
		}

	}

	public static void main(String[] args) {
		// int[] seq = { 1, 12, 7, 0, 23, 11, 52, 31, 61, 69, 70, 2 };
		// int[] seq = { 1, 12, 7, 0, 23, 11, 52, 31, };
		// int[] seq = { 1, 21, 24, 23, 25, 26, 27, 28 };
		int[] seq = { 1, 2, 3, 4, 5, 6, 7 };
		System.out.println(getLongestIncreasingSubseqRecurse(seq, 0));
		getLongestIncreasingSeqDynamicProgramming(seq);
	}

}
