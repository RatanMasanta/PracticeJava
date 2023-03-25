package com.masanta.ratan.daily.practice.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

public class CouontUnreachablePairOfNodesInAnUndirectedGraph {




    private List<Integer>[] graph;

    /**
     * 2316. Count Unreachable Pairs of Nodes in an Undirected Graph
     * You are given an integer n. There is an undirected graph with n nodes, numbered from 0 to n - 1. You are given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting nodes ai and bi.
     *
     * Return the number of pairs of different nodes that are unreachable from each other.
     *
     * @param n number of Nodes
     * @param edges edges array
     * @return number of unreachable pairs
     */
    public long countPairs(int n, int[][] edges) {
        createGraph(n, edges);
        boolean[] visited = new boolean[n];
        int numVisitedNodes = 0;
        long numUnreachablePairsOfNodes = 0;

        for (int node = 0; node < n; ++node) {
            if (!visited[node]) {
                int numNodesInCurrentGroup = depthFirstSearch_countConnectedNodesInCurrentGroup(node, visited);
                numUnreachablePairsOfNodes += (long) numNodesInCurrentGroup * numVisitedNodes;
                numVisitedNodes += numNodesInCurrentGroup;
            }
        }
        return numUnreachablePairsOfNodes;
    }

    private int depthFirstSearch_countConnectedNodesInCurrentGroup(int node, boolean[] visited) {
        visited[node] = true;
        int numConnectedNodes = 1;

        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) {
                numConnectedNodes += depthFirstSearch_countConnectedNodesInCurrentGroup(neighbor, visited);
            }
        }
        return numConnectedNodes;
    }

    private void createGraph(int n, int[][] edges) {
        graph = new List[n];
        for (int node = 0; node < n; ++node) {
            graph[node] = new ArrayList<>();
        }
        for (int i = 0; i < edges.length; ++i) {
            graph[edges[i][0]].add(edges[i][1]);
            graph[edges[i][1]].add(edges[i][0]);
        }
    }

    public static void main(String[] args) {
        CouontUnreachablePairOfNodesInAnUndirectedGraph couontUnreachablePairOfNodesInAnUndirectedGraph =
                new CouontUnreachablePairOfNodesInAnUndirectedGraph();
        int numberrOfNodes = 7;
        int[][] edgeArray = {{0,2},{0,5},{2,4},{1,6},{5,4}};
        System.out.println(couontUnreachablePairOfNodesInAnUndirectedGraph.countPairs(numberrOfNodes, edgeArray));
    }

    /*
             https://leetcode.com/problems/count-unreachable-pairs-of-nodes-in-an-undirected-graph/solutions/3337487/python-java-c-simple-solution-easy-to-understand/
     */


}
