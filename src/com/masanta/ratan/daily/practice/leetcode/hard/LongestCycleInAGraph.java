package com.masanta.ratan.daily.practice.leetcode.hard;

public class LongestCycleInAGraph {


    /**
     * You are given a directed graph of n nodes numbered from 0 to n - 1, where each node has at most one outgoing edge.
     *
     * The graph is represented with a given 0-indexed array edges of size n, indicating that there is a directed edge from node i to node edges[i]. If there is no outgoing edge from node i, then edges[i] == -1.
     *
     * Return the length of the longest cycle in the graph. If no cycle exists, return -1.
     *
     * A cycle is a path that starts and ends at the same node.
     * @param edges unidirectional pointed edge nodes
     * @return length of longest Cycle
     */
    public int longestCycle(int[] edges) {
        int longestCycleLen = -1;
        int timeStep = 1;
        int[] nodeVisitedAtTime = new int[edges.length];

        for (int currentNode = 0; currentNode < edges.length; ++currentNode) {
            if (nodeVisitedAtTime[currentNode] > 0)
                continue;
            final int startTime = timeStep;
            int u = currentNode;
            while (u != -1 && nodeVisitedAtTime[u] == 0) {
                nodeVisitedAtTime[u] = timeStep++;
                u = edges[u];
            }
            if (u != -1 && nodeVisitedAtTime[u] >= startTime)
                longestCycleLen = Math.max(longestCycleLen, timeStep - nodeVisitedAtTime[u]);
        }

        return longestCycleLen;
    }


    /*
        Java Solution: https://www.youtube.com/watch?v=bGMR2lRksCc
        https://leetcode.com/problems/longest-cycle-in-a-graph/solutions/3341638/python-java-c-simple-solution-easy-to-understand/
     */

    public static void main(String[] args) {
        LongestCycleInAGraph longestCycleInAGraph = new LongestCycleInAGraph();
        int[] edgeArray1 = {3,3,4,2,3};
        int[] edgeArray2 = {2,-1,3,1};
        System.out.println(longestCycleInAGraph.longestCycle(edgeArray1));
        System.out.println(longestCycleInAGraph.longestCycle(edgeArray2));
    }

}
