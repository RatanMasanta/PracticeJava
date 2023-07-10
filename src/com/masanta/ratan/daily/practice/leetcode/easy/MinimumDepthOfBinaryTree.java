package com.masanta.ratan.daily.practice.leetcode.easy;

import com.masanta.ratan.daily.practice.leetcode.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class MinimumDepthOfBinaryTree {


    /**
     *
     * If you're not familiar with BFS, check out our Explore Card.
     *
     * We traversed the tree depth-wise in the previous approach; the other way to iterate it is in a breadth-wise manner using BFS. BFS uses FIFO, i.e. first in, first out approach using a queue, and hence we are able to traverse all the nodes at a level first before going further down.
     *
     * We will use a queue to store all the nodes that are there at the same level. Starting with the root node, we will store the root node in the queue. Then we will iterate over all the current nodes in the queue and for each node we will add its left and right child to the queue. The important point to note here is that since we are traversing nodes level-wise, the first node which is a leaf, i.e. both left and right child are null; We will know that this is the node with the minimum depth.
     *
     * Algorithm
     *
     * Return 0 if the root is NULL.
     *
     * Initialize the queue q with the root node and depth to 1.
     *
     * Do the following while the queue isn't empty:
     *
     * i. Iterate over all the nodes that are currently in the queue.
     *
     * ii. Skip the node if it's null; otherwise, if it's a leaf node, then return depth.
     *
     * iii. For each node, add the left and right child to the queue.
     *
     * iv. Increment the depth once the level is fully iterated.
     *
     * Ideally, our code shouldn't reach here, so return any value once the queue is empty.
     *
     * @param root TreeNode object provided
     * @return minimum length in the binary tree
     */
    public int minDepthDFS(TreeNode root) {
        return dfs(root);
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // If only one of child is non-null, then go into that recursion.
        if (root.getLeft() == null) {
            return 1 + dfs(root.getRight());
        } else if (root.getRight() == null) {
            return 1 + dfs(root.getLeft());
        }

        // Both children are non-null, hence call for both childs.
        return 1 + Math.min(dfs(root.getLeft()), dfs(root.getRight()));
    }

    /**
     *
     *
     * Approach 1: Depth-First Search (DFS)
     * Intuition
     *
     * If you're not familiar with DFS, check out our Explore Card.
     *
     * We are given a binary tree; we must return the minimum number of nodes between the root and any leaf node, including both. Let's try to break this problem into subproblems; we need to return the answer from the root of the current tree; what if we know the answer considering the left and right child of the root node? If the minimum depth for the root node's left child is x and the minimum depth for the root node's right child is y, then the minimum depth for the whole tree with the root node will be 1 + min(x, y). The additional +1 is for the current root node.
     *
     * This way, we can divide the current problem into subproblems and then solve them using recursion. The base condition of this recursion would be when the node is NULL, in which case we should return 0. One tricky thing that we need to consider is when one of the children is NULL and the other one isn't. We shouldn't move forward with recursion on the NULL child; if we do, we would return 0 due to the base condition and the count of nodes from the leaf node on the other side would be discarded as we are taking the minimum of the two. In case both children are NULL, it's fine to go into recursion as both would return 0, and the minimum of the two won't cause an issue.
     *
     * If we observe closely, we are first traversing to the deepest node and then backtrack to the parent node to find the minimum depth for it; hence, this process is actually Depth-First Search (DFS).
     *
     * fig
     *
     * Algorithm
     *
     * We will use the dfs method with root as an argument.
     * The base condition of the recursion would be for the NULL node, in which case we should return 0.
     * If the left child of root is NULL, then we should return 1 + minimum depth for the right child of the root node, which is 1 + dfs(root.right).
     * If the right child of root is NULL, then we should return 1 + minimum depth for the left child of the root node, which is 1 + dfs(root.left).
     * If both child are non-null, then return 1 + min(dfs(root.left), dfs(root.right)).
     *
     * @param root TreeNode object provided
     * @return minimum length in the binary tree
     */
    public int minDepthBFS(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int depth = 1;

        while (q.isEmpty() == false) {
            int qSize = q.size();

            while (qSize > 0) {
                qSize--;

                TreeNode node = q.remove();
                // Since we added nodes without checking null, we need to skip them here.
                if (node == null) {
                    continue;
                }

                // The first leaf would be at minimum depth, hence return it.
                if (node.getLeft() == null && node.getRight() == null) {
                    return depth;
                }

                q.add(node.getLeft());
                q.add(node.getRight());
            }
            depth++;
        }
        return -1;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.setLeft(new TreeNode(2));
        root.setRight(new TreeNode(3));
        root.getLeft().setRight(new TreeNode(5));
        root.getRight().setLeft(new TreeNode (6));
        root.getRight().setRight(new TreeNode(7));


        MinimumDepthOfBinaryTree minimumDepthOfBinaryTree =
                new MinimumDepthOfBinaryTree();
        System.out.println(minimumDepthOfBinaryTree.minDepthBFS(root));
        System.out.println(minimumDepthOfBinaryTree.minDepthDFS(root));
    }

}
