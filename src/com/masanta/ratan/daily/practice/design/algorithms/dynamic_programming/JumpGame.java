package com.masanta.ratan.daily.practice.design.algorithms.dynamic_programming;

import java.util.Arrays;

public class JumpGame {

    /**
     *
     * 55. Jump Game
     *
     * You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.
     *
     * Return true if you can reach the last index, or false otherwise.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [2,3,1,1,4]
     * Output: true
     * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
     *
     * @param nums int array provided
     * @return Return true if you can reach the last index, or false otherwise.
     */
    public boolean canJump(int[] nums) {
        int goal = nums.length-1;
        for(int i = nums.length-2; i >= 0; i--){
            if(nums[i]+ i  >= goal) goal = i;
        }
        return goal == 0;
    }

    /**
     *
     * Approach
     * we will start from index 0 and will try to reach the last index.
     *
     * Base Cases:
     * 1. when we can reach the last index ie n-1 ;
     * 2. if we get 0 in the middle then return false since we can not move forward anymore
     *
     * Inductive Step:
     * if you are at a particular index i try all possible combinations of reaching from position i. that is from i+1 to i+nums[i];
     * At anytime if you get true return the loop no need to proceed ahead.
     * return result.
     *
     * Memoization:
     * we memoize using an array of 0/1(0 for false and 1 for true) which is filled with -1 initially.
     *
     * @param nums num Array passed
     * @return boolean if we can visit the last index or not
     */
    public boolean canJump1(int[] nums) {
        // return recursive(nums,0)==1?true:false;
        int[] memoize = new int[nums.length];
        Arrays.fill(memoize,-1);
        //return recMemoize(nums,0,memoize)==1?true:false;
        /* Recursive solution */
        //return recursive(nums,0)==1?true:false; 

/* Normal backtraversing solution in O(n) time  */
        return backTraversing(nums);
    }


/* O(n) solution using backtraversing */
    public boolean backTraversing(int[] nums)
    {
        int n= nums.length;
        boolean[] result = new boolean[n];
/* mark last array as true since we need to reach there */
            result[n-1]=true;

/* from 2nd last element try to find if you can reach last element similary try this consequent solutions, if they can reach their nearest true with the cost of operations they have for all other index till 0  */
        for(int i=n-2;i>=0;i--)
        {
            for(int j=1;j<=nums[i] && i+j<n;j++)
            {
                if(result[i+j]==true)
                {
                    result[i]=true;
                    break;
                }
            }
        }
        return result[0];
    }

/* **recursive Solution** */
    public int recursive(int[] nums,int i)
    {
/* if you reach the end return true or 1 */
        if(i>=nums.length-1) return 1;

/* if  you get 0 you can not move anymore hence return false but if you get 0 in the end you return true hence the i>=nums.length-1 condition is checked above */
        if(nums[i]==0)
            return 0;
    
        

/* Inductive step */
        int result= 0;

/* trying all possible steps */
        for(int j=1;j<=nums[i];j++)
        {

/* storing or of the result */
                result=result|recursive(nums,i+j);
            if(result==1)
            {
                return result;
            }
        }
        return result;
    }


/* **Memoized Solution** */
    public int recMemoize(int[] nums,int i,int[] memoize)
    {
        if(i>=nums.length-1) return 1;
        if(nums[i]==0)
            return 0;
        if(memoize[i]!=-1) return memoize[i];

        int result= 0;
        for(int j=1;j<=nums[i];j++)
        {

            result=result|recMemoize(nums,i+j,memoize);

/* if you get true at anytime no need of looping other paths */
            if(result==1)
            {
                memoize[i]=result;
                return memoize[i];
            }
        }
        memoize[i]=result;
        return memoize[i];
    }

    public static void main(String[] args) {
        int[] nums = {3,2,1,0,4};
        JumpGame jumpGame = new JumpGame();
        System.out.println(jumpGame.canJump(nums));
        System.out.println(jumpGame.canJump1(nums));
    }

}
