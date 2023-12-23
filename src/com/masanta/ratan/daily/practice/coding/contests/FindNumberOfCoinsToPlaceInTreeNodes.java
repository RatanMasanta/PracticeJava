package com.masanta.ratan.daily.practice.coding.contests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindNumberOfCoinsToPlaceInTreeNodes {

    static long ans[];
    static ArrayList<Integer>[] graph;
    static boolean visited[];
    static int ec[];
    static int entry =0;
    static int index[];
    static int ss[];

    public static long[] placedCoins(int[][] edges, int[] cost) {
        int n = cost.length;
        ans = new long[n];
        visited = new boolean[n];
        ec = new int[n];
        index = new int[n];
        ss = new int[n];
        graph = new ArrayList[n];
        for(int i = 0 ; i<n;i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i < edges.length;i++){
            int src = edges[i][0];
            int dst = edges[i][1];
            graph[src].add(dst);
            graph[dst].add(src);
        }
        dfs2(0,cost);
        visited = new boolean[n];
        dfs(0, cost);
        for(int i = 0 ;i<n;i++){
            ans[i] = ans[i] < 0 ?0:ans[i];
        }
        return ans;

    }

    private static void dfs2(int src, int cost[]){
        List<Integer> child = graph[src];
        ec[entry] = cost[src];
        visited[src]=true;
        index[src]=entry;
        //  System.out.println(src +" entry time "+entry);
        entry++;
        for(int c : child){
            if(visited[c])continue;
            visited[c] = true;
            dfs2(c, cost);
        }

    }


    private static void dfs(int src,int cost[]){
        ss[src]=1;
        List<Integer> child = graph[src];
        visited[src]=true;
        for(int c : child){
            if(visited[c])continue;
            visited[c] = true;
            dfs(c,cost);
            ss[src]+=ss[c];

        }
        // System.out.println(" src "+src+" suze "+ss[src] +" "+ec[src]);
        if(ss[src]<3){
            ans[src]=1;

            return;
        }
        // System.out.println(src +" s- rc range of substree euler " +" "+index[src] +" "+(index[src]+ss[src]) );
        ans[src] = maxProduct(ec,index[src], index[src]+ss[src]);
        //ans[src] = maxProduct(costs);


    }

    public static long maxProduct(int[] nums, int l, int r) {
        if (r - l + 1 < 3) {
            throw new IllegalArgumentException("Array must have at least 3 elements within the specified range");
        }

        // Initialize the three maximum and two minimum values
        long max1 = Integer.MIN_VALUE;
        long max2 = Integer.MIN_VALUE;
        long max3 = Integer.MIN_VALUE;
        long min1 = Integer.MAX_VALUE;
        long min2 = Integer.MAX_VALUE;

        for (int i = l; i < r; i++) {
            long current = nums[i];

            // Update maximum values
            if (current > max1) {
                max3 = max2;
                max2 = max1;
                max1 = current;
            } else if (current > max2) {
                max3 = max2;
                max2 = current;
            } else if (current > max3) {
                max3 = current;
            }

            // Update minimum values
            if (current < min1) {
                min2 = min1;
                min1 = current;
            } else if (current < min2) {
                min2 = current;
            }
        }

        // Calculate the two possible products and return the maximum
        long product1 = max1 * max2 * max3;
        long product2 = min1 * min2 * max1;

        return Math.max(product1, product2);
    }

    public static void main(String[] args) {
        int[][] edges = {{0,1},{0,2},{0,3},{0,4},{0,5}};
        int[] cost = {1,2,3,4,5,6};
        int[][] edges2 = {{0,1},{0,2},{1,3},{1,4},{1,5},{2,6},{2,7},{2,8}};
        int[] cost2 = {1,4,2,3,5,7,8,-4,2};
        System.out.println(Arrays
                .toString(FindNumberOfCoinsToPlaceInTreeNodes.placedCoins(edges, cost)));
        /*System.out.println(Arrays
                .toString(FindNumberOfCoinsToPlaceInTreeNodes.placedCoins(edges2, cost2)));*/
    }

}
