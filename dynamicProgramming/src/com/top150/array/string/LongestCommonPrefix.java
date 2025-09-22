package com.top150.array.string;

public class LongestCommonPrefix {

    public static String longestCommonPrefix(String[] strs) {
        StringBuilder result = new StringBuilder();
        int index = 0;
        boolean match = true;
        do {
            if (index < strs[0].length()) {
                char c = strs[0].charAt(index);
                for (String str : strs) {
                    if (index >= str.length() || c != str.charAt(index)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    result.append(c);
                    index++;
                }
            }
        } while (match == true);
        return result.toString();
    }

    public static void main(String... args) {
        System.out.println(longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        System.out.println(longestCommonPrefix(new String[]{"ab", "a"}));
        System.out.println(longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
        System.out.println(longestCommonPrefix(new String[]{""}));
    }
}
