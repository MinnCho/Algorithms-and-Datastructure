/*
Author: Minn Cho
Date Generated: 16/09/20
Last Updated: 19/09/20
Solves assignment 3 in LAB2

Implemets insertion sort.
Also counts the number of swaps.

Inspired  from Algorithms, 4th ed. Sedgewick & Wayne, page 155
*/

import java.util.Scanner;

public class Lab2_3{

    public static void sort(int[] a){
        int swapcount = 0;                  //initialize a variable to increment whenever there is a swap.

        for (int i = 1; i < a.length; i++){
            for(int j = i; j > 0 && a[j] < a[j - 1]; j--){
                int temp;
                temp = a[j];
                a[j] = a[j - 1];
                a[j - 1] = temp;
                swapcount++;                //in insertion sort, an inversion is a swap, so increment here.
            }
            print(a);
        }
        System.out.println("The number of swaps is: " + swapcount);
    }

    public static void print(int [] a){
        int length = a.length;
        for(int i = 0; i < length; i++){
            System.out.print("[" + a[i] + "]" + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Length of Array: ");
        int arrayLength = scan.nextInt();

        int[] array = new int[arrayLength];

        Scanner scan2 = new Scanner(System.in);
        System.out.println("Elements of Array: ");
        for(int i = 0; i < arrayLength; i++){
            int element = scan2.nextInt();
            array[i] = element;
        }

        sort(array);
    }
}
/*
Length of Array: 6
Elements of Array:
1
2
4
3
5
0
[1] [2] [4] [3] [5] [0]
[1] [2] [4] [3] [5] [0]
[1] [2] [3] [4] [5] [0]
[1] [2] [3] [4] [5] [0]
[0] [1] [2] [3] [4] [5]
The number of swaps is: 6
*/