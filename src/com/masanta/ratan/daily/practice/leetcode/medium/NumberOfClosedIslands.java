package com.masanta.ratan.daily.practice.leetcode.medium;

public class NumberOfClosedIslands {

    /**
     * 1254. Number of Closed Islands
     *
     * Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.
     *
     * Return the number of closed islands.
     * @param grid 2D grid of islands
     * @return number of islands completely covered by water
     */
    public int closedIsland(int[][] grid) {
        int rows = grid.length, cols = grid[0].length, count = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0 && dfs(grid,i, j)) {
                    count++;
                }
            }
        }

        return count;
    }
    public boolean dfs(int[][] grid,int i, int j) {
        int rows = grid.length, cols = grid[0].length;
        if (i < 0 || j < 0 || i >= rows || j >= cols) {
            return false;
        }
        if (grid[i][j] == 1) {
            return true;
        }
        grid[i][j] = 1; // mark as visited
        boolean left = dfs(grid,i, j-1), right = dfs(grid,i, j+1);
        boolean up = dfs(grid,i-1, j), down = dfs(grid,i+1, j);
        return left && right && up && down;
    }

    public static void main(String[] args) {
        NumberOfClosedIslands numberOfClosedIslands = new NumberOfClosedIslands();
        int[][] grid ={{1,1,1,1,1,1,1,0},{1,0,0,0,0,1,1,0},{1,0,1,0,1,1,1,0},{1,0,0,0,0,1,0,1},{1,1,1,1,1,1,1,0}};
        System.out.println(numberOfClosedIslands.closedIsland(grid));
    }

    /*
        Leetcode Editorial: https://leetcode.com/problems/number-of-closed-islands/editorial/
        Leetcode discuss: https://leetcode.com/problems/number-of-closed-islands/solutions/3384821/python3-c-java-easy-and-understand-dfs-solution-beats-100/
     */

}
