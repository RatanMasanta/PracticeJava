package com.masanta.ratan.daily.practice.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

public class MinimumNumberOfVerticesToReachAllVertices {

    /**
     * 1557. Minimum Number of Vertices to Reach All Nodes
     * 
     * Given a directed acyclic graph, with n vertices numbered from 0 to n-1, and an array edges where edges[i] = [fromi, toi] represents a directed edge from node fromi to node toi.
     *
     * Find the smallest set of vertices from which all nodes in the graph are reachable. It's guaranteed that a unique solution exists.
     *
     * Notice that you can return the vertices in any order.
     *
     * Solution
     *
     * We have NNN vertices connected via EEE directed edges, and there is no cycle. We need to return the minimum set of vertices that are required so that all the vertices are reachable from these vertices.
     *
     * Let's first find out the vertices we are sure won't be in the final set. Consider the below figure, there is a directed edge from vertex a to b; should vertex b be in the final answer set? No, if we include a we can reach b from it using this edge. Similarly, consider the edge c -> d; the vertex d will also not be present in the answer as we can shrink the required vertices group from {c, d} to just c and then traverse tod from it
     *
     * a -> b ->e
     * c -> d -> b
     *
     * From this discussion, one thing is clear: any vertex that has an edge in-coming from some other vertex should not be part of the answer.
     *
     * The next question concerns the vertices that don't have an incoming edge. Are these all required and will we need to include all of them, or will only a few be sufficient?
     *
     * Let's try to answer the first question, are all those vertices that don't have an incoming edge required? If a vertex doesn't have any edge that ends on it, this implies there is no way to reach it from any other vertex. The only way to cover these vertices is to include them in the set. Hence, all vertices that have no in-coming edge or have the in-degree as 0 will be included in the answer.
     *
     * The second question is, are these vertices enough? Will we be able to cover the remaining vertices using these vertices? Think of any vertex that we didn't include, this vertex would have at least one incoming edge, i.e., instead of including it, we will consider including the vertices that can lead to this. All these vertices again have two possibilities; first, they don't have any incoming edge, in which case it would already be in our answer set, the second, they have an incoming edge in which we shouldn't include it. Hence, every vertex that has not been included; an edge from the included vertices can lead to it, and thus all vertices are reachable in this set of vertices.
     *
     * Algorithm
     *
     * Create a boolean list isIncomingEdgeExists of length NNN which store true if there is an incoming edge to it, false otherwise. Initialize it with every entry as false.
     * Iterate over edges, and for every edge, mark the second vertex as true in the list isIncomingEdgeExists.
     * Iterate over the list isIncomingEdgeExists and include all the vertices that have a false value in the final answer list requiredNodes.
     * Return requiredNodes.
     *
     * @param n number of vertices in the graph
     * @param edges edge array with directed edges of [a,b] meaning from vertice a to b
     * @return smallest set of vertices required to reach all vertices
     */
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        // List to signify if the vertex has an incoming edge or not.
        boolean[] isIncomingEdgeExists = new boolean[n];
        for (List<Integer> edge : edges) {
            isIncomingEdgeExists[edge.get(1)] = true;
        }

        List<Integer> requiredNodes = new ArrayList();
        for (int i = 0; i < n; i++) {
            // Store all the nodes that don't have an incoming edge.
            if (!isIncomingEdgeExists[i]) {
                requiredNodes.add(i);
            }
        }

        return requiredNodes;
    }

    public static void main(String[] args) {

        int numberOfVertices = 6;
        List<List<Integer>> edgeArray = new ArrayList<>();
        List<Integer> edge1 = new ArrayList<Integer>();
        edge1.add(0);
        edge1.add(1);
        List<Integer> edge2 = new ArrayList<Integer>();
        edge2.add(0);
        edge2.add(2);
        List<Integer> edge3 = new ArrayList<Integer>();
        edge3.add(2);
        edge3.add(5);
        List<Integer> edge4 = new ArrayList<Integer>();
        edge4.add(3);
        edge4.add(4);
        List<Integer> edge5 = new ArrayList<Integer>();
        edge5.add(4);
        edge5.add(2);
        edgeArray.add(edge1);
        edgeArray.add(edge2);
        edgeArray.add(edge3);
        edgeArray.add(edge4);
        edgeArray.add(edge5);

        MinimumNumberOfVerticesToReachAllVertices minNumberOfVerticesToReachAllVertices =
                new MinimumNumberOfVerticesToReachAllVertices();
        System.out.println(minNumberOfVerticesToReachAllVertices.findSmallestSetOfVertices(numberOfVertices,edgeArray));

    }
    /*
    Leetcode editorial:
        https://leetcode.com/problems/minimum-number-of-vertices-to-reach-all-nodes/editorial/
        Complexity Analysis

        Here NNN is the number of vertices and EEE is the number of edges.

        Time complexity: O(N+E)O(N + E)O(N+E).

        We first iterate over every one of EEE edges to mark the vertex in the list isIncomingEdgeExists, then we iterate over the vertices to see if it's required or not. Hence, the total time complexity equals O(N+E)O(N + E)O(N+E).

        Space complexity: O(N)O(N)O(N).

        The only space required is the list isIncomingEdgeExists of size NNN to store if vertices have an incoming edge or not. The other list, requiredNodes is to store the output, which is not considered part of space complexity.

     */
}
