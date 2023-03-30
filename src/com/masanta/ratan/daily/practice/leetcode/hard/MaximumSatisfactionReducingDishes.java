package com.masanta.ratan.daily.practice.leetcode.hard;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MaximumSatisfactionReducingDishes {

    /**
     * 1402. Reducing Dishes
     * A chef has collected data on the satisfaction level of his n dishes. Chef can cook any dish in 1 unit of time.
     *
     * Like-time coefficient of a dish is defined as the time taken to cook that dish including previous dishes multiplied by its satisfaction level i.e. time[i] * satisfaction[i].
     *
     * Return the maximum sum of like-time coefficient that the chef can obtain after dishes preparation.
     *
     * Dishes can be prepared in any order and the chef can discard some dishes to get this maximum value.
     *
     *
     *
     * Example 1:
     *
     * Input: satisfaction = [-1,-8,0,5,-9]
     * Output: 14
     * Explanation: After Removing the second and last dish, the maximum total like-time coefficient will be equal to (-1*1 + 0*2 + 5*3 = 14).
     * Each dish is prepared in one unit of time.
     *
     * @param satisfaction satisfaction array with the level of satisfaction in order
     * @return maximum Satisfaction value
     */
    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        int n = satisfaction.length;
        int presum = 0, res = 0;
        for (int i = n - 1; i >= 0; i--) {
            presum += satisfaction[i];
            if (presum < 0) {
                break;
            }
            res += presum;
        }
        return res;
    }

    /*    Lee/*tcode Editorial Solutions discussed   */
    /*    https://leetcode.com/problems/reducing-dishes/editorial/   */



    /*  Approach 1: Top-Down Dynamic Programming  */

    private int findMaxSatisfaction(int[] satisfaction, int[][] memo, int index, int time) {
        // Return 0 if we have iterated over all the dishes.
        if (index == satisfaction.length) {
            return 0;
        }

        // We have already calculated the answer, so no need to go into recursion.
        if (memo[index][time] != -1) {
            return memo[index][time];
        }

        // Return the maximum of two choices:
        // 1. Cook the dish at `index` with the time taken as `time` and move on to the next dish with time as time + 1.
        // 2. Skip the current dish and move on to the next dish at the same time.
        return memo[index][time] = Math.max(satisfaction[index] * time + findMaxSatisfaction(satisfaction, memo, index + 1, time + 1),
                findMaxSatisfaction(satisfaction, memo, index + 1, time));
    }

    public int maxSatisfactionLeetCodeEditorial1(int[] satisfaction) {
        Arrays.sort(satisfaction);

        int[][] memo = new int[satisfaction.length + 1][satisfaction.length + 1];
        // Mark, all the states as -1, denoting not yet calculated.
        for (int i = 0; i < satisfaction.length; i++) {
            Arrays.fill(memo[i], -1);
        }

        return findMaxSatisfaction(satisfaction, memo, 0, 1);
    }


    /*  Approach 2: Bottom-Up Dynamic Programming  */

    public int maxSatisfactionLeetCodeEditorial2(int[] satisfaction) {
        Arrays.sort(satisfaction);

        int[][] dp = new int[satisfaction.length + 1][satisfaction.length + 2];
        // Mark all the states initially as 0.
        for (int i = 0; i <= satisfaction.length; i++) {
            Arrays.fill(dp[i], 0);
        }

        for (int i = satisfaction.length - 1; i >= 0; i--) {
            for (int j = 1; j <= satisfaction.length; j++) {
                // Maximum of two choices:
                // 1. Cook the dish at `index` with the time taken as `time` and move on to the next dish with time as time + 1.
                // 2. Skip the current dish and move on to the next dish at the same time.
                dp[i][j] = Math.max(satisfaction[i] * j + dp[i + 1][j + 1], dp[i + 1][j]);
            }
        }

        return dp[0][1];
    }

    /*  Approach 3: Bottom-Up Dynamic Programming (Space Optimized)  */

    public int maxSatisfactionEditorial3(int[] satisfaction) {
        Arrays.sort(satisfaction);

        // Array to keep the result for the previous iteration.
        int[] prev = new int[satisfaction.length + 2];
        Arrays.fill(prev, 0);

        for (int index = satisfaction.length - 1; index >= 0; index--) {
            // Array to keep the result for the current iteration.
            int[] dp = new int[satisfaction.length + 2];

            for (int time = 1; time <= satisfaction.length; time++) {
                // Maximum of two choices:
                // 1. Cook the dish at `index` with the time taken as `time` and move on to the next dish with time as time + 1.
                // 2. Skip the current dish and move on to the next dish at the same time.
                dp[time] = Math.max(satisfaction[index] * time + prev[time + 1], prev[time]);
            }
            // Assign the current iteration result to prev to be used in the next iteration.
            prev = dp;
        }
        // dp and prev have the same value here, but dp is not defined at this scope.
        return prev[1];
    }

    /*  Approach 4: Greedy  */

    public int maxSatisfactionLeetCodeEditorial4(int[] satisfaction) {
        Arrays.sort(satisfaction);

        int maxSatisfaction = 0;
        int suffixSum = 0;
        for (int i = satisfaction.length - 1; i >= 0 && suffixSum + satisfaction[i] > 0; i--) {
            // Total satisfaction with all dishes so far.
            suffixSum += satisfaction[i];
            // Adding one instance of previous dishes as we add one more dish on the left.
            maxSatisfaction +=  suffixSum;
        }
        return maxSatisfaction;
    }

    public int maxSatisfactionType5(int[] satisfaction) {
        Arrays.sort(satisfaction);
        return Math.max(0, IntStream.range(0, satisfaction.length).map(i -> IntStream.range(i, satisfaction.length).map(j -> satisfaction[j] * (j - i + 1)).sum()).max().orElse(0));
    }


    public static void main(String[] args) {
        int[] satisfactionArray = {-1,-8,0,5,-9};
        MaximumSatisfactionReducingDishes maximumSatisfactionReducingDishes =
                new MaximumSatisfactionReducingDishes();
        System.out.println(maximumSatisfactionReducingDishes.maxSatisfaction(satisfactionArray));
        System.out.println(maximumSatisfactionReducingDishes.maxSatisfactionLeetCodeEditorial1(satisfactionArray));
        System.out.println(maximumSatisfactionReducingDishes.maxSatisfactionLeetCodeEditorial2(satisfactionArray));
        System.out.println(maximumSatisfactionReducingDishes.maxSatisfactionEditorial3(satisfactionArray));
        System.out.println(maximumSatisfactionReducingDishes.maxSatisfactionLeetCodeEditorial4(satisfactionArray));
        System.out.println(maximumSatisfactionReducingDishes.maxSatisfactionType5(satisfactionArray));
    }


}
