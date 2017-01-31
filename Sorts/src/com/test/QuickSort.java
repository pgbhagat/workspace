package com.test;

public class QuickSort {

	public static void main(String[] args) {
		int[] input = new int[] { 777, 1, 2, 1, 2, 4, 6, 3, 2, 45, 6, 23, 56, 342, 76, 21, 19 };
		quickSort(input, 0, input.length - 1);
		print(input);
	}

	private static void quickSort(int[] input, int low, int high) {
		if (low >= high) {
			return;
		}
		int j = partition(input, low, high);
		quickSort(input, low, j - 1);
		quickSort(input, j + 1, high);
	}

	private static int partition(int[] input, int low, int high) {
		int pivot = input[low];
		int down = low;
		int up = high;
		while (down < up) {
			while (input[down] <= pivot && down < high) {
				down++;
			}
			while (input[up] > pivot) {
				up--;
			}
			if (up > down) {
				int tmp = input[up];
				input[up] = input[down];
				input[down] = tmp;
			}
		}
		input[low] = input[up];
		input[up] = pivot;
		return up;
	}

	private static void print(int[] input) {
		for (int el : input) {
			System.out.print(el + " ");
		}
	}
}
