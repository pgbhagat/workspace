package com.top150.matrix;

import java.util.Arrays;

public class GameOfLife {

    private static final int DEAD = 0;
    private static final int ALIVE = 1;
    private static final int DEAD_TO_ALIVE = 2;
    private static final int ALIVE_TO_DEAD = 3;

    public static void gameOfLife(int[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                int alive = getAliveCount(board, row, col);
                if (board[row][col] == DEAD) {
                    if (alive == 3) {
                        board[row][col] = DEAD_TO_ALIVE;
                    }
                } else {
                    if (alive < 2 || alive > 3) {
                        board[row][col] = ALIVE_TO_DEAD;
                    }
                }

            }
        }

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == DEAD_TO_ALIVE) {
                    board[row][col] = 1;
                } else if (board[row][col] == ALIVE_TO_DEAD) {
                    board[row][col] = 0;
                }
            }
        }
    }

    private static int getAliveCount(int[][] board, int row, int col) {
        int alive = 0;
        if ((col - 1) >= 0) {
            if (board[row][col - 1] == ALIVE || board[row][col - 1] == ALIVE_TO_DEAD) {
                alive++;
            }
        }
        if ((col + 1) < board[0].length) {
            if (board[row][col + 1] == ALIVE || board[row][col + 1] == ALIVE_TO_DEAD) {
                alive++;
            }
        }
        if ((row - 1) >= 0) {
            if (board[row - 1][col] == ALIVE || board[row - 1][col] == ALIVE_TO_DEAD) {
                alive++;
            }
        }
        if ((row + 1) < board.length) {
            if (board[row + 1][col] == ALIVE || board[row + 1][col] == ALIVE_TO_DEAD) {
                alive++;
            }
        }
        if (((row + 1) < board.length) && ((col + 1) < board[0].length)) {
            if (board[row + 1][col + 1] == ALIVE || board[row + 1][col + 1] == ALIVE_TO_DEAD) {
                alive++;
            }
        }
        if (((row - 1) >= 0) && ((col - 1) >= 0)) {
            if (board[row - 1][col - 1] == ALIVE || board[row - 1][col - 1] == ALIVE_TO_DEAD) {
                alive++;
            }
        }
        if (((row - 1) >= 0) && ((col + 1) < board[0].length)) {
            if (board[row - 1][col + 1] == ALIVE || board[row - 1][col + 1] == ALIVE_TO_DEAD) {
                alive++;
            }
        }
        if (((row + 1) < board.length) && ((col - 1) >= 0)) {
            if (board[row + 1][col - 1] == ALIVE || board[row + 1][col - 1] == ALIVE_TO_DEAD) {
                alive++;
            }
        }


        return alive;
    }

    public static void main(String[] args) {
        int[][] input = new int[][]{
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        };

        gameOfLife(input);

        for (int[] row : input) {
            System.out.println(Arrays.toString(row));
        }
    }
}
