/*
Author: Minn Cho
Date Generated: 06/09/20
Last Updated: 06/09/20
Solves assignment 5 in LAB1

Implements a generic iterable FIFO queue using a double linked list. 
This is FIFO queue, so adds elements at the back of the queue and removes elements from the front.
Print function is implemented using both the iterator and double linked list.
You can also remove the kth element from the list.

Code for iterator from the book from Algorithms, 4th ed. Sedgewick & Wayne, page 155
*/

import java.util.Iterator;

public class Lab1_5<Item> implements Iterable<Item>{

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
    
        public void add(Item item){
            Node n = new Node();
            n.item = item;

            if(first == null){
                first = n;
                last = n;
                print();
            }
            else{
                last.next = n;
                n.prev = last;
                last = n;
                print();
            }
        }

        public void removeKth(int k){           //removes kth element from list
            Node n = last;                      

            for(int i = 0; i < k; i++){         //initiate a node from last and iterate through the list k times
                n = n.prev;
            }
            if(n == last){                      //if the node you end up on is the last node
                last = last.prev;               //set last to be the previous node of old last
                last.next = null;               //set last to point to null
                print();    
            }
            else if(n == first){                //if the node you end up on is the first node
                first = first.next;             //set the next node of old first to be the first
                print();
            }
            else{                               //if it is none of the extremity cases
                n.prev.next = n.next;           //the previous node to the node to be removed(remNode) points to the next node of remNode
                n.next.prev = n.prev;           //the previous pointer of next node of remNode points to the previous node of remNode
                print();
            }
        }
    
        public void remove(){
            first = first.next;
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

        public void printIte(Lab1_5<Item> list){          //print function using the iterator
            Iterator iterator = list.iterator();

            while(iterator.hasNext()){                    //while there is a next element, print that element
                System.out.print("[" + iterator.next() + "]");
                if(iterator.hasNext()){                   //prints a comma if there is a next element, if not doesn't
                    System.out.print(","); 
                }
            }
        }
        
        public static void main(String[] args) {
            Lab1_5<Integer> list = new Lab1_5<Integer>();

            list.add(5);
            list.add(6);
            list.add(8);
            list.add(9);
            list.remove();
            list.removeKth(0);
            System.out.println();

            Lab1_5<Integer> list1 = new Lab1_5<Integer>();

            list1.add(5);
            list1.add(6);
            list1.add(8);
            list1.add(9);
            list1.remove();
            list1.removeKth(1);
            System.out.println();

            Lab1_5<Integer> list2 = new Lab1_5<Integer>();

            list2.add(5);
            list2.add(6);
            list2.add(8);
            list2.add(9);
            list2.remove();
            list2.removeKth(2);


            System.out.println();
            //Iterator Test
            list.printIte(list);
            System.out.println();
            list1.printIte(list);
            System.out.println();
            list2.printIte(list);
            
        }
    }
    