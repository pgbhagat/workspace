package com.backtracking;

//https://www.youtube.com/watch?v=9z2BunfoZ5Y
//https://leetcode.com/problems/surrounded-regions/submissions/1770890201/
public class SurroundedRegions {
    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        //connect the regions first, i.e. boundaries
        //top and bottom
        for (int col = 0; col < board[0].length; col++) {
            if (board[0][col] == 'O') {
                dfs(board, 0, col);
            }
            if (board[board.length - 1][col] == 'O') {
                dfs(board, board.length - 1, col);
            }
        }
        //left and right
        for (int row = 0; row < board.length; row++) {
            if (board[row][0] == 'O') {
                dfs(board, row, 0);
            }
            if (board[row][board[0].length - 1] == 'O') {
                dfs(board, row, board[0].length - 1);
            }
        }

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == 'O') {
                    board[row][col] = 'X';
                } else if (board[row][col] == '#') {
                    board[row][col] = 'O';
                }
            }
        }
    }

    private void dfs(char[][] board, int row, int col) {
        if (row < 0 || row > board.length - 1 || col < 0 || col > board[0].length - 1 || board[row][col] != 'O') {
            return;
        }
        board[row][col] = '#';

        dfs(board, row - 1, col);
        dfs(board, row + 1, col);
        dfs(board, row, col - 1);
        dfs(board, row, col + 1);
    }
}
