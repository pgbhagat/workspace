package arrays;

public class MaxProfitShareMarket {

	public static void main(String[] args) {
		System.out.println(
				new MaxProfitShareMarket().getMaxProfit(new int[] { 23171, 21011, 21123, 21366, 21013, 21367 }));
	}

	public int getMaxProfit(int[] shareValue) {
		int profit = 0;
		int min[] = new int[shareValue.length];
		int max[] = new int[shareValue.length];
		int currMin = Integer.MAX_VALUE;
		int currMax = Integer.MIN_VALUE;
		if (verifyInput(shareValue)) {
			for (int i = 0; i < shareValue.length; i++) {
				if (shareValue[i] < currMin) {
					currMin = shareValue[i];
				}
				min[i] = currMin;

			}
			for (int i = shareValue.length - 1; i >= 0; i--) {
				if (shareValue[i] > currMax) {
					currMax = shareValue[i];
				}
				max[i] = currMax;
			}
			for (int i = 0; i < shareValue.length; i++) {
				if (max[i] - min[i] > profit)
					profit = max[i] - min[i];
			}
		}

		return profit;

	}

	private boolean verifyInput(int[] shareValue) {
		if (shareValue == null || shareValue.length < 2 || shareValue.length > 400000) {
			return false;
		}
		for (int element : shareValue) {
			if (element > 200000 || element < 0) {
				return false;
			}
		}
		return true;
	}

}
