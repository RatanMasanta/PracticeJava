package com.masanta.ratan.daily.practice.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CloneGraph {

    /**
     * 133. Clone Graph
     *
     *
     * Given a reference of a node in a connected undirected graph.
     *
     * Return a deep copy (clone) of the graph.
     *
     * Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
     *
     * class Node {
     *     public int val;
     *     public List<Node> neighbors;
     * }
     *
     *
     * Test case format:
     *
     * For simplicity, each node's value is the same as the node's index (1-indexed). For example, the first node with val == 1, the second node with val == 2, and so on. The graph is represented in the test case using an adjacency list.
     *
     * An adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.
     *
     * The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.
     *
     * Example:
     * Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
     * Output: [[2,4],[1,3],[2,4],[1,3]]
     * Explanation: There are 4 nodes in the graph.
     * 1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
     * 2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
     * 3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
     * 4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
     *
     * Constraints:
     * The number of nodes in the graph is in the range [0, 100].
     * 1 <= Node.val <= 100
     * Node.val is unique for each node.
     * There are no repeated edges and no self-loops in the graph.
     * The Graph is connected and all nodes can be visited starting from the given node.
     *
     * @param node Given Graph object
     * @return new cloned object
     */
    public Node cloneGraph(Node node) {
        if(node == null) return null; // if the actual node is empty there is nothing to copy, so return null
        Node copy = new Node(node.val); // create a new node , with same value as the root node(given node)
        Node[] visited = new Node[101]; // in this question we will create an array of Node(not boolean) why ? ,
        // because i have to add all the adjacent nodes of particular vertex, whether it's visited or not, so
        // in the Node[] initially null is stored, if that node is visited, we will store the respective node at
        // the index, and can retrieve that easily.
        Arrays.fill(visited , null); // initially store null at all places
        dfs(node , copy , visited); // make a dfs call for traversing all the vertices of the root node
        return copy; // in the end return the copy node
    }

    private void dfs(Node node , Node copy , Node[] visited){
        visited[copy.val] = copy;// store the current node at it's val index which will tell us that this node is now visited

//         now traverse for the adjacent nodes of root node
        for(Node n : node.neighbors){
//             check whether that node is visited or not
//              if it is not visited, there must be null
            if(visited[n.val] == null){
//                 so now if it not visited, create a new node
                Node newNode = new Node(n.val);
//                 add this node as the neighbor of the prev copied node
                copy.neighbors.add(newNode);
//                 make dfs call for this unvisited node to discover whether it's adjacent nodes are explored or not
                dfs(n , newNode , visited);
            }else{
//                 if that node is already visited, retrieve that node from visited array and add it as the adjacent node of prev copied node
//                 THIS IS THE POINT WHY WE USED NODE[] INSTEAD OF BOOLEAN[] ARRAY
                copy.neighbors.add(visited[n.val]);
            }
        }

    }

    // A mapping of each node in the old graph to its corresponding node in the newGraph
    HashMap<Node, Node> mp = new HashMap<>();
    public Node cloneGraphUsingHashMap(Node node) {
        if (node == null) return null;
        if (mp.containsKey(node)) return mp.get(node);

        Node newNode = new Node(node.val);
        mp.put(node, newNode);

        for (Node nb: node.neighbors) {
            newNode.neighbors.add(cloneGraph(nb));
        }

        return newNode;
    }


    static class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public static void main(String[] args) {
        CloneGraph cloneGraph = new CloneGraph();
        Node firstNode = new Node(1);
        Node secondNode = new Node(2);
        Node thirdNode = new Node(3);
        Node fourthNode = new Node(4);

        firstNode.neighbors = new ArrayList<>();
        firstNode.neighbors.add(secondNode);
        firstNode.neighbors.add(fourthNode);

        secondNode.neighbors = new ArrayList<>();
        secondNode.neighbors.add(firstNode);
        secondNode.neighbors.add(thirdNode);

        thirdNode.neighbors = new ArrayList<>();
        thirdNode.neighbors.add(secondNode);
        thirdNode.neighbors.add(fourthNode);

        fourthNode.neighbors = new ArrayList<>();
        fourthNode.neighbors.add(firstNode);
        fourthNode.neighbors.add(thirdNode);
        System.out.println(firstNode.hashCode());
        System.out.println(secondNode.hashCode());
        System.out.println(thirdNode.hashCode());
        System.out.println(fourthNode.hashCode());
        Node resultGraph = cloneGraph.cloneGraph(firstNode);
        System.out.println(resultGraph.hashCode());
        System.out.println("Are they the same object? " + firstNode.equals(resultGraph));
        List<Node> neighbors = resultGraph.neighbors;
        neighbors.stream().forEach( x-> System.out.print(x.val + " "));

    }
    /*
     * Leetcode solution: https://leetcode.com/problems/clone-graph/solutions/1793436/java-simple-code-with-heavy-comments/
     * https://leetcode.com/problems/clone-graph/solutions/3391987/simplest-java-solution/
     */
}
