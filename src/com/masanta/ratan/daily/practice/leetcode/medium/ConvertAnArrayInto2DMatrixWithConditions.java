package com.masanta.ratan.daily.practice.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

public class ConvertAnArrayInto2DMatrixWithConditions {


    /*
        You are given an integer array nums. You need to create a 2D array from nums satisfying the following conditions:

        The 2D array should contain only the elements of the array nums.
        Each row in the 2D array contains distinct integers.
        The number of rows in the 2D array should be minimal.
        Return the resulting array. If there are multiple answers, return any of them.

        Note that the 2D array can have a different number of elements on each row.



        Example 1:

        Input: nums = [1,3,4,1,2,3,1]
        Output: [[1,3,4,2],[1,3],[1]]
        Explanation: We can create a 2D array that contains the following rows:
        - 1,3,4,2
        - 1,3
        - 1
        All elements of nums were used, and each row of the 2D array contains distinct integers, so it is a valid answer.
        It can be shown that we cannot have less than 3 rows in a valid array.
        Example 2:

        Input: nums = [1,2,3,4]
        Output: [[4,3,2,1]]
        Explanation: All elements of the array are distinct, so we can keep all of them in the first row of the 2D array.


        Constraints:

        1 <= nums.length <= 200
        1 <= nums[i] <= nums.length


     */

    public static List<List<Integer>> findMatrix(int[] nums) {
        int freq[] = new int[nums.length + 1];

        ArrayList<List<Integer>> ans = new ArrayList<>();
        for (int c : nums) {
            if (freq[c] >= ans.size()) {
                ans.add(new ArrayList<>());
            }

            // Store the integer in the list corresponding to its current frequency.
            ans.get(freq[c]).add(c);
            freq[c]++;
        }

        return ans;
    }

    public static void main(String[] args) {
        int nums[] = {1,3,4,1,2,3,1};
        ConvertAnArrayInto2DMatrixWithConditions.findMatrix(nums).stream().forEach(x -> System.out.println(x));
    }


}
