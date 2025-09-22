package com.backtracking;

public class RatInMaze {

    public static void main(String[] args) {
        //@formatter:off
		int [][] maze = new int [][] { 
				{ 1, 0, 1, 1, 1 }, 
				{ 1, 1, 1, 0, 1 }, 
				{ 0, 1, 0, 1, 1 }, 
				{ 1, 1, 0, 1, 0 },
				{ 1, 1, 1, 1, 1 },
		};
		//@formatter:on
        findSolution(maze);
    }

    private static void print(int[][] ratPath) {
        for (int[] outputRow : ratPath) {
            for (int element : outputRow) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    private static void findSolution(int[][] maze) {
        int[][] solution = new int[maze.length][maze[0].length];
        if (find(maze, solution, 0, 0, "")) {
            print(solution);
        } else {
            System.out.println("No path found..");
        }
    }

    private static boolean find(int[][] maze, int[][] solution, int row, int col, String dir) {
        if (row == maze.length - 1 && col == maze[0].length - 1 && isSafeCell(maze, row, col)) {
            solution[row][col] = 1;
            return true;
        }
        if (isSafeCell(maze, row, col)) {
            solution[row][col] = 1;
            if ((!dir.equals("down") && find(maze, solution, row + 1, col, "up"))
                    || (!dir.equals("left") && find(maze, solution, row, col + 1, "right"))
                    || (!dir.equals("right") && find(maze, solution, row, col - 1, "left"))
                    || (!dir.equals("up") && find(maze, solution, row - 1, col, "down"))) {

                return true;
            }
            solution[row][col] = 0;
        }
        return false;
    }

    private static boolean isSafeCell(int[][] maze, int row, int col) {
        if (row >= 0 && row < maze.length && col >= 0 && col < maze[0].length && maze[row][col] == 1) {
            return true;
        }
        return false;
    }

}
