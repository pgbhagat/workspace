package com.dynamicprogramming;

import java.util.Arrays;
import java.util.Comparator;

public class MaxPlatformNeededAtRailwayStation {

	static class TrainSchedule {
		float arrival;
		float departure;
	}

	static class ScheduleCompartor implements Comparator<TrainSchedule> {
		@Override
		public int compare(TrainSchedule train1, TrainSchedule train2) {
			return train1.arrival - train2.arrival >= 0 ? 1 : -1;
		}
	}

	public static void main(String[] args) {
		float[] arrivalTime = { 9.00f, 9.40f, 9.50f, 11.00f, 15.00f, 18.00f };
		float[] departureTime = { 9.10f, 12.00f, 11.20f, 11.30f, 19.00f, 20.00f };

		TrainSchedule[] trains = new TrainSchedule[arrivalTime.length];

		for (int i = 0; i < arrivalTime.length; i++) {
			trains[i] = new TrainSchedule();
			trains[i].arrival = arrivalTime[i];
			trains[i].departure = departureTime[i];
		}

		System.out.println("max platform needed : " + getMaxPlatformNeeded(trains));
	}

	private static int getMaxPlatformNeeded(TrainSchedule[] trains) {
		int maxPlatform = 0;

		Arrays.sort(trains, new ScheduleCompartor());
		int i = 0, j = 0;
		int neededPlatform = 0;
		while (i < trains.length && j < trains.length) {
			if (trains[i].arrival < trains[j].departure) {
				neededPlatform++;
				i++;
			} else {
				neededPlatform--;
				j++;
			}
			if (neededPlatform > maxPlatform) {
				maxPlatform = neededPlatform;
			}
		}
		return maxPlatform;
	}

}
