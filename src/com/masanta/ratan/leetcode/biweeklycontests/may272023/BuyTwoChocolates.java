package com.masanta.ratan.leetcode.biweeklycontests.may272023;

import java.util.Arrays;

public class BuyTwoChocolates {

    /**
     *
     * 2706. Buy Two Chocolates
     *
     * You are given an integer array prices representing the prices of various chocolates in a store. You are also given a single integer money, which represents your initial amount of money.
     *
     * You must buy exactly two chocolates in such a way that you still have some non-negative leftover money. You would like to minimize the sum of the prices of the two chocolates you buy.
     *
     * Return the amount of money you will have leftover after buying the two chocolates. If there is no way for you to buy two chocolates without ending up in debt, return money. Note that the leftover must be non-negative.
     *
     * Example 1:
     *
     * Input: prices = [1,2,2], money = 3
     * Output: 0
     * Explanation: Purchase the chocolates priced at 1 and 2 units respectively. You will have 3 - 3 = 0 units of money afterwards. Thus, we return 0.
     * Example 2:
     *
     * Input: prices = [3,2,3], money = 3
     * Output: 3
     * Explanation: You cannot buy 2 chocolates without going in debt, so we return 3.
     *
     * @param prices int array of the chocolate prices
     * @param money amount you have
     * @return money left after buying the chocolates if you can buy two chocolates, else return the money
     */
    public int buyChoco(int[] prices, int money) {
        Arrays.sort(prices);
        int priceOfTwoChocolates = prices[0] + prices[1];
        if(money >= priceOfTwoChocolates){
            return money - priceOfTwoChocolates;
        } else {
            return money;
        }
    }

    public static void main(String[] args) {
        int[]  prices = {1,1,2,2};
        int money = 3;
        BuyTwoChocolates buyTwoChocolates = new BuyTwoChocolates();
        System.out.println(buyTwoChocolates.buyChoco(prices, money));
    }

}
