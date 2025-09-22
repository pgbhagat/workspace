package com.dynamicprogramming;

/**
 *
 */
public class Knapsack {

    public static void main(String... args) {
        int[] wt = new int[]{1, 3, 4, 5, 7, 8};
        int[] val = new int[]{1, 4, 5, 7, 9, 10};

        //int[] wt = new int[]{1, 3, 4, 5};
        //int[] val = new int[]{1, 4, 5, 7};

        int[][] solution = findMaxValueForWt(12, wt, val);
        printWtToPick(solution, wt, val);
    }

    private static int[][] findMaxValueForWt(int maxWeight, int[] wt, int[] val) {
        int[][] solution = new int[wt.length + 1][maxWeight + 1];
        //init 0th column
        for (int i = 0; i < solution.length; i++) {
            solution[i][0] = 0;
        }
        //init 0th row
        for (int i = 0; i < solution[0].length; i++) {
            solution[0][i] = 0;
        }

        for (int i = 1; i < solution.length; i++) {
            for (int j = 1; j < solution[0].length; j++) {
                int maxVal;
                if (j >= wt[i - 1]) {
                    maxVal = Math.max(solution[i - 1][j], val[i - 1] + solution[i - 1][j - wt[i - 1]]);
                } else {
                    maxVal = solution[i - 1][j];
                }
                solution[i][j] = maxVal;
            }
        }

        return solution;
    }

    private static void printWtToPick(int[][] solution, int[] wt, int[] val) {
        for (int i = 1; i < solution.length; i++) {
            for (int j = 1; j < solution[0].length; j++) {
                System.out.print(solution[i][j] + " ");
            }
            System.out.println();
        }
        int startRow = solution.length - 1;
        int startCol = solution[0].length - 1;
        while (startCol >= 1 && startRow >= 1) {
            if (solution[startRow][startCol] != solution[startRow - 1][startCol]) {
                System.out.println("Wt=[" + wt[startRow - 1] + "], Val=[" + val[startRow - 1] + "]");
                startCol = startCol - wt[startRow - 1];
            }
            startRow--;
        }

    }

}
