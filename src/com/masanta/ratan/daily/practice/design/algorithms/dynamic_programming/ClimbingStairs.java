package com.masanta.ratan.daily.practice.design.algorithms.dynamic_programming;

public class ClimbingStairs {
    private int[] arr = new int[46]; // as constraint is: 1 <= n <= 45

    /**
     * 70. Climbing Stairs
     *
     * You are climbing a staircase. It takes n steps to reach the top.
     *
     * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
     *
     *
     *
     * Example 1:
     *
     * Input: n = 2
     * Output: 2
     * Explanation: There are two ways to climb to the top.
     * 1. 1 step + 1 step
     * 2. 2 steps
     *
     * Tabulation
     *
     * @param n number of stairs
     * @return the number of ways can we climb to the top
     */
    public int climbStairs(int n) {
        if(n <= 3) {
            return n;
        }
        int count = 0;
        if(arr[n] > 0) {
            count = arr[n];
        } else {
            count = climbStairs(n-1)+climbStairs(n-2);
            arr[n] = count;
        }
        return count;
    }

    /**
     *
     * MEMOIZATION
     *
     * Intuition:
     *
     *   Create an array of size dp numberOfSteps + 1, return the last value which would keep on
     *   adding all the ways to reach the point.
     *   We initiate the array with a pre defined value to check if it was visited previously.
     *   Now we create a recursion call and define base cases and then check if we already have visited the array before.
     *   So, we give the base condition (here for value 1 and 0, we return 1)
     *   And when we have the pre-filled value in the array, we write a recursive call to get the value for that particular
     *   index and we then set the values in the dp array.
     *   Finally we return nth index of dp.
     *
     * @param numberOfSteps number Of Steps we have to climb
     * @return max number of ways to climb the number of steps
     */
    public int climbStairsMemoization(int numberOfSteps){

        int[] dp = new int[numberOfSteps + 1]; // initiate the array of size n + 1
        for(int i = 0; i < numberOfSteps + 1; i++){ // prefill with a value to check if visited or not
            dp[i] = -1;
        }
        return memoization(numberOfSteps, dp);
    }

    private int memoization(int numberofSteps, int[] dp){
        if(numberofSteps == 1) return 1;
        if(numberofSteps == 0) return 1;
        if(dp[numberofSteps] != -1) return dp[numberofSteps];
        dp[numberofSteps] = memoization(numberofSteps - 1, dp)
                + memoization(numberofSteps - 2, dp);
        return dp[numberofSteps];
    }

    public static void main(String[] args) {
        ClimbingStairs climbingStairs = new ClimbingStairs();
        System.out.println(climbingStairs.climbStairs(10));
        System.out.println(climbingStairs.climbStairsMemoization(10));
    }

}
