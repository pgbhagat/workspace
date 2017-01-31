package arrays;

public class RotateTwoDArray {

	public static void main(String[] args) {
		int[][] input = { { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 10 }, { 11, 12, 13, 14, 15 }, { 16, 17, 18, 19, 20 },
				{ 21, 22, 23, 24, 25 } };
		System.out.println("Input - ");
		print(input);
		int[][] output = rorateBy90degree(input);
		System.out.println("90 rotation - ");
		print(output);
		System.out.println("180 rotation - ");
		output = rorateBy180degree(input);
		print(output);
	}

	private static void print(int[][] output) {
		for (int[] row : output) {
			for (int element : row) {
				System.out.format("%02d ", element);
			}
			System.out.println();
		}
	}

	private static int[][] rorateBy90degree(int[][] input) {
		int output[][] = new int[input[0].length][input.length];
		int outputRow = 0;
		int outputCol = 0;
		for (int i = 0; i < output.length; i++) {
			outputRow = 0;
			outputCol = output[0].length - i - 1;
			for (int j = 0; j < output[0].length; j++) {
				output[outputRow][outputCol] = input[i][j];
				outputRow++;
			}
		}
		return output;
	}

	private static int[][] rorateBy180degree(int[][] input) {
		return rorateBy90degree(rorateBy90degree(input));
	}

}
