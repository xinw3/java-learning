package purestorage;

public class MyHashMap<K, V> {
	private Entry<K, V>[] entry;
	private static int DEFAULT_INITIAL_CAPACITY = 1 << 4;	// aka 16
	private static double DEFAULT_LOADFACTOR = 0.75;
	private int capacity;
	private int size;
	private double loadFactor;
	
	public MyHashMap() {
		capacity = DEFAULT_INITIAL_CAPACITY;
		loadFactor = DEFAULT_LOADFACTOR;
		size = 0;
		entry = new Entry[capacity];
	}
	public MyHashMap(int cap) {
		this(cap, DEFAULT_LOADFACTOR);
	}
	public MyHashMap(int cap, double lf) {
		capacity = cap;
		loadFactor = lf;
		size = 0;
		entry = new Entry[capacity];
	}
	// First we need to have our own
	// underlying data structures
	// which is a key value pair.
	// in the case of collision happens,
	// we need to store different key with
	// the same hash value in the form of a 
	// linkedlist
	private class Entry<K, V> {
		K key;
		V value;
		Entry<K, V> next;
		Entry (K k, V v) {
			key = k;
			value = v;
			next = null;
		}
	}
	
	public void add(K k, V v) {
		int index = getHashVal(k);
		if (entry[index] == null) {
			entry[index] = new Entry<K, V>(k, v);
			size++;
		} else {	
			// collision happens
			// they will be stored in the form of linkedlist
			Entry<K, V> head = entry[index];
			Entry<K, V> curr = head;
			while (curr != null && curr.key != k) {
				curr = curr.next;
			}
			// no same key found, insert in the first place
			// much like a LRU cache, we insert the most recently
			// object in the front of the "linkedlist", also can
			// insert it in the final place.
			if (curr == null) {
				entry[index] = new Entry(k, v);
				entry[index].next = head;
				size++;
			} else {
				// update the key's value
				curr.value = v;
			}
		}
		double currLoadFactor = (double)size / capacity;
		if (currLoadFactor > loadFactor) {
			rehash();
		}
	}
	public void delete() {
		
	}
	public boolean lookup() {
		
	}
	public void clear() {
		
	}
	public void iterate() {
		
	}
	public int getHashVal(K k) {
		return Math.abs(k.hashCode()) % capacity;
	}
	// TODO: implement
	public void rehash() {
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
