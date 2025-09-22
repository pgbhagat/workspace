package com.top150.array.string;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TextJustification {

    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        List<StringBuilder> tmpResults = new ArrayList<>();
        int width = words[0].length();
        tmpResults.add(new StringBuilder(words[0]));
        for (int i = 1; i < words.length; i++) {
            if ((width + words[i].length() + tmpResults.size()) > maxWidth) {
                result.add(justify(tmpResults, false, width, maxWidth));
                tmpResults.clear();
                tmpResults.add(new StringBuilder(words[i]));
                width = words[i].length();
            } else {
                tmpResults.add(new StringBuilder(words[i]));
                width += words[i].length();
            }
        }
        if (!tmpResults.isEmpty()) {
            result.add(justify(tmpResults, true, width, maxWidth));
        }
        return result;

    }

    private static String justify(List<StringBuilder> tmpResults, boolean isLastLine, int width, int maxWidth) {
        int spacesBetweenWords = tmpResults.size() - 1;
        int extraPadding = maxWidth - (width + spacesBetweenWords);
        for (int i = 0; spacesBetweenWords > 0; i++, spacesBetweenWords--) {
            tmpResults.get(i).append(" ");
        }

        if (!isLastLine) {
            for (int i = 0; extraPadding > 0; extraPadding--) {
                tmpResults.get(i++).append(" ");
                if (i >= tmpResults.size() - 1) {
                    i = 0;
                }
            }
        } else {
            for (int i = 0; extraPadding > 0; extraPadding--) {
                tmpResults.get(tmpResults.size() - 1).append(" ");
            }
        }
        return tmpResults.stream().map(sb -> sb.toString()).collect(Collectors.joining(""));
    }

    public static void main(String... args) {
        System.out.println(fullJustify(new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16));
        System.out.println(fullJustify(new String[]{"What", "must", "be", "acknowledgment", "shall", "be"}, 16));
    }
}
