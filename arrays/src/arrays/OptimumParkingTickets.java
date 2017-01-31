package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OptimumParkingTickets {

	List<List<Integer>> ticket1 = new ArrayList<>();
	List<List<Integer>> ticket5 = new ArrayList<>();
	List<List<Integer>> ticket9 = new ArrayList<>();
	List<List<Integer>> ticket17 = new ArrayList<>();

	public static void main(String[] args) {
		List<Integer> visitingDays = Arrays.asList(1, 2, 3, 4, 5, 15, 16, 21, 22, 23, 30);

		Collections.sort(visitingDays);

		System.out.print("Visiting itinerary - ");
		for (int day : visitingDays) {
			System.out.print(day + " ");
		}
		System.out.println();
		System.out.println();

		OptimumParkingTickets booker = new OptimumParkingTickets();
		booker.reservedTheTickets(visitingDays);
		booker.printTickets();

	}

	private void printTickets() {
		System.out.println("Ticket 1 to buy   - " + ticket1.size());
		if (ticket1.size() > 0)
			System.out.println("      days - " + ticket1);

		System.out.println("Ticket 5 to buy   - " + ticket5.size());
		if (ticket5.size() > 0)
			System.out.println("      days - " + ticket5);

		System.out.println("Ticket 9 to buy   - " + ticket9.size());
		if (ticket9.size() > 0)
			System.out.println("      days - " + ticket9);

		System.out.println("Ticket 17 to buy  - " + ticket17.size());
		if (ticket17.size() > 0)
			System.out.println("      days - " + ticket17);

		int totalCost = ticket1.size() + (ticket5.size() * 5) + (ticket9.size() * 9) + (ticket17.size() * 17);
		System.out.println();
		System.out.println("Total cost - " + totalCost);

	}

	private void reservedTheTickets(List<Integer> visitingDays) {
		executeRuleForTicket1(visitingDays);
		executeRuleForTicket5(visitingDays);
		executeRuleForTicket9(visitingDays);
		executeRuleForTicket17(visitingDays);

	}

	private void executeRuleForTicket17(List<Integer> visitingDays) {
		internalExecuteRule(17, ticket9, ticket17);

	}

	private void executeRuleForTicket9(List<Integer> visitingDays) {
		internalExecuteRule(9, ticket5, ticket9);
	}

	private void executeRuleForTicket5(List<Integer> visitingDays) {
		// if consecutive five or more
		// buy ticket5
		internalExecuteRule(5, ticket1, ticket5);

	}

	private void internalExecuteRule(int ruleNo, List<List<Integer>> fromList, List<List<Integer>> toList) {
		if (fromList.isEmpty()) {
			return;
		}

		int consecutiveCounter = 1;
		List<List<Integer>> tmpFromList = new ArrayList<List<Integer>>(fromList);

		for (List<Integer> from : fromList) {

			int start = from.get(0);
			int expectedNextDay = start + 1;

			List<Integer> tmpTicket = new ArrayList<Integer>(from);

			int startIndex = 0;
			for (int i = 1; i < from.size(); i++) {
				if (from.get(i) == expectedNextDay) {
					consecutiveCounter++;
					expectedNextDay++;
				}

				if (consecutiveCounter == ruleNo) {
					tmpTicket.removeAll(from.subList(startIndex, i));
					toList.add(from.subList(startIndex, i));
					startIndex = i;
					consecutiveCounter = 0;
				}
			}
			tmpFromList.remove(from);
			tmpFromList.add(tmpTicket);
		}
		fromList.clear();
		fromList.addAll(tmpFromList);
	}

	private void executeRuleForTicket1(List<Integer> visitingDays) {
		ticket1.add(visitingDays);

	}

}
