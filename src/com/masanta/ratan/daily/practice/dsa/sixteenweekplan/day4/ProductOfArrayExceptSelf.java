package com.masanta.ratan.daily.practice.dsa.sixteenweekplan.day4;

import java.util.Arrays;

public class ProductOfArrayExceptSelf {

    /*

    238. Product of Array Except Self

        Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].

        The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

        You must write an algorithm that runs in O(n) time and without using the division operation.



        Example 1:

        Input: nums = [1,2,3,4]
        Output: [24,12,8,6]
        Example 2:

        Input: nums = [-1,1,0,-3,3]
        Output: [0,0,9,0,0]


        Constraints:

        2 <= nums.length <= 105
        -30 <= nums[i] <= 30
        The input is generated such that answer[i] is guaranteed to fit in a 32-bit integer.


        Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space for space complexity analysis.)

     */

    public static void main(String[] args) {
        int nums1[] = {1,2,3,4};
        int nums2[] = {-1,1,0,-3,3};
        int nums3[] = {-1,1,0,-3,3,4,-4,0,5};
        System.out.println(Arrays.toString(productExceptSelf(nums1)));
        System.out.println(Arrays.toString(productExceptSelf(nums2)));
        System.out.println(Arrays.toString(productExceptSelf(nums3)));

    }

    public static int[] productExceptSelf(int[] nums) {
        int product = 1, zeroCount = 0, n = nums.length;
        boolean hasZero = false;
        boolean hasMultiZero = false;
        for(int num: nums){
            if(num != 0) {
                product *= num;
            } else {
                zeroCount++;
                hasZero = true;
                if(zeroCount > 1){
                    hasMultiZero = true;
                }
            }
        }
        if(hasMultiZero){
            return new int[n];
        }
        for(int  i = 0; i < nums.length; i++){
            if(hasZero){
                if(nums[i] == 0){
                    nums[i] = product;
                } else {
                    nums[i] = 0;
                }
            } else {
                int temp = product;
                nums[i] = temp / nums[i];
            }
        }
        return nums;
    }


}
