import java.util.Iterator;
import java.util.ArrayList;

public class HashMap<K, V> implements Map<K, V> {

    private static class Node<K, V> {
        K key;
	V data;
	Node<K, V> next;
	Node<K, V> prev;

	public Node(K key, V data) {
            this.key = key;
	    this.data = data;
	}

	public K getKey() {
            return this.key;
	}

	public V getData() {
            return this.data;
	}
    }

    private Node[] table;
    private int numKeys;
    private int tableSize;

    public HashMap() {
        this.table = (Node[]) new Object[10];
	this.numKeys = 0;
	this.tableSize = 10;
    }

    @Override
    public void insert(K k, V v) throws IllegalArgumentException {
        if (this.has(k)) {
            throw new IllegalArgumentException("key already in map.");
	}
	int index = k.hashCode() % this.tableSize;
	Node<K, V> toAdd = new Node<K, V>(k, v);
	toAdd.next = table[index];
	this.table[index].prev = toAdd;
	this.table[index] = toAdd;
	this.numKeys++;
	if (((1.0*this.numKeys)/this.tableSize) >= 0.75) {
            this.rehash();
	} 
    }

    @Override
    public V remove(K k) throws IllegalArgumentException {
    	int index = k.hashCode() % this.tableSize;
	Node<K, V> head = this.table[index];
	while (head != null) {
            if (head.key.equals(k)) {
                head.prev.next = head.next;
		head.next.prev = head.prev;
		V toReturn = head.data;
		head = null;
		this.numKeys--;
		return toReturn;
	    }
	    head = head.next;
	}
	throw new IllegalArgumentException();
    }

    @Override
    public void put(K k, V v) throws IllegalArgumentException {
	int index = k.hashCode() % this.tableSize;
	Node<K, V> head = this.table[index];
	while (head != null) {
            if (head.key.equals(k)) {
                head.data = v;
		return;
	    }
	    head = head.next;
	}
        throw new IllegalArgumentException();
    }

    @Override
    public V get(K k) throws IllegalArgumentException {
        int index = k.hashCode() % this.tableSize;
	Node<K, V> head = table[index];
	while (head != null) {
            if (head.key.equals(k)) {
                return head.data;
	    }
	    head = head.next;
	}
        throw new IllegalArgumentException();
    }

    @Override
    public boolean has(K k) {
    	int index = k.hashCode() % this.tableSize;
	Node<K, V> head = this.table[index];
	while (head != null) {
            if (head.key.equals(k)) {
                return true;
	    }
	    head = head.next;
	}
        return false;
    }

    @Override
    public int size() {
    	return this.numKeys;
    }

    @Override
    public Iterator<K> iterator() {
    	ArrayList<K> iterable = new ArrayList<K>();
	for (int i = 0; i < this.tableSize; i++) {
	    Node<K, V> head = table[i];
            while (head != null) {
                iterable.add(head.key);
		head = head.next;
	    }
	}
	return iterable.iterator();
    }

    private void rehash() {
	Iterator<K> iter = this.iterator();
	ArrayList<Node<K, V>> nodes = new ArrayList<>();
	while (iter.hasNext()) {
            K key = iter.next();
	    V data = get(key);
	    Node<K, V> n = new Node<K, V>(key,data);
	    nodes.add(n);
	}
        this.table = (Node[])new Object[this.tableSize * 2];
	tableSize *= 2;
	this.numKeys = 0;
	int numElems =  nodes.size();
	for (int i = 0; i < numElems; i++) {
            this.insert(nodes.get(i).getKey(),
			nodes.get(i).getData());
	}
    }
}
