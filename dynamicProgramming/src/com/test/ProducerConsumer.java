package com.test;

import java.util.LinkedList;
import java.util.List;

public class ProducerConsumer<T> {

  private List<T> queue;
  private int queueLength;

  ProducerConsumer(int queueLength) {
    this.queueLength = queueLength;
    queue = new LinkedList<>();
  }

  public static void main(String... args) throws InterruptedException {

    ProducerConsumer<Integer> producerConsumer = new ProducerConsumer(10);

    Thread producer = new Thread(() -> {
      int k = 0;
      while (true) {
        try {
          producerConsumer.produce(Integer.valueOf(k));
          System.out.println("Produced: " + k++);
          Thread.sleep(10);
        } catch (InterruptedException e) {
          //TODO
          e.printStackTrace();
        }
      }
    });

    Thread consumer = new Thread(() -> {
      while (true) {
        try {
          Integer k = producerConsumer.consume();
          System.out.println("consumed: " + k);
          Thread.sleep(100);
        } catch (InterruptedException e) {
          //TODO
          e.printStackTrace();
        }
      }

    });

    producer.start();
    consumer.start();

    producer.join();
    consumer.join();

  }

  private void produce(T n) throws InterruptedException {
    synchronized (this) {
      if (queue.size() == queueLength) {
        wait();
      }
      queue.add(n);
      notifyAll();
    }
  }

  private T consume() throws InterruptedException {
    synchronized (this) {
      if (queue.isEmpty()) {
        wait();
      }
      T item = queue.remove(0);
      notifyAll();
      return item;
    }
  }
}
