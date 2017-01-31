package com.test;

public class MergeSort {
	static int mult = 0;
	static int count = 0;

	public static void main(String[] args) {
		int[] input = { 2, 4, 3, 2, 5, 1, 4, 6, 9, 2, 4, 6, 9, 0 };
		mergeSort(input);
		print(input);
	}

	private static void mergeSort(int[] input) {
		int size = input.length;
		if (size > 1) {
			mult++;
			int mid = size / 2;
			int[] left = new int[mid];
			int[] right = new int[size - mid];
			for (int i = 0; i < mid; i++) {
				left[i] = input[i];
			}
			for (int i = mid; i < size; i++) {
				right[i - mid] = input[i];
			}
			mergeSort(left);
			mergeSort(right);
			merge(left, right, input);
		}
	}

	private static void merge(int[] left, int[] right, int[] sortedResult) {
		count++;
		int leftSize = left.length;
		int rightSize = right.length;
		int i = 0, j = 0, k = 0;
		while (i < leftSize && j < rightSize) {
			if (left[i] < right[j]) {
				sortedResult[k++] = left[i++];
			} else {
				sortedResult[k++] = right[j++];
			}
		}
		while (i < leftSize) {
			sortedResult[k++] = left[i++];
		}
		while (j < rightSize) {
			sortedResult[k++] = right[j++];
		}
	}
	
	private static void print(int[] input) {
		for (int el : input) {
			System.out.print(el + " ");
		}
		System.out.println("count - " + count + ", mult - " + mult);
	}
}
