package arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Competition {

	static class Contest implements Comparable<Contest> {
		int luck;
		boolean isImportant;

		Contest(int l, boolean b) {
			luck = l;
			isImportant = b;
		}

		@Override
		public int compareTo(Contest o) {
			if (o == this || o.luck == this.luck)
				return 0;
			else if (o.luck == 0)
				return 1;
			else if (this.luck < o.luck)
				return -1;
			else {
				return 1;
			}
		}
	}

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		int noOfContests = scanner.nextInt();
		int canNotLoseMoreThan = scanner.nextInt();

		List<Contest> contests = new ArrayList<>();
		for (int i = 0; i < noOfContests; i++) {
			contests.add(readContest(scanner));
		}

		int allImpContest = 0;
		Collections.sort(contests);
		for (Contest contest : contests) {
			if (contest.isImportant)
				allImpContest++;
		}
		int mustWin = allImpContest - canNotLoseMoreThan;

		int totalLuck = 0;
		for (Contest contest : contests) {
			if (contest.isImportant && mustWin > 0) {
				totalLuck -= contest.luck;
				mustWin--;
			} else {
				totalLuck += contest.luck;
			}

		}

		System.out.println(totalLuck);

	}

	private static Contest readContest(Scanner scanner) {
		int luck = scanner.nextInt();
		int OneOrZero = scanner.nextInt();
		boolean isImp = OneOrZero == 1 ? true : false;
		return new Contest(luck, isImp);
	}

}
