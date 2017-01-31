package com.test;

public class HeapSort {

	public static void main(String[] args) {
		int[] input = new int[] { 777, 1, 2, 1, 2, 4, 6, 3, 2, 45, 6, 23, 56, 342, 76, 21, 19 };
		heapSort(input);
		print(input);
	}

	private static void heapSort(int[] input) {
		buildInitialMinHeap(input);
		for (int i = input.length - 1; i >= 0; i--) {
			int min = extractMin(input);
			adjustMinHeap(input, i - 1, input[i]);
			input[i] = min;
		}
	}

	private static void buildInitialMinHeap(int[] input) {
		for (int i = 0; i < input.length; i++) {
			insertInMinHeap(input, i, input[i]);
		}
	}

	private static void insertInMinHeap(int[] input, int index, int indexValue) {
		int son = index;
		int value = indexValue;
		int father = (son - 1) / 2;
		while (son > 0 && input[father] > value) {
			input[son] = input[father];
			son = father;
			father = (son - 1) / 2;
		}
		input[son] = value;
	}

	private static int extractMin(int[] input) {
		return input[0];
	}

	private static void adjustMinHeap(int[] input, int till, int value) {
		int father = 0;
		int smallerSon = smallerSon(input, father, till);
		while (smallerSon > 0 && value > input[smallerSon]) {
			input[father] = input[smallerSon];
			father = smallerSon;
			smallerSon = smallerSon(input, father, till);
		}
		input[father] = value;
	}

	private static int smallerSon(int[] input, int father, int till) {
		int smallerSon = 2 * father + 1;
		if ((smallerSon + 1) <= till && input[smallerSon + 1] < input[smallerSon]) {
			smallerSon = smallerSon + 1;
		}
		if (smallerSon > till) {
			smallerSon = -1;
		}
		return smallerSon;
	}

	private static void print(int[] input) {
		for (int a : input) {
			System.out.print(a + " ");
		}
	}

}
