package com.masanta.ratan.daily.practice.leetcode.hard;

import com.masanta.ratan.daily.practice.leetcode.common.TreeNode;
import com.sun.source.tree.Tree;

import java.util.Arrays;

public class HeightOfBinaryTreeAfterSubtreeRemovalQueries_2458 {

    /**
     * Problem statement:
     *
     * You are given the root of a binary tree with n nodes. Each node is assigned a unique value from 1 to n. You are also given an array queries of size m.
     *
     * You have to perform m independent queries on the tree where in the ith query you do the following:
     *
     * Remove the subtree rooted at the node with the value queries[i] from the tree. It is guaranteed that queries[i] will not be equal to the value of the root.
     * Return an array answer of size m where answer[i] is the height of the tree after performing the ith query.
     *
     * Note:
     *
     * The queries are independent, so the tree returns to its initial state after each query.
     * The height of a tree is the number of edges in the longest simple path from the root to some node in the tree.
     *
     *
     * Example 1:
     *
     *
     * Input: root = [1,3,4,2,null,6,5,null,null,null,null,null,7], queries = [4]
     * Output: [2]
     * Explanation: The diagram above shows the tree after removing the subtree rooted at node with value 4.
     * The height of the tree is 2 (The path 1 -> 3 -> 2).
     * Example 2:
     *
     *
     * Input: root = [5,8,9,2,1,3,7,4,6], queries = [3,2,4,8]
     * Output: [3,2,3,2]
     * Explanation: We have the following queries:
     * - Removing the subtree rooted at node with value 3. The height of the tree becomes 3 (The path 5 -> 8 -> 2 -> 4).
     * - Removing the subtree rooted at node with value 2. The height of the tree becomes 2 (The path 5 -> 8 -> 1).
     * - Removing the subtree rooted at node with value 4. The height of the tree becomes 3 (The path 5 -> 8 -> 2 -> 6).
     * - Removing the subtree rooted at node with value 8. The height of the tree becomes 2 (The path 5 -> 9 -> 3).
     *
     */

    /*
    Solution: https://www.youtube.com/watch?v=eEfW7CLbhvU&t=1655s
    Revisit the question
     */


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.setLeft(new TreeNode(3));
        root.setRight(new TreeNode(4));
        root.getLeft().setLeft(new TreeNode(2));
        root.getRight().setLeft(new TreeNode(6));
        root.getRight().setRight(new TreeNode(5));
        root.getRight().getRight().setRight(new TreeNode(7));

        int[] queries = new int[] {4,2,5,7};

        System.out.println(STR."Resultant heights after queries \{Arrays.toString(queries)} is \{Arrays.toString(treeQueries(root, queries))} ");
    }

    static final int[] heights = new int[100001];
    static int maxHeight = 0;

    /**
     *
     * @param root
     * @param queries
     * @return
     */
    public static int[] treeQueries(TreeNode root, int[] queries) {
        getLeftHeights(root, 0);
        maxHeight = 0;
        getRightHeights(root, 0);

        int n = queries.length;
        int[] result = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            result[i] = heights[queries[i]];
        }

        return result;
    }

    private static void getLeftHeights(TreeNode node, int height) {
        heights[node.getVal()] = maxHeight;
        maxHeight = Math.max(maxHeight, height);
        if (node.getLeft() != null) getLeftHeights(node.getLeft(), height + 1);
        if (node.getRight() != null) getLeftHeights(node.getRight(), height + 1);
    }

    private static void getRightHeights(TreeNode node, int height) {
        heights[node.getVal()] = Math.max(heights[node.getVal()], maxHeight);
        maxHeight = Math.max(height, maxHeight);
        if (node.getRight() != null) getRightHeights(node.getRight(), height + 1);
        if (node.getLeft() != null) getRightHeights(node.getLeft(), height + 1);
    }

}
