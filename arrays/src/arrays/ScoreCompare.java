package arrays;

import java.util.Scanner;

public class ScoreCompare {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int a0 = in.nextInt();
		int a1 = in.nextInt();
		int a2 = in.nextInt();

		int b0 = in.nextInt();
		int b1 = in.nextInt();
		int b2 = in.nextInt();
		
		in.close();
		
		Integer aliceScore = 0;
		Integer bobScore = 0;

		if (a0 > b0) {
			aliceScore++;
		} else if (a0 < b0) {
			bobScore++;
		}

		if (a1 > b1) {
			aliceScore++;
		} else if (a1 < b1) {
			bobScore++;
		}

		if (a2 > b2) {
			aliceScore++;
		} else if (a2 < b2) {
			bobScore++;
		}
		String strAliceScore = aliceScore != 0 ? aliceScore.toString() : "";
		String strBobScore = bobScore != 0 ? bobScore.toString() : "";

		System.out.println(strAliceScore + " " + strBobScore);

	}
}
