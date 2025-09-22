package com.top150.matrix;

import java.util.HashSet;
import java.util.Set;

public class IsValidSudoku {
    public static boolean isValidSudoku(char[][] board) {
        boolean isValid = true;
        Set<Character>[] rows = new HashSet[9];
        Set<Character>[] cols = new HashSet[9];
        Set<Character>[] boxes = new HashSet[9];
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet();
            cols[i] = new HashSet();
            boxes[i] = new HashSet();
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char ch = board[i][j];
                int boxIndex = (i / 3) * 3 + (j / 3);
                if (ch != '.') {
                    if (rows[i].contains(ch) || cols[j].contains(ch) || boxes[boxIndex].contains(ch)) {
                        isValid = false;
                        break;
                    }
                }
                rows[i].add(ch);
                cols[j].add(ch);
                boxes[boxIndex].add(ch);

            }
        }
        return isValid;
    }

    public static void main(String[] args) {
        System.out.println(isValidSudoku(new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'}
                , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        }));

    }
}
