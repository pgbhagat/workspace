package com.stock.cache;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

//LRU cache implementation.. for now by default keeps 200 entries in memory
//if size grows beyond this limit, oldest entry gets removed
public class LRUCacheImpl<K, V> implements ILRUCache<K, V> {

	private int MAX_SIZE = 200;
	private Map<K, V> cacheStore = new LinkedHashMap<>(MAX_SIZE);

	LRUCacheImpl(int size) {
		if (size > 0) {
			this.MAX_SIZE = size;
		}
	}

	@Override
	public synchronized V get(K k) {
		if (cacheStore.containsKey(k)) {
			V value = cacheStore.remove(k);
			cacheStore.put(k, value);
			return value;
		}
		return null;
	}

	@Override
	public synchronized void put(K k, V v) {
		V value = get(k);
		if (value == null) {
			if (cacheStore.size() >= MAX_SIZE) {
				Iterator<K> iterator = cacheStore.keySet().iterator();
				cacheStore.remove(iterator.next());
			}
			cacheStore.put(k, v);
		} else {
		}
	}
}