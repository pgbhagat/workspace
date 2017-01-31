package com.array;

public class GreatestCommonDivisor {

	public static void main(String[] args) {

		int num1 = 282;
		int num2 = 156;
		int commonDivisor = getGreatestCommonDivisor(num1, num2);
		System.out.println(commonDivisor);

	}

	private static int getGreatestCommonDivisor(int num1, int num2) {
		if (num2 == 0) {
			return num1;
		}
		return getGreatestCommonDivisor(num2, num1 % num2);
	}

}
