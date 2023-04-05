package com.masanta.ratan.daily.practice.leetcode.easy;

import java.util.Arrays;

public class SquaresOfASortedArray {

    /**
     * 977. Squares of a Sorted Array
     * Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.
     *
     * Input: nums = [-4,-1,0,3,10]
     * Output: [0,1,9,16,100]
     * Explanation: After squaring, the array becomes [16,1,0,9,100].
     * After sorting, it becomes [0,1,9,16,100].
     * @param nums sorted integer array
     * @return sorted square array of the given integer array
     */
    public int[] sortedSquaresWithLibraryUse(int[] nums) {
        int[] numsAbs =new int[nums.length];
        for(int i = 0; i< nums.length; i++){
            numsAbs[i] = nums[i]*nums[i];
        }
        Arrays.sort(numsAbs);
        return numsAbs;

    }

    /**
     *
     * @param nums sorted integer array
     * @return sorted square array of the given integer array
     */
    public int[] sortedSquares(int[] nums) {
        int left=0, right=nums.length-1;
        int ans[]= new int[nums.length];
        int k=nums.length-1;
        while(left<=right){
            int max=(Math.abs(nums[left])<Math.abs(nums[right])? nums[right]: nums[left]);
            if(max==nums[right])
                right--;
            else
                left++;
            ans[k]=max*max;
            k--;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {-3, -2, -1, 0, 1, 2, 3, 4, 5,10,15};
        SquaresOfASortedArray squaresOfASortedArray = new SquaresOfASortedArray();
        System.out.println(Arrays.toString(squaresOfASortedArray.sortedSquares(nums)));
        System.out.println(Arrays.toString(squaresOfASortedArray.sortedSquaresWithLibraryUse(nums)));
    }

}
