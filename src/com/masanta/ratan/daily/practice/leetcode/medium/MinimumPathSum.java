package com.masanta.ratan.daily.practice.leetcode.medium;

import java.util.Arrays;

public class MinimumPathSum {

    /**
     * 64. Mininmum Path Sum
     * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.
     * Note: You can only move either down or right at any point in time.
     * @param grid 2-D matrix where we need to find the minimum path sum
     * @return minimum path sum
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 1; i < m; i++) {
            grid[i][0] += grid[i-1][0];
        }

        for (int j = 1; j < n; j++) {
            grid[0][j] += grid[0][j-1];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] += Math.min(grid[i-1][j], grid[i][j-1]);
            }
        }

        return grid[m-1][n-1];
    }

    public int minSumPathUtil(int i, int j, int[][] matrix, int[][] dp) {
        if(i==0 && j == 0)
            return matrix[0][0];
        if(i<0 || j<0)
            return (int)Math.pow(10,9);
        if(dp[i][j]!=-1) return dp[i][j];

        int up = matrix[i][j]+minSumPathUtil(i-1,j,matrix,dp);
        int left = matrix[i][j]+minSumPathUtil(i,j-1,matrix,dp);

        return dp[i][j] = Math.min(up,left);

    }

    public int minSumPath(int n, int m, int[][] matrix){

        int dp[][]=new int[n][m];

        for(int i=0; i<n ; i++){
            for(int j=0; j<m; j++){
                if(i==0 && j==0) dp[i][j] = matrix[i][j];
                else{

                    int up = matrix[i][j];
                    if(i>0) up += dp[i-1][j];
                    else up += (int)Math.pow(10,9);

                    int left = matrix[i][j];
                    if(j>0) left+=dp[i][j-1];
                    else left += (int)Math.pow(10,9);

                    dp[i][j] = Math.min(up,left);
                }
            }
        }

        return dp[n-1][m-1];

    }


    public int minSumPathSpaceOptimized(int n, int m, int[][] matrix){

        int prev[] = new int[m];
        for(int i=0; i<n ; i++){
            int temp[] = new int[m];
            for(int j=0; j<m; j++){
                if(i==0 && j==0) temp[j] = matrix[i][j];
                else{

                    int up = matrix[i][j];
                    if(i>0) up += prev[j];
                    else up += (int)Math.pow(10,9);

                    int left = matrix[i][j];
                    if(j>0) left+=temp[j-1];
                    else left += (int)Math.pow(10,9);

                    temp[j] = Math.min(up,left);
                }
            }
            prev=temp;
        }

        return prev[m-1];
    }

    public static void main(String[] args) {
        int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
        MinimumPathSum minimumPathSum = new MinimumPathSum();
        System.out.println(minimumPathSum.minPathSum(grid));
        int n = grid.length, m=grid[0].length;
        int dp[][]=new int[n][m];
        for(int row[]: dp)
            Arrays.fill(row,-1);
        System.out.println(minimumPathSum.minSumPathUtil(n -1, m - 1, grid, dp)) ;
        System.out.println(minimumPathSum.minSumPath(n, m, grid));
        System.out.println(minimumPathSum.minSumPathSpaceOptimized(n,m,grid));
    }

    /*
            Leetcode discuss: https://leetcode.com/problems/minimum-path-sum/solutions/3345656/python-java-c-simple-solution-easy-to-understand/
            Youtube : https://www.youtube.com/watch?v=_rgTlyky1uQ
     */

}
