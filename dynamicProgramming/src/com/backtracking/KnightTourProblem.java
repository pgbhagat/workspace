package com.backtracking;

public class KnightTourProblem {

    static int path = 1;

    public static void main(String[] args) {
        knightTour(6);
    }

    private static void knightTour(int BoardSize) {
        // all paths initialized to ZERO
        int[][] solution = new int[BoardSize][BoardSize];
        if (findPath(solution, 0, 0)) {
            printPath(solution);
        } else {
            System.out.println("No path found..");
        }
    }

    private static void printPath(int[][] solution) {
        for (int[] row : solution) {
            for (int element : row) {
                System.out.format("%02d ", element);
            }
            System.out.println();
        }

    }

    private static boolean findPath(int[][] solution, int row, int col) {
        if (solution[row][col] != 0) {
            return false;
        }
        solution[row][col] = path++;
        if (path == (solution.length * solution.length)) {
            return true;
        }
        if (
            //@formatter:off
				   isValidMove(row + 2, col + 1, solution.length) && findPath(solution, row + 2, col + 1)
				|| isValidMove(row + 2, col - 1, solution.length) && findPath(solution, row + 2, col - 1)
				
				|| isValidMove(row + 1, col + 2, solution.length) && findPath(solution, row + 1, col + 2)
				|| isValidMove(row - 1, col + 2, solution.length) && findPath(solution, row - 1, col + 2)
				
				|| isValidMove(row - 2, col + 1, solution.length) && findPath(solution, row - 2, col + 1)
				|| isValidMove(row - 2, col - 1, solution.length) && findPath(solution, row - 2, col - 1)
				
				|| isValidMove(row - 1, col - 2, solution.length) && findPath(solution, row - 1, col - 2)
				|| isValidMove(row + 1, col - 2, solution.length) && findPath(solution, row + 1, col - 2)) {
		//@formatter:on
            return true;
        }
        solution[row][col] = 0;
        path--;

        return false;
    }

    private static boolean isValidMove(int i, int j, int length) {
        if (i >= 0 && i < length && j >= 0 && j < length) {
            return true;
        }
        return false;
    }

}
