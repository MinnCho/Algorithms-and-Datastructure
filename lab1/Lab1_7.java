/*
Author: Minn Cho
Date Generated: 06/09/20
Last Updated: 06/09/20
Solves assignment 6 in LAB1

Implements a filter that checks if brackets "()" "[]" "{}" are balanced in a text file.

Code for stack implementation from the book from Algorithms, 4th ed. Sedgewick & Wayne, page 149
*/

import java.util.Scanner;

public class Lab1_7{
    
    public static class Stack{

        private Node first; // top of stack (most recently added node)
        private int N; // number of items

        private class Node{ // nested class to define nodes
            char item;
            Node next;
        }

        public boolean isEmpty() { return first == null; } // Or: N == 0.

        public int size() { return N; }

        public void push(char item){ // Add item to top of stack.
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
            N++;
        }

        public void pop(){ // Remove item from top of stack.
            char item = first.item;
            first = first.next;
            N--;
        }

        public boolean checkStack(char check){
            Node n = first;
            while(n.next != null){
                if(n.item == check){
                    return true;
                }
                n = n.next;
            }
            return false;
        }

        public boolean balanced (String item, Lab1_7.Stack stack){//method to check if brackets are balanced
            int length = item.length();
            char store;

            for(int i = 0; i < length; i++){//loop that checks the whole string using the charAt function

                store = item.charAt(i);                           

                if(store == '{' || store == '[' || store == '('){//if a left bracket is spotted, push it onto the stack
                    stack.push(store);
                }
                else if((store == ']' || store == '}' || store == ')') && (stack.isEmpty() == true)){//if the expression starts with a right bracket, no way it is balanced, so return false
                    return false;
                }
                else if((store == '}' && first. item != '{' )|| (store == ']' && first.item != '[')|| (store == ')' && first.item != '(')){//if right side bracket is found but the top of the stack does not contain the corresponding left side bracket, it is unbalanced so return false
                    return false;
                }
                else if((store == '}' && first. item == '{' )|| (store == ']' && first.item == '[')|| (store == ')' && first.item == '(') ){//if right side bracket is found and the top of the stack contains the corresponding left bracket, then pop that bracket and continue iterating through the string
                   stack.pop();                                                                                                                                                                                                          
                }
                else{//if anything other than brackets are found, ignore it and continue looking through the string
                    continue;
                }
            }
            return stack.isEmpty();//if the stack is empty at the end of the loop, it means that every left bracket had a matching pair at the correct position, thus meaning it is balanced
        }
    
    }

    public static void main(String[] args) {
        Lab1_7.Stack brackets_stack = new Lab1_7.Stack();

        Scanner scan = new Scanner(System.in);

            String input = scan.nextLine();
            Boolean n1 = brackets_stack.balanced(input, brackets_stack);

            if(n1){
                System.out.println("Balanced Brackets");
            }
            else{
                System.out.println("Unbalanced Brackets");
            }

    }
}

