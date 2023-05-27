package com.masanta.ratan.daily.practice.design.algorithms.dynamic_programming;

import java.util.Arrays;

public class HouseRobber {

    /**
     *
     * 198. House Robber
     *
     * You are a professional robber planning to rob houses along a street.
     * Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them
     * is that adjacent houses have security systems connected and it will automatically contact the police if two
     * adjacent houses were broken into on the same night.
     *
     * Given an integer array nums representing the amount of money of each house, return the maximum amount of
     * money you can rob tonight without alerting the police.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [1,2,3,1]
     * Output: 4
     * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
     * Total amount you can rob = 1 + 3 = 4.
     *
     *
     * @param num amount of money in each house
     * @return maximum amount you can rob without alerting the police
     */
    public static int rob(int[] num) {
        int rob = 0; //max monney can get if rob current house
        int notrob = 0; //max money can get if not rob current house
        for(int i=0; i<num.length; i++) {
            int currob = notrob + num[i]; //if rob current value, previous house must not be robbed
            notrob = Math.max(notrob, rob); //if not rob ith house, take the max value of robbed (i-1)th house and not rob (i-1)th house
            rob = currob;
        }
        return Math.max(rob, notrob);
    }

    // recursion

    /**
     *
     * @param nums number array
     * @param i start index
     * @param dp dp array
     * @return max amount that can be robbed
     */
    private int solveMemo(int nums[],int i,int dp[]){

        if(i>=nums.length) return 0;

        if(dp[i]!=-1) return dp[i];

        int a=nums[i]+solveMemo(nums,i+2,dp);
        int b=solveMemo(nums,i+1,dp);

        return dp[i]=Math.max(a,b);


    }

    // tabular
    private int solveTab(int nums[]){

        int len=nums.length;
        int dp[]=new int[len+2];

        for(int i=len-1;i>=0;i--){
            int a=nums[i]+dp[i+2];
            int b=dp[i+1];
            dp[i]=Math.max(a,b);
        }
        return dp[0];
    }

    // SC = (1)
    private int solve1(int nums[]){

        int len=nums.length;

        int prev=0;
        int curr=0;

        for(int i=len-1;i>=0;i--){

            int a=nums[i]+prev;
            int b=curr;
            prev=curr;
            curr=Math.max(a,b);

        }

        return curr;
    }

    public static void main(String[] args) {
        HouseRobber houseRobber = new HouseRobber();
        int[] nums = {2,7,9,3,1};
        System.out.println(houseRobber.rob(nums));
        System.out.println(houseRobber.solve1(nums));
        System.out.println(houseRobber.solveTab(nums));
        int[] dp = new int[nums.length + 1];
        Arrays.fill(dp, -1);
        System.out.println(houseRobber.solveMemo(nums, 0, dp));
    }
}
