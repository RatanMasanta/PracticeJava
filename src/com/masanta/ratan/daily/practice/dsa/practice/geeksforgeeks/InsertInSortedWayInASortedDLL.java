package com.masanta.ratan.daily.practice.dsa.practice.geeksforgeeks;

public class InsertInSortedWayInASortedDLL {

    /**
     *
     * Problem Statement:
     *
     * Given a sorted doubly linked list and an element x, you need to insert the element x into the correct position in the sorted Doubly linked list(DLL).
     *
     * Note: The DLL is sorted in ascending order
     *
     * Example:
     *
     * Input: LinkedList: 3->5->8->10->12 , x = 9
     *
     * Output: 3->5->8->9->10->12
     *
     * Explanation: Here node 9 is inserted in the Doubly Linked-List.
     * Input: LinkedList: 1->4->10->11 , x = 15
     *
     * Output: 1->4->10->11->15
     *
     * Constraints:
     * 1 <= number of nodes <= 103
     * 1 <= node -> data , x <= 104
     *
     */

    void main() {
        Node node = new Node(3);
        node.next = new Node(5);
        node.next.prev = node;
        node.next.next = new Node(8);
        node.next.next.prev = node.next;
        node.next.next.next = new Node(10);
        node.next.next.next.prev = node.next.next;
        node.next.next.next.next = new Node(12);
        node.next.next.next.next.prev = node.next.next.next;

        int insertNum = 9;

        System.out.println(STR."Value after adding \{insertNum} is \{sortedInsert(node, insertNum).toString()}");


    }

    private static Node sortedInsert(Node head, int x) {
        // add your code here
        if (head == null) return new Node(x);

        Node temp = head;


        if (x < head.data) {
            Node newNode = new Node(x);
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            return head;
        }


        while (temp.next != null) {
            if (x >= temp.data) {
                temp = temp.next;
            } else {
                Node newNode = new Node(x);
                Node previous = temp.prev;
                previous.next = newNode;
                newNode.prev = previous;
                newNode.next = temp;
                temp.prev = newNode;
                return head;
            }
        }

        // Insert at the end of the list
        Node newNode = new Node(x);
        if(x>=temp.data){

            temp.next = newNode;
            newNode.prev = temp;
        }else{
            newNode.prev=temp.prev;
            temp.prev.next=newNode;
            newNode.next=temp;

        }

        return head;

    }

    static class Node {
        int data;
        Node prev, next;

        Node(int data) {
            this.data = data;
            this.prev = this.next = null;
        }

        @Override public String toString() {
            StringBuilder sb = new StringBuilder();
            Node current = this; // Start from the current node
            while (current != null) {
                sb.append(current.data);
                if (current.next != null) {
                    sb.append(" <-> "); // Double arrow for doubly linked list
                }
                current = current.next;
            }
            return sb.toString();
        }
    }

}
