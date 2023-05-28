package com.masanta.ratan.daily.practice.leetcode.hard;

import java.util.Arrays;

public class MinimumCostToCutAStick {
    /*
        1547. Minimum Cost to Cut a Stick

        Given a wooden stick of length n units. The stick is labelled from 0 to n. For example, a stick of length 6 is labelled as follows:


Given an integer array cuts where cuts[i] denotes a position you should perform a cut at.

You should perform the cuts in order, you can change the order of the cuts as you wish.

The cost of one cut is the length of the stick to be cut, the total cost is the sum of costs of all cuts. When you cut a stick, it will be split into two smaller sticks (i.e. the sum of their lengths is the length of the stick before the cut). Please refer to the first example for a better explanation.

Return the minimum total cost of the cuts.



Example 1:


Input: n = 7, cuts = [1,3,4,5]
Output: 16
Explanation: Using cuts order = [1, 3, 4, 5] as in the input leads to the following scenario:

The first cut is done to a rod of length 7 so the cost is 7. The second cut is done to a rod of length 6 (i.e. the second part of the first cut), the third is done to a rod of length 4 and the last cut is to a rod of length 3. The total cost is 7 + 6 + 4 + 3 = 20.
Rearranging the cuts to be [3, 5, 1, 4] for example will lead to a scenario with total cost = 16 (as shown in the example photo 7 + 4 + 3 + 2 = 16).
Example 2:

Input: n = 9, cuts = [5,6,1,4,2]
Output: 22
Explanation: If you try the given cuts ordering the cost will be 25.
There are much ordering with total cost <= 25, for example, the order [4, 6, 5, 2, 1] has total cost = 22 which is the minimum possible.


Constraints:

2 <= n <= 106
1 <= cuts.length <= min(n - 1, 100)
1 <= cuts[i] <= n - 1
All the integers in cuts array are distinct.

     */

    /**
     Approach:
     Sort the cuts in ascending order.
     Create a 2D array dp of size (m+2) x (m+2) (where m is the number of cuts) to store the minimum cost for each subinterval.
     Iterate over the lengths l from 2 to m+1.
     For each length l, iterate over the starting indices i from 0 to m+1-l.
     Calculate the ending index j as i + l.
     Initialize dp[i][j] with a maximum value.
     Iterate over the cutting points k from i+1 to j-1.
     Calculate the cost of cutting the interval [cuts[i-1], cuts[j-1]] at point cuts[k-1].
     Update dp[i][j] by taking the minimum between the current value and the cost of the new cut.
     Calculate the left and right lengths of the interval before and after the cuts using the indices i and j.
     Update dp[i][j] by adding the length of the interval [cuts[i-1], cuts[j-1]].
     Repeat steps 3-9 until all subintervals of different lengths are processed.
     Return dp[0][m+1] as the minimum cost to cut the stick.

     Intuition:
     The problem can be solved using dynamic programming. We want to find the minimum cost to cut the stick into smaller segments at the given cutting points. By considering subintervals of different lengths, we can break down the problem into smaller subproblems.

     We use a bottom-up approach, starting from the smallest subintervals and gradually building up to the larger ones. We maintain a 2D array dp to store the minimum cost for each subinterval. The value at dp[i][j] represents the minimum cost to cut the interval [cuts[i-1], cuts[j-1]] into smaller segments.

     To calculate the minimum cost for a subinterval, we iterate over all possible cutting points within that interval. For each cutting point, we calculate the cost of making the cut and update the minimum cost if necessary.

     We also keep track of the lengths of the interval before and after the cut. By considering the left and right lengths, we can calculate the cost of cutting the stick at a particular point. We update the dp array by adding the cost of the cut and the lengths of the left and right segments.

     By repeating this process for all subintervals of different lengths, we can determine the minimum cost to cut the stick. Finally, the value at dp[0][m+1] represents the minimum cost to cut the entire stick.



     */
    public int minCost(int n, int[] cuts) {
        Arrays.sort(cuts);
        int m = cuts.length;
        int[][] dp = new int[m + 2][m + 2];

        for (int l = 2; l <= m + 1; l++) {
            for (int i = 0; i + l <= m + 1; i++) {
                int j = i + l;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
                int left = (i == 0) ? 0 : cuts[i - 1];
                int right = (j == m + 1) ? n : cuts[j - 1];
                dp[i][j] += right - left;
            }
        }

        return dp[0][m + 1];
    }

    public static void main(String[] args) {
        int n= 9;
         int[] cuts = {5,6,1,4,2};
         MinimumCostToCutAStick minimumCostToCutAStick = new MinimumCostToCutAStick();
        System.out.println(minimumCostToCutAStick.minCost(n, cuts));
    }
}
