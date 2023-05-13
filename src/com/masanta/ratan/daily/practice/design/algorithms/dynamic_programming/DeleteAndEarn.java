package com.masanta.ratan.daily.practice.design.algorithms.dynamic_programming;

public class DeleteAndEarn {

    /**
     *
     * 740. Delete and Earn
     *
     * You are given an integer array nums. You want to maximize the number of points you get by performing the following operation any number of times:
     *
     * Pick any nums[i] and delete it to earn nums[i] points. Afterwards, you must delete every element equal to nums[i] - 1 and every element equal to nums[i] + 1.
     * Return the maximum number of points you can earn by applying the above operation some number of times.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [3,4,2]
     * Output: 6
     * Explanation: You can perform the following operations:
     * - Delete 4 to earn 4 points. Consequently, 3 is also deleted. nums = [2].
     * - Delete 2 to earn 2 points. nums = [].
     * You earn a total of 6 points.
     *
     * ITERATIVE Solution
     *
     * @param nums given integer array
     * @return max number of points we can get by performing any number of operations as specified
     */
    public int deleteAndEarnIterative(int[] nums) {
        int max=0;
        for(int x:nums)
            max = Math.max(max,x);
        int[] a = new int[max+1];

        for(int x:nums)
            a[x]++;

        int prev=a[1], prev2=0;
        for(int i=2;i<a.length;i++){
            int curr = Math.max(a[i]*i + prev2, prev);
            prev2 = prev;
            prev = curr;
        }
        return prev;
    }

    public int deleteAndEarnTabulation(int[] nums) {
        int max=0;
        for(int x:nums)
            max = Math.max(max,x);
        int[] a = new int[max+1];

        for(int x:nums)
            a[x]++;
        int[] dp = new int[max+1];

        dp[1] = a[1];
        for(int i=2;i<a.length;i++){
            dp[i] = Math.max(a[i]*i + dp[i-2], dp[i-1]);
        }
        return dp[a.length-1];
    }

    public int deleteAndEarnMemoization(int[] nums) {
        int max=0;
        for(int x:nums)
            max = Math.max(max,x);
        int[] a = new int[max+1];

        for(int x:nums)
            a[x]++;
        Integer[] dp = new Integer[max+1];
        return solve(a,1,dp);
    }

    private int solve(int[] a,int i,Integer[] dp){
        if(i>=a.length){
            return 0;
        }
        if(dp[i]!=null){
            return dp[i];
        }
        int pick = a[i]*i + solve(a,i+2,dp);
        int notPick = solve(a,i+1,dp);

        return dp[i] = Math.max(pick,notPick);
    }

    public static void main(String[] args) {
        DeleteAndEarn deleteAndEarn = new DeleteAndEarn();
        int[] nums = {2,2,3,3,3,4};
        System.out.println(deleteAndEarn.deleteAndEarnIterative(nums));
        System.out.println(deleteAndEarn.deleteAndEarnMemoization(nums));
        System.out.println(deleteAndEarn.deleteAndEarnTabulation(nums));
    }

}
