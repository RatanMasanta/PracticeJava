package com.masanta.ratan.leetcode.weeklycontests.may142023;

public class CountTheNumberOfCompleteComponents {

    /**
     *
     * 2685. Count the Number of Complete Components
     *
     * You are given an integer n. There is an undirected graph with n vertices, numbered from 0 to n - 1.
     * You are given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an
     * undirected edge connecting vertices ai and bi.
     *
     * Return the number of complete connected components of the graph.
     *
     * A connected component is a subgraph of a graph in which there exists a path between any two vertices, and
     * no vertex of the subgraph shares an edge with a vertex outside of the subgraph.
     *
     * A connected component is said to be complete if there exists an edge between every pair of its vertices.
     *
     *
     * Input: n = 6, edges = [[0,1],[0,2],[1,2],[3,4]]
     * Output: 3
     * Explanation: From the picture above, one can see that all of the components of this graph are complete.
     *
     * @param n number of vertices
     * @param edges 2-D integer array which tells us that their is an edge in between two nodes
     * @return complete components
     */
    public int countCompleteComponents(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);

        for (int[] edge : edges) {
            uf.union(edge[0], edge[1]);
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (uf.parent[i] == i && uf.isComplete(i)) {
                count++;
            }
        }

        return count;
    }
}

class UnionFind {
    int[] parent;
    int[] rank;
    int[] edgeCount;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        edgeCount = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
                rank[rootX] += rank[rootY];
                edgeCount[rootX] += edgeCount[rootY] + 1;
            } else {
                parent[rootX] = rootY;
                rank[rootY] += rank[rootX];
                edgeCount[rootY] += edgeCount[rootX] + 1;
            }
        } else {
            edgeCount[rootX]++;
        }
    }

    public boolean isComplete(int i) {
        int root = find(i);
        // In a complete graph, the number of edges is equal to n*(n-1)/2, where n is the number of vertices
        return edgeCount[root] == rank[root] * (rank[root] - 1) / 2;
    }

    public static void main(String[] args) {
        int numberOfVertices = 6;
        int[][] edges = {{0,1},{0,2},{1,2},{3,4},{3,5}};
        CountTheNumberOfCompleteComponents countTheNumberOfCompleteComponents =
                new CountTheNumberOfCompleteComponents();
        System.out.println(countTheNumberOfCompleteComponents.countCompleteComponents(numberOfVertices, edges));
    }

}
