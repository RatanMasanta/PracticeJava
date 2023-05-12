package com.masanta.ratan.daily.practice.design.algorithms.dynamic_programming;

public class MinCostClimbingStairs {

    /**
     *
     * 746. Min Cost Climbing Stairs
     *
     * You are given an integer array cost where cost[i] is the cost of ith step on a staircase. Once you pay the cost, you can either climb one or two steps.
     *
     * You can either start from the step with index 0, or the step with index 1.
     *
     * Return the minimum cost to reach the top of the floor.
     *
     *
     *
     * Example 1:
     *
     * Input: cost = [10,15,20]
     * Output: 15
     * Explanation: You will start at index 1.
     * - Pay 15 and climb two steps to reach the top.
     * The total cost is 15.
     *
     * @param cost array with the cost for each step
     * @return minimum cost to reach the top of the floor
     */
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] opt = new int[n + 1];
        opt[0] = 0; // init
        opt[1] = 0;
        for (int i = 2; i <= n; ++i) {
            opt[i] = Math.min(opt[i - 1] + cost[i - 1], opt[i - 2] + cost[i - 2]);
        }
        return opt[n];
    }

    public static void main(String[] args) {
        MinCostClimbingStairs costClimbingStairs = new MinCostClimbingStairs();
        int[] costArray = {1,100,1,1,1,100,1,1,100,1};
        System.out.println(costClimbingStairs.minCostClimbingStairs(costArray));
    }

}
