package com.masanta.ratan.daily.practice.leetcode.medium;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestpathInABinaryMatrix {

    /*
        1091. Shortest Path in Binary Matrix

        Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.

        A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:

        All the visited cells of the path are 0.
        All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
        The length of a clear path is the number of visited cells of this path.



        Example 1:


        Input: grid = [[0,1],[1,0]]
        Output: 2
     */

    private int[][] dirs = {
            { 0, 1 },
            { 1, 1 },
            { 1, 0 },
            { 1, -1 },
            { 0, -1 },
            { -1, -1 },
            { -1, 0 },
            { -1, 1 },
    };

    public int shortestPathBinaryMatrix(int[][] grid) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        if (grid[0][0] != 0) return -1;
        int[] start = new int[] { 0, 0 };
        q.offer(start);
        visited[0][0] = true;
        int step = 1;
        while (q.size() > 0) {
            int size = q.size();
            while (size-- > 0) {
                int[] curr = q.poll();
                if (
                        curr[0] == grid.length - 1 && curr[1] == grid[0].length - 1
                ) return step;
                for (int[] dir : dirs) {
                    int nx = curr[0] + dir[0];
                    int ny = curr[1] + dir[1];
                    if (
                            nx >= 0 &&
                                    ny >= 0 &&
                                    nx < grid.length &&
                                    ny < grid[0].length &&
                                    !visited[nx][ny] &&
                                    grid[nx][ny] == 0
                    ) {
                        q.offer(new int[] { nx, ny });
                        visited[nx][ny] = true;
                    }
                }
            }
            step++;
        }
        return -1;
    }

    public static void main(String[] args) {
        ShortestpathInABinaryMatrix shortestpathInABinaryMatrix =
                new ShortestpathInABinaryMatrix();
        int[][] grid ={{0,0,0},{1,1,0},{1,1,0}};
        System.out.println(shortestpathInABinaryMatrix.shortestPathBinaryMatrix(grid));
    }

}
