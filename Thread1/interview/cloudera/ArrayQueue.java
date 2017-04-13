package cloudera;

import java.util.NoSuchElementException;

public class ArrayQueue<T> {
	
	private Object[] elements;
	private static final int DEFAULT_CAPACITY = 6;	
	private int front, back, nItems;
	
	public ArrayQueue() {
		elements = new Object[DEFAULT_CAPACITY];
		front = 0;
		back = -1;
		nItems = 0;
	}
	
	public synchronized void enqueue(T item) throws InterruptedException {
		if (isFull()) {
			wait();
		}
		
		back++;
		elements[back % elements.length] = item;
		nItems++;
		notifyAll();
	}
	
	public synchronized T dequeue() {
		if (isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		int index = front % elements.length;
		T item = (T) elements[index];
		elements[index] = null;
		front++;
		nItems--;
		return item;
	}
	
	public T peekFront() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return (T) elements[front % elements.length];
		
	}
	
	public boolean isEmpty() {
		return nItems == 0;
	}
	
	private boolean isFull() {
		return nItems == elements.length;
	}

	public static void main(String[] args) throws InterruptedException {
		ArrayQueue<Integer> queue = new ArrayQueue<Integer>();
        queue.enqueue(1);
        queue.enqueue(3);
        System.out.println(queue.dequeue()); // 1
        System.out.println(queue.peekFront()); // 3
        queue.enqueue(2);
        queue.enqueue(10);
        queue.enqueue(5);
        queue.enqueue(4);
        queue.enqueue(8);
        System.out.println(queue.peekFront()); // 3
        System.out.println(queue.dequeue()); // 3
        System.out.println(queue.peekFront()); // 2
        queue.enqueue(9);
        queue.enqueue(11); // exception (Queue is full)
	}

}
