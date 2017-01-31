package arrays;

public class OddOccurencesInArray {

	public static void main(String[] args) {
		int[] input = { 1, 2, 3, 4, 3, 2, 4, 1, 5 };
		System.out.println(new OddOccurencesInArray().getUnpairedElement(input));
	}

	private int getUnpairedElement(int[] input) {
		int ret = 0;
		if (validInput(input)) {
			ret = input[0];
			for (int i = 1; i < input.length; i++) {
				ret = ret ^ input[i];
			}
		}
		return ret;
	}

	private boolean validInput(int[] input) {
		if (input == null || input.length == 0 || input.length > 1000000 || input.length % 2 == 0)
			return false;
		for (int a : input) {
			if (a > 1000000000 || a < 1) {
				return false;
			}
		}
		return true;
	}

}
