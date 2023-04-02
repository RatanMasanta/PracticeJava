package com.masanta.ratan.daily.practice.leetcode.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    /**
     * 1. Two Sum
     *
     * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
     *
     * You may assume that each input would have exactly one solution, and you may not use the same element twice.
     *
     * You can return the answer in any order.
     *
     * Input: nums = [2,7,11,15], target = 9
     * Output: [0,1]
     * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
     *
     * This is a O(2n) solution
     *
     * @param nums integer array
     * @param target required sum which needds to be compared with
     * @return integer arary with indices of the number in the nums array
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numMapWithIndex = new HashMap<>();
        for(int i =0; i < nums.length; i++){
            numMapWithIndex.put(nums[i], i);
        }
        for(int i = 0; i < nums.length; i++){
            int requiredNumber = target - nums[i];
            if(numMapWithIndex.containsKey(requiredNumber) && (i != numMapWithIndex.get(requiredNumber))){
                return new int[] {i, numMapWithIndex.get(requiredNumber)};
            }
        }
        return new int[] {-1,-1};
    }

    /*
    The above code can be further optimized to check for the condition in the hashmap filling itself
     */

    public int[] twoSumOptimized(int[] nums, int target) {
        Map<Integer, Integer> numToIndex = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (numToIndex.containsKey(target - nums[i])) {
                return new int[] {numToIndex.get(target - nums[i]), i};
            }
            numToIndex.put(nums[i], i);
        }
        return new int[] {};
    }


    public static void main(String[] args) {
        int[] numsArray = {1,3,4,5,9,7,8,23,12,16,19,21,26,38};
        int target = 50;
        TwoSum twoSum = new TwoSum();
        System.out.println(Arrays.toString(twoSum.twoSum(numsArray, target)));
        System.out.println(Arrays.toString(twoSum.twoSumOptimized(numsArray, target)));
    }
}
