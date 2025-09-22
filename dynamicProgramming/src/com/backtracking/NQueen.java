package com.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueen {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }
        solve(board, 0, result);
        return result;
    }

    private void solve(char[][] board, int row, List<List<String>> result) {
        if (row == board.length) {
            result.add(print(board));
        } else {
            for (int col = 0; col < board[0].length; col++) {
                if (isValid(board, row, col)) {
                    board[row][col] = 'Q';
                    solve(board, row + 1, result);
                    board[row][col] = '.';
                }
            }
        }
    }

    private boolean isValid(char[][] board, int row, int col) {
        for (int i = 0; i < board[0].length; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        for (int i = row - 1, j = col + 1; i >= 0 && j < board[0].length; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    private List<String> print(char[][] board) {
        List<String> result = new ArrayList<>();
        for (char[] row : board) {
            result.add(new String(row));
        }
        return result;
    }

    public static void main(String... args) {
        NQueen nQueen = new NQueen();
        List<List<String>> result = nQueen.solveNQueens(4);
        for (List<String> row : result) {
            System.out.println(row);
        }
    }
}
