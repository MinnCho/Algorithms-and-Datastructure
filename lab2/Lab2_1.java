/*
Author: Minn Cho
Date Generated: 16/09/20
Last Updated: 19/09/20
Solves assignment 1 in LAB2

Implemets insertion sort.

Inspired  from Algorithms, 4th ed. Sedgewick & Wayne, page 155
*/

import java.util.Scanner;

public class Lab2_1{

    public static void sort(int[] a){                           //insertion sort

        for (int i = 1; i < a.length; i++){                     //starts from the 2nd index of the array
            for(int j = i; j > 0 && a[j] < a[j - 1]; j--){      //second loop compares current index with the value in the array index directly smaller 
                int temp;                                       //and swaps the positions if the value in the current index is smaller.
                temp = a[j];
                a[j] = a[j - 1];
                a[j - 1] = temp;
            }
            print(a);
        }
    }

    public static void print(int [] a){
        for(int i = 0; i < a.length; i++){
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
        System.out.print("Elements of Array: ");
        for(int i = 0; i < arrayLength; i++){
            int element = scan2.nextInt();
            array[i] = element;
        }

        sort(array);
    }
}
/*abstractLength of Array: 6
Elements of Array: 1
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
*/