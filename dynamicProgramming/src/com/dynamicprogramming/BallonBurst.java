package com.dynamicprogramming;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BallonBurst {

	public static void main(String[] args) {
		int[] ballons = { 3, 1, 5, 8 };
		System.out.println("max profit - " + getMaxProfitRecurse(ballons));

	}

	private static int getMaxProfitRecurse(int[] ballons) {
		if (ballons == null || ballons.length == 0) {
			return 0;
		} else {
			List<Integer> ballonsList = new LinkedList<>();
			for (int i : ballons) {
				ballonsList.add(i);
			}
			return getMaxProfitRecurse(ballonsList);
		}
	}

	private static int getMaxProfitRecurse(List<Integer> ballonsList) {
		int maxProfit = 0;
		if (ballonsList != null && ballonsList.size() != 0) {
			if (ballonsList.size() == 1) {
				maxProfit = ballonsList.get(0);
			} else {
				for (int i = 0; i < ballonsList.size(); i++) {
					int leftProfit = i == 0 ? 1 : ballonsList.get(i - 1);
					int rightProfit = i == ballonsList.size() - 1 ? 1 : ballonsList.get(i + 1);
					int currentProfit = ballonsList.get(i) * leftProfit * rightProfit;
					List<Integer> postBurstList = new ArrayList<Integer>(ballonsList.size());
					postBurstList.addAll(ballonsList);
					postBurstList.remove(i);
					int totalProfit = currentProfit + getMaxProfitRecurse(postBurstList);
					if (totalProfit > maxProfit) {
						maxProfit = totalProfit;
					}
				}

			}
		}
		return maxProfit;
	}
}
