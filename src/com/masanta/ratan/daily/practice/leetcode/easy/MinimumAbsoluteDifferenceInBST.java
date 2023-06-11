package com.masanta.ratan.daily.practice.leetcode.easy;

import com.masanta.ratan.daily.practice.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinimumAbsoluteDifferenceInBST {
    /*530. Minimum Absolute Difference in BST
    Easy
    3.8K
    184
    Companies
    Given the root of a Binary Search Tree (BST), return the minimum absolute difference between the values of any two different nodes in the tree.



    Example 1:


    Input: root = [4,2,6,1,3]
    Output: 1
    */

    private List<Integer> nodeValues = new ArrayList<>();

    void dfs(TreeNode node) {
        if (node == null) {
            return;
        }

        nodeValues.add(node.getVal());
        dfs(node.getLeft());
        dfs(node.getRight());
    }

    int getMinimumDifference(TreeNode root) {
        dfs(root);

        Collections.sort(nodeValues);
        int minDifference = Integer.MAX_VALUE;
        // Find the diff between every two consecutive values in the list.
        for (int i = 1; i < nodeValues.size(); i++) {
            minDifference = Math.min(minDifference, nodeValues.get(i) - nodeValues.get(i - 1));
        }

        return minDifference;
    }

    public static void main(String[] args) {
        TreeNode tree = new TreeNode(1);
        tree.setLeft(new TreeNode(0));
        tree.setRight(new TreeNode(48));
        tree.getRight().setLeft(new TreeNode(12));
        tree.getRight().setRight(new TreeNode(49));
        MinimumAbsoluteDifferenceInBST minimumAbsoluteDifferenceInBST =
                new MinimumAbsoluteDifferenceInBST();
        System.out.println(minimumAbsoluteDifferenceInBST.getMinimumDifference(tree));


    }

}
