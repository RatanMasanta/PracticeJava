package com.masanta.ratan.daily.practice.dsa.practice.linear.linkedlists.design;

public class SingleLinkedList {
    private SingleLinkedList next;

    private int val;

    public SingleLinkedList(int val){
        this.val = val;
    }

    public int getVal(){
        return this.val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public void setNext(SingleLinkedList next){
        this.next = next;
    }

    public SingleLinkedList getNext(){
        return this.next;
    }

    public int length(SingleLinkedList headNode){
        int length = 0;
        SingleLinkedList currentNode = headNode;
        if(null == currentNode) return length;
        while(currentNode != null){
            length++;
            currentNode = currentNode.getNext();
        }
        return length;
    }

}
