/*
Author: Minn Cho
Date Generated: 06/09/20
Last Updated: 09/09/20
Solves assignment 3 in LAB1

Implements a generic iterable FIFO queue using a double linked list. 
This is FIFO queue, so adds elements at the back of the queue and removes elements from the front.
Print function is implemented using both the iterator and double linked list.

Code for iterator from the book from Algorithms, 4th ed. Sedgewick & Wayne, page 155
*/

import java.util.Iterator;      //imports iterator

public class Lab1_3<Item> implements Iterable<Item>{

    public Iterator<Item> iterator(){ 
        return new ListIterator(); 
    }

    private class ListIterator implements Iterator<Item>{
        private Node current = first;

        public boolean hasNext(){
             return current != null; 
        }

        public void remove() { }

        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
 
    private Node first;
    private Node last;

    public class Node
        {
            Item item;
            Node next;
            Node prev;
        }
    
        public void add(Item item){         //method to add element to back of queue
            Node n = new Node();            //creates a new node with desired data value
            n.item = item;

            if(first == null){              //if this is an empty list, set desired node as first (=last)
                first = n;
                last = n;
                print();
            }
            else{                           //otherwise, add it to end of queue
                last.next = n;              //the old last node points to the new node
                n.prev = last;              //the new node points to the old last node
                last = n;                   //the added node becomes the last node
                print();
            }

        }
    
        public void remove(){               //removes the first element of the queue
            first = first.next;             //set first node of queue to be the next node of old first node
            print();
            
        }

        public void print(){
            if(first.next == null){
                System.out.println("[" + first.item + "]");
            }
            else{
                Node ite = first;
                while(ite.next != null){
                    System.out.print("[" + ite.item + "]" + ",");
                    ite = ite.next;
                }
                System.out.println("[" + ite.item + "]");
            }
        }

        public void printIte(Lab1_3<Item> list){          //print function using the iterator
            Iterator iterator = list.iterator();

            while(iterator.hasNext()){                    //while there is a next element, print that element
                System.out.print("[" + iterator.next() + "]");
                if(iterator.hasNext()){                   //prints a comma if there is a next element, if not doesn't
                    System.out.print(","); 
                }
            }
        }
        
        public static void main(String[] args) {
            Lab1_3<Integer> list = new Lab1_3<Integer>();

            list.add(5);
            list.remove();
            list.add(6);
            list.add(8);
            list.add(9);
            list.remove();
            list.remove();

            list.printIte(list);
            
        }
    }
    