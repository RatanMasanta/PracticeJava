package com.masanta.ratan.leetcode.weeklycontests.may282023;

import java.util.Arrays;

public class MaximumStrictlyIncreasingCellsInAMatrix {
    /*
        Given a 1-indexed m x n integer matrix mat, you can select any cell in the matrix as your starting cell.

        From the starting cell, you can move to any other cell in the same row or column, but only if the value of the destination cell is strictly greater than the value of the current cell. You can repeat this process as many times as possible, moving from cell to cell until you can no longer make any moves.

        Your task is to find the maximum number of cells that you can visit in the matrix by starting from some cell.

        Return an integer denoting the maximum number of cells that can be visited.



        Example 1:



        Input: mat = [[3,1],[3,4]]
        Output: 2
        Explanation: The image shows how we can visit 2 cells starting from row 1, column 2. It can be shown that we cannot visit more than 2 cells no matter where we start from, so the answer is 2.
        Example 2:



        Input: mat = [[1,1],[1,1]]
        Output: 1
        Explanation: Since the cells must be strictly increasing, we can only visit one cell in this example.
        Example 3:



        Input: mat = [[3,1,6],[-9,5,7]]
        Output: 4
        Explanation: The image above shows how we can visit 4 cells starting from row 2, column 1. It can be shown that we cannot visit more than 4 cells no matter where we start from, so the answer is 4.
     */


    /**
     Intuition
     Use top-down dp. The dp function is
     dp[i][j] = max(max(all the next larger dp[i][k] in the same row i), all the next larger dp[l][j] in the same column j)) + 1

     To find the next larger cell in the same row/column, we need to sort every columns and rows.

     Approach
     After sorting each row/column, we can use a linked List like array to save the next larger-or-equal index in the same row/column. Every cell is pointing to the next one in the sorted order.

     next greater or equal horizontal (ngeh):
     if ngeh[i][j] = k, mat[i][k] is the next smallest value in row i such that mat[i][k] >= mat[i][j]

     Similarly
     next greater or equal vertical (ngev):
     if ngev[i][j] = k, mat[k][j] is the next smallest value in column j such that mat[k][j] >= mat[i][j]

     We than use this info to do our top-down dp (dfs + memo).

     Complexity
     Time complexity:
     O(mnlgn + nmlgm)

     Space complexity:
     O(mn)

     */
    public int maxIncreasingCells(int[][] mat) {
        int m = mat.length, n = mat[0].length, res = 0;
        int[][] ngeh = new int[m][n], ngev = new int[m][n], dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            Integer[] tmp = new Integer[n];
            for (int j = 0; j < n; j++) {
                tmp[j] = j;
            }
            final int fi = i;
            Arrays.sort(tmp, (a, b) -> mat[fi][a] - mat[fi][b]);
            for (int j = 0; j < n - 1; j++) {
                ngeh[i][tmp[j]] = tmp[j + 1];
            }
            ngeh[i][tmp[n - 1]] = -1;
        }
        for (int i = 0; i < n; i++) {
            Integer[] tmp = new Integer[m];
            for (int j = 0; j < m; j++) {
                tmp[j] = j;
            }
            final int fi = i;
            Arrays.sort(tmp, (a, b) -> mat[a][fi] - mat[b][fi]);
            for (int j = 0; j < m - 1; j++) {
                ngev[tmp[j]][i] = tmp[j + 1];
            }
            ngev[tmp[m - 1]][i] = -1;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == 0) {
                    res = Math.max(res, helper(i, j, ngeh, ngev, dp, mat));
                }
            }
        }
        return res;
    }

    private int helper(int i, int j, int[][] ngeh, int[][] ngev, int[][] dp, int[][] mat) {
        if (dp[i][j] > 0) {
            return dp[i][j];
        }
        int res = 0;
        // h
        int next = ngeh[i][j];
        while (next != -1 && mat[i][next] == mat[i][j]) {
            next = ngeh[i][next];
        }
        if (next != -1) {
            int larger = mat[i][next];
            while (next != -1 && mat[i][next] == larger) {
                res = Math.max(res, helper(i, next, ngeh, ngev, dp, mat));
                next = ngeh[i][next];
            }
        }
        // v
        next = ngev[i][j];
        while (next != -1 && mat[next][j] == mat[i][j]) {
            next = ngev[next][j];
        }
        if (next != -1) {
            int larger = mat[next][j];
            while (next != -1 && mat[next][j] == larger) {
                res = Math.max(res, helper(next, j, ngeh, ngev, dp, mat));
                next = ngev[next][j];
            }
        }
        dp[i][j] = 1 + res;
        return 1 + res;
    }

    public static void main(String[] args) {
        MaximumStrictlyIncreasingCellsInAMatrix strictlyIncreasingCellsInAMatrix =
                new MaximumStrictlyIncreasingCellsInAMatrix();
        int[][] matrix  = {{3,1,6},{-9,5,7}};
        System.out.println(strictlyIncreasingCellsInAMatrix.maxIncreasingCells(matrix));
    }

}
