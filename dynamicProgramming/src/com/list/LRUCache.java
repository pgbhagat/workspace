package com.list;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    static class CacheEntry {
        int key;
        int value;
        CacheEntry next;
        CacheEntry prev;
    }

    int capacity;
    CacheEntry head;
    CacheEntry trail;
    Map<Integer, CacheEntry> cache = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new CacheEntry();
        trail = new CacheEntry();
        head.next = trail;
        trail.prev = head;
    }

    public int get(int key) {
        CacheEntry entry = cache.get(key);
        if (entry != null) {
            return entry.value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        CacheEntry entry = cache.get(key);
        if (entry != null) {
            entry.value = value;
            moveToHead(entry);
        } else {
            entry = new CacheEntry();
            entry.value = value;
            cache.put(key, entry);
            add(entry);

            if (cache.size() == capacity) {
                CacheEntry toDelete = popTrail();
                cache.remove(toDelete.key);
            }
        }
    }

    private CacheEntry popTrail() {
        CacheEntry toDelete = trail.prev;
        CacheEntry prev = toDelete.prev;
        prev.next = trail;
        trail.prev = prev;
        return toDelete;
    }

    private void moveToHead(CacheEntry entry) {
        remove(entry);
        add(entry);
    }

    private void add(CacheEntry entry) {
        CacheEntry next = head.next;
        CacheEntry prev = next.prev;
        entry.next = next;
        next.prev = entry;
        entry.prev = prev;
        prev.next = entry;
        head = entry;
    }

    private void remove(CacheEntry entry) {
        CacheEntry prev = entry.prev;
        CacheEntry next = entry.next;

        prev.next = next;
        next.prev = prev;
    }

}
