package com.masanta.ratan.leetcode.biweeklycontests.june102023;

import java.util.*;
import java.util.stream.IntStream;

public class FindAGoodSubsetOfTheMatrix {
    /*
    You are given a 0-indexed m x n binary matrix grid.

    Let us call a non-empty subset of rows good if the sum of each column of the subset is at most half of the length of the subset.

    More formally, if the length of the chosen subset of rows is k, then the sum of each column should be at most floor(k / 2).

    Return an integer array that contains row indices of a good subset sorted in ascending order.

    If there are multiple good subsets, you can return any of them. If there are no good subsets, return an empty array.

    A subset of rows of the matrix grid is any matrix that can be obtained by deleting some (possibly none or all) rows from grid.



    Example 1:

    Input: grid = [[0,1,1,0],[0,0,0,1],[1,1,1,1]]
    Output: [0,1]
    Explanation: We can choose the 0th and 1st rows to create a good subset of rows.
    The length of the chosen subset is 2.
    - The sum of the 0th column is 0 + 0 = 0, which is at most half of the length of the subset.
    - The sum of the 1st column is 1 + 0 = 1, which is at most half of the length of the subset.
    - The sum of the 2nd column is 1 + 0 = 1, which is at most half of the length of the subset.
    - The sum of the 3rd column is 0 + 1 = 1, which is at most half of the length of the subset.
    Example 2:

    Input: grid = [[0]]
    Output: [0]
    Explanation: We can choose the 0th row to create a good subset of rows.
    The length of the chosen subset is 1.
    - The sum of the 0th column is 0, which is at most half of the length of the subset.
    Example 3:

    Input: grid = [[1,1,1],[1,1,1]]
    Output: []
    Explanation: It is impossible to choose any subset of rows to create a good subset.
     ``
     */

    /**
     *
     * Intuition
     *
     *      If there is an all 0s row, it is one of the solutions.
     *      Otherwise, if there are a pair of two rows that the AND operation between them is 0, then it is one of the solutions.
     *      Since n <= 5, we can use a HashMap/dict to store visited row(which can be converted to an int) and corresponding row number, and traverse all numbers within 2 ^ 5 - 1 = 31 to find current number's pair.
     *
     *
     * @param grid matrix provided
     * @return an integer array that contains row indices of a good subset sorted in ascending order
     */
    public List<Integer> goodSubsetofBinaryMatrix(int[][] grid) {
        int R = grid.length;
        if (R == 1) {
            return IntStream.of(grid[0]).sum() == 0 ? List.of(0) : List.of();
        }
        Map<Integer, Integer> rowIndices = new HashMap<>();
        for (int r = 0, C = grid[0].length; r < R; ++r) {
            int r1 = r, num = IntStream.range(0, C).map(k -> grid[r1][k] << k).sum();
            for (int i = 0; i < 32; ++i) {
                if ((i & num) == 0 && rowIndices.containsKey(i)) {
                    return List.of(rowIndices.get(i), r);
                }
            }
            rowIndices.putIfAbsent(num, r);
        }
        return List.of();
    }

    private static int idx;
    public List<Integer> goodSubsetofBinaryMatrixBitMask(int[][] grid) {
        List<Integer> res = new ArrayList<>();
        HashMap<Integer, Integer> fre = new HashMap<>();
        ll:
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    continue ll;
                }
            }
            res.add(i);
            return res;
        }
        for (int i = 0; i < grid.length; i++) {
            int c = 0;
            for (int j = 0; j < grid[0].length; j++) {
                c += (1 << j) * grid[i][j];
            }
            idx = -1;
            int v = 1<<(grid[0].length-1);
            sol(0,fre,v,c);
            if(idx!=-1){
                res.add(idx);
                res.add(i);
                return res;
            }
            fre.put(c,i);
        }
        return res;
    }

    static void sol(int v, HashMap<Integer, Integer> fre, int e, int val) {
        if (e == 0) {
            if (fre.get(v) != null) {
                idx = fre.get(v);
            }
            return;
        }
        if ((val & e) > 0) {
            e >>= 1;
            sol(v, fre, e, val);
        } else {
            int c = e >> 1;
            sol(v + e, fre, c, val);
            sol(v, fre, c, val);
        }
    }

    public static void main(String[] args) {
        int[][] grid = {{0,1,1,0},{0,0,0,1},{1,1,1,1}};
        FindAGoodSubsetOfTheMatrix findAGoodSubsetOfTheMatrix = new FindAGoodSubsetOfTheMatrix();
        System.out.println(Arrays.toString(findAGoodSubsetOfTheMatrix.goodSubsetofBinaryMatrix(grid).toArray()));
        System.out.println(Arrays.toString(findAGoodSubsetOfTheMatrix.goodSubsetofBinaryMatrixBitMask(grid).toArray()));
    }
}
