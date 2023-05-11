package com.masanta.ratan.daily.practice.dsa.practice.algorithms.dynamic_programming;

import java.util.Arrays;

public class Fibonacci {

    /**
     * 509. Fibonacci Number
     *
     * The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,
     *
     * F(0) = 0, F(1) = 1
     * F(n) = F(n - 1) + F(n - 2), for n > 1.
     * Given n, calculate F(n).
     *
     *
     *
     * Example 1:
     *
     * Input: n = 2
     * Output: 1
     * Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.
     *
     * Space Optimized
     *
     * @param n Integer whose fibonacci number we have to find
     * @return fibonacci value
     */
    public int fibSpaceOptimized(int n) {
        // base case: if n is 0 or 1, return n
        if (n <= 1) {
            return n;
        }

        // initialize variables to store the previous two Fibonacci numbers
        int prev2 = 0;
        int prev1 = 1;

        // compute the n-th Fibonacci number using a loop
        for (int i = 2; i <= n; i++) {
            // compute the current Fibonacci number as the sum of the previous two
            int cur = prev2 + prev1;

            // update prev2 and prev1 to be the previous two numbers for the next iteration
            prev2 = prev1;
            prev1 = cur;
        }

        // return the final Fibonacci number
        return prev1;
    }

    /**
     * Memoisation
     * @param n Integer whose fibonacci number we have to find
     * @return fibonacci value
     */
    public int fibMemoisation(int n) {
        // Create a dynamic programming array to store computed Fibonacci numbers
        int dp[] = new int[n+1];
        // Fill the array with a placeholder value
        Arrays.fill(dp, -1);
        // Call the helper method to calculate the nth Fibonacci number
        return helper(n, dp);
    }

    private int helper(int n, int dp[]){
        // Base case: return the first or second Fibonacci number
        if(n <= 1){
            return n;
        }
        // If the nth Fibonacci number has already been computed, return it
        if(dp[n] != -1){
            return dp[n];
        }
        // Compute the nth Fibonacci number recursively
        dp[n] = helper(n-1,dp) + helper(n-2,dp);
        return dp[n];
    }

    /**
     * Tabulation
     * @param n Integer whose fibonacci number we have to find
     * @return fibonacci value
     */
    public int fibTabulation(int n) {

        //Base case
        if(n == 0){
            return 0;
        }

        //create a dp array of length n+1 to handel n== 0 case

        int dp[] = new int [n+1];

        /* 0th and 1st number of the series are 0 and 1*/
        dp[0] = 0;
        dp[1] = 1;

        for(int i = 2; i <= n; i++){
            /* Add the previous 2 numbers in the series and store it */
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        System.out.println(fibonacci.fibTabulation(4));
        System.out.println(fibonacci.fibMemoisation(4));
        System.out.println(fibonacci.fibSpaceOptimized(4));
    }

}
