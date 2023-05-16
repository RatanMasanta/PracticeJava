package com.masanta.ratan.daily.practice.leetcode.medium;

import com.masanta.ratan.daily.practice.leetcode.common.ListNode;

public class SwappingNodesInALinkedList {

    /**
     *
     * 1721. Swapping Nodes in a Linked List
     *
     * You are given the head of a linked list, and an integer k.
     *
     * Return the head of the linked list after swapping the values of the kth node from the beginning and the kth node from the end (the list is 1-indexed).
     *
     *
     *
     * Example 1:
     * Input: head = [1,2,3,4,5], k = 2
     * Output: [1,4,3,2,5]
     * Example 2:
     *
     * Input: head = [7,9,6,6,7,8,3,0,9,5], k = 5
     * Output: [7,9,6,6,8,7,3,0,9,5]
     *
     *
     * @param head ListNode provided
     * @param k replace the values from end and back
     * @return ListNode after swapping
     */
    public ListNode swapNodes(ListNode head, int k) {
        // To calculate end of list
        int size = 0;
        ListNode temp = head;
        while(temp!=null){
            temp=temp.getNext();
            size++;
        }

        int start = 1;
        // Moving Pointer p till k
        ListNode p = head;
        while(start < k){
            p = p.getNext();
            start++;
        }

        // Moving Pointer q from start to end-k rather than moving k step backwards from last
        start = 0;
        ListNode q = head;
        while(start < size-k){
            q = q.getNext();
            start++;
        }

        // swap logic
        int v = p.getVal();
        p.setVal(q.getVal());
        q.setVal(v);

        return head;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(7);
        head.setNext(new ListNode(9));
        head.getNext().setNext(new ListNode(6));
        head.getNext().getNext().setNext(new ListNode(6));
        head.getNext().getNext().getNext().setNext(new ListNode(7));
        head.getNext().getNext().getNext().getNext().setNext(new ListNode(8));
        head.getNext().getNext().getNext().getNext().getNext().setNext(new ListNode(3));
        head.getNext().getNext().getNext().getNext().getNext().getNext().setNext(new ListNode(0));
        head.getNext().getNext().getNext().getNext().getNext().getNext().getNext().setNext(new ListNode(9));
        head.getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext().setNext(new ListNode(5));
        int swapVal = 5;
        SwappingNodesInALinkedList linkedList = new SwappingNodesInALinkedList();
        head = linkedList.swapNodes(head, swapVal);
        while(head.getNext() != null){
            System.out.println("Linked list values are: " + head.getVal());
            head = head.getNext();
        }
    }


}
