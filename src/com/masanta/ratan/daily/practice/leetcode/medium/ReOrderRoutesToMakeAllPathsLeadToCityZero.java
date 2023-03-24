package com.masanta.ratan.daily.practice.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

public class ReOrderRoutesToMakeAllPathsLeadToCityZero {


    /**
     * LEETCODE 1466. Reorder Routes to Make All Paths Lead to the City Zero
     * There are n cities numbered from 0 to n - 1 and n - 1 roads such that there is only one way to travel between two different cities (this network form a tree). Last year, The ministry of transport decided to orient the roads in one direction because they are too narrow.
     *
     * Roads are represented by connections where connections[i] = [ai, bi] represents a road from city ai to city bi.
     *
     * This year, there will be a big event in the capital (city 0), and many people want to travel to this city.
     *
     * Your task consists of reorienting some roads such that each city can visit the city 0. Return the minimum number of edges changed.
     *
     * It's guaranteed that each city can reach city 0 after reorder.
     *
     * @param n numberOfCities
     * @param connections edgeArray
     * @return numberOfReOrdersNecessary
     */
    public int minReorder(int n, int[][] connections) {
        List<List<Edge>> graph = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < connections.length; i++) {
            // 1 denotes original edge
            graph.get(connections[i][0]).add(new Edge(connections[i][1], 1));

            // 0 denotes reversed edge
            graph.get(connections[i][1]).add(new Edge(connections[i][0], 0));
        }

        // Run a dfs from 0 and dissolve(mark) the edges that can be reached from zero, thereby increasing the count
        // Because if an edge is directly reachable from 0, it actually needs to get reversed
        return dfs(graph, 0, new boolean[n]);
    }

    // DFS
    private int dfs(List<List<Edge>> graph, int source, boolean[] isVisited) {
        int cost = 0;
        isVisited[source] = true;

        for(Edge neighbour : graph.get(source)) {
            if(!isVisited[neighbour.vertex]) {
                cost += neighbour.count + dfs(graph, neighbour.vertex, isVisited);
            }
        }
        return cost;
    }

    public static void main(String[] args) {
        int[][] edgeArray = new int[][] {{0,1},{1,3},{2,3},{4,0},{4,5}};
        int targetCount = 6;
        ReOrderRoutesToMakeAllPathsLeadToCityZero reOrderRoutesToMakeAllPathsLeadToCityZero =
                new ReOrderRoutesToMakeAllPathsLeadToCityZero();
        System.out.println(reOrderRoutesToMakeAllPathsLeadToCityZero.minReorder(6,edgeArray));
    }

    private class Edge {
        int vertex, count;
        public Edge(int vertex, int count) {
            this.vertex = vertex;
            this.count = count;
        }
    }


    /*
        Leetcode discuss: https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/solutions/3334051/easy-solutions-with-exaplanation-in-java-python-and-c-look-at-once/
        Youtube Solution: https://www.youtube.com/watch?v=tnxrs_NelQ4
     */

    /*   Solution 2    */

    int dfs2(List<List<Integer>> al, boolean[] visited, int from) {
        int change = 0;
        visited[from] = true;
        for (var to : al.get(from))
            if (!visited[Math.abs(to)])
                change += dfs2(al, visited, Math.abs(to)) + (to > 0 ? 1 : 0);
        return change;
    }
    public int minReorder2(int n, int[][] connections) {
        List<List<Integer>> al = new ArrayList<>();
        for(int i = 0; i < n; ++i)
            al.add(new ArrayList<>());
        for (var c : connections) {
            al.get(c[0]).add(c[1]);
            al.get(c[1]).add(-c[0]);
        }
        return dfs2(al, new boolean[n], 0);
    }

}



