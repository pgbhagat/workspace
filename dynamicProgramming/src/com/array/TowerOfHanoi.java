package com.array;

public class TowerOfHanoi {
	public static void main(String[] args) {
		move(4, "a", "b", "c");
	}

	private static void move(int totalPeg, String source, String aux, String destination) {
		if (totalPeg == 1) {
			System.out.println("Move disk " + totalPeg + " from " + source + " to " + destination);
		} else {
			move(totalPeg - 1, source, destination, aux);
			System.out.println("Move disk " + totalPeg + " from " + source + " to " + destination);
			move(totalPeg - 1, aux, source, destination);
		}
	}
}
