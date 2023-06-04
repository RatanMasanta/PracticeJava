package com.masanta.ratan.leetcode.weeklycontests.june042023;

public class SemiOrderedPermutation {

    /*
        2717. Semi-Ordered Permutation
        You are given a 0-indexed permutation of n integers nums.

        A permutation is called semi-ordered if the first number equals 1 and the last number equals n. You can perform the below operation as many times as you want until you make nums a semi-ordered permutation:

        Pick two adjacent elements in nums, then swap them.
        Return the minimum number of operations to make nums a semi-ordered permutation.

        A permutation is a sequence of integers from 1 to n of length n containing each number exactly once.

        Input: nums = [2,1,4,3]
        Output: 2
        Explanation: We can make the permutation semi-ordered using these sequence of operations:
        1 - swap i = 0 and j = 1. The permutation becomes [1,2,4,3].
        2 - swap i = 2 and j = 3. The permutation becomes [1,2,3,4].
        It can be proved that there is no sequence of less than two operations that make nums a semi-ordered permutation.
     */

    public int semiOrderedPermutation(int[] nums) {
        int first = 0, last = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            if(nums[i]==1){
                first=i;
            }
            if(nums[i]==n){
                last=i;
            }
        }
        int min = 0;
        if(first<last)
            min = (first + (n-last-1));
        else
            min = (first + (n-last-2));
        return min;
    }

    public static void main(String[] args) {
        int[] nums = {2,4,1,3};
        SemiOrderedPermutation semiOrderedPermutation = new SemiOrderedPermutation();
        System.out.println(semiOrderedPermutation.semiOrderedPermutation(nums));
    }


}
