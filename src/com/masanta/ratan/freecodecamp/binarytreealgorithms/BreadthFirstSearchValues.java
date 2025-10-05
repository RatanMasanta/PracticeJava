package com.masanta.ratan.freecodecamp.binarytreealgorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearchValues {

    static class Node{

        private BreadthFirstSearchValues.Node left = null;
        private BreadthFirstSearchValues.Node right  = null;
        private int data;

        public Node(int value){
            this.data = value;
        }

        public Node(int value, BreadthFirstSearchValues.Node left, BreadthFirstSearchValues.Node right){
            this.data = value;
            this.left = left;
            this.right = right;
        }

        public BreadthFirstSearchValues.Node getLeft(){
            return this.left;
        }
        public void setLeft(BreadthFirstSearchValues.Node left){
            this.left = left;
        }

        public void setRight(BreadthFirstSearchValues.Node right){
            this.right = right;
        }

        public BreadthFirstSearchValues.Node getRight(){
            return this.right;
        }

        public int getData(){
            return this.data;
        }

        public void setData(int value){
            this.data = value;
        }

    }

    public static void main(String[] args) {
        Node root = new Node(1, new BreadthFirstSearchValues.Node(2), new BreadthFirstSearchValues.Node(3));
        root.getLeft().setLeft(new BreadthFirstSearchValues.Node(4));
        root.getLeft().setRight(new BreadthFirstSearchValues.Node(5));
        root.getRight().setRight(new BreadthFirstSearchValues.Node(6));
        breadthFirstSearch(root).forEach(System.out::println);
    }
    
    public static List<Integer> breadthFirstSearch(Node root){
        if(root == null){ return null;}
        List<Integer> list = new ArrayList<>();
        Queue<Node> breadthQueue = new LinkedList<Node>();
        breadthQueue.add(root);
        while(!breadthQueue.isEmpty()){
            Node currentNode = breadthQueue.poll();
            list.add(currentNode.getData());
            if(currentNode.getLeft() != null){breadthQueue.add(currentNode.getLeft());}
            if(currentNode.getRight() != null){breadthQueue.add(currentNode.getRight());}
        }
        return list;
    }
    
}
