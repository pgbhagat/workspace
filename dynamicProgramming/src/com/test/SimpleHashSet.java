package com.test;

public class SimpleHashSet<E> {

  private Entry<E>[] entries;
  private int size;

  public SimpleHashSet(int capacity) {
    if (capacity <= 0) {
      throw new ExceptionInInitializerError("Zero or negative capacity");
    }
    size = 0;
    entries = new Entry[capacity];
  }

  public static void main(String... args) {
    SimpleHashSet<Integer> myHashSet = new SimpleHashSet<>(10);
    myHashSet.add(8);
    System.out.println("added 8");
    myHashSet.add(7);
    System.out.println("added 7");
    System.out.println("contains 7: " + myHashSet.contains(7));
    System.out.println("contains 9: " + myHashSet.contains(9));
    myHashSet.remove(7);
    System.out.println("removed 7");
    System.out.println("contains 7: " + myHashSet.contains(7));
    System.out.println("contains 9: " + myHashSet.contains(9));

  }

  private int getHashCode(int hashcode) {
    if (hashcode < 0) {
      hashcode = -hashcode;
    }
    return hashcode % entries.length;
  }

  public boolean add(E element) {
    if (element != null) {
      if (!contains(element)) {
        Entry<E> thisEntry = new Entry<>();
        thisEntry.element = element;

        Entry<E> entry = entries[getHashCode(element.hashCode())];
        thisEntry.next = entry;
        entries[getHashCode(element.hashCode())] = thisEntry;
        return true;
      }
      return false;
    } else {
      throw new NullPointerException("Null element is not permitted");
    }
  }

  public boolean remove(E element) {
    if (element != null) {
      if (contains(element)) {
        Entry<E> previous = null;
        Entry<E> current = entries[getHashCode(element.hashCode())];
        while (current != null) {
          if (current.element.equals(element)) {
            if (previous == null) {
              current = current.next;
              entries[getHashCode(element.hashCode())] = current;
              return true;
            } else {
              previous = current;
              current = current.next;
            }
          }
        }
      }
    }
    return false;
  }

  public boolean contains(E element) {
    if (element != null) {
      Entry<E> entry = entries[getHashCode(element.hashCode())];
      while (entry != null) {
        if (entry.element.equals(element)) {
          return true;
        } else {
          entry = entry.next;
        }
      }
    }
    return false;
  }

  class Entry<E> {

    E element;
    Entry<E> next;
  }

}
