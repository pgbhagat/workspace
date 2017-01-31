package arrays;

public class ParkingCharge {

	public static void main(String[] args) {
		String entry = "09:42";
		String exit = "11:42";
		System.out.println(new ParkingCharge().getParkingCost(entry, exit));
	}

	private int getParkingCost(String entry, String exit) {
		int totalCost = 0;
		int startHour = getHours(entry);
		int startMin = getMins(entry);
		int endHour = getHours(exit);
		int endMin = getMins(exit);
		int totalHours = endHour - startHour;
		int totalMins = endMin - startMin;
		int moreThanOneHour = totalHours - 1;
		if (totalMins > 0) {
			moreThanOneHour++;
		}
		int baseCost = 2 + 3;
		totalCost = baseCost + (4 * moreThanOneHour);
		return totalCost;
	}

	private int getMins(String entry) {
		String[] tokens = entry.split(":");
		return Integer.parseInt(tokens[1].trim());
	}

	private int getHours(String entry) {
		String[] tokens = entry.split(":");
		return Integer.parseInt(tokens[0].trim());
	}

}
