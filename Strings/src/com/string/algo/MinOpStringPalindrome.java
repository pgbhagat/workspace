package com.string.algo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MinOpStringPalindrome {

	public static void main(String[] args) {
		MinOpStringPalindrome op = new MinOpStringPalindrome();
		List<String> letters = op.readInput();
		List<Integer> answers = op.findMinOpForPalimdrome(letters);
		StringBuilder line = new StringBuilder();
		for (Integer answer : answers) {
			line.append(answer + " ");
		}
		System.out.println(line);
	}

	private List<Integer> findMinOpForPalimdrome(List<String> letters) {
		List<Integer> answers = new ArrayList<Integer>(letters.size());
		for (String letter : letters) {
			int answer = findMinOpForPalimdrome(letter);
			answers.add(answer);
		}
		return answers;
	}

	private int findMinOpForPalimdrome(String letter) {
		int answer = 0;
		int mid = letter.length() / 2;
		int left = mid - 1;
		int right = mid + 1;
		if (letter.length() % 2 == 0) {
			right = left + 1;
		}
		while (left >= 0 && right < letter.length()) {
			int ops = 0;
			if (letter.charAt(left) < letter.charAt(right)) {
				ops = letter.charAt(right) - letter.charAt(left);
			} else if (letter.charAt(left) > letter.charAt(right)) {
				ops = letter.charAt(left) - letter.charAt(right);
			}
			answer += ops;
			left--;
			right++;
		}
		return answer;
	}

	public List<String> readInput() {
		List<String> input = new ArrayList<>();
		try (Scanner scanner = new Scanner(System.in);) {
			int totalLines = scanner.nextInt();
			for (int i = 0; i < totalLines; i++) {
				input.add(scanner.next());
			}
		}
		return input;
	}

}
