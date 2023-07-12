package com.masanta.ratan.daily.practice.leetcode.medium;

import com.masanta.ratan.daily.practice.leetcode.common.TreeNode;

import java.util.*;

public class AllNodesDistanceKInBinaryTree {

    /*
        863. All Nodes Distance K in Binary Tree
        Medium
        9.9K
        190
        Companies
        Given the root of a binary tree, the value of a target node target, and an integer k, return an array of the values of all nodes that have a distance k from the target node.

        You can return the answer in any order.



        Example 1:


        Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
        Output: [7,4,1]
        Explanation: The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.
     */

        Map<Integer, List<Integer>> graph;
        List<Integer> answer;
        Set<Integer> visited;

    /**
     *
     * Approach 2: Depth-First Search on Equivalent Graph
     * Intuition
     * The previous approach of dynamically adding attributes is not a recommended practice. A safer method is to transform the given binary tree into an equivalent graph, where each pointer is treated as an undirected edge. Hence, the graph retains all the connected nodes from the original binary tree, including the pointers from children to parents. Consequently, we can perform a regular search in this graph, starting with depth-first search algorithm as an example.
     *
     * img
     *
     * In the equivalent graph, we only need to recursively visit all unvisited neighboring nodes of the current node, which include nodes that are equivalent to the left and right children and the parent in the original tree.
     *
     * Similarly, we can use a hash set to keep track of all the visited nodes. Whenever we find an unvisited neighbor node, we add it to the hash set so it won't be visited anymore.
     *
     *
     * Algorithm
     * We will build a hash map graph. Define a recursive function build_graph(cur, parent) to recursively build the equivalent graph: If both cur and parent are not empty, add an edge that connects cur and parent in the hash map graph. Then recursively call add_parent on the left and right children of cur:
     *
     * If cur.left is not empty, call add_parent(cur.left, cur)
     * If cur.right is not empty, call add_parent(cur.right, cur)
     * Call add_parent(root, None) to build the equivalent graph, note that the root node does not have a parent node.
     *
     * Initialize an empty array answer and an empty hash set visited.
     *
     * Define another recursive function dfs(cur, distance) to recursively find all nodes with a distance of k to node target:
     *
     * Add cur to visited so it won't be revisited later.
     * If distance = k, it means cur is one of the destination nodes, add it to answer, and return.
     * Recursively call dfs on the unvisited neighbors of cur.
     * Add target.val to visited. Call dfs(target.val, 0) to find all destination nodes with a distance of k to the target node.
     *
     * Return answer when the DFS is complete.
     *
     * @param root root of a binary tree
     * @param target target node
     * @param k distance from the target
     * @return an array of the values of all nodes that have a distance k from the target node
     */
        public List<Integer> distanceKDFS(TreeNode root, TreeNode target, int k) {
            graph = new HashMap<>();
            buildGraph(root, null);

            answer = new ArrayList<>();
            visited = new HashSet<>();
            visited.add(target.getVal());

            dfs(target.getVal(), 0, k);

            return answer;
        }

        // Recursively build the undirected graph from the given binary tree.
        private void buildGraph(TreeNode cur, TreeNode parent) {
            if (cur != null && parent != null) {
                graph.computeIfAbsent(cur.getVal(), k -> new ArrayList<>()).add(parent.getVal());
                graph.computeIfAbsent(parent.getVal(), k -> new ArrayList<>()).add(cur.getVal());
            }
            if (cur.getLeft() != null) {
                buildGraph(cur.getLeft(), cur);
            }
            if (cur.getRight() != null) {
                buildGraph(cur.getRight(), cur);
            }
        }

        private void dfs(int cur, int distance, int k) {
            if (distance == k) {
                answer.add(cur);
                return;
            }
            for (int neighbor : graph.getOrDefault(cur, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    dfs(neighbor, distance + 1, k);
                }
            }
        }


