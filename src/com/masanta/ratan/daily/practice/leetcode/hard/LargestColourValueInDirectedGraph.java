package com.masanta.ratan.daily.practice.leetcode.hard;

import java.util.*;

public class LargestColourValueInDirectedGraph {

    /**
     * 1857. Largest Color Value in a Directed Graph
     *
     * There is a directed graph of n colored nodes and m edges. The nodes are numbered from 0 to n - 1.
     *
     * You are given a string colors where colors[i] is a lowercase English letter representing the color of the ith node in this graph (0-indexed). You are also given a 2D array edges where edges[j] = [aj, bj] indicates that there is a directed edge from node aj to node bj.
     *
     * A valid path in the graph is a sequence of nodes x1 -> x2 -> x3 -> ... -> xk such that there is a directed edge from xi to xi+1 for every 1 <= i < k. The color value of the path is the number of nodes that are colored the most frequently occurring color along that path.
     *
     * Return the largest color value of any valid path in the given graph, or -1 if the graph contains a cycle.
     *
     * Input: colors = "abaca", edges = [[0,1],[0,2],[2,3],[3,4]]
     * Output: 3
     * Explanation: The path 0 -> 2 -> 3 -> 4 contains 3 nodes that are colored "a" (red in the above image).
     *
     *
     *
     * Approach 1: Topological Sort Using Kahn's Algorithm
     * Intuition
     * To find the maximum frequency of a color in a directed graph, we must iterate over all of its paths in an optimal manner. If we know the maximum frequency of all the colors for paths ending at u, we can use it to calculate the frequency of all colors for paths that use the outgoing edges from u.
     *
     * If there is an edge from u -> v, the path ending at v will have the same color frequencies as the path ending at u, except that the color of v will be incremented by one.
     *
     * Now, if we do this for all the nodes that have an incoming edge to v and take the maximum frequency of each color across these edges, we will have the maximum frequency of all the colors for paths ending at v. After covering all the edges going into v, we can use the maximum frequency of all the colors stored in v for edges going out of v.
     *
     * We notice that for each edge u -> v, we must first obtain the maximum frequency of all the colours for paths ending until u, and only then can we form the answers for paths ending until v. This leads us to consider using topological sort to solve the problem.
     *
     * A topological sort or topological ordering of a directed graph is a linear ordering of its vertices such that for every directed edge u -> v from vertex u to vertex v, u comes before v in the ordering.
     *
     * In a directed acyclic graph, we can use Kahn's algorithm to get the topological ordering. Kahn’s algorithm works by keeping track of the number of incoming edges into each node (indegree). It works by repeatedly visiting the nodes with an indegree of zero and deleting all the edges associated with it leading to a decrement of indegree for the nodes whose incoming edges are deleted. This process continues until no elements with zero indegree can be found.
     *
     * If you are not familiar with Kahn's algorithm, we suggest you read our Leetcode Explore Card.
     *
     * We also need to detect if a cycle is in the graph and return -1 if so. The advantage of using Kahn's algorithm is that it also aids in the detection of graph cycles.
     *
     * Let's perform Kahn's algorithm on directed graph having a cycle. Here's a visual step-by-step represenntation of how it would work:
     *
     * img
     *
     * We can see that if there is a cycle, the indegree of nodes in the cycle cannot be set to 0 due to cyclic dependency. We are unable to visit the cycle's nodes. So, if the number of visited nodes is less than the total number of nodes in the graph, we have a cycle.
     *
     * To solve the problem, we create a 2D-array count with n rows and 26 columns, where n is the number of graph nodes. We have an array of size 26 for each node to store the maximum frequency of each colour across the paths that end at the node.
     *
     * We use Kahn's algorithm to perform the topological sort. A popped-out node indicates that all of its incoming edges have been processed, and it can now be used to iterate over all of its outgoing edges. So for each node -> neighbor edge, we use count[neighbor][i] = max(count[neighbor][i], count[node][i]) (we use max here instead of just setting it because there could be multiple ways to reach the neighbor) for all colors i.
     *
     * We also need to count the colour of node. So when node is popped (or pushed) out of the queue, we increase the frequency of the colour of node by '1'.
     *
     * We make an answer variable to compute the answer. We update it every time when node is popped out of the queue by simply taking node's colour into account. We perform answer = max(answer, count[node][colors[node] - 'a']).
     *
     * You may have noticed that we can update answer each time we update the frequencies of the colours using the outgoing edges of nodes, but only the node colour is sufficient. This is because on any path with the highest frequency of a particular colour, we can shorten it by starting from the first node with that colour and keeping the same count of the colour on this path. As a result, simply using the colour of the node itself suffices for the answer computations.
     *
     * Algorithm
     * Create an integer variable n = colors.length() which stores the number of nodes in the graph.
     * Create an adjacency list adj in which adj[x] contains all nodes with an incoming edge from node x.
     * Create an array indegree of length n where indegree[x] stores the number of edges entering node x.
     * Create a 2D-array count with n rows and 26 columns where count[x] keeps track of the maximum frequencies of all the colors for paths that end at node x. Note that color a corresponds to column 0, b corresponds to column 1, and so on.
     * Initialize a queue of integers q and start a BFS algorithm moving from the leaf nodes to the parent nodes.
     * Begin the BFS traversal by pushing all of the leaf nodes (indegree equal to 0) in the queue.
     * Create two integer variables answer = 0 to store the answer to the problem and nodesSeen = 0 to count number of visited nodes.
     * While the queue is not empty;
     * Dequeue the first node from the queue.
     * Increment the frequency of the color of node by 1 and also update answer. We perform answer = max(answer, ++count[node][colors[node] - 'a']).
     * Increment nodesSeen by 1.
     * For each neighbor (nodes that have an incoming edge from node) of node, we try to update the frequencies of all colors stored for neighbor to cover all the paths that use node -> neighbor edge. We perform count[neighbor][i] = max(count[neighbor][i], count[node][i]) for every color i where color a corresponds to 0, b corresponds to 1, and so on.
     * We further decrement indegree[neighbor]by 1 to delete the node -> neighbor edge.
     * If indegree[neighbor] == 0, it means that neighbor behaves as a leaf node, so we push neighbor in the queue.
     * If number of nodes visited is less than total number of nodes, i.e., nodesSeen < n we return -1 as there must be a cycle. Otherwise, we return answer.
     *
     * @param colors String mentioning the colour of the ith node
     * @param edges integer array of edges for index
     * @return  largest color value of any valid path in the given graph, or -1 if the graph contains a cycle
     */
    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        Map<Integer, List<Integer>> adj = new HashMap<>();
        int[] indegree = new int[n];

