package com.masanta.ratan.daily.practice.dsa.practice.geeksforgeeks;

import com.masanta.ratan.daily.practice.leetcode.common.ListNode;

public class IsLinkedListLengthEven {

    /**
     * Problem Statement:
     *
     * Given a linked list, your task is to complete the function isLengthEven() which contains the head of the linked list, and check whether the length of the linked list is even or not. Return true if it is even, otherwise false.
     *
     * Examples:
     *
     * Input: Linked list: 12->52->10->47->95->0
     *
     * Output: true
     * Explanation: The length of the linked list is 6 which is even, hence returned true.
     * Input: Linked list: 9->4->3
     *
     * Output: false
     * Explanation: The length of the linked list is 3 which is odd, hence returned false.
     * Expected Time Complexity: O(n)
     * Expected Auxillary Space: O(1)
     *
     * Constraints:
     * 1 <= number of nodes <= 105
     * 1 <= elements of the linked list <= 105
     *
     */

    void main(){
        ListNode node = new ListNode(5);
        node.setNext(new ListNode(9));
        node.getNext().setNext(new ListNode(6));
        node.getNext().getNext().setNext(new ListNode(3));
        node.getNext().getNext().getNext().setNext(new ListNode(4));
        node.getNext().getNext().getNext().getNext().setNext(new ListNode(10));

        System.out.println("Is the linked list length even? " + isLengthEven(node));
    }

    public static boolean isLengthEven(ListNode head) {
        // code here

        int counter = 0;
        while(head != null){
            counter++;
            head = head.getNext();
        }
        return counter%2 == 0;

    }



}
