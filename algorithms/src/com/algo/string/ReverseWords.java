package com.algo.string;

/**
 * 
 * No extra space...
 * 
 * 
 * "i love you" should result to "you love i"
 * 
 * i love you uoy evol i // reverse the sentence you love i // reverse each
 * words
 * 
 * @author Prashant.Bhagat
 *
 */
public class ReverseWords {

	public static void main(String[] args) {
		reverseWords("I love you".toCharArray());

	}

	public static void reverseWords(char[] sentence) {
		if (sentence != null && sentence.length > 0) {
			int start = 0, end = sentence.length - 1;
			reverseChars(sentence, start, end);
			end = 0;
			for (char c : sentence) {
				if (c == ' ') {
					reverseChars(sentence, start, end - 1);
					end++;
					start = end;
				} else {
					end++;
				}
			}
			System.out.println(sentence);
		}
	}

	private static void reverseChars(char[] sentence, int start, int end) {
		while (start < end) {
			char tmp = sentence[end];
			sentence[end] = sentence[start];
			sentence[start] = tmp;
			start++;
			end--;
		}
	}

}
