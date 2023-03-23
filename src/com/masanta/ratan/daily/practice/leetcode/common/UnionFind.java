package com.masanta.ratan.daily.practice.leetcode.common;

public class UnionFind {

    private int[] parentArray = new int[100010];
    private int[] minRoadWeight = new int[100010];

    public UnionFind(int n){
        for(int i =0; i<= n; i++){
            parentArray[i] = i;
            minRoadWeight[i] = Integer.MAX_VALUE;
        }
    }

    public int findRoot(int number){
       return (number == parentArray[number]) ? number : findRoot(parentArray[number]);
    }

    public void setUnion(int first, int second, int weight){
        int rootFirst = findRoot(first);
        int rootSecond = findRoot(second);
        parentArray[rootSecond] = rootFirst;
        minRoadWeight[rootFirst] = Math.min(
                Math.min(minRoadWeight[rootFirst], minRoadWeight[rootSecond]), weight);
    }

    public boolean isConnected(int first, int second){
        return findRoot(first) == findRoot(second);
    }

    public int getMinWeight(int first, int second){
        return Math.min(minRoadWeight[first], minRoadWeight[second]);
    }

}
