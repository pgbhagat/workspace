package com.top150.array.string;

public class StockBuyAndSell {
    public static int profit(int[] prices) {
        int maxProfit = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            }
            if (prices[i] - min > maxProfit) {
                maxProfit = prices[i] - min;
            }
        }
        return maxProfit;
    }

    public static void main(String... args) {
        // maxSumSubArray(new int[]{6, -2, 7});
        System.out.println(profit(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }
}
