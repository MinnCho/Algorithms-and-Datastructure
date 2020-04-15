/*
Author: Minn Cho
Date Generated: 26/09/20
Last Updated: 26/09/20
Solves assignment 5 in LAB3

Implements a sparate chaining hash table.
Prints its content to see how well distributed it is.

Code from Algorithms, 4th ed. Sedgewick & Wayne, page 465
*/

import java.util.*;
@SuppressWarnings("unchecked")

public class Lab3_5<Key, Value> {

    private int n;       // number of key-value pairs
    private int m;       // hash table size
    private Node[] st;   // array of linked-list symbol tables

    // a helper linked list data type
    private static class Node {
        private Object key;
        private Object val;
        private Node next;

        public Node(Object key, Object val, Node next)  {
            this.key  = key;
            this.val  = val;
            this.next = next;
        }
    }

    // create separate chaining hash table
    public Lab3_5() {
        this(997);
    }

    // create separate chaining hash table with m lists
    public Lab3_5(int m) {
        this.m = m;
        st = new Node[m];
    }


    // hash value between 0 and m-1
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    // return number of key-value pairs in symbol table
    public int size() {
        return n;
    }

    // is the symbol table empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // is the key in the symbol table?
    public boolean contains(Key key) {
        return get(key) != null;
    }

    // return value associated with key, null if no such key
    public Value get(Key key) {
        int i = hash(key);
        for (Node x = st[i]; x != null; x = x.next) {
            if (key.equals(x.key)) 
                return (Value) x.val;
        }
        return null;
    }

    // insert key-value pair into the table
    public void put(Key key, Value val) {
        if (val == null) {
            delete(key);
            return;
        }
        int i = hash(key);
        for (Node x = st[i]; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        n++;
        st[i] = new Node(key, val, st[i]);
    }

    // delete key (and associated value) from the symbol table
    public void delete(Key key) {
        throw new UnsupportedOperationException("delete not currently supported");
    }

    // return all keys as an Iterable
    public Iterable<Key> keys()  {
        LinkedList<Key> queue = new LinkedList<Key>();
        for (int i = 0; i < m; i++) {
            for (Node x = st[i]; x != null; x = x.next) {
                queue.add((Key) x.key);
            }
        }
        return queue;
    }

    public void printHT () {
		for (int i = 0; i < st.length ; i++) {
			int size = 0;
			Node linkedlist = st[i];
			while( linkedlist != null){
				size++;
				linkedlist = linkedlist.next;
			}
			System.out.println(size);
		}
	}




    /***************************************************************************
     *  Unit test client.
     ***************************************************************************/
    public static void main(String[] args) {

        

        Scanner sc = new Scanner(System.in);

        class Stopwatch {
            private final long start;

            public Stopwatch() {
                start = System.currentTimeMillis();
            }

            public double elapsedTime() {
                long now = System.currentTimeMillis();
                return (now - start) / 1000.0;
            }
        }

        Stopwatch timer = new Stopwatch();

        Lab3_5<String, Integer> st = new Lab3_5<String, Integer>(97);

        for (int i = 0; sc.hasNext(); i++) {
            String key = sc.next();
            st.put(key, i);
        }
        

        st.printHT();


        // print keys

       /* for (String s : st.keys())
            System.out.println(s + " " + st.get(s));

            int index = 0;

            /*for (String s : st.keys()) {
                int length = 0;
                index = st.get(s);
                //Node startPoint = new mainst.No
                Node startPoint = st.st[index % 97];
                while (startPoint.next != null) {
                    length++;
                    startPoint = startPoint.next;
                }
                length++; //last node doesnt go in to the while loop
                System.out.println(length);
            }*/

            double time = timer.elapsedTime();
            System.out.println("Separate Chaining: " + time);

    }
}