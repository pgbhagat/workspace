package com.traffic.test;

import com.traffic.control.TrafficControl;

public class SimpleTest {
	public static String[] validInputOne = new String[] { "6,8", "1,2,8", "1,4,7", "1,5,12", "2,3,4", "2,4,2", "3,6,6",
			"4,6,8", "5,6,10", };
	public static String[] validInputTwo = new String[] { "4,6", "1,2,7", "1,2,8", "1,3,10", "2,4,4", "2,4,3",
			"3,4,15", };
	public static String[] validInputThree = new String[] { "8,11", "1,2,9", "1,5,2", "1,6,1", "2,3,4", "2,5,6",
			"3,4,7", "4,8,4", "5,7,3", "5,7,3", "6,5,7", "7,8,2" };
	public static String[] validInputFour = new String[] { "4,4", "1,2,7", "2,3,9", "2,4,11", "3,4,4", };
	public static String[] validInputFive = new String[] { "6,8", "1,2,8", "1,4,7", "1,5,12", "2,3,4", "2,4,2", "3,6,6",
			"4,6,8", "5,6,10" };
	public static String[] validInputSix = new String[] { "6,7", "1,2,4", "1,4,9", "2,3,8", "3,6,9", "4,2,12", "4,5,5",
			"5,3,7" };
	public static String[] validInputSeven = new String[] { "6,8", "1,2,4", "1,4,9", "2,3,8", "3,6,9", "4,2,12",
			"4,5,5", "5,3,7", "5,6,9" };

	public static String[] validInputEight = new String[] { "7,7", "1,2,4", "1,5,2", "2,3,4", "3,4,2", "4,7,9", "5,6,3",
			"6,4,8" };

	public static String[] invalidInputOne = new String[] { "6,7", "1,2,8", "1,4,7", "1,5,12", "2,3,4", "2,4,2",
			"3,6,6", "4,6,8", "5,6,10", };
	public static String[] invalidInputTwo = new String[] { "9,8", "1,2,8", "1,4,7", "1,5,12", "2,3,4", "2,4,2",
			"3,6,6", "4,6,8", "5,6,10", };
	public static String[] invalidInputThree = new String[] { "6,7", "1,2,1000000", "1,4,7", "1,5,12", "2,3,4", "2,4,2",
			"3,6,6", "4,6,8", "5,6,10", };
	public static String[] invalidInputFour = new String[] { "6,7", "1,2,1000", "1,7,7", "1,5,12", "2,3,4", "2,4,2",
			"3,6,6", "4,6,8", "5,6,10", };
	public static String[] invalidInputFive = new String[] { "6,7", "1,2,1000", "1,4,a", "1,5,12", "2,3,4", "2,4,2",
			"3,6,6", "4,6,8", "5,6,10", };

	public static void main(String[] args) {
		testAllInput();
	}

	public static void testAllInput() {
		System.out.println("Invalid input one output ->");
		testInput(invalidInputOne);

		System.out.println();
		System.out.println("Invalid iInput two output ->");
		testInput(invalidInputTwo);

		System.out.println();
		System.out.println("Invalid input three output ->");
		testInput(invalidInputThree);

		System.out.println();
		System.out.println("Invalid input four output ->");
		testInput(invalidInputFour);

		System.out.println();
		System.out.println("Invalid input five output ->");
		testInput(invalidInputFive);

		System.out.println();
		System.out.println("Valid input one output ->");
		testInput(validInputOne);

		System.out.println();
		System.out.println("Valid input two output ->");
		testInput(validInputTwo);

		System.out.println();
		System.out.println("Valid input three output ->");
		testInput(validInputThree);

		System.out.println();
		System.out.println("Valid input four output ->");
		testInput(validInputFour);

		System.out.println();
		System.out.println("Valid input five output ->");
		testInput(validInputFive);

		System.out.println();
		System.out.println("Valid input six output ->");
		testInput(validInputSix);

		System.out.println();
		System.out.println("Valid input seven output ->");
		testInput(validInputSeven);

		System.out.println();
		System.out.println("Valid input eightoutput ->");
		testInput(validInputEight);
	}

	private static void testInput(String[] in) {
		TrafficControl controller = new TrafficControl();
		String[] output = controller.getTollPlan(in);
		for (String line : output) {
			System.out.println(line);
		}
	}

}
