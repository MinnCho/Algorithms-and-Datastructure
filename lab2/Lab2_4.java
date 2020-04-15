/*
Author: Minn Cho
Date Generated: 16/09/20
Last Updated: 19/09/20
Solves assignment 4 in LAB2

Implemets insertion sort.
Counts the number of inversions and displays the pairs to be inverted.

Inspired  from Algorithms, 4th ed. Sedgewick & Wayne, page 155
*/

import java.util.Scanner;

public class Lab2_4{

    public static void sort(int[] a){
        int swapcount = 0;

        for (int i = 1; i < a.length; i++){
            for(int j = i; j > 0 && a[j] < a[j - 1]; j--){
                int temp;
                temp = a[j];
                a[j] = a[j - 1];
                a[j - 1] = temp;
                swapcount++;
            }
            print(a);
        }
        System.out.println("The number of swaps is: " + swapcount);
    }

    public static void swapCount(int[] a){
        int swapcount = 0;
        int j = 0;
        while(j < a.length){//traverse array    
            int i = j + 1;
            while(i < a.length){//traverse array 1 index after the outer loop
                if(a[j] > a[i]){//compare every elements after the index and print the pairs when i is smaller than j
                System.out.println("[" + i + "," + a[i] + "] , [" + j + "," + a[j] + "]");
                swapcount++;
                }
                i++;
            }
            j++;
       }
        System.out.println("The number of inversions: " + swapcount);
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

        swapCount(array);
        sort(array);
    }
}
/*abstractLength of Array: 6
Elements of Array:
1
2
4
3
5
0
[5,0] , [0,1]
[5,0] , [1,2]
[3,3] , [2,4]
[5,0] , [2,4]
[5,0] , [3,3]
[5,0] , [4,5]
The number of inversions: 6
[1] [2] [4] [3] [5] [0]
[1] [2] [4] [3] [5] [0]
[1] [2] [3] [4] [5] [0]
[1] [2] [3] [4] [5] [0]
[0] [1] [2] [3] [4] [5]
The number of swaps is: 6
*/