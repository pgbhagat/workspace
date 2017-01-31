package com.dynamicprogramming;

public class MaxCommonSubSeq {

	public static int getMaxCommonSubStrLengthRecurse(String firstStr, String secondStr) {
		int maxLength = 0;
		if (!firstStr.isEmpty() && !secondStr.isEmpty()) {
			int firstStrLen = firstStr.length();
			int secondStrLen = secondStr.length();
			if (firstStr.charAt(firstStrLen - 1) == secondStr.charAt(secondStrLen - 1)) {
				maxLength = 1 + getMaxCommonSubStrLengthRecurse(firstStr.substring(0, firstStrLen - 1),
						secondStr.substring(0, secondStrLen - 1));
			} else {
				maxLength = Math.max(
						getMaxCommonSubStrLengthRecurse(firstStr, secondStr.substring(0, secondStrLen - 1)),
						getMaxCommonSubStrLengthRecurse(firstStr.substring(0, firstStrLen - 1), secondStr));
			}
		}
		return maxLength;
	}

	public static String[][] getMaxCommonSubStrLengthIterative(String firstStr, String secondStr) {
		String[][] track = new String[firstStr.length() + 1][secondStr.length() + 1];
		int[][] sol = new int[firstStr.length() + 1][secondStr.length() + 1];

		for (int i = 0; i < sol.length; i++) {
			sol[0][i] = 0;
			track[0][i] = "0";
		}
		for (int i = 0; i < sol[0].length; i++) {
			sol[i][0] = 0;
			track[i][0] = "0";
		}

		for (int i = 1; i < sol.length; i++) {
			for (int j = 1; j < sol[0].length; j++) {
				if (firstStr.charAt(i - 1) == secondStr.charAt(j - 1)) {
					sol[i][j] = sol[i - 1][j - 1] + 1;
					track[i][j] = "diagonal";
				} else {
					sol[i][j] = Math.max(sol[i - 1][j], sol[i][j - 1]);
					if (sol[i - 1][j] > sol[i][j - 1])
						track[i][j] = "top";
					else
						track[i][j] = "left";
				}
			}
		}

		for (int i = 1; i < track.length; i++) {
			for (int j = 1; j < track[0].length; j++) {
				if(track[i][j].equals("diagonal")){
					System.out.println(secondStr.charAt(j-1));
				}
				
			}
		}
		return track;

	}

	public static void main(String[] args) {
		String firstStr = "ABCDE";
		String secondStr = "AEBDF";
		System.out.println(getMaxCommonSubStrLengthRecurse(firstStr, secondStr));
		System.out.println(getMaxCommonSubStrLengthIterative(firstStr, secondStr));
	}

}
