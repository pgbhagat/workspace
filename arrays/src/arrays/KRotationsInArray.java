package arrays;

public class KRotationsInArray {

	public static void main(String[] args) {
		int[] array = new int[] { 1, 2, 3, 4, 5, 6 };
		int[] rotated = new KRotationsInArray().rotate(new int []{}, 0);
		print(rotated);
	}

	private static void print(int[] rotated) {
		for (int element : rotated) {
			System.out.print(" " + element);
		}
	}

	private int[] rotate(int[] array, int times) {
		int swapWithIndex = 0;
		int startIndex = 0;
		int[] rotatedArray = null;
		if (validInput(array)) {
			rotatedArray = new int[array.length];
			times = times % array.length;
			if (times == 0)
				return array;
			if (times > 0) {
				int count = array.length;
				while (count > 0) {
					swapWithIndex = getSwapIndex(startIndex, times, array.length);
					rotatedArray[swapWithIndex] = array[startIndex];
					count--;
					startIndex++;
				}

			}
		}
		return rotatedArray;
	}

	private int getSwapIndex(int start, int times, int max) {
		int swapIndex = start + times;
		if (swapIndex >= max) {
			swapIndex = swapIndex - max;
		}
		return swapIndex;
	}

	private boolean validInput(int[] array) {
		if (array == null || array.length == 0  || array.length > 100)
			return false;
		for (int a : array) {
			if (a > 1000 || a < -1000) {
				return false;
			}
		}
		return true;
	}

}
