package com.top150.matrix;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> solution = new ArrayList<>();
        if (matrix != null && matrix.length != 0) {
            int bottom = 0;
            int top = matrix.length - 1;
            int left = 0;
            int right = matrix[0].length - 1;
            while (bottom <= top && left <= right) {
                //left to right, from bottom
                for (int i = left; i <= right; i++) {
                    solution.add(matrix[bottom][i]);
                }
                bottom++;
                //bottom to up from right
                for (int i = bottom; i <= top; i++) {
                    solution.add(matrix[i][right]);
                }
                right--;
                //right to left from top
                if (bottom <= top) {
                    for (int i = right; i >= left; i--) {
                        solution.add(matrix[top][i]);
                    }
                    top--;
                }
                if (left <= right) {
                    //top to bottom from left
                    for (int i = top; i >= bottom; i--) {
                        solution.add(matrix[i][left]);
                    }
                    left++;
                }
            }
        }
        return solution;
    }

    public static void main(String[] args) {
        //System.out.println(spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
        System.out.println(spiralOrder(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}}));
    }
}
