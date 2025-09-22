package com.test;

public class Knapsack {

    public static void main(String... args) {
        int[] wt = new int[]{1, 3, 4, 5, 7, 8};
        int[] val = new int[]{1, 4, 5, 7, 9, 10};

        //int[] wt = new int[]{1, 3, 4, 5};
        //int[] val = new int[]{1, 4, 5, 7};

        int[][] solution = findMaxValueForWt(wt, val, 12);
        printWtToPick(solution, wt, val);
    }

    public static int[][] findMaxValueForWt(int[] wt, int[] val, int maxWt) {
        int[][] solution = new int[wt.length + 1][maxWt + 1];
        //all rows/columns are filled with ZEROs
        for (int i = 1; i < solution.length; i++) {
            for (int j = 1; j < maxWt + 1; j++) {
                int maxValue = solution[i - 1][j];
                if (j >= wt[i - 1]) {
                    maxValue = Math.max(maxValue, val[i - 1] + solution[i - 1][j - wt[i - 1]]);
                }
                solution[i][j] = maxValue;
            }
        }
        return solution;
    }

    private static void printWtToPick(int[][] solution, int[] wt, int[] val) {
        for (int i = 0; i < solution.length; i++) {
            for (int j = 0; j < solution[0].length; j++) {
                System.out.print(solution[i][j] + " ");
            }
            System.out.println("");
        }

    }


}