    /**
     *
     * Intuition
     * If you are not familiar with breadth-first search, please refer to our explore cards Breadth-First Search Explore Card. We will focus on the usage in this article and not the implementation details.
     *
     * Back to this problem, we start with the node target with distance = 0, then we mark all its unvisited neighbor nodes with distance = 1 to be visited soon, once we visit a node with distance = 1, we mark all its unvisited neighbor nodes with distance = 2 as well.
     *
     * We can use a queue queue as a container to store all nodes to be visited without mixing the order. Since the operation on the queue is done in First In, First Out (FIFO) order, it allows us to explore all nodes with the current distance to the target node, before moving on to the nodes with larger distances.
     *
     * img
     *
     * Similarly, we use a hash set to keep track of all the visited nodes. Whenever we find an unvisited neighbor node, we add it to the hash set so it won't be visited anymore.
     *
     *
     * Algorithm
     * Define a recursive function build_graph(cur, parent) to recursively build the equivalent graph: If both cur and parent are not empty, add an edge that connects cur and parent in the hash map graph. Then recursively call add_parent on the left and right children of cur:
     *
     * If cur.left is not empty, call add_parent(cur.left, cur)
     * If cur.right is not empty, call add_parent(cur.right, cur)
     * Call add_parent(root, None) to build the equivalent graph, note that the root node does not have a parent node.
     *
     * Initialize an empty array answer and an empty hash set visited.
     *
     * Initialize an empty queue queue to store the nodes to be visited. Enqueue the target node, in the format of (target.value, distance = 0)
     *
     * If queue has nodes, dequeue the first node (cur, distance). If distance is equal to k, add it to answer. Otherwise, enqueue all unvisited neighbor nodes of cur to queue in the format of (neighbor, distance + 1), and mark them as visited, then repeat step 5.
     *
     * Return answer when the BFS is complete.
     *
     * @param root root of a binary tree
     * @param target target node
     * @param k distance from the target
     * @return an array of the values of all nodes that have a distance k from the target node
     */
    public List<Integer> distanceKBFS(TreeNode root, TreeNode target, int k) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        dfsBuild(root, null, graph);

        List<Integer> answer = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();

        // Add the target node to the queue with a distance of 0
        queue.add(new int[]{target.getVal(), 0});
        visited.add(target.getVal());

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int node = cur[0], distance = cur[1];

            // If the current node is at distance k from target,
            // add it to the answer list and continue to the next node.
            if (distance == k) {
                answer.add(node);
                continue;
            }

            // Add all unvisited neighbors of the current node to the queue.
            for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(new int[]{neighbor, distance + 1});
                }
            }
        }

        return answer;
    }

    // Recursively build the undirected graph from the given binary tree.
    private void dfsBuild(TreeNode cur, TreeNode parent, Map<Integer, List<Integer>> graph) {
        if (cur != null && parent != null) {
            int curVal = cur.getVal(), parentVal = parent.getVal();
            graph.putIfAbsent(curVal, new ArrayList<>());
            graph.putIfAbsent(parentVal, new ArrayList<>());
            graph.get(curVal).add(parentVal);
            graph.get(parentVal).add(curVal);
        }

        if (cur != null && cur.getLeft() != null) {
            dfsBuild(cur.getLeft(), cur, graph);
        }

        if (cur != null && cur.getRight() != null) {
            dfsBuild(cur.getRight(), cur, graph);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode target = new TreeNode(5);
        root.setRight(new TreeNode(1));
        root.getRight().setLeft(new TreeNode(0));
        root.getRight().setRight(new TreeNode(8));
        root.setLeft(new TreeNode(5));
        root.getLeft().setLeft(new TreeNode(6));
        root.getLeft().setRight(new TreeNode(2));
        root.getLeft().getRight().setLeft(new TreeNode(7));
        root.getLeft().getRight().setRight(new TreeNode(4));
        int distance = 2;
        AllNodesDistanceKInBinaryTree allNodesDistanceKInBinaryTree =
                new AllNodesDistanceKInBinaryTree();
        System.out.println(allNodesDistanceKInBinaryTree.distanceKBFS(root, target, distance));
        System.out.println(allNodesDistanceKInBinaryTree.distanceKDFS(root, target, distance));

    }


}
