package com.array;

/**
 * 
 * https://leetcode.com/problems/sort-colors
 * 
 * Given an array with n objects colored red, white or blue, sort them so that
 * objects of the same color are adjacent, with the colors in the order red,
 * white and blue. Here, we will use the integers 0, 1, and 2 to represent the
 * color red, white, and blue respectively. Follow up: A rather straight forward
 * solution is a two-pass algorithm using counting sort. First, iterate the
 * array counting number of 0's, 1's, and 2's, then overwrite array with total
 * number of 0's, then 1's and followed by 2's. Could you come up with an
 * one-pass algorithm using only constant space?
 * 
 * 
 * @author Prashant.Bhagat
 *
 */
public class ColorSort {

	public static void main(String[] args) {
		int[] colors = {1, 0, 1, 2, 0, 1, 1 };
		int[] sorted = sort(colors);
		for (int e : sorted) {
			System.out.print(e);
		}
		System.out.println();
		sorted = sortInPlace(colors);
		for (int e : sorted) {
			System.out.print(e);
		}
	}

	private static int[] sortInPlace(int[] colors) {
		if (colors != null && colors.length > 0) {
			int tmp = 0;
			int zeroStart = 0, currIndex = 0, twoIndex = colors.length - 1;
			while (currIndex <= twoIndex) {
				if (colors[currIndex] == 0) {
					tmp = colors[zeroStart];
					colors[zeroStart] = colors[currIndex];
					colors[currIndex] = tmp;
					zeroStart++;
					currIndex++;
				} else if (colors[currIndex] == 2) {
					tmp = colors[twoIndex];
					colors[twoIndex] = colors[currIndex];
					colors[currIndex] = tmp;
					twoIndex--;
				} else {// 1 case
					currIndex++;
				}
			}
		}
		return colors;
	}

	private static int[] sort(int[] colors) {
		int[] sorted = null;
		if (colors != null && colors.length > 0) {
			int start = 0, end = colors.length - 1;
			sorted = new int[colors.length];
			for (int i = 0; i < colors.length; i++) {
				if (colors[i] == 0) {
					sorted[start++] = 0;
				} else if (colors[i] == 2) {
					sorted[end--] = 2;
				}
			}
			for (; start <= end;) {
				sorted[start++] = 1;
			}
		}
		return sorted;
	}

}
