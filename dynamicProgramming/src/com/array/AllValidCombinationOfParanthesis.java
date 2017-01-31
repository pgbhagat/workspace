package com.array;

public class AllValidCombinationOfParanthesis {

	public static void main(String[] args) {
		printParanthesis(3, 3, "");
	}

	private static void printParanthesis(int openLeft, int closeLeft, String result) {
		if (openLeft == 0 && closeLeft == 0) {
			System.out.println(result + " ");
		} else {
			if (openLeft > closeLeft) {
				return;
			}
			if (closeLeft > 0) {
				printParanthesis(openLeft, closeLeft - 1, result + ")");
			}
			if (openLeft > 0) {
				printParanthesis(openLeft - 1, closeLeft, result + "(");
			}
		}
	}

}
