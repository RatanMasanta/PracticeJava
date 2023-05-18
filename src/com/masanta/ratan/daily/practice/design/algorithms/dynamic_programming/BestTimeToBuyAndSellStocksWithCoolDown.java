package com.masanta.ratan.daily.practice.design.algorithms.dynamic_programming;

import java.util.Arrays;

public class BestTimeToBuyAndSellStocksWithCoolDown {

    public int fun(int i,int[] prices,int canBuy,int[][] dp)
    {
        if(i>=prices.length)  return 0;

        if(dp[i][canBuy]!=-1)  return dp[i][canBuy]; //for memoization

        int profit=Integer.MIN_VALUE;
        if(canBuy==1)
        {
            profit=Math.max(profit,-prices[i]+fun(i+1,prices,0,dp)); //case where buying the stock at that day
            profit=Math.max(profit,0+fun(i+1,prices,1,dp));//made a choice of not buying on the current day
        }
        else
        {
            profit=Math.max(profit,prices[i]+fun(i+2,prices,1,dp)); // lets sell the stock on this day
            profit=Math.max(profit,0+fun(i+1,prices,0,dp)); // hold the stock to sell it an another day
        }
        return dp[i][canBuy]=profit; // returning and memoizing the ans
    }

    /**
     * 309. Best Time to Buy and Sell Stock with Cooldown
     * You are given an array prices where prices[i] is the price of a given stock on the ith day.
     *
     * Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:
     *
     * After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
     * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
     *
     * @param prices Stock price array
     * @return max Profit that you can earn
     */
    public int maxProfit(int[] prices)
    {
        int[][] dp=new int[prices.length][2];
        for(int[] i : dp)  Arrays.fill(i,-1);
        return fun(0,prices,1,dp);
    }

    public static void main(String[] args) {
        BestTimeToBuyAndSellStocksWithCoolDown bestTimeToBuyAndSellStocksWithCoolDown =
                new BestTimeToBuyAndSellStocksWithCoolDown();
        int[] stockPrices = {1,2,3,0,2};
        System.out.println(bestTimeToBuyAndSellStocksWithCoolDown.maxProfit(stockPrices));
    }

    /*
     * Leetcode solution: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/solutions/2702355/simple-java-solution-recursion-dynamic-programming-top-down-approach/
     */
}