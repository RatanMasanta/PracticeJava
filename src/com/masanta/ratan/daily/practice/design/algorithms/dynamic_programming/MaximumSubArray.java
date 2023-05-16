package com.masanta.ratan.daily.practice.design.algorithms.dynamic_programming;

public class MaximumSubArray {


    /**
     *
     * 53. Maximum Subarray
     *
     * Given an integer array nums, find the
     * subarray
     *  with the largest sum, and return its sum.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
     * Output: 6
     * Explanation: The subarray [4,-1,2,1] has the largest sum 6.
     * Example 2:
     *
     * Input: nums = [1]
     * Output: 1
     * Explanation: The subarray [1] has the largest sum 1.
     *
     * @param nums given num integer array
     * @return sum of the subarray with the largest sum
     */
    public int maxSubArray(int[] nums) {
        if(nums.length == 1) return nums[0];
        int windowSum = -10000;
        int max = -10000;
        for(int start = 0, end = 0; end < nums.length; end++){
            windowSum += nums[end];
            if(windowSum < nums[end]){
                start = end;
                windowSum = nums[end];
            }
            max = Math.max(max, windowSum);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums ={5,4,-1,7,8};
        System.out.println(new MaximumSubArray().maxSubArray(nums));
    }
}
