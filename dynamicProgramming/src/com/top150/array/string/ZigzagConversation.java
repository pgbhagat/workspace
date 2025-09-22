package com.top150.array.string;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ZigzagConversation {
    public static String convert(String s, int numRows) {
        if (s == null || numRows == 1 || s.length() <= numRows) {
            return s;
        }
        int index = 0;
        int increment = 1;
        StringBuilder answer = new StringBuilder();

        List<Character>[] solution = new ArrayList[numRows];
        for (int i = 0; i < solution.length; i++) {
            solution[i] = new ArrayList<>();
        }
        for (Character c : s.toCharArray()) {
            solution[index].add(c);
            if (index == 0) {
                increment = 1;
            } else if (index == numRows - 1) {
                increment = -1;
            }
            index += increment;
        }
        for (List<Character> each : solution) {
            answer.append(each.stream().map(String::valueOf).collect(Collectors.joining()));
        }
        return answer.toString();
    }

    public static void main(String... args) {
        System.out.println(convert("PAYPALISHIRING", 3));
        System.out.println(convert("PAYPALISHIRING", 4));
    }

}
