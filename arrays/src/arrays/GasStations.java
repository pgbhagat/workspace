package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GasStations {

	public static void matrix(Character[][] matrix) {

		for (int row = 0; row < matrix.length; row++) {
			Arrays.sort(matrix[row]);
		}
		for (int column = 0; column < matrix[0].length; column++) {
			Arrays.sort(matrix[column]);
		}
		boolean isSorted = true;

		for (int row = 0; row < matrix.length; row++) {
			for (int column = 0; column < matrix[0].length - 1; column++) {
				if (matrix[row][column] <= matrix[row][column + 1]) {
					continue;
				} else {
					isSorted = false;
					break;
				}
			}
		}

		if (isSorted) {
			for (int column = 0; column < matrix[0].length; column++) {
				for (int row = 0; row < matrix.length - 1; row++) {
					if (matrix[row][column] <= matrix[row + 1][column]) {
						continue;
					} else {
						isSorted = false;
						break;
					}
				}
			}
		}

		if (isSorted) {
			System.out.println("YES");

		} else {
			System.out.println("NOT");
		}

		for (int row = 0; row < matrix.length; row++) {
			StringBuilder line = new StringBuilder();
			for (int column = 0; column < matrix[0].length; column++) {
				line.append(matrix[row][column]);
			}
			System.out.println(line);
		}

	}

	public static void start() {
		Scanner scanner = new Scanner(System.in);

		List<Character[][]> allMatrix = new ArrayList<Character[][]>();

		int noOfTestCases = scanner.nextInt();

		for (int i = 0; i < noOfTestCases; i++) {
			Character[][] tmpMatrix = readTestCase(scanner);
			allMatrix.add(tmpMatrix);
		}
		for (Character[][] matrix : allMatrix) {
			matrix(matrix);
		}
	}

	private static Character[][] readTestCase(Scanner scanner) {
		int matrixSize = scanner.nextInt();
		Character[][] matrix = new Character[matrixSize][matrixSize];
		for (int i = 0; i < matrixSize; i++) {
			String line = scanner.next();
			for (int j = 0; j < line.length(); j++) {
				matrix[i][j] = line.charAt(j);
			}
		}
		return matrix;
	}

	public static void main(String[] args) {

		int[] gasInStation = new int[] { 3, 2, 5 };
		int[] costInGas = new int[] { 2, 3, 4 };

		// int index = findGasStationToStart(gasInStation, costInGas);
		// System.out.println("Gas station to start with - " + index);
		/*
		 * Character[][] matrix = { { 'e', 'b', 'a', 'c', 'd' }, { 'f', 'g',
		 * 'h', 'i', 'j' }, { 'o', 'l', 'm', 'k', 'n' }, { 't', 'r', 'p', 'q',
		 * 's' }, { 'x', 'y', 'w', 'u', 'v' } };
		 */

		Character[][] matrix = { { 's', 'u', 'r' }, { 'e', 'y', 'y' }, { 'g', 'x', 'y' } };

		// start();
		matrix(matrix);
	}

	private static int findGasStationToStart(int[] gas, int[] cost) {
		int index = -1;
		if (gas.length == cost.length) {
			for (int i = 0; i < gas.length; i++) {
				index = i;
				int canTravelKm = 0;
				for (int j = 0; j > gas.length; j++) {
					canTravelKm = canTravelKm + (gas[j] - cost[j]);
					if (canTravelKm < 0) {
						index = -1;
						break;
					}
				}
			}
		}
		return index;
	}
}
