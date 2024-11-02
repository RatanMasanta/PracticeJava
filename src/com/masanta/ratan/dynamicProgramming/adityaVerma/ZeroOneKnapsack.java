package com.masanta.ratan.dynamicProgramming.adityaVerma;

import java.util.Arrays;

public class ZeroOneKnapsack {

    // static matrix for keeping values in memoization (Top Down)
    static int[][] t;
    // static matrix for keeping solution values in tabulation dp approach (Bottom Up)
    static int[][] tp;

    void main() {
        int[] weights = {1, 3, 4, 5}, values = {1, 4, 5, 7};
        int maxCapacity = 7;

        System.out.println(STR."The max profit of carrying weights \{Arrays.toString(weights)} having values \{Arrays.toString(values)} using recursion is \{zeroOneKnapSackRecursive(weights, values, maxCapacity, weights.length)}");


        // Changes for memoization
        t = new int[weights.length + 1][maxCapacity + 1];
        for(int i = 0; i < t.length; i++){
            Arrays.fill(t[i], -1);
        }

        System.out.println(STR."The max profit of carrying weights \{Arrays.toString(weights)} having values \{Arrays.toString(values)} using memoization is \{zeroOneKnapSackMemoization(weights, values, maxCapacity, weights.length)}");

        tp = new int[weights.length + 1][maxCapacity + 1];

        System.out.println(STR."Top Down approach returns \{zeroOneKnapSizeTabulationApproach(weights, values, maxCapacity, weights.length)}");


    }

    private static int zeroOneKnapSackRecursive(int[] weights, int[] values, int maxCapacity, int n) {
        if (n == 0 || maxCapacity == 0) {
            return 0;
        }
        if (weights[n - 1] <= maxCapacity) {
            // If we have to take the value or not
            return Math.max((values[n - 1] + zeroOneKnapSackRecursive(weights, values, maxCapacity - weights[n - 1], n - 1)),
                    zeroOneKnapSackRecursive(weights, values, maxCapacity, n - 1));
        } else {
            return zeroOneKnapSackRecursive(weights, values, maxCapacity, n - 1);
        }
    }

    private static int zeroOneKnapSackMemoization(int[] weights, int[] values, int maxCapacity, int n) {
        if (n == 0 || maxCapacity == 0) {
            return 0;
        }
        // Check if value was set beforehand or not
        if (t[n][maxCapacity] != -1) {
            return t[n][maxCapacity];
        } else {
            if (weights[n - 1] <= maxCapacity) {
                // If we have to take the value or not
                t[n][maxCapacity] = Math.max((values[n - 1] + zeroOneKnapSackMemoization(weights, values, maxCapacity - weights[n - 1], n - 1)),
                        zeroOneKnapSackMemoization(weights, values, maxCapacity, n - 1));
            } else {
                t[n][maxCapacity] = zeroOneKnapSackMemoization(weights, values, maxCapacity, n - 1);
            }
        }
        return t[n][maxCapacity];
    }

    /**
     *
     * Set a matrix with changing parameters as size
     * Initialize the first row and column with zeroes
     * Check on the condition and what to do in case of choosing or not choosing the value
     * Fill in the values for rest of the matrix
     *
     * This is easier if you have memoization ready
     *
     * @param weights
     * @param values
     * @param maxCapacity
     * @param n
     * @return
     */
    private static int zeroOneKnapSizeTabulationApproach(int[] weights, int[] values, int maxCapacity, int n){
        for(int i = 0; i < n+1;i++) {
            for (int j = 0; j < weights.length + 1; j++) {
                // initialization
                if (i == 0 || j == 0) {
                    tp[i][j] = 0;
                }
            }
        }

        for(int i = 1; i < n+1;i++) {
            for (int j = 1; j < maxCapacity + 1; j++) {
                if(weights[i-1] <= j){
                    tp[i][j] = Math.max( (values[i-1] + tp[i-1][j - weights[i-1]]), tp[i-1][j]);
                } else {
                    tp[i][j] = tp[i-1][j];
                }
            }
        }
        return tp[n][maxCapacity];
    }

}
