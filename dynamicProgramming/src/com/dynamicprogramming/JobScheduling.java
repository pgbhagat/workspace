package com.dynamicprogramming;

import java.util.Arrays;
import java.util.Comparator;

public class JobScheduling {

	static class Job {
		int startTime;
		int endTime;
		int profit;

		Job(int start, int end, int profit) {
			this.startTime = start;
			this.endTime = end;
			this.profit = profit;
		}

		public String toString() {
			return "job start time -  " + this.startTime + ", end time - " + this.endTime + ", profit -  "
					+ this.profit;
		}
	}

	static class JobComparator implements Comparator<Job> {

		@Override
		public int compare(Job one, Job other) {
			return one.endTime - other.endTime;
		}

	}

	public static void main(String[] args) {
		//@formatter:off
		int[][] jobs = { 
				{ 1, 3, 5 }, 
				{ 2, 5, 6 }, 
				{ 4, 6, 5 }, 
				{ 6, 7, 4 }, 
				{ 5, 8, 11 }, 
				{ 7, 9, 2 }
			};
		//@formatter:on
		scheduleJobsForMaxProfit(jobs);
	}

	private static void scheduleJobsForMaxProfit(int[][] arrJobs) {
		Job[] jobs = new Job[arrJobs.length];
		int index = 0;
		for (int[] jobElement : arrJobs) {
			jobs[index++] = new Job(jobElement[0], jobElement[1], jobElement[2]);
		}
		Arrays.sort(jobs, new JobComparator());

		int[][] solution = new int[2][jobs.length];
		for (int i = 0; i < solution[0].length; i++) {
			solution[0][i] = jobs[i].profit;
			solution[1][i] = i;
		}

		for (int i = 1; i < jobs.length; i++) {
			for (int j = 0; j < i; j++) {
				if (!timeOverlap(jobs[i], jobs[j])) {
					int currentProfit = solution[0][i];
					if (solution[0][j] + jobs[i].profit > currentProfit) {
						solution[0][i] = solution[0][j] + jobs[i].profit;
						solution[1][i] = j;
					}
				}
			}
		}
		printJobScheduleAndProfit(solution, jobs);
	}

	private static void printJobScheduleAndProfit(int[][] solution, Job[] jobs) {
		int maxProfit = Integer.MIN_VALUE;
		int startJobIndex = 0;
		for (int i = 0; i < solution[0].length; i++) {
			if (solution[0][i] > maxProfit) {
				maxProfit = solution[0][i];
				startJobIndex = i;
			}
		}
		System.out.println("Max profit - " + maxProfit);
		Job job = jobs[startJobIndex];
		System.out.println("schedule job - " + job);
		do {
			startJobIndex = solution[1][startJobIndex];
			job = jobs[startJobIndex];
			System.out.println("schedule job - " + job);
		} while (solution[1][startJobIndex] != startJobIndex);
	}

	private static boolean timeOverlap(Job job1, Job job2) {
		if (job1.startTime >= job2.startTime && job1.startTime < job2.endTime)
			return true;
		if (job2.startTime >= job1.startTime && job2.startTime < job1.endTime)
			return true;
		return false;
	}

}
