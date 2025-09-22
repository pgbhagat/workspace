package com.dynamicprogramming;

import java.util.HashSet;
import java.util.Set;

public class WordBreakProblem {

	public static boolean wordBreakRecurse(String word, Set<String> dict, String answer) {
		if (word == null || word.isEmpty()) {
			System.out.println(answer);
			return true;
		} else {
			int startIndex = 0;
			String subWord = "";
			while (startIndex < word.length()) {
				subWord += word.charAt(startIndex);
				if (dict.contains(subWord)) {
					if (wordBreakRecurse(word.substring(startIndex + 1), dict, answer + " " + subWord)) {
						return true;
					} else {
						startIndex++;
					}
				} else {
					startIndex++;
				}
			}
		}
		return false;
	}

	public static boolean wordBreakIterative(String word, Set<String> dict, String answer,
			Set<String> subProblems) {
		if (word == null || word.isEmpty()) {
			System.out.println(answer);
			return true;
		} else if (subProblems.contains(word)) {
			return false;
		} else {
			int startIndex = 0;
			String subWord = "";
			while (startIndex < word.length()) {
				subWord += word.charAt(startIndex);
				if (dict.contains(subWord)) {
					if (wordBreakIterative(word.substring(startIndex + 1), dict, answer + " " + subWord, subProblems)) {
						return true;
					} else {
						startIndex++;
					}
				} else {
					startIndex++;
				}
			}
		}
		subProblems.add(word);
		return false;

	}

	public static void main(String[] args) {
		Set<String> dict = new HashSet<String>();
		dict.add("Hi");
		dict.add("I");
		dict.add("am");
		dict.add("Prashant");
		Set<String> memory = new HashSet<String>();
		wordBreakIterative("Prashant", dict, "", memory);
		wordBreakRecurse("IamPrashant", dict, "");
		wordBreakRecurse("Sushant", dict, "");
	}
}
