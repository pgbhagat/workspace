package com.dynamicprogramming;

public class Fibonacci {

	public static int fibRecursive(int n) {
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			return fibRecursive(n - 1) + fibRecursive(n - 2);
		}
	}

	public static int fibIterative(int n) {
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		}
		int num0 = 0;
		int num1 = 1;
		int next = 0;
		for (int i = 2; i <= n; i++) {
			next = num0 + num1;
			num0 = num1;
			num1=next;
		}
		return next;

	}

	public static void main(String[] args) {
		System.out.println(fibRecursive(20));
		System.out.println(fibIterative(20));
	}
}
