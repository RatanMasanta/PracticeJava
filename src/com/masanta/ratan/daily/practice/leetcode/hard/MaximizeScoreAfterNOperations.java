package com.masanta.ratan.daily.practice.leetcode.hard;

import java.util.Arrays;

public class MaximizeScoreAfterNOperations {

    private int backtrack(int[] nums, int mask, int pairsPicked, int[] memo) {
        // If we have picked all the numbers from 'nums' array, we can't get more score.
        if (2 * pairsPicked == nums.length) {
            return 0;
        }

        // If we already solved this sub-problem then return the stored result.
        if (memo[mask] != -1) {
            return memo[mask];
        }

        int maxScore = 0;

        // Iterate on 'nums' array to pick the first and second number of the pair.
        for (int firstIndex = 0; firstIndex < nums.length; ++firstIndex) {
            for (int secondIndex = firstIndex + 1; secondIndex < nums.length; ++secondIndex) {

                // If the numbers are same, or already picked, then we move to next number.
                if (((mask >> firstIndex) & 1) == 1 || ((mask >> secondIndex) & 1) == 1) {
                    continue;
                }

                // Both numbers are marked as picked in this new mask.
                int newMask = mask | (1 << firstIndex) | (1 << secondIndex);

                // Calculate score of current pair of numbers, and the remaining array.
                int currScore = (pairsPicked + 1) * gcd(nums[firstIndex], nums[secondIndex]);
                int remainingScore = backtrack(nums, newMask, pairsPicked + 1, memo);

                // Store the maximum score.
                maxScore = Math.max(maxScore, currScore + remainingScore);
                // We will use old mask in loop's next interation,
                // means we discarded the picked number and backtracked.
            }
        }

        // Store the result of the current sub-problem.
        memo[mask] = maxScore;
        return maxScore;
    }

    /**
     *
     * 1799. Maximize Score After N Operations
     *
     * You are given nums, an array of positive integers of size 2 * n. You must perform n operations on this array.
     *
     * In the ith operation (1-indexed), you will:
     *
     * Choose two elements, x and y.
     * Receive a score of i * gcd(x, y).
     * Remove x and y from nums.
     * Return the maximum score you can receive after performing n operations.
     *
     * The function gcd(x, y) is the greatest common divisor of x and y.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [1,2]
     * Output: 1
     * Explanation: The optimal choice of operations is:
     * (1 * gcd(1, 2)) = 1
     *
     *
     * Solution
     *
     * Approach 1: DP with Bitmasking (Recursive)
     * Intuition
     * The problem can be solved by using a backtracking approach, we can try forming all cases of all possible pairs of elements, generating the total score in each case, and selecting the one with the maximum score.
     *
     *
     * We can write a recursive function backtrack() which generates all possibilities by picking two elements and recursively finding the answer for the remaining array after discarding the two chosen elements. We break the current bigger problem into smaller similar sub-problems.
     *
     * def backtrack(array) -> int:
     *     for element1 in array:
     *         for element2 in array:
     *             # get the current score for pair (element1, element2)
     *             # remove both elements from the array and get the remaining array score
     *             # put the elements back in the array and try other elements, i.e. BACKTRACK
     * This is a brute-force approach. We can implement some optimizations.
     *
     * Now, say we have an array of eight elements [a, b, c, d, e, f, g, h]\text{[a, b, c, d, e, f, g, h]}[a, b, c, d, e, f, g, h]. Consider two cases.
     *
     * In case 1, we picked pairs (c, d)\text{(c, d)}(c, d) and (b, e)\text{(b, e)}(b, e), and we are left to find out the answer of the array [a, f, g, h]\text{[a, f, g, h]}[a, f, g, h].
     * In case 2, we picked pairs (b, c)\text{(b, c)}(b, c) and (d, e)\text{(d, e)}(d, e), and we are left to find out the answer of the array [a, f, g, h]\text{[a, f, g, h]}[a, f, g, h].
     * In both cases, we can see that the sub-problem [a, f, g, h]\text{[a, f, g, h]}[a, f, g, h] needs to be calculated, thus we can memoize the results to save computation time whenever a sub-problem is repeated.
     *
     * Now, we know that the state of the current sub-problem depends on the remaining elements of the array. So we need to memoize the result based on this state. An easy way to implement this is using bitmasking.
     *
     * We can keep a boolean array, and we mark picked numbers in this array. But instead of using an array, we can achieve the same functionality using an integer.
     *
     * As integers have 323232 bits, each bit can be 000 or 111. We can use these bits to represent if an element of our array is picked or not.
     * In an integer number (say mask\text{mask}mask) if the bit at position i\text{i}i is 000, it means the array element at the ith\text{i}^{th}i
     * th
     *   index is not picked otherwise if it's 111 it means the element was picked earlier.
     *
     * Note: If number of elements in the nums array will exceed 323232 then we will not be able to use this method with a 32 bit integer.
     *
     * So we can map the mask (current state) with the result, using a hashmap or an array, memo.
     * Here, the mask's value will vary from 000 (no element is picked) to 2nums array size−12^{\text{nums array size}} - 12
     * nums array size
     *  −1 (i.e. 111111...11111111...11111111...11 in binary, all elements are picked).
     * Thus, the memo array's size will be 2nums array size=22n2^{\text{nums array size}} = 2^{2n}2
     * nums array size
     *  =2
     * 2n
     *  .
     *
     * Algorithm
     * Create a function backtrack which takes the nums array, mask and pairsPicked integers, and memo array as arguments:
     * If we picked all elements from the nums array, then we return 000 from here as no score can be received now.
     * If we had already solved the same sub-problem earlier, i.e. memo[mask] != -1, then we return the stored result from the memo array.
     * Otherwise, initialize maxScore as 0.
     * Using two nested for loops we iterate on each pair of numbers pointed by firstIndex and secondIndex in the nums array. We check if the bit of mask at these indices is 0 to make sure those numbers were not picked earlier.
     * We mark them as picked in newMask, calculate the current score currScore, and find the score of the remaining numbers remainingScore recursively passing newMask in its parameter.
     * If the maxScore is smaller than currScore + remainingScore, we update maxScore with it.
     * At the end of the loops, we discard the picked numbers and reset the mask to its previous value (i.e. we are backtracking).
     * In the end, we store the result of the current sub-problem in the memo array and return the result maxScore.
     * Create a memo array of size 22n2^{2 n}2
     * 2n
     *   and initialize with -1.
     * Call the backtrack function with mask = 0 and pairsPicked = 0 to denote no element is initially picked and return the result.
     *
     * @param nums
     * @return
     */
    public int maxScore(int[] nums) {
        int memoSize = 1 << nums.length; // 2^(nums array size)
        int[] memo = new int[memoSize];
        Arrays.fill(memo, -1);
        return backtrack(nums, 0, 0, memo);
    }

    // Utility function to calculate the gcd of two numbers.
    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        int[] nums = {3,4,6,8};
        MaximizeScoreAfterNOperations maximizeScoreAfterNOperations = new MaximizeScoreAfterNOperations();
        System.out.println(maximizeScoreAfterNOperations.maxScore(nums));
    }


}
