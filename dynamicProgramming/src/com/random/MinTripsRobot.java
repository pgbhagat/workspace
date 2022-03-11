package com.random;

import java.util.Arrays;
import java.util.Collections;

public class MinTripsRobot {

  public Integer getMinTrips(int[] weights, int maxWeight) {
    int minTrips = 0;
    if (weights == null || weights.length == 0) {
      System.out.println("Null or empty array");
    } else {
      weights = Arrays.stream(weights).boxed().sorted(Collections.reverseOrder()).mapToInt(Integer::intValue).toArray();
      int i = 0, j = weights.length - 1;
      if (weights[j] > maxWeight) {
        System.out.println("Robot can not carry all weights");
      } else {
        int currentTripLoad = 0;
        while (i <= j) {
          currentTripLoad = weights[i];
          while (currentTripLoad < maxWeight && i <= j) {
            currentTripLoad += weights[j];
            j--;
          }
          minTrips++;
          i++;
          currentTripLoad = 0;
        }
      }
    }
    return minTrips;
  }

  public static void main(String... args) {
    MinTripsRobot minTripsRobot = new MinTripsRobot();
    int k = minTripsRobot.getMinTrips(new int[]{2, 3, 1, 5, 3, 6, 2, 2}, 6);
    System.out.println(k);
    k = minTripsRobot.getMinTrips(new int[]{2, 3, 1, 5, 3, 6, 2, 2}, 10);
    System.out.println(k);
  }

}
