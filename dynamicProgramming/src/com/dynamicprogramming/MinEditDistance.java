package com.dynamicprogramming;

public class MinEditDistance {

    public static void main(String... args) {
            printSolution(minEditDistance("azced".toCharArray(), "abcdef".toCharArray()));
    }

    public static int[][] minEditDistance(char[] str1, char[] str2) {
        int[][] solution = new int[str1.length + 1][str2.length + 1];
        for (int j = 1; j < solution[0].length; j++) {
            solution[0][j] = j;
        }
        for (int i = 1; i < solution.length; i++) {
            solution[i][0] = i;
        }
        for (int i = 1; i < solution.length; i++) {
            for (int j = 1; j < solution[0].length; j++) {
                if (str1[i - 1] == str2[j - 1]) {
                    solution[i][j] = solution[i - 1][j - 1];
                } else {
                    solution[i][j] = Math.min(solution[i - 1][j - 1], Math.min(solution[i - 1][j], solution[i][j - 1])) + 1;
                }
            }
        }
        return solution;
    }

    private static void printSolution(int[][] solution) {
        for (int i = 0; i < solution.length; i++) {
            for (int j = 0; j < solution[0].length; j++) {
                System.out.print(solution[i][j] + " ");
            }
            System.out.println("");
        }

    }
}
