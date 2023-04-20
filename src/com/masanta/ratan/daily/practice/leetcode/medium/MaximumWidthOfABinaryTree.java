package com.masanta.ratan.daily.practice.leetcode.medium;

import com.masanta.ratan.daily.practice.leetcode.common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class MaximumWidthOfABinaryTree {

    /**
     *
     * 662. Maximum Width of Binary Tree
     *
     * Given the root of a binary tree, return the maximum width of the given tree.
     *
     * The maximum width of a tree is the maximum width among all levels.
     *
     * The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes), where the null nodes between the end-nodes that would be present in a complete binary tree extending down to that level are also counted into the length calculation.
     *
     * It is guaranteed that the answer will in the range of a 32-bit signed integer.
     *
     * Input: root = [1,3,2,5,3,null,9]
     * Output: 4
     * Explanation: The maximum width exists in the third level with length 4 (5,3,null,9).
     *
     * Approach:
     * Normal BFS by setting appropriate value and adding it in queue
     *
     * @param root TreeNode provided
     * @return maximum width of the binary tree
     */
    public int widthOfBinaryTree(TreeNode root) {
        Deque<TreeNode> queue=new ArrayDeque<>();
        root.setVal(0);
        queue.add(root);
        return bfs(queue);
    }

    private int bfs(Deque<TreeNode> queue){
        int maxWidth = 1;
        while(!queue.isEmpty()){
            int s=queue.size();
            int left = queue.peek().getVal();
            int right = left;
            for(int i=0;i<s;i++){
                TreeNode node=queue.removeFirst();

                if(node.getLeft()!=null){
                    node.getLeft().setVal(node.getVal() * 2 - 1);
                    queue.add(node.getLeft());
                }
                if(node.getRight()!=null){
                    node.getRight().setVal(node.getVal() * 2);
                    queue.add(node.getRight());
                }
                if(i==s-1)
                    right=node.getVal();
            }
            maxWidth=Math.max(maxWidth,right-left+1);
        }
        return maxWidth;
    }

    private static class Node {
        TreeNode node;
        int idx;
        Node(TreeNode node, int idx){
            this.node = node;
            this.idx = idx;
        }
    }

    /**
     *
     * Intuition
     * Since we need to find width of the tree first approach that comes to mind is level order, but we will need to apply it with some modifictions
     * If you haven't solved Level order traversal i would recommend you to solve that first.
     * LC 102 Binary Tree Level Order Traversal
     *
     * If the position of the parent node is n, then the left child is 2 * n and the right child is 2 * n + 1 The width of each level is the last node's position in this level subtracts the first node's position in this level plus 1 that is at any level width = endLevel- startLevel + 1
     *
     * Approach
     * For normal level order we use a queue but here we need to store an index too.
     * So either use pair in java or create a class which has 2 variables
     * TreeNode (to store current-node)
     * Index (will track of index for each Node)
     * Insert first root into the queue with 0 index and now unitl queue is not empty
     * Find left, find righmost of the level and at each level compare max with
     * that levels max ie max = Math.max(max, end - start + 1)
     *
     *
     * @param root TreeNode
     * @return maximum wdth in the treenode
     */
    public int widthOfBinaryTree1(TreeNode root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(root, 0));
        int max = 0;
        while(!queue.isEmpty())
        {
            int size = queue.size();
            int start = 0, end = 0;
            for(int i=0; i<size; i++)
            {
                Node eachNode = queue.remove();
                int index = eachNode.idx;
                if(i==0)
                    start = index; //start and end index for each level

                if(i==size-1)
                    end = index;

                if(eachNode.node.getLeft()!=null)
                    queue.add(new Node(eachNode.node.getLeft(), 2 * eachNode.idx));

                if(eachNode.node.getRight()!=null)
                    queue.add(new Node(eachNode.node.getRight(), 2 * eachNode.idx + 1));

            }
            max = Math.max(max, end - start + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.setLeft(new TreeNode(3));
        root.getLeft().setLeft(new TreeNode(5));
        root.getLeft().setRight(new TreeNode(3));
        root.setRight(new TreeNode(2));
        root.getRight().setRight(new TreeNode(9));
        MaximumWidthOfABinaryTree maximumWidthOfABinaryTree = new MaximumWidthOfABinaryTree();
        System.out.println(maximumWidthOfABinaryTree.widthOfBinaryTree(root));
        System.out.println(maximumWidthOfABinaryTree.widthOfBinaryTree1(root));
    }

    /**
     * Leetcode Discuss:
     * https://leetcode.com/problems/maximum-width-of-binary-tree/solutions/3436537/easy-solution-of-java-c-python-100-faster-code-bfs-beginner-friendly/
     * https://leetcode.com/problems/maximum-width-of-binary-tree/solutions/3436680/java-easy-image-explaination-level-order-beginner-friendly/
     * https://leetcode.com/problems/maximum-width-of-binary-tree/solutions/2904020/java-bfs-level-order-traversal/
     *
     */

}
