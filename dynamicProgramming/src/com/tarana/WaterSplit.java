package com.tarana;


public class WaterSplit {
    public static final int PACIFIC = 1;
    public static final int ATLANTIC = 2;
    public static final int BOTH = 3;

    public void solve(int[][] matrix) {
        if (matrix == null) {
            return;
        }
        int[][] solution = new int[matrix.length][matrix[0].length];
        for (int j = 0; j < solution[0].length; j++) {
            solution[0][j] = PACIFIC;
            solution[solution.length - 1][j] = ATLANTIC;
        }
        for (int i = 0; i < solution.length; i++) {
            solution[i][0] = PACIFIC;
            solution[i][solution[0].length - 1] = ATLANTIC;
        }
        solution[0][solution[0].length - 1] = BOTH;
        solution[solution.length - 1][0] = BOTH;

        checkForPacificWay(matrix, solution);
        print(solution);
        System.out.println("");
        checkForAtlanticWay(matrix, solution);
        print(solution);
    }

    public static void main(String... args) {
        int[][] input = new int[][]{
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}

        };
        WaterSplit waterSplit = new WaterSplit();
        waterSplit.solve(input);
    }

    public void print(int[][] solution) {
        for (int i = 0; i < solution.length; i++) {
            for (int j = 0; j < solution[0].length; j++) {
                System.out.print(solution[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void checkForPacificWay(int[][] matrix, int[][] solution) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                boolean left = isValidCell(matrix, i, j, i, j - 1, solution, PACIFIC);
                boolean right = isValidCell(matrix, i, j, i, j + 1, solution, PACIFIC);
                boolean top = isValidCell(matrix, i, j, i - 1, j, solution, PACIFIC);
                boolean bottom = isValidCell(matrix, i, j, i + 1, j, solution, PACIFIC);
                if (left || right || top || bottom) {
                    if (solution[i][j] == ATLANTIC) {
                        solution[i][j] = BOTH;
                    } else if (solution[i][j] != BOTH) {
                        solution[i][j] = PACIFIC;
                    }
                }
            }
        }
    }

    private boolean isValidCell(int[][] matrix, int currentI, int currentJ, int nextI, int nextJ, int[][] solution, int Ocean) {
        if (nextI >= 0 && nextI <= matrix.length - 1 && nextJ >= 0 && nextJ <= matrix[0].length - 1) {
            if (matrix[nextI][nextJ] <= matrix[currentI][currentJ] && (solution[nextI][nextJ] == Ocean || solution[nextI][nextJ] == BOTH)) {
                return true;
            }
        }
        return false;
    }

    private void checkForAtlanticWay(int[][] matrix, int[][] solution) {
        for (int i = matrix.length - 1; i >= 0; i--) {
            for (int j = matrix[0].length - 1; j >= 0; j--) {
                boolean left = isValidCell(matrix, i, j, i, j - 1, solution, ATLANTIC);
                boolean right = isValidCell(matrix, i, j, i, j + 1, solution, ATLANTIC);
                boolean top = isValidCell(matrix, i, j, i - 1, j, solution, ATLANTIC);
                boolean bottom = isValidCell(matrix, i, j, i + 1, j, solution, ATLANTIC);
                if (left || right || top || bottom) {
                    if (solution[i][j] == PACIFIC || solution[i][j] == BOTH) {
                        solution[i][j] = BOTH;
                    } else {
                        solution[i][j] = ATLANTIC;
                    }
                }
            }
        }
    }
}
