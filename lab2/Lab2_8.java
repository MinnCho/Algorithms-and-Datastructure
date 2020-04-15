/*
Author: Minn Cho
Date Generated: 21/09/20
Last Updated: 22/09/20
Solves assignment 8 in LAB2

Implements merge sort and quicksort.

From Algorithms, 4th ed. Sedgewick & Wayne, page 271, 273, 289, 291
*/

import java.util.Random;

public class Lab2_8{

    private static Comparable[] aux;

    public static void merge(Comparable[] a, int lo, int mid, int hi){ 
        int i = lo;
        int j = mid+1;

        for (int k = lo; k <= hi; k++){ // Copy a[lo..hi] to aux[lo..hi].
            aux[k] = a[k];
        }

        for (int k = lo; k <= hi; k++){ // Merge back to a[lo..hi].
            if (i > mid){
                a[k] = aux[j++];
            }
            else if (j > hi ){
                a[k] = aux[i++];
            }
            else if (less(aux[j], aux[i])){
                a[k] = aux[j++];
            }
            else{
                a[k] = aux[i++];
            }
        }
    }

    public static void mergeSort(Comparable[] a){
        aux = new Comparable[a.length]; // Allocate space just once.
        mergeSort(a, 0, a.length - 1);
    }

    private static void mergeSort(Comparable[] a, int lo, int hi){ // Sort a[lo..hi].
        if (hi <= lo) return;
        int mid = lo + (hi - lo)/2;
        mergeSort(a, lo, mid); // Sort left half.
        mergeSort(a, mid+1, hi); // Sort right half.
        merge(a, lo, mid, hi); // Merge results (code on page 271).
    }

    public static void quickSort(Comparable[] a){
        StdRandom.shuffle(a); // Eliminate dependence on input.
        quickSort(a, 0, a.length - 1);
    }

    private static void quickSort(Comparable[] a, int lo, int hi){
        if (hi <= lo) return;
        int j = partition(a, lo, hi); // Partition.
        quickSort(a, lo, j-1); // Sort left part a[lo .. j-1].
        quickSort(a, j+1, hi); // Sort right part a[j+1 .. hi].
    }

    private static int partition(Comparable[] a, int lo, int hi){ // Partition into a[lo..i-1], a[i], a[i+1..hi].
        int i = lo, j = hi+1; // left and right scan indices
        Comparable v = a[lo]; // partitioning item
        while (true){ // Scan right, scan left, check for scan complete, and exchange.
            while (less(a[++i], v)) 
                if (i == hi) 
                    break;
            while (less(v, a[--j])) 
                if (j == lo) 
                    break;
                if (i >= j) 
                    break;
            exch(a, i, j);
        }
        exch(a, lo, j); // Put v = a[j] into position
        return j; // with a[lo..j-1] <= a[j] <= a[j+1..hi].
    }

    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0; 
    }

    private static void exch(Comparable[] a, int i, int j){
        Comparable t = a[i]; a[i] = a[j]; a[j] = t; 
    }
    
    public static boolean isSorted(Comparable[] a){ // Test whether the array entries are in order.
        for (int i = 1; i < a.length; i++)
        if (less(a[i], a[i-1])) return false;
        return true;
    }

    private static void show(Comparable[] a){ // Print the array, on a single line.
        for (int i = 0; i < a.length; i++)
        System.out.print(a[i] + " ");
        System.out.println();
    }

    public static void print(Comparable [] a){
        int length = a.length;
        for(int i = 0; i < length; i++){
            System.out.print("[" + a[i] + "]" + " ");
        }
        System.out.println();
    }

    public static Integer[] randomArray(int size){
        Random rand = new Random(); 
        Integer[] a = new Integer[size];

        for(int i = 0; i < size; i++){
            a[i] = rand.nextInt(100);
        }

        return a;
    }

    public static class Stopwatch{
        private final long start;

        public Stopwatch(){
            start = System.currentTimeMillis(); 
        }
        public double elapsedTime(){
            long now = System.currentTimeMillis();
            return (now - start) / 1000.0;
        }
    }

    public static void main(String[] args){ // Read strings from standard input, sort them, and print.

        Integer[] a = randomArray(100000000);
        Integer[] b = randomArray(100000000);

        Stopwatch timer1 = new Stopwatch();
        mergeSort(a);
        double time1 = timer1.elapsedTime();

        Stopwatch timer2 = new Stopwatch();
        quickSort(b);
        double time2 = timer2.elapsedTime();

        if(isSorted(a))
        System.out.println("Merge sort time taken: " + time1);
        if(isSorted(b))
        System.out.println("Quick sort time taken: " + time2);
    }
}