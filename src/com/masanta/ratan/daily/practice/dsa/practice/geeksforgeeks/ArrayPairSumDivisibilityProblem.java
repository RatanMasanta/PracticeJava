package com.masanta.ratan.daily.practice.dsa.practice.geeksforgeeks;

import java.util.HashMap;

public class ArrayPairSumDivisibilityProblem {

    /*
            Company: Amazon, Microsoft, Goldman Sachs, Directi

            Given an array of integers nums and a number k, write a function that returns true if given array can be divided into pairs such that sum of every pair is divisible by k.

            Example 1 :

            Input :
            nums = [9, 5, 7, 3]
            k = 6
            Output:
            True
            Explanation:
            {(9, 3), (5, 7)} is a
            possible solution. 9 + 3 = 12 is divisible
            by 6 and 7 + 5 = 12 is also divisible by 6.
            Example 2:

            Input :
            nums = [4, 4, 4]
            k = 4
            Output:
            False
            Explanation:
            You can make 1 pair at max, leaving a single 4 unpaired.
            Your Task:
            You don't need to read or print anything. Your task is to complete the function canPair() which takes array nums and k as input parameter and returns true if array can be divided into pairs such that sum of every pair is divisible by k otherwise returns false.

            Expected Time Complexity: O(n)
            Expected Space Complexity : O(n)

            Constraints:
            1 <= length( nums ) <= 105
            1 <= numsi <= 105
            1 <= k <= 105
     */

    public static boolean canPair(int[] nums, int k) {
        // Code here
        HashMap<Integer,Integer> map = new HashMap<>();
        int n = nums.length;
        for(int i = 0; i<n;i++)
        {
            map.put(nums[i]%k,map.getOrDefault(nums[i]%k,0)+1);
        }
        int rem = 0;
        for(int i = 0;i <n; i++)
        {
            rem =nums[i]%k;
            if(rem == 0)
            {
                if(map.get(rem)%2!= 0) return false;

            }
            else if(map.get(rem)!= map.get(k - rem)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] arr = {9, 5, 7, 3};
        int targetVal = 6;
        System.out.println(ArrayPairSumDivisibilityProblem.canPair(arr, targetVal));
    }
}
