package arrays;

public class EquillibriumPointInArray {

	public static void main(String[] args) {
		// int[] a = { -1, 3, -4, 5, 1, -6, 2, 1 };
		int[] a = { 500, 1, -2, -1, 2 };
		System.out.println(new EquillibriumPointInArray().solution(a));

	}

	public int solution(int[] A) {
		int equill = -1;
		if (validateInput(A)) {
			long[] allSum = new long[A.length];
			allSum[0] = A[0];
			for (int i = 1; i < A.length; i++) {
				allSum[i] = allSum[i - 1] + A[i];
			}

			for (int i = 0; i < allSum.length; i++) {
				long lastSum = allSum[allSum.length - 1];
				int thisElement = A[i];
				long prevSum = 0;
				if (i == 0) {
					if (lastSum == 0) {
						return i;
					}
					continue;
				} else {
					prevSum = allSum[i - 1];
				}
				if (i == allSum.length - 1) {
					if (prevSum == 0) {
						return i;
					}
				}

				if ((lastSum - thisElement - prevSum) == prevSum) {
					equill = i;
					break;
				}
			}
		}
		return equill;
	}

	private boolean validateInput(int[] a) {
		if (a == null || a.length == 0 || a.length > 100000)
			return false;
		for (int element : a) {
			if (element > Integer.MAX_VALUE || element < Integer.MIN_VALUE) {
				return false;
			}
		}
		return true;
	}

}
