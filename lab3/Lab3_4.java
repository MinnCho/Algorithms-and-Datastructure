/*
Author: Minn Cho
Date Generated: 24/09/20
Last Updated: 24/09/20
Solves assignment 4 in LAB3

Implements a Red-Black tree.

Code from Algorithms, 4th ed. Sedgewick & Wayne, page 439
*/

import java.util.*;
import java.io.*;
import java.util.LinkedList; 
import java.util.ListIterator; 
import java.util.Scanner;

public class Lab3_4<Key extends Comparable<Key>, Value extends Comparable<Value>>{
    private Node root;

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node
    {
        Key key; // key
        Value val; // associated data
        Node left, right; // subtrees
        int N; // # nodes in this subtree
        boolean color; // color of link from

        Node(Key key, Value val, int N, boolean color)
        {
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }
    }

    public int sizeBst(){
        return sizeBst(root);
    }

    private int sizeBst(Node x){
        if (x == null) return 0;
        else return x.N;
    }

    public boolean contains(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("Argument to contains() cannot be null");
		}
		return getBst(key) != null;
    }

    public Value getBst(Key key){
        return getBst(root, key);
    }

    private Value getBst(Node x, Key key){ // Return value associated with key in the subtree rooted at x;
        // return null if key not present in subtree rooted at x.
        if (x == null) return null;
            int cmp = key.compareTo(x.key);
        if (cmp < 0) 
            return getBst(x.left, key);
        else if (cmp > 0) 
            return getBst(x.right, key);
        else 
            return x.val;
    }

    // See page 399.
    public void putBst(Key key, Value val){
        root = putBst(root, key, val);
    }
    
    private Node putBst(Node x, Key key, Value val){// Change keys value to val if key in subtree rooted at x.
    // Otherwise, add new node to subtree associating key with val.
        if (x == null) 
            return new Node(key, val, 1, RED);

        int cmp = key.compareTo(x.key);
        if (cmp < 0) 
            x.left = putBst(x.left, key, val);
        else if (cmp > 0) 
            x.right = putBst(x.right, key, val);
        else 
            x.val = val;
        
        x.N = sizeBst(x.left) + sizeBst(x.right) + 1;
        return x;
    }

    private boolean isRed(Node x)
    {
        if (x == null) return false;
        return x.color == RED;
    }

    private Node rotateLeft(Node h)
    {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left)+ size(h.right);
        return x;
    } 

    private Node rotateRight(Node h)
    {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    } 

    private void flipColors(Node h)
    {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    public int size()
    { 
        return size(root); 
    }

    private int size(Node x)
    {
        if (x == null) return 0;
        else return x.N;
    }

    public void put(Key key, Value val)
    { // Search for key. Update value if found; grow table if new.
        root = put(root, key, val);
        root.color = BLACK;
    }
    private Node put(Node h, Key key, Value val)
    {
        if (h == null) // Do standard insert, with red link to parent.
        return new Node(key, val, 1, RED);
        int cmp = key.compareTo(h.key);
        if (cmp < 0) 
            h.left = put(h.left, key, val);
        else if (cmp > 0) 
            h.right = put(h.right, key, val);
        else 
            h.val = val;
        if (isRed(h.right) && !isRed(h.left)) 
            h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) 
            h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) 
            flipColors(h);
        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }

    public Key min()
    {
        return min(root).key;
    }

    private Node min(Node x)
    {
        if (x.left == null) return x;
        return min(x.left);
    }

    public Key max()
    {
        return max(root).key;
    }

    private Node max(Node x)
    {
        if (x.right == null) return x;
        return max(x.right);
    }

    public Iterable<Key> keys()
    { 
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi)
    {
        Queue<Key> queue = new LinkedList<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi)
    {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) 
            keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) 
            queue.add(x.key);
        if (cmphi > 0) 
            keys(x.right, queue, lo, hi);
    }

    public static void main(String[] args) {
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
		
		Scanner sc = new Scanner(System.in);
		int minlen = 2; // key-length cutoff
        Stopwatch timer = new Stopwatch();
		Lab3_4<String, Integer> st = new Lab3_4<String, Integer>();
		while (sc.hasNext()) { // Build symbol table and count frequencies.
			String word = sc.next(); 
			if (word.length() < minlen)
				continue; // Ignore short keys.
			if (!st.contains(word))
				st.putBst(word, 1);
			else
				st.putBst(word, st.getBst(word) + 1);
        }
        
		// Find a key with the highest frequency count.
		String max = "";
		st.putBst(max, 0);
		for (String word : st.keys())
			if (st.getBst(word) > st.getBst(max))
				max = word;
		System.out.println(max + " " + st.getBst(max));

		double time = timer.elapsedTime();
        System.out.println("Red Black tree time: " + time);
	}
}