package com.random;

import java.util.*;

public class ProducerConsumer {
    public static void main(String[] args) {
        Data<Integer> data = new Data(5);

        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        data.produce(new Random().nextInt(100));
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        data.consume();
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        consumer.start();
        producer.start();
        try {
            consumer.join();
            producer.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}

class Data<T> {
    Queue<T> queue = new LinkedList<>();
    int capacity;

    Data(int capacity) {
        this.capacity = capacity;
    }

    public void produce(T element) throws InterruptedException {
        synchronized (this) {
            if (capacity == queue.size()) {
                System.out.println("Queue is full, need to wait");
                this.wait();
            }
            queue.add(element);
            System.out.println("Element [" + element + "] added to the queue");
            this.notifyAll();
        }
    }

    public T consume() throws InterruptedException {
        synchronized (this) {
            if (queue.isEmpty()) {
                System.out.println("Queue is empty, need to wait");
                this.wait();
            }
            T element = queue.poll();
            System.out.println("Element [" + element + "] removed from the queue");
            this.notifyAll();
            return element;
        }
    }
}