        for (int[] edge : edges) {
            adj.computeIfAbsent(edge[0], k->new ArrayList<Integer>()).add(edge[1]);
            indegree[edge[1]]++;
        }

        int[][] count = new int[n][26];
        Queue<Integer> q = new LinkedList<>();

        // Push all the nodes with indegree zero in the queue.
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        int answer = 1, nodesSeen = 0;
        while (!q.isEmpty()) {
            int node = q.poll();
            answer = Math.max(answer, ++count[node][colors.charAt(node) - 'a']);
            nodesSeen++;

            if (!adj.containsKey(node)) {
                continue;
            }

            for (int neighbor : adj.get(node)) {
                for (int i = 0; i < 26; i++) {
                    // Try to update the frequency of colors for the neighbor to include paths
                    // that use node->neighbor edge.
                    count[neighbor][i] = Math.max(count[neighbor][i], count[node][i]);
                }

                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    q.offer(neighbor);
                }
            }
        }

        return nodesSeen < n ? -1 : answer;
    }

    public static void main(String[] args) {
        LargestColourValueInDirectedGraph largestColourValueInDirectedGraph =
                new LargestColourValueInDirectedGraph();
        String colours = "abaca";
        int[][] edges = {{0,1},{0,2},{2,3},{3,4}};
        System.out.println(largestColourValueInDirectedGraph.largestPathValue(colours, edges));
    }

}
