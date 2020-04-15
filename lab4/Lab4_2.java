/*
Author: Minn Cho
Date Generated: 01/10/20
Last Updated: 03/10/20
Solves assignment 2 in LAB4

Takes in two state names and outputs the path between them using BFS on an undirected graph.

Code from Algorithms, 4th ed. Sedgewick & Wayne.
*/

import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;

public class Lab4_2{

    public static class Paths
    {
        private boolean[] marked; // Is a shortest path to this vertex known?
        private int[] edgeTo; // last vertex on known path to this vertex
        private final int s; // source

        public Paths(Graph G, int s){
            marked = new boolean[G.V()];
            edgeTo = new int[G.V()];
            this.s = s;
            bfs(G, s);
        }

        private void bfs(Graph G, int s){
            LinkedList<Integer> queue = new LinkedList<Integer>();
            marked[s] = true; // Mark the source
            queue.add(s); // and put it on the queue.
            while (!queue.isEmpty()){
                int v = queue.remove(); // Remove next vertex from the queue.

                for (int w : G.adj(v))
                    if (!marked[w]){ // For every unmarked adjacent vertex,
                        edgeTo[w] = v; // save last edge on a shortest path,
                        marked[w] = true; // mark it because path is known,
                        queue.add(w); // and add it to the queue.
                    }
                }
        }

        public boolean hasPathTo(int v){
            return marked[v]; 
        }

        public Iterable<Integer> pathTo(int v){
            if (!hasPathTo(v)) 
                return null;
            LinkedList<Integer> path = new LinkedList<Integer>();
            for (int x = v; x != s; x = edgeTo[x])
                path.addFirst(x);
            path.addFirst(s);
            return path;
        }
    }

	public static class Graph {
		private final int V;
		private int E;
		private ArrayList<LinkedList<Integer>> adj; //private Bag<Integer>[] adj;

		public Graph(int V) {
			this.V = V;
			this.E = 0;
			adj = new ArrayList<LinkedList<Integer>>(); //(Bag<Integer>[]) new Bag[V];
			for (int v = 0; v < V; v++) {
				adj.add(new LinkedList<Integer>()); //new Bag<Integer>();
			}
		}

		public Graph(Scanner in) {
			this(in.nextInt());
			int E = in.nextInt();
			for (int i = 0; i < E; i++){ //Add an edge.
				int v = in.nextInt();
				int w = in.nextInt();
				addEdge(v, w);
			}
		}

		public int V() {
			return V;
		}

		public int E() {
			return E;
		}

		public void addEdge(int v, int w) {
			adj.get(v).add(w); // adj[v].add(w); Add w to vs list.
			adj.get(w).add(v); // adj[w].add(v); Add v to ws list.
			E++;
		}

		public Iterable<Integer> adj(int v) {
			return adj.get(v);
		}
	}

	public class DepthFirstSearch {
		private boolean[] marked;
		private int count;

		public DepthFirstSearch(Graph G, int s) {
			marked = new boolean[G.V()];
			dfs(G, s);
		}

		private void dfs(Graph G, int v) {
			marked[v] = true;
			count++;
			for (int w : G.adj(v)) {
				if (!marked[w]) {
					dfs(G, w);
				}
			}
		}

		public boolean marked(int w) {
			return marked[w];
		}

		public int count() {
			return count;
		}
	}

	public static void main(String[] args) {
        Lab3_2_2<String, Integer> st = new Lab3_2_2<String, Integer>();
        Lab3_2_2<Integer, String> st2 = new Lab3_2_2<Integer, String>();

        int i = 0;
        int k = 0;
        int[] text = new int[214];

        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String word = sc.next();
            if(!st.contains(word)){
                st.putBst(word, i);
                st2.putBst(i , word);
                i++;
            }

            int convert = st.getBst(word);
            text[k] = convert;
            //System.out.println(text[k]);
            k++;
        }

        Graph G = new Graph(st2.sizeBst());


        for(int j = 0; j < text.length; j += 2){
            G.addEdge(text[j],text[j+1]);
        }

        String from = args[0];
        String to = args[1];

        Paths search = new Paths(G, st.getBst(from));
 

        StdOut.print(st2.getBst(st.getBst(from)) + " to " + st2.getBst(st.getBst(to)) + ": ");
        if (search.hasPathTo(st.getBst(to)))
        for (int x : search.pathTo(st.getBst(to)))
            if (x == 0) 
                StdOut.print(st2.getBst(x));
            else 
                StdOut.print(" - " + st2.getBst(x));
        StdOut.println();
        StdOut.println();

	}
}