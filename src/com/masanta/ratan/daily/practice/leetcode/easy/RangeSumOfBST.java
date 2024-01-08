package com.masanta.ratan.daily.practice.leetcode.easy;

import com.masanta.ratan.daily.practice.leetcode.common.TreeNode;

public class RangeSumOfBST {

    /*
        Given the root node of a binary search tree and two integers low and high, return the sum of values of all nodes with a value in the inclusive range [low, high].

        Example 1:

        Input: root = [10,5,15,3,7,null,18], low = 7, high = 15
        Output: 32
        Explanation: Nodes 7, 10, and 15 are in the range [7, 15]. 7 + 10 + 15 = 32.
        Example 2:

        Input: root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
        Output: 23
        Explanation: Nodes 6, 7, and 10 are in the range [6, 10]. 6 + 7 + 10 = 23.


        Constraints:

        The number of nodes in the tree is in the range [1, 2 * 104].
        1 <= Node.val <= 10^5
        1 <= low <= high <= 10^5
        All Node.val are unique.

     */

    public static int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }

        // If the current node's value is within the range, add it to the sum
        int sum = 0;
        if (root.getVal() >= low && root.getVal() <= high) {
            sum += root.getVal();
        }

        // Recursively explore the left and right subtrees if they can possibly contain values in the range
        if (root.getVal() > low) {
            sum += rangeSumBST(root.getLeft(), low, high);
        }
        if (root.getVal() < high) {
            sum += rangeSumBST(root.getRight(), low, high);
        }

        return sum;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(10);
        treeNode.setLeft(new TreeNode(5));
        treeNode.getLeft().setLeft(new TreeNode(2));
        treeNode.getLeft().setRight(new TreeNode(7));
        treeNode.getLeft().getLeft().setLeft(new TreeNode(1));
        treeNode.getLeft().getLeft().setRight(new TreeNode(3));
        treeNode.getLeft().getRight().setLeft(new TreeNode(6));
        treeNode.getLeft().getRight().setRight(new TreeNode(9));
        treeNode.setRight(new TreeNode(15));
        treeNode.getRight().setLeft(new TreeNode(13));
        treeNode.getRight().setRight(new TreeNode(17));
        System.out.println(RangeSumOfBST.rangeSumBST(treeNode, 2, 13));
    }

}
