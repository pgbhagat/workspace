package com.top150.array.string;

import java.util.ArrayList;
import java.util.List;

public class MajorityElement {
    public static int majorityElement(int[] num) {
        if (num != null && num.length != 0) {
            int currentElement = -1;
            int count = 0;
            for (int i = 0; i < num.length; i++) {
                if (count == 0) {
                    currentElement = num[i];
                }
                if (num[i] == currentElement) {
                    count++;
                } else {
                    count--;
                }

            }
            return currentElement;
        }
        return -1;
    }

    public static void main(String... args) {
        int[] num = new int[]{0, 0, 0, 0, 2, 2, 2, 2, 2, 2};
        System.out.println(majorityElement(num));

    }
}
