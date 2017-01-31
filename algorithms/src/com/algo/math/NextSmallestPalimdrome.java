package com.algo.math;

public class NextSmallestPalimdrome {

	public static void main(String... args) {
		printNextSmallestPalimdrome(121);
		printNextSmallestPalimdrome(99);
		printNextSmallestPalimdrome(9);
		printNextSmallestPalimdrome(4);
		printNextSmallestPalimdrome(742247);
		printNextSmallestPalimdrome(742246);
		printNextSmallestPalimdrome(732247);
		printNextSmallestPalimdrome(732246);
	}

	private static void printNextSmallestPalimdrome(int input) {
		int[] ints = intToChars(input);
		int[] answer = {};
		// condition 1.. if this is single digit number..
		if (ints.length == 1) {
			answer = handleSingleDigitCase(ints);
		} else if (areAllDigitsNine(ints)) {
			// condition.. if all digits are 9
			answer = handleAllNineDigitCase(ints);
		} else if (isPalindrome(ints)) {
			answer = handlePalimdroneCase(ints);
		} else {
			return;
		}
		printInts(answer);
	}

	private static int[] handlePalimdroneCase(int[] ints) {
		// add 1 at the middle element.
		// if the element is 9, then make it zero with carry as 1
		int mid = ints.length / 2;
		boolean evenDigits = ints.length % 2 == 0;
		if (evenDigits) {
			mid--;
		}
		boolean carry = false;
		int num;
		int result = 0;
		for (int i = mid; i >= 0; i--) {
			num = ints[i];
			if (carry) {
				num++;
				carry = false;
			}
			if (num == 9) {
				carry = true;
				result = 0;
			} else {
				result = num;
			}
			ints[i] = result;
		}
		return null;
	}

	private static boolean isPalindrome(int[] ints) {
		int start = 0;
		int end = ints.length - 1;
		while (start < end) {
			if (ints[start++] != ints[end--]) {
				return false;
			}
		}
		return true;
	}

	private static int[] handleAllNineDigitCase(int[] ints) {
		int[] nextSmallPalim = new int[ints.length + 1];
		nextSmallPalim[0] = nextSmallPalim[nextSmallPalim.length - 1] = 1;
		return nextSmallPalim;
	}

	private static boolean areAllDigitsNine(int[] ints) {
		for (int i : ints) {
			if (i != 9) {
				return false;
			}
		}
		return true;
	}

	private static int[] handleSingleDigitCase(int[] ints) {
		if (ints[0] == 9) {
			return new int[] { 1, 1 };
		} else {
			return new int[] { ints[0] + 1 };
		}

	}

	private static int[] intToChars(int input) {
		char[] chars = new Integer(input).toString().toCharArray();
		int[] ints = new int[chars.length];
		int i = 0;
		for (char c : chars) {
			ints[i++] = c - '0';
		}
		return ints;
	}

	private static void printInts(int[] answer) {
		StringBuilder result = new StringBuilder();
		for (int i : answer) {
			result.append(i);
		}
		System.out.println(result);
	}
}
