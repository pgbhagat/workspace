package com.hackerank;

import java.util.HashMap;
import java.lang.ref.WeakReference;

public class HashMapCloneLeak {
	static WeakReference<Object> wr = null;

	private static HashMap<Integer, Object> makeMap() {
		HashMap<Integer, Object> map = new HashMap<Integer, Object>();
		Object testObject = new Object();
		wr = new WeakReference<Object>(testObject);
		map.put(42, testObject);
		return map;
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		HashMap<Integer, Object> hm = makeMap();
		hm = (HashMap<Integer, Object>) hm.clone();
		hm.clear();
		Object[] chain = null;
		long count = 0;
		while (wr.get() != null) {
			try {
				Object[] allocate = new Object[1000000];
				allocate[0] = chain;
				chain = allocate;
			} catch (OutOfMemoryError oome) {
				chain = null;
				System.out.println("out of memory error");
			}
			System.gc();
			count++;
			Thread.sleep(100);
		}
		System.out.println("done.. time : " + count);
	}
}
