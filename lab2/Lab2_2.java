/*
Author: Minn Cho
Date Generated: 16/09/20
Last Updated: 19/09/20
Solves assignment 2 in LAB2

Implemets insertion sort. Prints in descending order using simple algebraic manipulation.

Inspired  from Algorithms, 4th ed. Sedgewick & Wayne, page 155
*/

import java.util.Scanner;

public class Lab2_2{

    public static void sort(int[] a){

        for (int i = 1; i < a.length; i++){
            for(int j = i; j > 0 && a[j] < a[j - 1]; j--){
                int temp;
                temp = a[j];
                a[j] = a[j - 1];
                a[j - 1] = temp;
            }
            print(a);
        }
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
        System.out.print("Elements of Array: ");
        for(int i = 0; i < arrayLength; i++){
            int element = scan2.nextInt();
            array[i] = element * (-1);              //multiplies with -1 when entering values into array
        }                                           //this makes what would be the biggest value normaly to be the smallest value and vice versa.
        sort(array);

        for(int i = 0; i < arrayLength; i++){      //multiply by -1 again to change the signs back 
            array[i] = array[i] * (-1);
        }

        print(array);
    }
}
/*
Length of Array: 6
Elements of Array: 1
2
4
3
5
0
[-2] [-1] [-4] [-3] [-5] [0]
[-4] [-2] [-1] [-3] [-5] [0]
[-4] [-3] [-2] [-1] [-5] [0]
[-5] [-4] [-3] [-2] [-1] [0]
[-5] [-4] [-3] [-2] [-1] [0]
[5] [4] [3] [2] [1] [0]
*/