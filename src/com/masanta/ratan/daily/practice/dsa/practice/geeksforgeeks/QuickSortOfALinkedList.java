package com.masanta.ratan.daily.practice.dsa.practice.geeksforgeeks;

import java.util.ArrayList;
import java.util.Collections;

public class QuickSortOfALinkedList {

    /**
     *
     * Problem statement
     *
     * You are given a Linked List. Sort the given Linked List using quicksort.
     *
     * Examples:
     *
     * Input: Linked list: 1->6->2
     * Output: 1->2->6
     *
     * Explanation:
     * After sorting the nodes, we have 1, 2 and 6.
     * Input: Linked list: 1->9->3->8
     * Output: 1->3->8->9
     *
     * Explanation:
     * After sorting the nodes, we have 1, 3, 8 and 9.
     * Constraints:
     * 1<= size of linked list <= 10^5
     *
     * Company: Paytm
     *
     */


    void main(){
        Node node = new Node(1);
        node.next = new Node(9);
        node.next.next = new Node(3);
        node.next.next.next = new Node(8);

        System.out.println("The quickSort returns : " + quickSort(node).toString());
    }

    /**
     *
     * @param node
     * @return
     */
    public static Node quickSort(Node node) {
        // Your code here
        if(node==null || node.next==null) return node;
        ArrayList<Integer>li=new ArrayList<>();
        Node cur=node;
        while(cur!=null){
            li.add(cur.data);
            cur=cur.next;
        }
        Collections.sort(li);
        cur=node;
        for(int i=0;i<li.size();i++){
            cur.data=li.get(i);
            cur=cur.next;
        }
        return node;
    }

    class Node
    {
        int data;
        Node next;
        Node(int key)
        {
            data = key;
            next = null;
        }

        @Override public String toString() {
            StringBuilder sb = new StringBuilder();
            Node current = this;
            while (current != null) {
                sb.append(current.data);
                if (current.next != null) {
                    sb.append(" -> ");
                }
                current = current.next;
            }
            return sb.toString();
        }

    }
}
