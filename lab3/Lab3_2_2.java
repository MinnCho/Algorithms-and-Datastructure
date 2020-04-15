/*
Author: Minn Cho
Date Generated: 23/09/20
Last Updated: 23/09/20
Solves assignment 2 in LAB3

Implements binary search tree.

Code from Algorithms, 4th ed. Sedgewick & Wayne, page 398 - 399
*/

import java.util.*;
import java.util.Scanner;

public class Lab3_2_2<Key extends Comparable<Key>, Value>{
    //BST
    private Node root; // root of BST

    private class Node
    {
        private Key key; // key       
        private Value val; // associated value
        private Node left, right; // links to subtrees
        private int N; // # nodes in subtree rooted here

        public Node(Key key, Value val, int N){
            this.key = key; 
            this.val = val; 
            this.N = N;
        }
    }

    public int sizeBst(){
        return sizeBst(root);
    }

    private int sizeBst(Node x){
        if (x == null) return 0;
        else return x.N;
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
            return new Node(key, val, 1);

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

    public Key floor(Key key)
        {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node floor(Node x, Key key)
    {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t;
        else return x;
    }

    public boolean contains(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("Argument to contains() cannot be null");
		}
		return getBst(key) != null;
    }
    
    public Key select(int k)
    {
        return select(root, k).key;
    }

    private Node select(Node x, int k)
    { // Return Node containing key of rank k.
        if (x == null) return null;
        int t = sizeBst(x.left);
        if (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k-t-1);
        else return x;
    }

    public int rank(Key key)
    { 
        return rank(key, root); 
    }

    private int rank(Key key, Node x)
    { // Return number of keys less than x.key in the subtree rooted at x.
        if (x == null) 
            return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) 
            return rank(key, x.left);
        else if (cmp > 0) 
            return 1 + sizeBst(x.left) + rank(key, x.right);
        else return 
            sizeBst(x.left);
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

		Lab3_2_2<String, Integer> st = new Lab3_2_2<String, Integer>();
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
        System.out.println("Algorithm 3.3 BST time: " + time);

	}
}