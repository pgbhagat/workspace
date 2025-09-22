package com.top150.array.string;

import java.util.Arrays;

public class MinCandies {

    public static int candy(int[] ratings) {
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);

        for (int i = 0; i < ratings.length; i++) {
            int leftNeighbor = i == 0 ? 0 : i - 1;
            int rightNeighbor = i == ratings.length - 1 ? ratings.length - 1 : i + 1;
            if (ratings[i] > ratings[leftNeighbor] && ratings[i] > ratings[rightNeighbor]) {
                candies[i] = Math.max(candies[leftNeighbor], candies[rightNeighbor]) + 1;
            } else if (ratings[i] > ratings[leftNeighbor]) {
                candies[i] = candies[leftNeighbor] + 1;
            } else if (ratings[i] > ratings[rightNeighbor]) {
                candies[i] = candies[rightNeighbor] + 1;
            }
        }
        for (int i = ratings.length - 1; i >= 0; i--) {
            int leftNeighbor = i == 0 ? 0 : i - 1;
            int rightNeighbor = i == ratings.length - 1 ? ratings.length - 1 : i + 1;
            if (ratings[i] > ratings[leftNeighbor] && ratings[i] > ratings[rightNeighbor]) {
                candies[i] = Math.max(candies[leftNeighbor], candies[rightNeighbor]) + 1;
            } else if (ratings[i] > ratings[leftNeighbor]) {
                candies[i] = candies[leftNeighbor] + 1;
            } else if (ratings[i] > ratings[rightNeighbor]) {
                candies[i] = candies[rightNeighbor] + 1;
            }
        }

        System.out.println(Arrays.toString(candies));


        return Arrays.stream(candies).sum();
    }

    public static void main(String... args) {
        /*System.out.println(candy(new int[]{1, 2, 3, 4, 2, 1, 7}));
        System.out.println(candy(new int[]{1, 0, 2}));
        System.out.println(candy(new int[]{1, 1, 2}));*/
        System.out.println(candy(new int[]{1, 2, 87, 87, 87, 2, 1}));
    }
}
