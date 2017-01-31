package com.burrito.items;

import java.util.Map;
import java.util.Map.Entry;

public class ItemHelper {

	public static void printItems(Map<IItem, Float> allItemMenu) {
		int count = 0;
		for (Entry<IItem, Float> entry : allItemMenu.entrySet()) {
			count++;
			System.out.println(count + ". " + entry.getKey().getName() + " -> $" + entry.getValue());
		}
	}
}
