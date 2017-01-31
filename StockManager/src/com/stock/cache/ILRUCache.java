package com.stock.cache;

public interface ILRUCache<K, V> {
	public V get(K k);

	public void put(K k, V v);
}
