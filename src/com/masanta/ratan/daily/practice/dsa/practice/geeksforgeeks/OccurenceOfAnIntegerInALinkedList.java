package com.masanta.ratan.daily.practice.dsa.practice.geeksforgeeks;

import com.masanta.ratan.daily.practice.leetcode.common.ListNode;

public class OccurenceOfAnIntegerInALinkedList {


    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.setNext(new ListNode(2));
        listNode.getNext().setNext(new ListNode(1));
        listNode.getNext().getNext().setNext(new ListNode(2));
        listNode.getNext().getNext().getNext().setNext(new ListNode(1));
        listNode.getNext().getNext().getNext().getNext().setNext(new ListNode(3));
        listNode.getNext().getNext().getNext().getNext().getNext().setNext(new ListNode(1));

        int finderValue = 1;

        System.out.println(STR."The occurence of \{finderValue} is \{count(listNode, finderValue)}");
    }

    public static int count(ListNode head, int key) {
        // code here
        int ans = 0;
        while(head != null){
            if(head.getVal() == key) ans++;
            head = head.getNext();
        }
        return ans;
    }
}
