package com.masanta.ratan.daily.practice.leetcode.hard;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class CheckingExistenceOfEdgeLengthLimitedPath {

    // Sort in increasing order based on the 3rd element of the array.
    private void sort(int[][] array) {
        Arrays.sort(array, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[2] - b[2];
            }
        });
    }

    /**
     * 1697. Checking Existence of Edge Length Limited Paths
     *
     * An undirected graph of n nodes is defined by edgeList, where edgeList[i] = [ui, vi, disi] denotes an edge between nodes ui and vi with distance disi. Note that there may be multiple edges between two nodes.
     *
     * Given an array queries, where queries[j] = [pj, qj, limitj], your task is to determine for each queries[j] whether there is a path between pj and qj such that each edge on the path has a distance strictly less than limitj .
     *
     * Return a boolean array answer, where answer.length == queries.length and the jth value of answer is true if there is a path for queries[j] is true, and false otherwise.
     * Input: n = 3, edgeList = [[0,1,2],[1,2,4],[2,0,8],[1,0,16]], queries = [[0,1,2],[0,2,5]]
     * Output: [false,true]
     * Explanation: The above figure shows the given graph. Note that there are two overlapping edges between 0 and 1 with distances 2 and 16.
     * For the first query, between 0 and 1 there is no path where each distance is less than 2, thus we return false for this query.
     * For the second query, there is a path (0 -> 1 -> 2) of two edges with distances less than 5, thus we return true for this query.
     *
     *
     * Approach 1: Disjoint-Set Union
     * Intuition
     * We have a graph with weighted, bidirectional edges and a set of queries. For each query, we need to determine if it's possible to go from one node to another while only using edges with weights strictly less than the limit specified in the query.
     *
     * The simplest solution would be to perform a breadth-first search or depth-first search for every query, starting from one of the nodes and only considering edges with weights less than the limit until we reach the end node or run out of edges. However, this approach is not efficient because we would end up traversing the same edges repeatedly for each query.
     *
     * Another way to solve this problem is to create connected components using only edges with weights less than the limit specified in the query. If both the starting and end nodes are in the same component, it means there is a path between them that uses only edges with weights less than the limit.
     *
     * To implement this solution, we can use a Disjoint Set Union (DSU) or Union-Find data structure. This article assumes that you are already familiar with these data structures and will not go into further detail. If you would like to learn more, you can refer to our Graph Explore Card, or this post Disjoint Set Union (DSU)/Union-Find - A Complete Guide written by one of our authors.
     *
     * If for each query, we generate the connected components from scratch (i.e. iterate on all edges in each query), then the approach will still not be optimized enough. Instead what we can do is we can sort the queries array by the weight limit in increasing order.
     *
     * The advantage of sorting is that when we reach a query at index iii, and we need to create connected components with edges having weights less than limit(i)\text{limit}_{(i)}limit
     * (i)
     *
     *  , we can utilize the components created in the previous query with a weight limit of limit(i−1)\text{limit}_{(i-1)}limit
     * (i−1)
     *
     *   as it cannot be more than the current limit, limit(i)\text{limit}_{(i)}limit
     * (i)
     *
     *  . This eliminates the need to generate connected components anew for each query and increases the optimization of the solution.
     *
     * We can also sort the edges by their weight in increasing order as well. This allows us to easily process the edges in the correct order.
     *
     * Algorithm
     * Create a UnionFind class:
     *
     * It has two arrays: group and rank which store the group and rank of the tree of index i respectively.
     * And three methods:
     * find(node) to find the group of node,
     * join(node1, node2) to include both nodes in the same component, and
     * areConnected(node1, node2) to check if both nodes belong to the same component.
     * Initialize variables:
     *
     * uf, a UnionFind class's object with n size:
     * queriesCount, an integer variable denoting the size of the queries array.
     * answer, an array with queriesCount size.
     * queriesWithIndex, an array storing all the queries of the queries array with their original indices.
     * edgesIndex, integer variable with initial value 0.
     * Sort all edges in the edgeList array in increasing order of their edge weights.
     *
     * Sort all queries in the queriesWithIndex array in increasing order of the limit of edge allowed.
     *
     * Iterate on queriesWithIndex array, for each query:
     *
     * Get the nodes p and q, the limit limit and the original index queryOriginalIndex of the current query.
     * While edgesIndex is less than the number of edges and its edge weight is less than limit, join the nodes in the edge using UnionFind's join method uf.join(node1, node2) and increment edgesIndex by 1.
     * Check if both nodes p, and q belong to the same component using UnionFind's uf.areConnected(p, q) method. If yes, set answer[queryOriginalIndex] to true, otherwise to false.
     * Return answer.
     *
     * @param n Number of nodes
     * @param edgeList 2-D matrix with the points and the distance in between
     * @param queries array with two nodes and the path allocated in between
     * @return boolean array with queries length giving true if path is possible
     */
    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        UnionFind uf = new UnionFind(n);
        int queriesCount = queries.length;
        boolean[] answer = new boolean[queriesCount];

        // Store original indices with all queries.
        int[][] queriesWithIndex = new int[queriesCount][4];
        for (int i = 0; i < queriesCount; ++i) {
            queriesWithIndex[i][0] = queries[i][0];
            queriesWithIndex[i][1] = queries[i][1];
            queriesWithIndex[i][2] = queries[i][2];
            queriesWithIndex[i][3] = i;
        }

        // Sort all edges in increasing order of their edge weights.
        sort(edgeList);
        // Sort all queries in increasing order of the limit of edge allowed.
        sort(queriesWithIndex);

        int edgesIndex = 0;

        // Iterate on each query one by one.
        for (int queryIndex = 0; queryIndex < queriesCount; queryIndex += 1) {
            int p = queriesWithIndex[queryIndex][0];
            int q = queriesWithIndex[queryIndex][1];
            int limit = queriesWithIndex[queryIndex][2];
            int queryOriginalIndex = queriesWithIndex[queryIndex][3];

            // We can attach all edges which satisfy the limit given by the query.
            while (edgesIndex < edgeList.length && edgeList[edgesIndex][2] < limit) {
                int node1 = edgeList[edgesIndex][0];
                int node2 = edgeList[edgesIndex][1];
                uf.join(node1, node2);
                edgesIndex += 1;
            }

            // If both nodes belong to the same component, it means we can reach them.
            answer[queryOriginalIndex] = uf.areConnected(p, q);
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] edgeList = {{0,1,10},{1,2,5},{2,3,9},{3,4,13}};
        int[][] queries = { {0,4,14},{1,4,13}};
        int numberOfNodes = 5;
        CheckingExistenceOfEdgeLengthLimitedPath checkingExistenceOfEdgeLengthLimitedPath =
                new CheckingExistenceOfEdgeLengthLimitedPath();
        System.out.println(Arrays.toString(checkingExistenceOfEdgeLengthLimitedPath.distanceLimitedPathsExist(numberOfNodes, edgeList, queries)));
        System.out.println("Stream function call below: ");
        Stream.of(checkingExistenceOfEdgeLengthLimitedPath
                .distanceLimitedPathsExist(numberOfNodes, edgeList, queries)).forEach(x -> System.out.println(Arrays.toString(x)));
    }

}

class UnionFind {
    private int[] group;
    private int[] rank;

    UnionFind(int size) {
        group = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; ++i) {
            group[i] = i;
        }
    }

    public int find(int node) {
        if (group[node] != node) {
            group[node] = find(group[node]);
        }
        return group[node];
    }

    public void join(int node1, int node2) {
        int group1 = find(node1);
        int group2 = find(node2);

        // node1 and node2 already belong to same group.
        if (group1 == group2) {
            return;
        }

        if (rank[group1] > rank[group2]) {
            group[group2] = group1;
        } else if (rank[group1] < rank[group2]) {
            group[group1] = group2;
        } else {
            group[group1] = group2;
            rank[group2] += 1;
        }
    }

    public boolean areConnected(int node1, int node2) {
        int group1 = find(node1);
        int group2 = find(node2);
        return group1 == group2;
    }
}
