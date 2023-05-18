package com.masanta.ratan.daily.practice.design.algorithms.dynamic_programming;

public class BestTimeToBuyAndSellStockWithTransactionFees {

    /**
     * 714. Best Time to Buy and Sell Stock with Transaction Fee
     *
     * You are given an array prices where prices[i] is the price of a given stock on the ith day, and an integer fee representing a transaction fee.
     *
     * Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.
     *
     * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
     *
     *
     *
     * Example 1:
     *
     * Input: prices = [1,3,2,8,4,9], fee = 2
     * Output: 8
     * Explanation: The maximum profit can be achieved by:
     * - Buying at prices[0] = 1
     * - Selling at prices[3] = 8
     * - Buying at prices[4] = 4
     * - Selling at prices[5] = 9
     * The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
     *
     *
     *
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[] free = new int[n], hold = new int[n];

        // In order to hold a stock on day 0, we have no other choice but to buy it for prices[0].
        hold[0] = -prices[0];

        for (int i = 1; i < n; i++) {
            hold[i] = Math.max(hold[i - 1], free[i - 1] - prices[i]);
            free[i] = Math.max(free[i - 1], hold[i - 1] + prices[i] - fee);
        }

        return free[n - 1];
    }

    public int maxProfitSpaceOptmized(int[] prices, int fee) {
        int n = prices.length;
        int free = 0, hold = -prices[0];

        for (int i = 1; i < n; i++) {
            int tmp = hold;
            hold = Math.max(hold, free - prices[i]);
            free = Math.max(free, tmp + prices[i] - fee);
        }

        return free;
    }

    public static void main(String[] args) {
        int[] prices = {1,3,7,5,10,3};
        int fee =3;
        BestTimeToBuyAndSellStockWithTransactionFees buyAndSellStockWithTransactionFees =
                new BestTimeToBuyAndSellStockWithTransactionFees();
        System.out.println(buyAndSellStockWithTransactionFees.maxProfit(prices, fee));
        System.out.println(buyAndSellStockWithTransactionFees.maxProfitSpaceOptmized(prices, fee));
    }

    /*
    Leetcode editorial: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/editorial/?envType=study-plan&id=dynamic-programming-i
     */
}
