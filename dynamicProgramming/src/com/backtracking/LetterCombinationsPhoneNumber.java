package com.backtracking;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsPhoneNumber {
    private final String[] KEYPAD = {
            "", //0
            "", //1
            "abc", //2
            "def", //3
            "ghi", //4
            "jkl", //5
            "mno", //6
            "pqrs",//7
            "tuv", //8
            "wxyz" //9
    };

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits != null && !digits.isEmpty()) {
            backtrack(digits, 0, new StringBuilder(), result);
        }
        return result;
    }

    private void backtrack(String digits, int index, StringBuilder currentResult, List<String> result) {
        if (index == digits.length()) {
            result.add(currentResult.toString());
            return;
        }
        String keys = KEYPAD[digits.charAt(index) - '0'];
        for (Character key : keys.toCharArray()) {
            currentResult.append(key);
            backtrack(digits, index + 1, currentResult, result);
            currentResult.deleteCharAt(currentResult.length() - 1);
        }
    }
}
