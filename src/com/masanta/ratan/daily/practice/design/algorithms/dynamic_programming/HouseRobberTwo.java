package com.masanta.ratan.daily.practice.design.algorithms.dynamic_programming;

public class HouseRobberTwo {

    /**
     *
     * 213. House Robber II
     *
     * You are a professional robber planning to rob houses along a street.
     * Each house has a certain amount of money stashed. All houses at this place are arranged in a circle.
     * That means the first house is the neighbor of the last one.
     * Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police
     * if two adjacent houses were broken into on the same night.
     *
     * Given an integer array nums representing the amount of money of each house, return the maximum amount of
     * money you can rob tonight without alerting the police.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [2,3,2]
     * Output: 3
     * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent
     * houses.
     *
     * @param nums amount of money in each house
     * @return maximum amount you can rob without alerting the police
     */
    public int rob(int[] nums) {
        int v1prev1 = nums[0], v1prev2 = 0;
        for(int i = 1; i < nums.length-1; i++){ // Considering 1st house and leaving the last one
            int pick = nums[i] + v1prev2;
            int notPick = 0 + v1prev1;
            int cur = Math.max(pick, notPick);
            v1prev2 = v1prev1;
            v1prev1 = cur;
        }

        int v2prev1 = nums[nums.length-1], v2prev2 = 0;
        for(int i = nums.length-2; i >= 1; i--){ // Considering last house and leaving the 1st one
            int pick = nums[i] + v2prev2;
            int notPick = v2prev1;
            int cur = Math.max(pick, notPick);
            v2prev2 = v2prev1;
            v2prev1 = cur;
        }
        return Math.max(v1prev1, v2prev1);
    }

    public static void main(String[] args) {
        HouseRobberTwo houseRobberTwo = new HouseRobberTwo();
        int[] nums = {1,2,3,1};
        System.out.println(houseRobberTwo.rob(nums));


    }
}
