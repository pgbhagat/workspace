package com.backtracking;

import java.util.ArrayList;
import java.util.List;

public class binaryWatch {

    public List<String> readBinaryWatch(int turnedOn) {
        List<String> result = new ArrayList<>();
        backtrack(turnedOn, 0, 0, 0, result);
        return result;
    }

    private void backtrack(int turnedOn, int index, int hours, int minutes, List<String> result) {
        if (turnedOn == 0) {
            if (hours <= 11 && minutes <= 59) {
                result.add(String.format("%d:%02d", hours, minutes));
            }
            return;
        }
        if (index > 11) {
            return;
        }

        //two choices, either turn on the current LED or NOT
        backtrack(turnedOn, index + 1, hours, minutes, result);
        if (index <= 3) {
            int newHours = hours + (int) Math.pow(2, index);
            backtrack(turnedOn - 1, index + 1, newHours, minutes, result);
        } else {
            int newMinutes = minutes + (int) Math.pow(2, index - 4);
            backtrack(turnedOn - 1, index + 1, hours, newMinutes, result);
        }

    }

}
