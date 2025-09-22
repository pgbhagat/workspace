package com.top150.array.string;

public class ReverseWords {

    public static String reverseWords(String s) {
        StringBuilder result = new StringBuilder();
        StringBuilder tmp = new StringBuilder();
        boolean word = false;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != ' ') {
                tmp.append(s.charAt(i));
                word = true;
            } else {
                if (word == true) {
                    if (result.length() > 0) {
                        result.append(" ");
                    }
                    result.append(tmp.reverse());
                    tmp = new StringBuilder();
                    word = false;
                }
            }
        }
        if (tmp.length() > 0) {
            if (result.length() > 0) {
                result.append(" ");
            }
            result.append(tmp.reverse().toString().trim());
        }
        return result.toString();
    }

    public static void main(String... args) {
        System.out.println(">" + reverseWords("the sky is blue") + "<");
        System.out.println(">" + reverseWords("the sky is     blue") + "<");
        System.out.println(">" + reverseWords("the sky is blue   ") + "<");
        System.out.println(">" + reverseWords("    the sky is blue   ") + "<");
    }
}
