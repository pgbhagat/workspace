package com.top150.matrix;

import java.util.Arrays;

public class SetZeroes {
    public static void setZeroes(int[][] matrix) {
        boolean firstRowHasZero = false;
        boolean firstColumnHasZero = false;
        for (int col = 0; col < matrix[0].length; col++) {
            if (matrix[0][col] == 0) {
                firstRowHasZero = true;
            }
        }
        for (int row = 0; row < matrix.length; row++) {
            if (matrix[row][0] == 0) {
                firstColumnHasZero = true;
            }
        }
        //Check Rows
        for (int row = 1; row < matrix.length; row++) {
            for (int col = 1; col < matrix[0].length; col++) {
                if (matrix[row][col] == 0) {
                    matrix[row][0] = 0;
                    matrix[0][col] = 0;
                }
            }
        }
        for (int row = 1; row < matrix.length; row++) {
            if (matrix[row][0] == 0) {
                for (int col = 1; col < matrix[0].length; col++) {
                    matrix[row][col] = 0;
                }
            }
        }

        for (int col = 1; col < matrix[0].length; col++) {
            if (matrix[0][col] == 0) {
                for (int row = 1; row < matrix.length; row++) {
                    matrix[row][col] = 0;
                }
            }
        }
        if (firstColumnHasZero) {
            for (int row = 0; row < matrix.length; row++) {
                matrix[row][0] = 0;
            }
        }
        if (firstRowHasZero) {
            for (int col = 0; col < matrix[0].length; col++) {
                matrix[0][col] = 0;
            }
        }

    }

    public static void main(String... args) {
        int[][] input = new int[][]{
                {0, 1, 2, 0},
                {3, 4, 5, 2},
                {1, 3, 1, 5}
        };

        setZeroes(input);

        for (int[] row : input) {
            System.out.println(Arrays.toString(row));
        }
    }
}
