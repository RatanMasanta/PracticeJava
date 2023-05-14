package com.masanta.ratan.leetcode.weeklycontests.may142023;

import java.util.HashMap;
import java.util.Map;

public class MaximumNumberOfMovesInAGrid {
    private Map<String, Integer> mp = new HashMap<>();
    public int dfs(int[][] grid, int row, int col){
        if(row<0 || row>=grid.length || col >=grid[0].length || col<0){
            return 0;
        }
        String as = row+""+col;
        if(mp.containsKey(as)){
            return mp.get(as);
        }
        int up = 0;
        if(row-1>=0 && col+1<grid[0].length && grid[row-1][col+1] > grid[row][col]){
            up = 1 + dfs(grid, row-1, col+1);
        }
        int right = 0;
        if(col+1<grid[0].length && grid[row][col+1] > grid[row][col]){
            right = 1 + dfs(grid, row, col+1);
        }
        int down = 0;
        if(row+1<grid.length && col+1<grid[0].length && grid[row+1][col+1] > grid[row][col]){
            down = 1 + dfs(grid, row+1, col+1);
        }
        int x = Math.max(up,Math.max(right, down));
        mp.put(as, x);
        return x;
    }

    /**
     *
     * 2684. Maximum Number of Moves in a Grid
     *
     * You are given a 0-indexed m x n matrix grid consisting of positive integers.
     *
     * You can start at any cell in the first column of the matrix, and traverse the grid in the following way:
     *
     * From a cell (row, col), you can move to any of the cells: (row - 1, col + 1), (row, col + 1) and (row + 1, col + 1) such that the value of the cell you move to, should be strictly bigger than the value of the current cell.
     * Return the maximum number of moves that you can perform.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: grid = [[2,4,3,5],[5,4,9,3],[3,4,2,11],[10,9,13,15]]
     * Output: 3
     * Explanation: We can start at the cell (0, 0) and make the following moves:
     * - (0, 0) -> (0, 1).
     * - (0, 1) -> (1, 2).
     * - (1, 2) -> (2, 3).
     * It can be shown that it is the maximum number of moves that can be made.
     * Example 2:
     *
     *
     * Input: grid = [[3,2,4],[2,1,9],[1,1,7]]
     * Output: 0
     * Explanation: Starting from any cell in the first column we cannot perform any moves.
     *
     * @param grid grid of values provided
     * @return maximum number of moves that you can perform on the grid
     */
    public int maxMoves(int[][] grid) {
        int row = grid.length, col = grid[0].length, ans = 0;
        for(int i=0; i<row; i++){
            int val = dfs(grid,i,0);
            ans = Math.max(ans, val);
        }
        return ans;
    }

    public static void main(String[] args) {
        MaximumNumberOfMovesInAGrid maximumNumberOfMovesInAGrid = new MaximumNumberOfMovesInAGrid();
        int[][] grid = {{3,2,4},{2,1,9},{1,1,7}};
        System.out.println(maximumNumberOfMovesInAGrid.maxMoves(grid));
    }
}
