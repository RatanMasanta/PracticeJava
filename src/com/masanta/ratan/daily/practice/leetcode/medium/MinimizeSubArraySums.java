package com.masanta.ratan.daily.practice.leetcode.medium;

public class MinimizeSubArraySums {

    /*
        209. Minimum Size Subarray Sum
        Medium
        11K
        300
        Companies
        Given an array of positive integers nums and a positive integer target, return the minimal length of a
        subarray
         whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.



        Example 1:

        Input: target = 7, nums = [2,3,1,2,4,3]
        Output: 2
        Explanation: The subarray [4,3] has the minimal length under the problem constraint.
     */

    /**
     *
     * Algorithm
     * Create three integer variables left, right and sumOfCurrentWindow. The variables left and right form a subarray by pointing to the starting and ending indices of the current subarray (or window), and sumOfCurrentWindow stores the sum of this window. Initialize all of them with 0.
     * Create another variable res to store the answer to the problem. We initialize it to a large integer value.
     * We iterate over nums using right starting from right = 0 till nums.length - 1 incrementing right by 1 after each iteration. We perform the following inside this iteration:
     * Add element at index right to the current window, incrementing sumOfCurrentWindow by nums[right].
     * We check if sumOfCurrentWindow >= target. If so, we have a subarray that satisfies our condition. As a result, we attempt to update our answer variable with the length of this subarray. We perform res = min(res, right - left + 1). We then remove the first element from this window by reducing sumOfCurrentWindow by nums[left] and incrementing left by 1. This step is repeated in an inner loop as long as sumOfCurrentWindow >= target.
     * The current window's sum is now smaller than target. We need to add more elements to it. As a result, right is incremented by 1.
     * Return res.
     *
     * @param target the target integer
     * @param nums integer array provided
     * @return return the minimal length of a subarray whose sum is greater than or equal to target
     */

    public int minSubArrayLen(int target, int[] nums) {
        int minLength = Integer.MAX_VALUE;
        int currSum= 0;
        int startWindow =0;
        for(int endWindow =0 ; endWindow<nums.length; endWindow++){
            currSum += nums[endWindow];
            while(currSum>=target){

                currSum = currSum - nums[startWindow];
                minLength = Math.min(minLength, endWindow - startWindow+1);
                startWindow++;
            }

        }
        if(minLength == Integer.MAX_VALUE ) return 0;
        return minLength;
    }

    public static void main(String[] args) {
        int target = 9;
        int[] nums = {2,3,1,2,4,3};
        System.out.println(new MinimizeSubArraySums().minSubArrayLen(target, nums));
    }

}
