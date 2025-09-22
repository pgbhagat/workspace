package com.top150.array.string;

import java.util.LinkedList;
import java.util.List;

public class PascalsTriangle {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new LinkedList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> current = new LinkedList<>();
            current.add(1);
            if (i > 0) {
                current.add(1);
                List<Integer> previous = result.get(i - 1);
                for (int j = 0; j < previous.size() - 1; j++) {
                    current.add(previous.get(j) + previous.get(j + 1));
                }
            }
            result.add(current);
        }
        return result;
    }

    public static void main(String... args) {

    }
}
