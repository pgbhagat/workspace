package com.top150.array.string;

public class StockBuyAndSell2 {
    public static int profit(int[] prices) {
        int maxProfit = 0;
        int start = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < start) {
                start = prices[i];
            }
            if (prices[i] - start > maxProfit) {
                maxProfit = prices[i] - start;
            }
        }
        return maxProfit;
    }

    public static void main(String... args) {
        // maxSumSubArray(new int[]{6, -2, 7});
        System.out.println(profit(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }
}
