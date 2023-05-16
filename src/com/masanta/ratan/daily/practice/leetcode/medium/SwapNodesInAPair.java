package com.masanta.ratan.daily.practice.leetcode.medium;

import com.masanta.ratan.daily.practice.leetcode.common.ListNode;

public class SwapNodesInAPair {

    private int cnt = 0;

    /**
     *
     * 24. Swap Nodes in Pairs
     *
     * Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
     *
     * Example
     * Input: head = [1,2,3,4]
     * Output: [2,1,4,3]
     *
     *
     * @param node ListNode object
     * @return listNode after swapping
     */
    public ListNode swapPairs(ListNode node) {
        if (node == null) return null;

        cnt++;
        var ret = swapPairs(node.getNext());

        if (cnt % 2 == 1 && ret != null) {
            // node -> the first (left) node in each pair
            node.setNext(
                 ret.getNext()
            );
            ret.setNext(node);
        } else {
            // node -> the second (right) node in each pair
            node.setNext(ret);
            ret = node;
        }
        cnt--;
        return ret;
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


        SwapNodesInAPair swapNodesInAPair = new SwapNodesInAPair();
        head = swapNodesInAPair.swapPairs(head);
        while(head.getNext() != null){
            System.out.println("Linked list values are: " + head.getVal());
            head = head.getNext();
        }
    }

}
