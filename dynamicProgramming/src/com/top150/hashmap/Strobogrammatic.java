package com.top150.hashmap;

import java.util.HashMap;
import java.util.Map;

public class Strobogrammatic {
    public static boolean isStrobogrammatic(String input) {
        Map<Character, Character> strobogrammaticPairs = new HashMap<>();
        strobogrammaticPairs.put('0', '0');
        strobogrammaticPairs.put('1', '1');
        strobogrammaticPairs.put('6', '9');
        strobogrammaticPairs.put('8', '8');
        strobogrammaticPairs.put('9', '6');

        int left = 0;
        int right = input.length() - 1;

        while (left <= right) {
            if (!strobogrammaticPairs.containsKey(input.charAt(left))) {
                return false;
            }
            if (strobogrammaticPairs.get(input.charAt(left)) != input.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isStrobogrammatic("69"));
        System.out.println(isStrobogrammatic("888"));
        System.out.println(isStrobogrammatic("999"));
        System.out.println(isStrobogrammatic("121"));
        System.out.println(isStrobogrammatic("191"));
    }
}
