package com.dynamicprogramming;

import java.util.Arrays;

class Box implements Comparable<Box> {
	int height;
	int width;
	int depth;

	Box(int h, int w, int d) {
		height = h;
		if (w > d) {
			width = w;
			depth = d;
		} else {
			width = d;
			depth = w;
		}
	}

	@Override
	public int compareTo(Box other) {
		int otherArea = other.depth * other.width;
		int thisArea = this.depth * this.width;
		return otherArea - thisArea;
	}

}

public class BoxStack {

	public static int maxBoxStackHeight(int[][] dimentions) {

		Box[] boxes = new Box[dimentions.length * 3];
		for (int i = 0; i < dimentions.length; i++) {
			// 0 = height, 1 = width, 2=depth
			int h = dimentions[i][0];
			int w = dimentions[i][1];
			int d = dimentions[i][2];

			boxes[3 * i] = new Box(h, w, d);
			boxes[3 * i + 1] = new Box(w, h, d);
			boxes[3 * i + 2] = new Box(d, h, w);
		}

		Arrays.sort(boxes);

		for (int i = 0; i < boxes.length; i++) {
			System.out.println(boxes[i].height + " " + boxes[i].width + " " + boxes[i].depth);
		}

		int[] max = new int[boxes.length];
		int[] result = new int[boxes.length];

		for (int i = 0; i < boxes.length; i++) {
			max[i] = boxes[i].height;
			result[i] = i;
		}

		for (int i = 1; i < boxes.length; i++) {
			for (int j = 0; j < i; j++) {
				if (boxes[j].width > boxes[i].width && boxes[j].depth > boxes[i].depth) {
					if (max[i] < (max[j] + boxes[i].height)) {
						max[i] = max[j] + boxes[i].height;
						result[i] = j;
					}
				}
			}
		}

		int maxIndex = 0;
		int maxHeight = 0;
		for (int i = 0; i < max.length; i++) {
			if (maxHeight < max[i]) {
				maxHeight = max[i];
				maxIndex = i;
			}
		}

		printBoxStack(maxIndex, result, boxes);
		return maxHeight;
	}

	private static void printBoxStack(int maxIndex, int[] result, Box[] boxes) {
		do {
			System.out.println("height - " + boxes[maxIndex].height + ", width -" + boxes[maxIndex].width + ", depth - "
					+ boxes[maxIndex].depth);
			maxIndex = result[maxIndex];
		} while (result[maxIndex] != maxIndex);
		System.out.println("height - " + boxes[maxIndex].height + ", width -" + boxes[maxIndex].width + ", depth - "
				+ boxes[maxIndex].depth);
	}

	public static void main(String[] args) {
		int[][] dimentions = { { 4, 7, 9 }, { 5, 8, 9 }, { 11, 20, 40 }, { 1, 2, 3 } };
		System.out.println("max possible height - " + maxBoxStackHeight(dimentions));
	}

}
