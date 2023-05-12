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

    public static void main(String[] args) {
        ClimbingStairs climbingStairs = new ClimbingStairs();
        System.out.println(climbingStairs.climbStairs(10));
    }

}
