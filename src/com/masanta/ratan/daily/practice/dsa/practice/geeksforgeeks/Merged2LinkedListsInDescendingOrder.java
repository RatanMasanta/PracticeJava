package com.masanta.ratan.daily.practice.dsa.practice.geeksforgeeks;


public class Merged2LinkedListsInDescendingOrder {

    /*
        Given two linked lists of size N and M, which are sorted in non-decreasing order. The task is to merge them in such a way that the resulting list is in non-increasing order.

        Example 1:

        Input:
        N = 2, M = 2
        list1 = 1->3
        list2 = 2->4
        Output:
        4->3->2->1
        Explanation:
        After merging the two lists in non-increasing order, we have new lists as 4->3->2->1.
        Example 2:

        Input:
        N = 4, M = 3
        list1 = 5->10->15->40
        list2 = 2->3->20
        Output:
        40->20->15->10->5->3->2
        Explanation:
        After merging the two lists in non-increasing order, we have new lists as 40->20->15->10->5->3->2.
        Your Task:
        The task is to complete the function mergeResult() which takes reference to the heads of both linked list and returns the pointer to the merged linked list.

        Expected Time Complexity : O(N+M)
        Expected Auxiliary Space : O(1)

        Constraints:
        0 <= N, M <= 10^4

     */

    static class Node
    {
        int data;
        Node next;

        Node(int d)
        {
            data = d;
            next = null;
        }
    }

    public static Node mergeResult(Node node1, Node node2)
    {
        // Your code here

        if(node1==null && node2==null){

            return node1;

        }

        Node d =new Node(-1);

        Node ans=d;



        while(node1!=null && node2!=null){

            if(node1.data>node2.data){

                ans.next=new Node(node2.data);

                node2=node2.next;

                ans=ans.next;

            }

            else{

                ans.next=new Node(node1.data);

                node1=node1.next;

                ans=ans.next;

            }

        }

        if(node1!=null){

            while(node1!=null){

                ans.next=new Node (node1.data);

                node1=node1.next;

                ans=ans.next;

            }

        }

        if(node2!=null){

            while(node2!=null){

                ans.next=new Node (node2.data);

                node2=node2.next;

                ans=ans.next;

            }

        }

        d=d.next;

        Node temp=null;

        Node cur =d;

        while(d.next!=null){

            cur=d.next;

            d.next=temp;

            temp=d;

            d=cur;

        }

        d.next=temp;

        temp=d;

        return temp;
    }

    public static void main(String[] args) {
        Node node1 = new Node(5);
        node1.next = new Node(10);
        node1.next.next = new Node(15);
        node1.next.next.next = new Node(40);
        Node node2 = new Node(2);
        node2.next = new Node(3);
        node2.next.next = new Node(8);
        node2.next.next.next = new Node(20);
        Node result = Merged2LinkedListsInDescendingOrder.mergeResult(node1, node2);
        while(result != null){
            System.out.print(result.data + " ");
            result = result.next;
        }
    }

}
