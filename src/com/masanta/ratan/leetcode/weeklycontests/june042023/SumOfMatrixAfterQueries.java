package com.masanta.ratan.leetcode.weeklycontests.june042023;

public class SumOfMatrixAfterQueries {

    /*
        2718. Sum of Matrix After Queries
           
        Companies
        You are given an integer n and a 0-indexed 2D array queries where queries[i] = [typei, indexi, vali].

        Initially, there is a 0-indexed n x n matrix filled with 0's. For each query, you must apply one of the following changes:

        if typei == 0, set the values in the row with indexi to vali, overwriting any previous values.
        if typei == 1, set the values in the column with indexi to vali, overwriting any previous values.
        Return the sum of integers in the matrix after all queries are applied.
        
        Input: n = 3, queries = [[0,0,1],[1,2,2],[0,2,3],[1,0,4]]
        Output: 23
        Explanation: The image above describes the matrix after each query. The sum of the matrix after all queries are applied is 23. 

     */

    public long matrixSumQueries(int n, int[][] queries) {
        var rows = new boolean[n];
        var cols = new boolean[n];
        int rowCnt = n, colCnt = n;
        var sum = 0L;

        for (var i = queries.length - 1; i >= 0 && rowCnt > 0 && colCnt > 0; i--) {
            if (queries[i][0] == 0) {
                if (rows[queries[i][1]]) continue;

                rows[queries[i][1]] = true;
                rowCnt--;
                sum += colCnt * queries[i][2];
            } else {
                if (cols[queries[i][1]]) continue;

                cols[queries[i][1]] = true;
                colCnt--;
                sum += rowCnt * queries[i][2];
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        SumOfMatrixAfterQueries sumOfMatrixAfterQueries = new SumOfMatrixAfterQueries();
        int[][] queries = {{ 0,0,4},{0,1,2},{1,0,1},{0,2,3},{1,2,1}};
        int n = 3;
        System.out.println(sumOfMatrixAfterQueries.matrixSumQueries(n, queries));
    }
}
