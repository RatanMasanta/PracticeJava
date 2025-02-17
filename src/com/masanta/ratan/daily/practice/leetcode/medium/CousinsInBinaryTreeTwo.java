package com.masanta.ratan.daily.practice.leetcode.medium;

import com.masanta.ratan.daily.practice.leetcode.common.TreeNode;

public class CousinsInBinaryTreeTwo {


    /*
        Problem statement:

        2641. Cousins in Binary Tree II

        Given the root of a binary tree, replace the value of each node in the tree with the sum of all its cousins' values.

        Two nodes of a binary tree are cousins if they have the same depth with different parents.

        Return the root of the modified tree.

        Note that the depth of a node is the number of edges in the path from the root node to it.



        Example 1:


        Input: root = [5,4,9,1,10,null,7]
        Output: [0,0,0,7,7,null,11]
        Explanation: The diagram above shows the initial binary tree and the binary tree after changing the value of each node.
        - Node with value 5 does not have any cousins so its sum is 0.
        - Node with value 4 does not have any cousins so its sum is 0.
        - Node with value 9 does not have any cousins so its sum is 0.
        - Node with value 1 has a cousin with value 7 so its sum is 7.
        - Node with value 10 has a cousin with value 7 so its sum is 7.
        - Node with value 7 has cousins with values 1 and 10 so its sum is 11.
        Example 2:


        Input: root = [3,1,2]
        Output: [0,0,0]
        Explanation: The diagram above shows the initial binary tree and the binary tree after changing the value of each node.
        - Node with value 3 does not have any cousins so its sum is 0.
        - Node with value 1 does not have any cousins so its sum is 0.
        - Node with value 2 does not have any cousins so its sum is 0.

     */

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(5);
        treeNode.setLeft(new TreeNode(4));
        treeNode.setRight(new TreeNode(9));
        treeNode.getLeft().setLeft(new TreeNode(1));
        treeNode.getLeft().setRight(new TreeNode(10));
        treeNode.getRight().setRight(new TreeNode(7));

        TreeNode result = replaceValueInTree(treeNode);

        System.out.println(result.toString());
    }

    /**
     *        Approach:
     *
     *         The code is designed to replace the value of each node in a binary tree with a new value that depends on its sibling nodes. Specifically, for each node, the new value is calculated as the sum of the values of all child nodes across the current level, excluding the current node's children.
     *
     *         Here's the intuition broken down step-by-step:
     *
     *         Current Node's Children Influence: For each node at a certain level, we look at the sum of all child nodes at that level (across all nodes at the same level).
     *
     *         Replacement Calculation: The node's left and right child values are replaced by the sum of all child nodes at that level minus the sum of their own children. In essence, each child node receives the sum of its "cousins" (children of its parent's sibling nodes).
     *
     *         Level-by-Level Traversal: The replacement process happens one level at a time, similar to a breadth-first traversal (BFS), but using DFS recursion to traverse down the tree while calculating and setting new values for each child node based on its cousins.
     *
     *         Root Initialization: The root node is initialized to 0, as it has no cousins at its level.
     *
     *         Approach
     *         The execution can be broken down into these key steps:
     *
     *         Initialize Root Value:
     *         The root of the tree is set to 0 since there are no sibling nodes for it at the top level.
     *         Start DFS from the Root:
     *         The dfs function is initially called with an array containing only the root node. This array represents the nodes at the current level of the tree.
     *         Calculate Sum of Children:
     *         For the current level of nodes (held in the arr array), you calculate the sum of all the child node values. This sum will be used to update the values of nodes at the next level.
     *         You iterate over each node in the arr, checking if they have left and/or right children, and if so, you add their values to the sum.
     *
     *         Update Child Node Values:
     *         For each node in the current level, you calculate the sum of its own children. Then, for each child (left and right), you subtract the sum of its own children from the total sum of all children at this level. This gives the new value for the child (which is essentially the sum of "cousins").
     *         After updating each child’s value, you add the child node to the childArr array to be processed in the next recursive call.
     *
     *         Recursive Call for the Next Level:
     *         After processing all nodes at the current level, you recursively call dfs with the childArr, which now contains all the nodes at the next level.
     *         The recursion continues level by level until all nodes are processed.
     *
     *         End Condition:
     *         The recursion stops when arr (the array of nodes at the current level) is empty, meaning no more child nodes are left to process.
     *         Consider a simple tree:
     *
     *           10
     *          /  \
     *         5    15
     *         / \ /
     *         2 6 12 20
     *         Initial root value: Set the root node 10 to 0.
     *
     *         First level (Nodes: 5, 15):
     *
     *         Calculate the sum of children: 2 + 6 + 12 + 20 = 40.
     *
     *         Replace 5's children:
     *
     *         5 has children 2 and 6. The sum of their values is 8. Replace 2 and 6 with 40 - 8 = 32.
     *         Replace 15's children:
     *
     *         15 has children 12 and 20. The sum of their values is 32. Replace 12 and 20 with 40 - 32 = 8.
     *         After this step, the tree becomes:
     *
     *           0
     *          /  \
     *         5    15
     *         / \ /
     *         32 32 8 8
     *         Second level (Nodes: 32, 32, 8, 8):
     *
     *         Since these nodes have no children, the recursion will stop at this point.
     *
     *         disclaimer: i have written the code in js and converted it to other langs via gpt please resolve the conversion bugs if any thank you
     *
     *         Complexity
     *         Time complexity:O(N)
     *         Space complexity:O(H)
     *
     *
     * @param root TreeNode
     * @return replaced TreeNode
     */
    public static TreeNode replaceValueInTree(TreeNode root) {

        dfs(new TreeNode[] {root});
        root.setVal(0);
        return root;
    }


    private static void dfs(TreeNode[] arr){
        if(arr.length == 0) return;

        int sum = 0;
        for(TreeNode node: arr){
            if(node == null) continue;
            if(node.getLeft() != null) sum += node.getLeft().getVal();
            if(node.getRight() != null) sum += node.getRight().getVal();
        }

        TreeNode[] childArr = new TreeNode[arr.length * 2];
        int index = 0;
        for(TreeNode node: arr){
            int currSum = 0;
            if(node.getLeft() != null){
                currSum += node.getLeft().getVal();
            }
            if(node.getRight() != null) currSum += node.getRight().getVal();

            if(node.getLeft() != null) {
                node.getLeft().setVal(sum - currSum);
                childArr[index++] = node.getLeft();
            }
            if(node.getRight() != null){
                node.getRight().setVal(sum - currSum);
                childArr[index++] = node.getRight();
            }
        }

        dfs(java.util.Arrays.copyOf(childArr, index));

    }

}
