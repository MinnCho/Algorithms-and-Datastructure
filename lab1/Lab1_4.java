/*
Author: Minn Cho
Date Generated: 06/09/20
Last Updated: 09/09/20
Solves assignment 4 in LAB1

Implements a generic double circular linked list where we can either insert or remove the head and tail node.

Code for iterator from the book from Algorithms, 4th ed. Sedgewick & Wayne, page 155
*/

import java.util.Iterator;

public class Lab1_4<Item> implements Iterable<Item>{
 
        private Node head = null;
        private Node tail = null;

        public class Node<Item>
        {
            Item item;
            Node next;
            Node prev;
        }
    
        public void addHead(Item item) {            //inserts a new node at the front of list
            Node newNode = new Node();
            newNode.item = item;

            if(head == null){                       //if the list is empty, the new node is the head and tail
                head = newNode;
                head.next = head;
                head.prev = head;
                tail = head;
                print();
            }
            else{                                   //otherwise, set the new node as the head node
                newNode.next = head;                //new node points to old head
                head.prev = newNode;                //previous pointer of old head points to new node
                tail.next = newNode;                //tail points to new node
                newNode.prev = tail;                //previous pointer of new node points to tail
                head = newNode;                     //new node becomes the new head node
                print();
            }
        }

        public void addTail(Item item) {            //inserts a new node at the back of list
            Node newNode = new Node();
            newNode.item = item;

            if(tail == null){                       //if list is empty, the new node is the head and tail
                head = newNode;
                head.next = head;
                head.prev = head;
                tail = head;
                print();
            }
            else{                                   //otherwise, set new node as the tail node
                newNode.next = head;                //new node points to head
                head.prev = newNode;                //previous pointer of head points to new node
                tail.next = newNode;                //old tail points to new node
                newNode.prev = tail;                //previous pointer of new node points to old tail
                tail = newNode;                     //new node becomes the new tail
                print();
            }
        }
    
        public void removeHead(){                   //removes head node
            head = head.next;                       //set the next node of head as the new head
            head.prev = tail;                       //previous pointer of new head points to tail
            tail.next = head;                       //tail now points at that new heat
            print();
        }

        public void removeTail(){                   //removes tail node
            tail = tail.prev;                       //set the previous node of tail as the new tail
            tail.next = head;                       //new tail points to head
            head.prev = tail;                       //previous pointer of head points to new tail
            print();
        }

        public void print(){
            if(head.next == head){
                System.out.println("[" + head.item + "]");
            }
            else{
                Node ite = head;
                while(ite.next != head){
                    System.out.print("[" + ite.item + "]" + ",");
                    ite = ite.next;
                }
                System.out.println("[" + ite.item + "]");
            }
        }

        public void printIte(Lab1_4<Item> list){
            Iterator iterator = list.iterator();

            while(iterator.hasNext()){
                System.out.print("[" + iterator.next() + "]");
                if(iterator.hasNext()){
                    System.out.print(",");
                }
            }
            System.out.print("," + "[" + iterator.next() + "]");
        }

        public Iterator<Item> iterator(){ 
            return new ListIterator(); 
        }
    
        private class ListIterator implements Iterator<Item>{
            private Node current = head;
    
            public boolean hasNext(){
                 return current != tail; 
            }
    
            public void remove() { }
    
            public Item next(){
                Item item = (Item) current.item;
                current = current.next;
                return item;
            }
        }
        public static void main(String[] args) {

            Lab1_4<Integer> list = new Lab1_4<Integer>();

            list.addHead(1);
            list.addHead(2);
            list.addHead(4);
            list.addTail(5);
            list.addTail(9);
            list.addTail(3);
            list.addTail(3);
            list.addTail(3);
            list.removeHead();
            list.removeTail();
            list.removeTail();
            list.removeTail();
            list.removeTail();
            //list.removeHead();

            list.printIte(list);
            


            //Iterator Test
            
        }
    }