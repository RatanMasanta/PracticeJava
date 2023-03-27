package com.masanta.ratan.daily.practice.dsa.practice.nonlinear.tree;

import com.masanta.ratan.daily.practice.leetcode.common.TreeNode;

import java.util.*;
import java.util.stream.Stream;

public class TreeInitials  extends TreeNode{

    private TreeNode left;
    private TreeNode right;
    private TreeNode tree;
    private int val;

    private List<Integer> ansPreOrderDFS = new ArrayList<>();
    private List<Integer> ansPostOrderDFS = new ArrayList<>();
    private List<Integer> ansInOrderDFS = new ArrayList<>();
    private List<List<Integer>> levelWiseDataListForBFS = new LinkedList<List<Integer>>();
    private Queue<TreeNode> heightQueueForBFS = new LinkedList<>();

    /**
     * Tree inital constructor to create a tree like object
     */
    public TreeInitials(int value){
        this.val = value;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    @Override
    public int getVal() {
        return val;
    }

    @Override
    public void setVal(int val) {
        this.val = val;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public TreeInitials(int value, TreeNode right, TreeNode left){
        this.val = value;
        this.left = left;
        this.right = right;
    }

    /** DFS Approaches **/

    /* InOrder */

    /**
     * DFS Approach: InOrder traversal
     * @param tree
     * @return values in the List in order of Left, Root, Right
     */
    public List<Integer> inOrder(TreeNode tree){
        if(tree == null) return ansInOrderDFS;
        if(tree!= null){
            inOrder(tree.getLeft());
            //System.out.println(tree.getVal());
            ansInOrderDFS.add(tree.getVal());
            inOrder(tree.getRight());
        }
        return ansInOrderDFS;
    }


    /* PreOrder */

    /**
     * DFS Approach: PreOrder Traversal
     * @param tree The tree to iterate over
     * @return Values in Order of Root, Left, Right
     */
    public List<Integer> preOrder(TreeNode tree){
        if (tree == null) return ansPreOrderDFS;

        if(tree != null){
            //System.out.println(tree.getVal());
            ansPreOrderDFS.add(Integer.valueOf(tree.getVal()));
            preOrder(tree.getLeft());
            preOrder(tree.getRight());
        }

        return ansPreOrderDFS;
    }


    /* PostOrder */

    /**
     * DFS Approach: PostOrder Traversal
     * @param tree
     * @return List of values in order of Left, Right, Root
     */
    public List<Integer> postOrder(TreeNode tree){
        if(tree == null){
            return ansPostOrderDFS;
        } else {
            postOrder(tree.getLeft());
            postOrder(tree.getRight());
            //System.out.println(tree.getVal());
            ansPostOrderDFS.add(tree.getVal());
        }
        return ansPostOrderDFS;
    }

    public List<Integer> iterativeInOrder(TreeNode tree){
        ArrayList < Integer > inOrder = new ArrayList <Integer>();
        Stack <TreeNode> s = new Stack <TreeNode> ();
        while (true) {
            if (tree != null) {
                s.push(tree);
                tree = tree.getLeft();
            } else {
                if (s.isEmpty()) break;
                tree = s.peek();
                inOrder.add(tree.getVal());
                s.pop();
                tree = tree.getRight();
            }
        }
        return inOrder;
    }

    public List<Integer> iterativePreOrder(TreeNode tree){
        List<Integer> preOrder = new ArrayList<Integer>();
        if(tree == null) return preOrder;
        Stack<TreeNode> treeStack = new Stack<TreeNode>();
        treeStack.push(tree);
        while(!treeStack.isEmpty()){
            tree = treeStack.pop();
            preOrder.add(tree.getVal());
            if(tree.getRight() != null){
                treeStack.push(tree.getRight());
            }
            if(tree.getLeft() != null){
                treeStack.push(tree.getLeft());
            }
        }
        return preOrder;
    }

    public List<Integer> iterativePostOrder(TreeNode tree){
        ArrayList < Integer > postOrder = new ArrayList < Integer > ();
        if (tree == null) return postOrder;

        Stack <TreeNode> s1 = new Stack < > ();
        Stack < TreeNode > s2 = new Stack < > ();
        s1.push(tree);
        while (!s1.isEmpty()) {
            tree = s1.peek();
            s1.pop();
            s2.push(tree);
            if (tree.getLeft() != null)
                s1.push(tree.getLeft());
            if (tree.getRight() != null)
                s1.push(tree.getRight());
        }
        while (!s2.isEmpty()) {
            postOrder.add(s2.peek().getVal());
            s2.pop();
        }
        return postOrder;
    }

    /** BFS Approaches / Level wise approaches **/

    public List<List<Integer>> breadthFirstSearch(TreeNode tree){
        if(tree == null){
            return levelWiseDataListForBFS;
        } else {
            heightQueueForBFS.offer(tree);
            while(!heightQueueForBFS.isEmpty()){
                int levelNum = heightQueueForBFS.size();
                List<Integer> subList = new LinkedList<Integer>();
                for(int i =0; i < levelNum; i++){
                    if(heightQueueForBFS.peek().getLeft() != null){
                        heightQueueForBFS.offer(heightQueueForBFS.peek().getLeft());
                    }
                    if(heightQueueForBFS.peek().getRight() != null){
                        heightQueueForBFS.offer(heightQueueForBFS.peek().getRight());
                    }
                    subList.add(heightQueueForBFS.poll().getVal());
                }
                levelWiseDataListForBFS.add(subList);
            }
        }
        return levelWiseDataListForBFS;
    }



    public static void main(String[] args) {
        TreeInitials treeInitials = new TreeInitials(1);
        treeInitials.setLeft(new TreeInitials(2));
        treeInitials.setRight(new TreeInitials(3));
        treeInitials.getLeft().setLeft(new TreeInitials(4));
        treeInitials.getLeft().setRight(new TreeInitials(5));
        treeInitials.getRight().setLeft(new TreeInitials(6));
        treeInitials.getRight().setRight(new TreeInitials(7));

        System.out.println("Pre Order traversal:");
        System.out.println(Arrays.toString(treeInitials.preOrder(treeInitials).toArray()));
        System.out.println("In Order Traversal: ");
        System.out.println(Arrays.toString(treeInitials.inOrder(treeInitials).toArray()));
        System.out.println("In Post Order traversal:");
        System.out.println(Arrays.toString(treeInitials.postOrder(treeInitials).toArray()));
        System.out.println("Breadth First / Level Order Search: ");
        Stream.of(treeInitials.breadthFirstSearch(treeInitials))
                .forEach(x -> System.out.println(Arrays.toString(x.toArray())));
        System.out.println("\nIterative checks: \n");
        System.out.println("PreOrder using Iteration: ");
        System.out.println(Arrays.toString(treeInitials.iterativePreOrder(treeInitials).toArray()));
        System.out.println("InOrder using Iteration: ");
        System.out.println(Arrays.toString(treeInitials.iterativeInOrder(treeInitials).toArray()));
        System.out.println("PostOrder traversal using iteration");
        System.out.println(Arrays.toString(treeInitials.iterativePostOrder(treeInitials).toArray()));
    }
}
