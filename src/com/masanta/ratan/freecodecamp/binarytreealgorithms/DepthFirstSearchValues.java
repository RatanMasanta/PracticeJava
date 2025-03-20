package com.masanta.ratan.freecodecamp.binarytreealgorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearchValues {

    static class Node{

        private Node left = null;
        private Node right  = null;
        private int data;

        public Node(int value){
            this.data = value;
        }

        public Node(int value, Node left, Node right){
            this.data = value;
            this.left = left;
            this.right = right;
        }

        public Node getLeft(){
            return this.left;
        }
        public void setLeft(Node left){
            this.left = left;
        }

        public void setRight(Node right){
            this.right = right;
        }

        public Node getRight(){
            return this.right;
        }

        public int getData(){
            return this.data;
        }

        public void setData(int value){
            this.data = value;
        }

    }

    void main(){
        Node root = new Node(1, new Node(2), new Node(3));
        root.getLeft().setLeft(new Node(4));
        root.getLeft().setRight(new Node(5));
        root.getRight().setRight(new Node(6));
        System.out.println(Arrays.toString(depthFirstSearchValues(root).stream().mapToInt(Integer::intValue).toArray()));
    }

    public static List<Integer> depthFirstSearchValues(Node root){
        Stack<Node> dfsStack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        dfsStack.push(root);
        while(!dfsStack.isEmpty()){
            Node currentNode = dfsStack.pop();
            res.add(currentNode.getData());
            if(currentNode.right != null){
                dfsStack.push(currentNode.getRight());
            }
            if(currentNode.left != null){
                dfsStack.push(currentNode.getLeft());
            }
        }
        return res;
    }



}
