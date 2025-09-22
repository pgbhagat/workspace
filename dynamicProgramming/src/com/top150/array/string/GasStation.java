package com.top150.array.string;

import java.util.Arrays;

public class GasStation {
    //https://www.youtube.com/watch?v=lJwbPZGo05A
    public static int canCompleteCircuitBruteForce(int[] gas, int[] cost) {
        int solutionIndex = -1;
        int currentGasAmount = 0;
        //Best case when if the solution does not exists at all
        if (Arrays.stream(gas).sum() < Arrays.stream(cost).sum()) {
            return solutionIndex;
        }

        for (int startStation = 0; startStation <= gas.length - 1; startStation++) {
            currentGasAmount = 0;
            int currentStation = startStation;
            int nextStation = startStation + 1;
            do {
                if (nextStation >= gas.length) {
                    nextStation = 0;
                }
                currentGasAmount += gas[currentStation] - cost[currentStation];
                if (currentGasAmount >= 0) {
                    currentStation = nextStation;
                    nextStation = currentStation + 1;
                }
                if (currentStation == startStation && currentGasAmount >= 0) {
                    solutionIndex = startStation;
                    break;
                } else if (currentGasAmount < 0) {
                    break;
                }
            } while (currentStation != startStation);
            if (solutionIndex != -1) {
                break;
            }
        }
        return solutionIndex;
    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {

        if (Arrays.stream(gas).sum() < Arrays.stream(cost).sum()) {
            return -1;
        }

        int currentGas = 0;
        int start = 0;

        for (int i = 0; i < gas.length; i++) {
            currentGas += gas[i] - cost[i];
            if (currentGas < 0) {
                currentGas = 0;
                start = i + 1;
            }
        }

        return start;
    }

    public static void main(String... args) {
        System.out.println(canCompleteCircuitBruteForce(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}));
        System.out.println(canCompleteCircuitBruteForce(new int[]{2, 3, 4}, new int[]{3, 4, 3}));
        System.out.println(canCompleteCircuitBruteForce(new int[]{1, 2, 3, 4, 5, 5, 70}, new int[]{2, 3, 4, 3, 9, 6, 2}));
        System.out.println(canCompleteCircuit(new int[]{1, 2, 3, 4, 5, 5, 70}, new int[]{2, 3, 4, 3, 9, 6, 2}));
    }
}
