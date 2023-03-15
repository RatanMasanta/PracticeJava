package com.masanta.ratan.daily.practice.leetcode.medium;

import com.masanta.ratan.daily.practice.leetcode.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class CheckCompletenessOfBinaryTree {

    public static void main(String[] args){
        TreeNode root = new TreeNode(1);
        root.setLeft(new TreeNode(2));
        root.setRight(new TreeNode(3));
        root.getLeft().setRight(new TreeNode(5));
        root.getRight().setLeft(new TreeNode (6));
        root.getRight().setRight(new TreeNode(7));

        System.out.println("The tree is complete: " + findCompletenessOfBinaryTree(root));
    }

    public static boolean findCompletenessOfBinaryTree(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while(queue.peek() != null){
            TreeNode node = queue.remove();
            queue.add(node.getLeft());
            queue.add(node.getRight());
        }
        while(!queue.isEmpty() && queue.peek() == null){
            queue.remove();
        }
        return queue.isEmpty();
    }

}
