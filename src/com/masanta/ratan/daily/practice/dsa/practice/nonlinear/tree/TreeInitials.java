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

    public List<Integer> iterativePostOrderUsingTwoStacks(TreeNode tree){
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

    /**
     * Intuition: First we need to understand what we do in a postorder traversal. We first explore the left side of the root node and keep on moving left until we encounter a node pointing to NULL. As now there is nothing more to traverse on the left, we move to the immediate parent(say node P) of that NULL node. Now we again start our left exploration from the right child of that node P. We will print a node’s value only when we have returned from its both children.
     *
     * Approach:
     *
     * The algorithm steps can be stated as:
     *
     * We take an explicit data structure and a cur pointer pointing to the root of the tree.
     * We run a while loop till the time the cur is not pointing to NULL or the stack is non-empty.
     * If cur is not pointing to NULL, it means then we simply push that value to the stack and move the cur pointer to its left child because we want to explore the left child before the root or the right child.
     * If the cur is pointing to NULL, it means we can’t go further left, then we take a variable temp and set it to  cur’s parent’s right child (as we have visited the left child, now we want to visit the right child). We have node cur’s parent at the top of the stack.
     * If node temp is not pointing to NULL, we simply initialise cur as node temp so that we can again start looking at the left of node temp from the next iteration.
     * If node temp is pointing to NULL, then first of all we are sure that we have visited both children of temp’s parent, so it’s time to print it. Therefore we set temp to its parent( present at the top of stack), pop the stack and then print temp’s value.
     * Additionally,  this new temp(parent of NULL-pointing node) can be the right child of the node present at the top of stack after popping.In that case the node at top of the stack is parent of temp and the next node to be printed. Therefore we run an additional while loop
     * to check if that is the case, if true then again move temp to its parent and print its value.
     *
     *
     * @param node provided binary tree
     * @return integers in the postorder (Left Right Root) of the given tree
     */
    public List<Integer> iterativePostOrderUsingSingleStack(TreeNode node){
        ArrayList<Integer> postOrder = new ArrayList<Integer>();
        if(node == null) return postOrder;
        Stack<TreeNode> stackForSingleStackIterationPostOrder = new Stack<TreeNode>();
        TreeNode currentNode = node;
        TreeNode temporaryNode = null;
        // remember the logic as this one is not intuitive
        while(!stackForSingleStackIterationPostOrder.isEmpty() ||  currentNode != null){
            if(currentNode != null){
                stackForSingleStackIterationPostOrder.push(currentNode);
                currentNode = currentNode.getLeft();
            } else{
                temporaryNode = stackForSingleStackIterationPostOrder.peek().getRight();
                if(temporaryNode == null){
                    temporaryNode = stackForSingleStackIterationPostOrder.peek();
                    stackForSingleStackIterationPostOrder.pop();
                    postOrder.add(temporaryNode.getVal());
                    while(!stackForSingleStackIterationPostOrder.isEmpty()
                    && temporaryNode == stackForSingleStackIterationPostOrder.peek().getRight()){
                        temporaryNode = stackForSingleStackIterationPostOrder.peek();
                        stackForSingleStackIterationPostOrder.pop();
                        postOrder.add(temporaryNode.getVal());
                    }
                } else {
                    currentNode = temporaryNode;
                }
            }
        }
        return postOrder;
    }

    /**
     * Intuition: If we study the preorder, inorder and postorder traversals, we will observe a pattern. To understand this pattern, we take a simple example:
     *
     *
     * The number inside the red boxes is the visit when we print the node. In preorder traversal, we print a node at the first visit itself. Whereas, in inorder traversal at the first visit to a node, we simply traverse to the left child. It is only when we return from the left child and visit that node the second time, that we print it. Similarly, in postorder traversal, we print a node in its third visit after visiting both its children.
     *
     * Approach:
     *
     * The algorithm steps can be described as follows:
     *
     * We take a stack data structure and push a pair<val, num> to it. Here Val is the value of the root node and num the visit to the node. Initially, the num is 1 as we are visiting the root node for the first time. We also create three separate lists for preorder, inorder and postorder traversals. Then we set an iterative loop to run till the time our stack is non-empty.
     *
     * In every iteration, we pop the top of the stack (say, T). Then we check the second value(num) of T. Three cases can arise:
     *
     * Case 1 : When num==1
     * This means that we are visiting the node for the very first time, therefore we push the node value to our preorder list. Then we push the same node with num=2(for Case 2). After this, we want to visit the left child. Therefore we make a new pair Y(<val, num>) and push it to the stack (if there exists a left child). The val of Y is equal to the left child’s node value and num is equal to 1.
     *
     * Case 2 : When num==2
     * This means that we are visiting the node for the second time, therefore on our second visit to the node, we push the node value to our inorder list. Then we push the same node with num=3( for Case 3). After this, we want to visit the right child. Therefore as in the first case, we check if there exists a right child or not. If there is, we push the right child and num value=1 as a pair to our stack.
     *
     * Case 3 When num==3
     * This means that we are visiting the node for the third time. Therefore we will push that node’s value to our postorder list. Next, we simply want to return to the parent so we will not push anything else to the stack.
     *
     *
     * @param root treenode to iterate over
     * @param pre preorder list
     * @param in inorder list
     * @param post postOrder list
     */
    public void allTraversal(TreeNode root, List < Integer > pre, List < Integer > in , List < Integer > post) {
        Stack < Pair > st = new Stack < Pair > ();
        st.push(new Pair(root, 1));

        if (root == null) return;

        while (!st.isEmpty()) {
            Pair it = st.pop();

            // this is part of pre
            // increment 1 to 2
            // push the left side of the tree
            if (it.num == 1) {
                pre.add(it.node.getVal());
                it.num++;
                st.push(it);

                if (it.node.getLeft() != null) {
                    st.push(new Pair(it.node.getLeft(), 1));
                }
            }

            // this is a part of in
            // increment 2 to 3
            // push right
            else if (it.num == 2) { in .add(it.node.getVal());
                it.num++;
                st.push(it);

                if (it.node.getRight() != null) {
                    st.push(new Pair(it.node.getRight(), 1));
                }
            }
            // don't push it back again
            else {
                post.add(it.node.getVal());
            }
        }


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
        System.out.println("PostOrder traversal using iteration with two stacks");
        System.out.println(Arrays.toString(treeInitials.iterativePostOrderUsingTwoStacks(treeInitials).toArray()));
        System.out.println("PostOrder traversal using iteration with a single stack");
        System.out.println(Arrays.toString(treeInitials.iterativePostOrderUsingSingleStack(treeInitials).toArray()));
        List<Integer> preOrder = new ArrayList<Integer>();
        List<Integer> inOrder = new ArrayList<Integer>();
        List<Integer> postOrder = new ArrayList<Integer>();
        System.out.println("All traversal");
        treeInitials.allTraversal(treeInitials, preOrder, inOrder, postOrder);
        System.out.println("In Order traversal: " + Arrays.toString(inOrder.toArray()));
        System.out.println("Pre Order traversal: " + Arrays.toString(preOrder.toArray()));
        System.out.println("Post Order traversal: " + Arrays.toString(postOrder.toArray()));
    }
}

class Pair {
    TreeNode node;
    int num;
    Pair(TreeNode _node, int _num) {
        num = _num;
        node = _node;
    }
}
