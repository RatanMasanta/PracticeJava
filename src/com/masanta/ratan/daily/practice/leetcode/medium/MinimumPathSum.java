package com.masanta.ratan.daily.practice.leetcode.medium;

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

    public static void main(String[] args) {
        int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
        MinimumPathSum minimumPathSum = new MinimumPathSum();
        System.out.println(minimumPathSum.minPathSum(grid));
    }

    /*
            Leetcode discuss: https://leetcode.com/problems/minimum-path-sum/solutions/3345656/python-java-c-simple-solution-easy-to-understand/
            Youtube : https://www.youtube.com/watch?v=_rgTlyky1uQ
     */

}
