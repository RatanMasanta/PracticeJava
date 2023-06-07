package com.masanta.ratan.daily.practice.leetcode.medium;

public class NumberOfProvinces {

    /*
    547. Number of Provinces

    There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.

    A province is a group of directly or indirectly connected cities and no other cities outside of the group.

    You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.

    Return the total number of provinces.
     */
    public int findCircleNum(int[][] isConnected) {
        int count=0;
        for(int i=0;i<isConnected.length;i++){
            if(isConnected[i][i]==0) continue;
            count++;
            dfs(isConnected,i);
        }
        return count;
    }
    private void dfs(int[][] graph,int i){
        graph[i][i]=0;
        for(int j=0;j<graph.length;j++){
            if(graph[i][j]==0) continue;
            graph[i][j]=0;
            dfs(graph,j);
        }
    }

    public static void main(String[] args) {
        NumberOfProvinces numberOfProvinces = new NumberOfProvinces();
        int[][] graph = {{1,1,0},{1,1,0},{0,0,1}};
        System.out.println(numberOfProvinces.findCircleNum(graph));
    }

}
