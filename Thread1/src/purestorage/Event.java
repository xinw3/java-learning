package purestorage;

import java.util.LinkedList;
import java.util.Queue;

import javax.security.auth.callback.Callback;

public class Event {
	

	boolean fired = false;
	Queue<Callback> queue = new LinkedList<Callback>();
	Object lock = new Object();
	
	/*
	 *  Sequential version
	 */
	public void register(Callback cb) {
		if (!fired) {
			queue.offer(cb);
		} else {
			cb.invoke();
		}
	}
	
	public void fire() {
		while(!queue.isEmpty()) {
			Callback cb = queue.poll();
			cb.invoke();
		}
		fired = true;
	}
	
	/*
	 *  Multi-threading version 1
	 *  We need to protect shared variables 
	 *  so that only one thread can access them at a time.
	 *  However, this version is slow because it makes the two
	 *  functions atomic so that when one thread executing a function
	 *  another function would never be executed.
	 */
	public void register(Callback cb) {
		synchronized(lock) {
			if (fired) {
				queue.offer(cb);
			} else {
				cb.invoke();
			}
		}
	}
	
	public void fire() {
		synchronized(lock) {
			while (!queue.isEmpty()) {
				Callback cb = queue.poll();
				cb.invoke();
			}
			fired = true;
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Event e = new Event();
		

	}

}
