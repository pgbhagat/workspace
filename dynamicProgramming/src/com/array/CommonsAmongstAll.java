package com.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommonsAmongstAll {

	public static void main(String[] args) {
		int[] input1 = new int[] { 1, 2, 3, 4, };
		int[] input2 = new int[] { 3, 4, };
		int[] input3 = new int[] { 4, 1 };

		int[] common = findAllCommons(input1, input2, input3);
		print(common);
	}

	private static void print(int[] common) {
		for (int ele : common) {
			System.out.println(ele + " ");
		}
	}

	private static int[] findAllCommons(int[] input1, int[] input2, int[] input3) {
		Arrays.sort(input1);
		Arrays.sort(input2);
		Arrays.sort(input3);
		List<Integer> common = new ArrayList<>();
		int iLimit = input1.length - 1;
		int jLimit = input2.length - 1;
		int kLimit = input3.length - 1;
		int i = 0, j = 0, k = 0;
		while (i <= iLimit && j <= jLimit && k <= kLimit) {
			if (input1[i] == input2[j] && input2[j] == input3[k]) {
				common.add(input1[i]);
				i++;
				j++;
				k++;
			} else {
				if (input1[i] < input2[j]) {
					if (input1[i] < input3[k]) {
						i++;
					} else {
						k++;
					}
				} else {
					if (input2[j] < input3[k]) {
						j++;
					} else {
						k++;
					}
				}
			}

		}
		j = 0;
		int[] tmp = new int[common.size()];
		for (int ele : common) {
			tmp[j++] = ele;
		}
		return tmp;
	}

}
