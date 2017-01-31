package com.array;

public class AllSubsetOfASet {

	public static void main(String[] args) {
		int[] input = { 1, 2, 3, 4 };
		printAllSubset(input);
	}

	private static void printAllSubset(int[] input) {
		int[] aux = new int[input.length];
		recurse(input, aux, 0);
	}

	private static void recurse(int[] input, int[] aux, int index) {
		if (index == input.length) {
			printInput(input, aux);
		} else {
			aux[index] = 0;
			recurse(input, aux, index + 1);
			aux[index] = 1;
			recurse(input, aux, index + 1);
		}
	}

	private static void printInput(int[] input, int[] aux) {
		boolean isEmptySet = true;
		System.out.print(" {");
		for (int i = 0; i < aux.length; i++) {
			if (aux[i] == 1) {
				if (isEmptySet) {
					System.out.print(input[i]);
				} else {
					System.out.print(", " + input[i]);
				}
				isEmptySet = false;
			}
		}
		if (isEmptySet) {
			System.out.print("Empty}");
		} else {
			System.out.print("}");
		}
	}

}
