package structures;

import java.util.NoSuchElementException;

/**
 * queue implementation.
 */
public class Queue<T> {
	
	/**
	 * Node used for queue's linked list
	 */
	static class Node<E> {
		E data; 
		Node<E> next;
		Node(E data, Node<E> next) {
			this.data = data;
			this.next = next;
		}
	}
	
	private Node<T> rear;
	private int size;
	
	public Queue() {
		rear = null;
		size = 0;
	}
	
	/**
	 * Adds an item to the end of the queue
	 */
	public void enqueue(T item) {
		Node<T> p = new Node<T>(item, null);
		if (rear == null) {
			p.next = p;
		} else {
			p.next = rear.next;
			rear.next = p;
		}
		++size;
		rear = p;
	}
	
	/**
	 * Deletes and returns the front of the queue
	 * 
	 */
	public T dequeue() {
		if (rear == null) {
			return null;
		}
		T temp = rear.next.data;
		if (size == 1) {
			rear = null;
		} else {
			rear.next = rear.next.next;
		}
		size--;
		return temp;
	}
	
	/**
	 * Tells if queue is empty.
	 * 
	 * @return True if queue is empty, false if not.
	 */
	public boolean isEmpty() {
		if(size==0) return true;
		return false;
	}
	
	/**
	 * Returns number of items in queue.
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Empties the queue.
	 */
	public void clear() {
		rear = null;
		size = 0;
	}
}

