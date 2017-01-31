package arrays;

import java.util.HashMap;
import java.util.Map;

public class StringTransformer {

	class Rules {
		Map<String, String> rules;

		Rules() {
			rules = new HashMap<>();
			rules.put("AB", "A");// AB->AA->A
			rules.put("BA", "A");// BA->AA->A
			rules.put("CB", "C");// BC->CC-C
			rules.put("BC", "C");// BC->CC-C
			rules.put("AA", "A");
			rules.put("CC", "C");
		}

		public String transform(char[] chars, int index) {
			if (index == chars.length) {
				return "";
			} else {
				char currentChar = chars[index];
				String str = String.valueOf(currentChar);
				String ret = transform(chars, index + 1);
				if (ret.length() > 0) {
					str += ret.charAt(0);
					if (rules.get(str) != null) {
						return rules.get(str) + ret.substring(1);
					} else {
						return currentChar + ret;
					}
				} else {
					return str;
				}
			}
		}
	}

	public String transform(String str) {
		if (validateInput(str)) {
			return new Rules().transform(str.toUpperCase().toCharArray(), 0);
		} else {
			return null;
		}
	}

	public static void main(String[] args) {
		String str = "ABBBCC";
		System.out.println(new StringTransformer().transform(str));
	}

	public boolean validateInput(String str) {
		if (str == null || str.isEmpty() || str.length() > 50000) {
			return false;
		}
		String input = str.toUpperCase();
		for (char c : input.toCharArray()) {
			if (c == 'A' || c == 'B' || c == 'C') {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}

}
