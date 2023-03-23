package com.masanta.ratan.daily.practice.leetcode.medium;

import com.masanta.ratan.daily.practice.leetcode.common.DisjointSet;

public class NumberOfOperationsToMakeNetworkConnected {

    /**
     * There are n computers numbered from 0 to n - 1 connected by ethernet cables connections forming a network where connections[i] = [ai, bi] represents a connection between computers ai and bi. Any computer can reach any other computer directly or indirectly through the network.
     *
     * You are given an initial computer network connections. You can extract certain cables between two directly connected computers, and place them between any pair of disconnected computers to make them directly connected.
     *
     * Return the minimum number of times you need to do this in order to make all the computers connected. If it is not possible, return -1.
     * @param n
     * @param edge
     * @return
     */



    public int Solve(int n, int[][] edge) {
        DisjointSet ds = new DisjointSet(n);
        int cntExtras = 0;
        int m = edge.length;
        for (int i = 0; i < m; i++) {
            int u = edge[i][0];
            int v = edge[i][1];
            if (ds.findUPar(u) == ds.findUPar(v)) {
                cntExtras++;
            } else {
                ds.unionBySize(u, v);
            }
        }
        int cntC = 0;
        for (int i = 0; i < n; i++) {
            if (ds.getParent().get(i) == i) cntC++;
        }
        int ans = cntC - 1;
        if (cntExtras >= ans) return ans;
        return -1;
    }

    public static void main (String[] args) {
        int V = 9;
        int[][] edge = {{0, 1}, {0, 2}, {0, 3}, {1, 2}, {2, 3}, {4, 5}, {5, 6}, {7, 8}};

        NumberOfOperationsToMakeNetworkConnected obj = new NumberOfOperationsToMakeNetworkConnected();
        int ans = obj.Solve(V, edge);
        System.out.println("The number of operations needed: " + ans);

    }

    /*
        Solution: https://www.youtube.com/watch?v=FYrl7iz9_ZU
     */


}
