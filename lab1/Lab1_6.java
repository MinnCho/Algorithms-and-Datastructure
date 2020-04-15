/*
Author: Minn Cho
Date Generated: 06/09/20
Last Updated: 06/09/20
Solves assignment 6 in LAB1

Implements a generic iterable FIFO queue using a double linked list. 
This is FIFO queue, so adds elements at the back of the queue and removes elements from the front.
Print function is implemented using both the iterator and double linked list.
You can also remove the kth element from the list.
Also is able to insert elemnts in ascending order.
*/

public class Lab1_6{

 
    private Node first;
    private Node last;

    public class Node
        {
            int item;
            Node next;
            Node prev;
        }
    
        public void add(int item){
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

        public void removeKth(int k){
            Node n = last;
            for(int i = 0; i < k; i++){
                n = n.prev;
            }
            if(n == last){
                last = last.prev;
                last.next = null;
                print();
            }
            else if(n == first){
                first = first.next;
                print();
            }
            else{
                n.prev.next = n.next;
                n.next.prev = n.prev;
                print();
            }
        }
    
        public void remove(){
            first = first.next;
            print();
            
        }

        public void insertAsc(int item){                //method to insert elements in ascending order
            Node n = first;
            Node newNode = new Node();
            newNode.item = item;

            if(first == null){                          //if list is empty, no need to sort by ascending, just insert the node    
                first = newNode;
                last = newNode;
                print();
            }
            else if(first.item >= newNode.item){        //if first node of the list is greater or equal to the new node, make the new node the first node
                newNode.next = first;
                first.prev = newNode;
                first = newNode;
                print();
            }
            else if(newNode.item >= last.item){         //if last node is smaller or equal to new node, set new node to be the last node
                newNode.prev = last;
                last.next = newNode;
                last = newNode;
                last.next = null;
                print();
            }
            else{
                while(newNode.item > n.item && n.next != null){     //otherwise, iterate through the list until a node that is greater than new node is found   
                    n = n.next;                                     
                }
                newNode.next = n;                                   //then rewire the nodes so that the new node is inserted before that node
                newNode.prev = n.prev;
                n.prev.next = newNode;
                n.prev = newNode;
                print();
            }
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


        
        public static void main(String[] args) {
            Lab1_6 list = new Lab1_6();

            list.add(5);
            list.add(6);
            list.add(8);
            list.add(9);
            list.remove();
            list.removeKth(0);

            Lab1_6 list2 = new Lab1_6();

            list2.insertAsc(1);
            list2.insertAsc(8);
            list2.insertAsc(3);
            list2.insertAsc(7);
            list2.insertAsc(7);
            list2.insertAsc(1);
            list2.insertAsc(9);
            list2.insertAsc(9);
            list2.insertAsc(10);
            
        }
    }
    