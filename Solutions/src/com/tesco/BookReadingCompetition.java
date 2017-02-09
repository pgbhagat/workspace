package com.tesco;

// Read only region start
public class BookReadingCompetition {

	public static void main(String... args) {
		BookReadingCompetition books = new BookReadingCompetition();
		int maxProfit = books.Books(3, 7, new int[] { 2, 6, 9 }, new int[] { 3, 5, 3 }, 0);
		System.out.println(maxProfit);
	}

	public int Books(int input1, int input2, int[] input3, int[] input4) {
		if (input1 <= 0) {
			throw new UnsupportedOperationException("total no of books should be more than zero");
		}
		if (input2 > 160 || input2 <= 0) {
			throw new UnsupportedOperationException("total time for cometition should be >0 and <=160");
		}
		if (input3 == null || input3.length == 0) {
			throw new UnsupportedOperationException("number of points array can not be empty or null");
		}
		if (input4 == null || input4.length == 0) {
			throw new UnsupportedOperationException("amount of time to read a book array can not be empty or null");
		}
		if (input3.length != input4.length) {
			throw new UnsupportedOperationException(
					"amount of time to read a book array and  number of points array  should match in size");
		}
		return Books(input1, input2, input3, input4, 0);
	}

	public int Books(int size, int maxTime, int[] price, int[] readTime, int index) {
		int profit = 0;
		if (index < price.length) {
			int costWithThisBook = 0;
			int costWithoutThisBook = 0;
			if (readTime[index] <= maxTime) {
				costWithThisBook = price[index] + Books(size, maxTime - readTime[index], price, readTime, index + 1);
				costWithoutThisBook = Books(size, maxTime, price, readTime, index + 1);
			} else {
				return Books(size, maxTime, price, readTime, index + 1);
			}

			profit = Math.max(costWithThisBook, costWithoutThisBook);
		}
		return profit;
	}
}