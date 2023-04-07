package com.masanta.ratan.daily.practice.leetcode.medium;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfEnclaves {

    /**
     * 1020. Number of Enclaves
     *
     * You are given an m x n binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.
     *
     * A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.
     *
     * Return the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.
     *
     *
     * You are given an m x n binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.
     *
     * A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.
     *
     * Return the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.
     *
     * Algorithm
     * Create two variables, m and n, to store the number of columns and rows in the given grid.
     * Create a 2D array called visit to keep track of visited cells.
     * Iterate over all the cells at the grid's boundary and for every such cell (i, j) check if it is a land cell or not. If it is a land cell and it has not been visited yet, begin a DFS traversal from (i, j) cell:
     * We use the dfs function to perform the traversal. For each call, pass x, y, and grid as the parameters. The x and y parameters represent the row and column of the cell from which DFS should begin. We start with (i ,j) cell.
     * If the cell (x, y) is out of bounds of grid or is a water cell or is an already visited cell, we don't do anything and return.
     * Otherwise, we visit this cell and mark it as visited.
     * We then call dfs recursively from each of the neighbors of (x, y).
     * Create an answer variable count to count required number of land cells.
     * Iterate over all the cells of grid and count number of unvisited land cells. For each unvisited land cell, increment count by 1.
     * Return count.
     *
     * @param grid 2D matrix of arrays
     * @return number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.
     */
    public int numEnclavesDFS(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visit = new boolean[m][n];

        for (int i = 0; i < m; ++i) {
            // First column.
            if (grid[i][0] == 1 && !visit[i][0]) {
                dfs(i, 0, m, n, grid, visit);
            }
            // Last column.
            if (grid[i][n - 1] == 1 && !visit[i][n - 1]) {
                dfs(i, n - 1, m, n, grid, visit);
            }
        }

        for (int i = 0; i < n; ++i) {
            // First row.
            if (grid[0][i] == 1 && !visit[0][i]) {
                dfs(0, i, m, n, grid, visit);
            }
            // Last row.
            if (grid[m - 1][i] == 1 && !visit[m - 1][i]) {
                dfs(m - 1, i, m, n, grid, visit);
            }
        }

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visit[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(int x, int y, int m, int n, int[][] grid, boolean[][] visit) {
        if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 0 || visit[x][y]) {
            return;
        }

        visit[x][y] = true;
        int[] dirx = {0, 1, 0, -1};
        int[] diry = {-1, 0, 1, 0};

        for (int i = 0; i < 4; i++) {
            dfs(x + dirx[i], y + diry[i], m, n, grid, visit);
        }
        return;
    }

    /**
     *
     * Approach 2: Breadth First Search
     * Intuition
     * As we have to traverse over grid modeled as a graph to find the closed islands, another method is to use a breadth-first search (BFS).
     *
     * BFS is an algorithm for traversing or searching a graph. It traverses in a level-wise manner, i.e., all the nodes at the present level (say l) are explored before moving on to the nodes at the next level (l + 1), where a level's number is the distance from a starting node. BFS is implemented with a queue.
     *
     * If you are not familiar with BFS traversal, we suggest you read our Leetcode Explore Card.
     *
     * Algorithm
     * Create two variables, m and n, to store the number of columns and rows in the given grid.
     * Create a 2D array called visit to keep track of visited cells.
     * Iterate over all the cells at the grid's boundary and for every such cell (i, j) check if it is a land cell or not. If it is a land cell and it has not been visited yet, begin a BFS traversal from (i, j) cell:
     * We use the bfs function to perform the traversal. For each call, pass x, y, m, n, grid and visit as the parameters. The x and y parameters represent the row and column of the cell from which BFS should begin. We start with (i ,j) cell.
     * We initialize a queue q of pair of integers and push (x, y) into it. We also mark (x, y) as visited.
     * While the queue is not empty, we dequeue the first pair (x, y) from the queue and iterate over all its neighbors. If any neighboring cell is not in bounds of grid, we don't do anything. Else, if it is a land cell and has not been visited yet, we mark it as visited and push (r, c) into the queue.
     * We return after the queue is empty.
     * Create an answer variable count to count required number of land cells.
     * Iterate over all the cells of grid and count number of unvisited land cells. For each unvisited land cell, increment count by 1.
     * Return count.
     *
     *
     * @param grid 2D matrix of arrays
     * @return number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.
     */
    public int numEnclavesBFS(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visit = new boolean[m][n];

        for (int i = 0; i < m; ++i) {
            // First column.
            if (grid[i][0] == 1 && !visit[i][0]) {
                bfs(i, 0, m, n, grid, visit);
            }
            // Last column.
            if (grid[i][n - 1] == 1 && !visit[i][n - 1]) {
                bfs(i, n - 1, m, n, grid, visit);
            }
        }

        for (int i = 0; i < n; ++i) {
            // First row.
            if (grid[0][i] == 1 && !visit[0][i]) {
                bfs(0, i, m, n, grid, visit);
            }
            // Last row.
            if (grid[m - 1][i] == 1 && !visit[m - 1][i]) {
                bfs(m - 1, i, m, n, grid, visit);
            }
        }

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visit[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    private void bfs(int x, int y, int m, int n, int[][] grid, boolean[][] visit) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        visit[x][y] = true;

        int[] dirx = {0, 1, 0, -1};
        int[] diry = {-1, 0, 1, 0};

        while (!q.isEmpty()) {
            int[] temp = q.poll();
            x = temp[0];  // row nnumber
            y = temp[1];  // column number

            for (int i = 0; i < 4; i++) {
                int r = x + dirx[i];
                int c = y + diry[i];
                if (r < 0 || r >= m || c < 0 || c >= n) {
                    continue;
                } else if (grid[r][c] == 1 && !visit[r][c]) {
                    q.offer(new int[]{r, c});
                    visit[r][c] = true;
                }
            }
        }
        return;
    }

    public static void main(String[] args) {
        NumberOfEnclaves numberOfEnclaves = new NumberOfEnclaves();
        int[][] grid ={{0,0,0,0}, {1,0,1,0}, {0,1,1,0}, {0,0,0,0}};
        System.out.println(numberOfEnclaves.numEnclavesBFS(grid));
        System.out.println(numberOfEnclaves.numEnclavesDFS(grid));
    }
}
