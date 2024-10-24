package com.masanta.ratan.daily.practice.leetcode.medium;

import com.masanta.ratan.daily.practice.leetcode.common.TreeNode;

import java.sql.SQLOutput;

public class FlipEquivalentBinaryTrees_951 {

    /*
        Problem statement:

        951. Flip Equivalent Binary Trees

        For a binary tree T, we can define a flip operation as follows: choose any node, and swap the left and right child subtrees.

        A binary tree X is flip equivalent to a binary tree Y if and only if we can make X equal to Y after some number of flip operations.

        Given the roots of two binary trees root1 and root2, return true if the two trees are flip equivalent or false otherwise.



        Example 1:

        Flipped Trees Diagram
        Input: root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,null,8,7]
        Output: true
        Explanation: We flipped at nodes with values 1, 3, and 5.
        Example 2:

        Input: root1 = [], root2 = []
        Output: true
        Example 3:

        Input: root1 = [], root2 = [1]
        Output: false


        Constraints:

        The number of nodes in each tree is in the range [0, 100].
        Each tree will have unique node values in the range [0, 99].

     */


    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        node1.setLeft(new TreeNode(2));
        node1.setRight(new TreeNode(3));
        node1.getLeft().setLeft(new TreeNode(4));
        node1.getLeft().setRight(new TreeNode(5));
        node1.getRight().setLeft(new TreeNode(6));
        node1.getLeft().getRight().setLeft(new TreeNode(7));
        node1.getLeft().getRight().setRight(new TreeNode(8));

        TreeNode node2 = new TreeNode(1);
        node2.setLeft(new TreeNode(3));
        node2.setRight(new TreeNode(2));
        node2.getLeft().setRight(new TreeNode(6));
        node2.getRight().setLeft(new TreeNode(4));
        node2.getRight().setRight(new TreeNode(5));
        node2.getRight().getRight().setLeft(new TreeNode(8));
        node2.getRight().getRight().setRight(new TreeNode(7));
        System.out.println("Node1 is " + node1.toString());
        System.out.println("Node2 is " + node2.toString());
        System.out.println("Are the two trees flip equivalent ? " + flipEquiv(node1, node2));
    }

    /**
     *
     * We check if both the nodes are null or not. If both of them are null, we return true, else if one is null, we return false.
     * For non-null values, we check the base condition that the root value should be the same. If not, return false.
     * Now we compare left and right subtree of both roots to check if they are equal. We also cross-check this
     * with left one root to the right of another root and vice versa to check for flip equivalents.
     *
     * @param node1
     * @param node2
     * @return if the nodes are flip equivalent
     */
    private static boolean flipEquiv(TreeNode node1, TreeNode node2){
        if(node1 == null || node2 == null) return node1 == null && node2 == null;
        if(node1.getVal() != node2.getVal()) return false;

        boolean isFlipEuivalent = (flipEquiv(node1.getLeft(), node2.getLeft()) && flipEquiv(node1.getRight(), node2.getRight()))
                || (flipEquiv(node1.getLeft(), node2.getRight()) && flipEquiv(node1.getRight(), node2.getLeft()));

        return isFlipEuivalent;

    }


}
