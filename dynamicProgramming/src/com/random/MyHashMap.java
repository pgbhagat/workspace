package com.random;

public class MyHashMap<K, V> {

    class Entry<K, V> {
        private K key;
        private V value;
        private Entry next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private int size = 0;
    private int capacity = 16;

    private Entry<K, V>[] table;

    public MyHashMap() {
        table = new MyHashMap.Entry[capacity];
        size = 0;
    }

    public void put(K key, V value) {
        int index = key.hashCode() % capacity;
        Entry node = table[index];
        Entry newEntry = new Entry<>(key, value);

        while (node != null) {
            if (node.key != null && node.key.equals(key)) {
                node.value = value;
                break;
            }
            node = node.next;
        }
        if (node == null) {
            newEntry.next = table[index];
            table[index] = newEntry;
        }

    }

    public V get(K key) {
        int index = key.hashCode() % capacity;
        Entry<K, V> node = table[index];
        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    public static void main(String... args) {
        MyHashMap<Integer, String> map = new MyHashMap();
        map.put(1, "Prashant1");
        map.put(2, "Prashant2");
        map.put(1, "Prashant3");
        System.out.println(map.get(1));

    }
}
