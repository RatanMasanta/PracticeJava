package com.masanta.ratan.daily.practice.leetcode.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;

public class LongestSquareStreakInAnArray {

    /**
     * Problem statement:
     * You are given an integer array nums. A subsequence of nums is called a square streak if:
     *
     * The length of the subsequence is at least 2, and
     * after sorting the subsequence, each element (except the first element) is the square of the previous number.
     * Return the length of the longest square streak in nums, or return -1 if there is no square streak.
     *
     * A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [4,3,6,16,8,2]
     * Output: 3
     * Explanation: Choose the subsequence [4,16,2]. After sorting it, it becomes [2,4,16].
     * - 4 = 2 * 2.
     * - 16 = 4 * 4.
     * Therefore, [4,16,2] is a square streak.
     * It can be shown that every subsequence of length 4 is not a square streak.
     * Example 2:
     *
     * Input: nums = [2,3,5,6,7]
     * Output: -1
     * Explanation: There is no square streak in nums so return -1.
     *
     *
     * Constraints:
     *
     * 2 <= nums.length <= 10^5
     * 2 <= nums[i] <= 10^5
     */

    public static void main(String[] args) {
        int nums[] = new int[] {2,3,5,4,6,8,9,26,16,81};
        System.out.println(STR."Longest square streak in \{Arrays.toString(nums)} is \{longestSquareStreak1(nums)}");
        System.out.println(STR."Longest square streak in \{Arrays.toString(nums)} via second method is \{longestSquareStreak2(nums)}");

    }

    /**
     * Input Processing:
     * nums = sorted(set(nums))
     * num_set = set(nums)
     * Converts input array to a sorted set to remove duplicates
     * Creates a separate set for O(1) lookups
     * Initialization:
     * max_length = 0
     * Sets up a variable to track the longest valid streak found
     * Main Logic:
     * for num in nums:
     *     length = 0
     *     current = num
     *     while current in num_set:
     *         length += 1
     *         current = current ** 2
     * Iterates through each number
     * For each number, keeps squaring it while the result exists in the set
     * Counts the length of the streak
     * Streak Validation:
     * if length > 1:
     *     max_length = max(max_length, length)
     * Only considers streaks with length > 1
     * Updates max_length if current streak is longer
     * Final Result:
     * return max_length if max_length > 1 else -1
     * Returns the longest streak if found (> 1)
     * Returns -1 if no valid streak exists
     * @param nums
     * @return
     */
     public static int longestSquareStreak1(int[] nums) {
         // Convert array to TreeSet to remove duplicates and have ordered numbers
         TreeSet<Integer> sortedSet = new TreeSet<>();
         for (int num : nums) {
             sortedSet.add(num);
         }

         // Create HashSet for O(1) lookup time
         HashSet<Integer> numSet = new HashSet<>(sortedSet);

         // Track the maximum streak length found
         int maxLength = 0;

         // Iterate through each number in sorted order
         for (int num : sortedSet) {
             // Initialize streak length for current number
             int length = 0;
             // Start with current number
             long current = num;

             // Keep squaring the number while it exists in our set
             while (current <= Integer.MAX_VALUE && numSet.contains((int)current)) {
                 length++;
                 current = current * current;
             }

             // Only update maxLength if we found a streak of length > 1
             if (length > 1) {
                 maxLength = Math.max(maxLength, length);
             }
         }

         // Return maxLength if we found a valid streak, otherwise return -1
         return maxLength > 1 ? maxLength : -1;
     }

    public static int longestSquareStreak2(int[] nums) {
        int result = -1;
        final int max = 100000;
        boolean[] isExisted = new boolean[max + 1];
        boolean[] isVisited = new boolean[max + 1];
        for (int num : nums) {
            isExisted[num] = true;
        }
        for (int i = 2; i * i <= max; i++) {
            if (!isExisted[i] || isVisited[i]) {
                continue;
            }
            isVisited[i] = true;
            int length = 1;
            int j = i * i;
            while (j >= 0 && j <= max && isExisted[j]) {
                isVisited[j] = true;
                length++;
                j = j * j;
            }
            if (length > 1) {
                result = Math.max(result, length);
            }
        }
        return result;
    }



}
