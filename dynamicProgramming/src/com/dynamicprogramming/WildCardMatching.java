package com.dynamicprogramming;

public class WildCardMatching {

	// considers only '*', '?' and alphanumeric in pattern..
	public static boolean isWildCardMatches(String pattern, String input) {

		if ((pattern == null || pattern.isEmpty()) && (input == null || input.isEmpty())) {
			return true;
		} else if ((pattern == null || pattern.isEmpty())) {
			return false;
		} else if (pattern.charAt(0) == '*' && pattern.length() == 1) {
			return true;
		}

		boolean matches = false;
		char[] arrPattern = pattern.toCharArray();
		char[] arrInput = input.toCharArray();

		int patternIndex = 0;
		while (arrPattern[patternIndex] == '*' && patternIndex < arrPattern.length) {
			patternIndex++;
		}
		if (patternIndex > 0) {
			patternIndex--;
		}

		boolean[][] solution = new boolean[input.length() + 1][pattern.length() - patternIndex + 1];
		pattern = pattern.substring(patternIndex);
		arrPattern = pattern.toCharArray();
		if (arrPattern[0] == '*') {
			for (int i = 0; i < solution.length; i++) {
				solution[i][1] = true;
			}
		} else {
			solution[0][0] = true;
		}

		for (int i = 1; i < solution.length; i++) {
			for (int j = 1; j < solution[0].length; j++) {
				if (arrInput[i - 1] == arrPattern[j - 1] || arrPattern[j - 1] == '?') {
					solution[i][j] = solution[i - 1][j - 1];
				} else if (arrPattern[j - 1] == '*') {
					solution[i][j] = solution[i - 1][j] || solution[i][j - 1];
				} else {
					solution[i][j] = false;
				}
			}
		}
		printSolution(solution);
		matches = solution[solution.length - 1][solution[0].length - 1];
		return matches;
	}

	private static void printSolution(boolean[][] solution) {
		for (boolean[] row : solution) {
			for (boolean element : row)
				System.out.print(element + " ");
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		System.out.println(isWildCardMatches("*a?*b", "ab"));
		//System.out.println(isWildCardMatches("*a?*b", "abb"));
		//System.out.println(isWildCardMatches("*a?bb", "abb"));
	}

}
