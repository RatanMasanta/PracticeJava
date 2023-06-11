package com.masanta.ratan.leetcode.weeklycontests.june112023;

import java.util.Arrays;

public class NeitherMinimumNorMaximum {

    /*
    6470. Neither Minimum nor Maximum
User Accepted:12145
User Tried:12493
Total Accepted:21513
Total Submissions:25346
Difficulty:Easy
Given an integer array nums containing distinct positive integers, find and return any number from the array that is neither the minimum nor the maximum value in the array, or -1 if there is no such number.

Return the selected integer.



Example 1:

Input: nums = [3,2,1,4]
Output: 2
Explanation: In this example, the minimum value is 1 and the maximum value is 4. Therefore, either 2 or 3 can be valid answers.
Example 2:

Input: nums = [1,2]
Output: -1
Explanation: Since there is no number in nums that is neither the maximum nor the minimum, we cannot select a number that satisfies the given condition. Therefore, there is no answer.
Example 3:

Input: nums = [2,1,3]
Output: 2
Explanation: Since 2 is neither the maximum nor the minimum value in nums, it is the only valid answer.


Constraints:

1 <= nums.length <= 100
1 <= nums[i] <= 100
All values in nums are distinct
     */


    public int findNonMinOrMax(int[] nums) {
        Arrays.sort(nums);
        int min = nums[0], max = nums[nums.length - 1];
        for(int i = 1; i < nums.length - 1; i++){
            if(nums[i] > min && nums[i] < max){
                return nums[i];
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {3,2,1,3,5};
        NeitherMinimumNorMaximum neitherMinimumNorMaximum = new NeitherMinimumNorMaximum();
        System.out.println(neitherMinimumNorMaximum.findNonMinOrMax(nums));
    }

}
