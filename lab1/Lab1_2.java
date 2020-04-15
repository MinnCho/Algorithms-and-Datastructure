/*
Author: Minn Cho
Date Generated: 05/09/20
Last Updated: 06/09/20
Solves assignment 2 in LAB1

Takes in a string input of max length 10 and returns a reversed version of the input. Implemented using a stack.
*/

import java.util.Scanner;

public class Lab1_2{
    int max = 10;                       //sets the max length of the input string
    char store[] = new char[max];       //array to store the string
    int size;                           //counter to count actual size of string
    int pos;                            //counter to know current position in the array

    public Lab1_2(){                    //constructor for stack, sets position to a non-zero, non-positive number, -1
        pos = -1;
    }

    public void push(char in){
        if(pos >= max){                 //if the current position in the array is bigger than the max size, there is a stack overflow; stack is full
            System.out.println("Stack Overflow");
        }
        store[++pos] =  in;             //otherwise, increment the position and push the character value at that position
    }

    public char pop(){
        if(pos < 0){                    //if the current position is smaller than 0, there is a stack underflow; stack is empty
            System.out.println("Stack Underflow");
        }
        char out = store[pos--];        //store the value in the current position of the array into a variable and decrement the position
        char copyOut[] = new char[store.length - 1];    //create an array of length 1 smaller than original array
        for(int i = 0; i < copyOut.length; i++){        //copy values of original array into the smaller array
            copyOut[i] = store[i];
        }
        store = copyOut;                                //sett smaller array to be the original array so that when called upon again the length is updated
        return out;                                     //return the current value of the array
    }

    public static void printRev(String input){
        Lab1_2 stack = new Lab1_2();

       for(int i = 0; i < input.length(); i++){
           stack.push(input.charAt(i));                 //push every character of the string onto the stack
       }
       for(int j = 0; j < input.length(); j++){     
           System.out.print(stack.pop());               //pop the stack and print everytime the stack is poped; this will reverse the original string
       }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        printRev(input);
    }
}