package com.masanta.ratan.daily.practice.leetcode.medium;

public class LongestSubArrayOfOneAfterDeletingOneElement {


    /**
     *
     * Intuition:
     * The Intuition is to use a sliding window approach to find the length of the longest subarray of 1's after removing at most one element (0 or 1) from the original array. It adjusts the window to have at most one zero, calculates the subarray length, and returns the maximum length found.
     *
     * Explanation:
     * The code aims to find the length of the longest subarray consisting of only 1's after deleting at most one element (0 or 1) from the original array.
     * The variable left represents the left pointer of the sliding window, which defines the subarray.
     * The variable zeros keeps track of the number of zeroes encountered in the current subarray.
     * The variable ans stores the maximum length of the subarray found so far.
     * The code iterates over the array using the right pointer right.
     * If nums[right] is 0, it means we encountered a zero in the array. We increment zeros by 1.
     * The while loop is used to adjust the window by moving the left pointer left to the right until we have at most one zero in the subarray.
     * If nums[left] is 0, it means we are excluding a zero from the subarray, so we decrement zeros by 1.
     * We update the left pointer by moving it to the right.
     * After adjusting the window, we calculate the length of the current subarray by subtracting the number of zeroes from the total length right - left + 1. We update ans if necessary.
     * Finally, we check if the entire array is the longest subarray. If it is, we subtract 1 from the maximum length to account for the one element we are allowed to delete. We return the resulting length.
     * Complexity:
     * Time complexity: O(N)O(N)O(N)
     *
     * Each element in the array will be iterated over twice at max. Each element will be iterated over for the first time in the for loop; then, it might be possible to re-iterate while shrinking the window in the while loop. No element can be iterated more than twice.
     * Space complexity: O(1)O(1)O(1)
     *
     * @param nums number array provided which has 0 and 1
     * @return longest subarray of 1 from the array after removing one element
     */
    public int longestSubarray(int[] nums) {
        int n = nums.length;

        int left = 0;
        int zeros = 0;
        int ans = 0;

        for (int right = 0; right < n; right++) {
            if (nums[right] == 0) {
                zeros++;
            }
            while (zeros > 1) {
                if (nums[left] == 0) {
                    zeros--;
                }
                left++;
            }
            ans = Math.max(ans, right - left + 1 - zeros);
        }
        return (ans == n) ? ans - 1 : ans;
    }

    public int longestSubarrayMethod2(int[] nums) {
        int c1=0,c2=0,ans=0;
        for(int i:nums)
        {
            if(i==0)
            {
                //c1 is number of one after last 0
                //c2 is number of one before last 0
                ans=Math.max(ans,c1+c2);
                c2=c1;
                c1=0;
            }
            else c1++;
        }
        if(c1+c2==nums.length) return nums.length-1; //if there is no 0's in array , then one 1 should be deleted
        return Math.max(ans,c1+c2);
    }

    Integer dp[][];

    public int longestSubarrayMethod3(int[] nums) {
        dp = new Integer[nums.length + 1][2];
        int max = getMaxSubarray(nums, 0, 1);

        return max == nums.length ? max - 1 : max;
    }

    private int getMaxSubarray(int[] nums, int curIdx, int canRemove) {
        if (curIdx >= nums.length) {
            return 0;
        }

        if (dp[curIdx][canRemove] != null) {
            return dp[curIdx][canRemove];
        }

        int count = 0, skip = 0;

        while (curIdx < nums.length && nums[curIdx] == 1) {
            count++;
            curIdx++;
        }

        if (canRemove == 1) {
            count += getMaxSubarray(nums, curIdx + 1, 0);
            skip = getMaxSubarray(nums, curIdx + 1, 1);
        }

        return dp[curIdx][canRemove] = Math.max(count, skip);
    }

    /*
            1493. Longest Subarray of 1's After Deleting One Element
            Medium
            3.1K
            53
            Companies
            Given a binary array nums, you should delete one element from it.

            Return the size of the longest non-empty subarray containing only 1's in the resulting array. Return 0 if there is no such subarray.



            Example 1:

            Input: nums = [1,1,0,1]
            Output: 3
            Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.
     */
    public static void main(String[] args) {
        LongestSubArrayOfOneAfterDeletingOneElement longestSubArrayOfOneAfterDeletingOneElement =
                new LongestSubArrayOfOneAfterDeletingOneElement();
        int[] nums = {0,1,1,1,0,1,1,0,1};
        int[] nums1 = {1,1,1};
        System.out.println(longestSubArrayOfOneAfterDeletingOneElement.longestSubarray(nums));
        System.out.println(longestSubArrayOfOneAfterDeletingOneElement.longestSubarrayMethod2(nums));
        System.out.println(longestSubArrayOfOneAfterDeletingOneElement.longestSubarrayMethod3(nums));
        System.out.println(longestSubArrayOfOneAfterDeletingOneElement.longestSubarray(nums1));
        System.out.println(longestSubArrayOfOneAfterDeletingOneElement.longestSubarrayMethod2(nums1));
        System.out.println(longestSubArrayOfOneAfterDeletingOneElement.longestSubarrayMethod3(nums1));
    }

}

