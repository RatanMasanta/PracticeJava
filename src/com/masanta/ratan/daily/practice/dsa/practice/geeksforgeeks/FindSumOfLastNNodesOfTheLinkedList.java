package com.masanta.ratan.daily.practice.dsa.practice.geeksforgeeks;

import com.masanta.ratan.daily.practice.leetcode.common.ListNode;

import java.util.ArrayList;
import java.util.List;

public class FindSumOfLastNNodesOfTheLinkedList {

    /* Problem statement:
     * Find the Sum of Last N ListNodes of the Linked List
        Difficulty: EasyAccuracy: 61.47%Submissions: 42K+Points: 2
        Given a single linked list, calculate the sum of the last n ListNodes.

        Note: It is guaranteed that n <= number of ListNodes.

        Examples:

        Input: Linked List: 5->9->6->3->4->10, n = 3

        Output: 17
        Explanation: The sum of the last three ListNodes in the linked list is 3 + 4 + 10 = 17.
        Input: Linked List: 1->2, n = 2

        Output: 3
        Explanation: The sum of the last two ListNodes in the linked list is 2 + 1 = 3.
        Constraints:
        1 <= number of ListNodes, n <= 105
        1 <= ListNode->data <= 103
     *
     */

    public static void main(String[] args) {
        ListNode node = new ListNode(5);
        node.setNext(new ListNode(9));
        node.getNext().setNext(new ListNode(6));
        node.getNext().getNext().setNext(new ListNode(3));
        node.getNext().getNext().getNext().setNext(new ListNode(4));
        node.getNext().getNext().getNext().getNext().setNext(new ListNode(10));

        System.out.println("sum of last N nodes is: " + sumOfLastN_ListNodes(node, 3));
    }


    public  static int sumOfLastN_ListNodes(ListNode head, int n) {
        List<Integer> list = new ArrayList<>();
        while(head != null){
            list.add (head.getVal());
            head = head.getNext();
        }

        int size = list.size(), sum = 0;
        for(int i = size - 1; i > size - n - 1; i-- ){
            sum += list.get(i);
        }
        return sum;
    }
}
