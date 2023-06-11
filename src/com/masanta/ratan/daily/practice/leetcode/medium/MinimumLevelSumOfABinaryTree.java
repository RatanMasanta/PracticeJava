package com.masanta.ratan.daily.practice.leetcode.medium;

import com.masanta.ratan.daily.practice.leetcode.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class MinimumLevelSumOfABinaryTree {
    /*
    1161. Maximum Level Sum of a Binary Tree
    Medium
    3.1K
    93
    Companies
    Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.

    Return the smallest level x such that the sum of all the values of nodes at level x is maximal.



    Example 1:


    Input: root = [1,7,0,7,-8,null,null]
    Output: 2
    Explanation:
    Level 1 sum = 1.
    Level 2 sum = 7 + 0 = 7.
    Level 3 sum = 7 + -8 = -1.
    So we return the level with the maximum sum which is level 2.
     */

    public int maxLevelSum(TreeNode root) {
        int maxSum = Integer.MIN_VALUE;
        int ans = 0, level = 0;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            level++;
            int sumAtCurrentLevel = 0;
            // Iterate over all the nodes in the current level.
            for (int sz = q.size(); sz > 0; --sz) {
                TreeNode node = q.poll();
                sumAtCurrentLevel += node.getVal();

                if (node.getLeft() != null) {
                    q.offer(node.getLeft());
                }
                if (node.getRight() != null) {
                    q.offer(node.getRight());
                }
            }

            if (maxSum < sumAtCurrentLevel) {
                maxSum = sumAtCurrentLevel;
                ans = level;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(989);
        treeNode.setRight(new TreeNode(10250));
        treeNode.getRight().setRight(new TreeNode(-89388));
        treeNode.getRight().setLeft(new TreeNode(98693));
        treeNode.getRight().getRight().setRight(new TreeNode(-32127));
        MinimumLevelSumOfABinaryTree minimumLevelSumOfABinaryTree =
                new MinimumLevelSumOfABinaryTree();
        System.out.println(minimumLevelSumOfABinaryTree.maxLevelSum(treeNode));
    }

}
