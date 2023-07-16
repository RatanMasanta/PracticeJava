package com.masanta.ratan.daily.practice.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

public class LongestArithmeticSubSequenceOfGivenDifference {

    /*
    1218. Longest Arithmetic Subsequence of Given Difference
    Medium
    2.9K
    81
    Companies
    Given an integer array arr and an integer difference, return the length of the longest subsequence in arr which is an arithmetic sequence such that the difference between adjacent elements in the subsequence equals difference.

    A subsequence is a sequence that can be derived from arr by deleting some or no elements without changing the order of the remaining elements.



    Example 1:

    Input: arr = [1,2,3,4], difference = 1
    Output: 4
    Explanation: The longest arithmetic subsequence is [1,2,3,4].
     */

    /**
     *
     * Intuition
     * One possible approach to solving this problem is to iterate through each element arr[i], and for each such arr[i], we look for the longest arithmetic subsequence that starts with arr[i]. We can simply iterate through the rest of the array starting from arr[i+1], and for each such element arr[j], check if arr[j] - arr[i] = difference. If it is, we have found the next element arr[i] + difference of the arithmetic subsequence and we can continue to look for the next element arr[i] + difference * 2 in the same way.
     *
     * We keep track of the length of the arithmetic subsequence that we find and update the maximum length found so far.
     *
     * img
     *
     * However, this brute force approach takes O(n2)O(n^2)O(n
     * 2
     *  ) time, which is likely to exceed the time limit, as we need to iterate through the rest of the array for each element arr[i].
     *
     *
     * To improve the time complexity of our solution, we can use dynamic programming (DP). DP is a technique where we solve subproblems and use their solutions to solve larger problems. In this case, we can use DP to avoid iterating through the array for each arr[i].
     *
     * If you are not familiar with dynamic programming, you can refer to our Dynamic Programming Explore Card on LeetCode.
     *
     * The key idea of the DP approach is to use a hash map dp to store the maximum length of an arithmetic subsequence that ends with each element in arr. We initialize dp as empty. Then, for each element arr[i], we check if arr[i] - difference is already present in dp.
     *
     * If it is, let's say dp[arr[i] - difference] = before_a. It means there exists an arithmetic subsequence of length before_a that ends with arr[i] - difference. Since we can append arr[i] to this sequence, we update dp[arr[i]] to be dp[arr[i] - difference] + 1.
     *
     * Otherwise, we simply set dp[arr[i]] = 1, as an element on its own is technically an arithmetic subsequence.
     *
     *
     * As shown in the picture below, during the iteration, if we want the longest arithmetic subsequence ending with 3, we need to find the longest arithmetic subsequence ending with 5 previously. If we have saved the maximum length of a subsequence that ends with each previous element in dp, we can easily look into dp and find if a subsequence that ends with 5 exists.
     *
     * Please refer to the slides below as a detailed example:
     *
     * Current
     *
     * After iterating through the entire array, we can find the maximum value in dp, which is the length of the longest arithmetic subsequence in arr, or alternatively we can keep track of the maximum dp[arr[i]] during the iteration by answer = max(answer, dp[arr[i]])
     *
     *
     * Algorithm
     * Initialize an empty hash map dp, set answer = 1.
     *
     * Iterate over arr, for each index i.
     *
     * Get before_a, the maximum length of an arithmetic subsequence that ends with arr[i] - difference:
     *
     * If arr[i] - difference is in dp, before_a = dp[arr[i] - difference].
     *
     * Otherwise, before_a = 0.
     *
     * Set dp[arr[i]] = before_a + 1, update answer as answer = max(answer, dp[arr[i]]).
     *
     * Return answer when the iteration ends.
     *
     * @param arr given integer array
     * @param difference difference of the arithmetic series
     * @return longest arithmetic sub-sequence of given difference
     */
    public int longestSubsequence(int[] arr, int difference) {
        Map<Integer, Integer> dp = new HashMap<>();
        int answer = 1;

        for (int a : arr) {
            int beforeA = dp.getOrDefault(a - difference, 0);
            dp.put(a, beforeA + 1);
            answer = Math.max(answer, dp.get(a));
        }

        return answer;
    }

    public static void main(String[] args) {
        LongestArithmeticSubSequenceOfGivenDifference longestArithmeticSubSequenceOfGivenDifference=
                new LongestArithmeticSubSequenceOfGivenDifference();
        int[] array = {1,5,7,8,5,3,4,2,1};
        int difference = 2;
        System.out.println(longestArithmeticSubSequenceOfGivenDifference.longestSubsequence(array, difference));
    }

}
