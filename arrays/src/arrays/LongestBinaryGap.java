package arrays;

public class LongestBinaryGap {

	public static void main(String[] args) {
		System.out.println(new LongestBinaryGap().getLongestBinaryGap(6));
	}

	private int getLongestBinaryGap(int i) {
		int longestGap = 0;
		String binaryString = Integer.toBinaryString(i);
		String[] tokens = binaryString.split("1");
		for (String token : tokens) {
			if (token.length() > longestGap) {
				longestGap = token.length();
			}
		}
		return longestGap;
	}

}
