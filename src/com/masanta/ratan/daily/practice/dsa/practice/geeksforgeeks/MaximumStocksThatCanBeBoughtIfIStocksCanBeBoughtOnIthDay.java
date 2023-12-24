package com.masanta.ratan.daily.practice.dsa.practice.geeksforgeeks;

import java.util.ArrayList;
import java.util.Collections;

public class MaximumStocksThatCanBeBoughtIfIStocksCanBeBoughtOnIthDay {

    /*
    Problem statement:
    In a stock market, there is a product with its infinite stocks. The stock prices are given for N days, where price[i] denotes the price of the stock on the ith day.
    There is a rule that a customer can buy at most i stock on the ith day.
    If the customer has an amount of k amount of money initially. The task is to find out the maximum number of stocks a customer can buy.

    Example 1:

    Input:
    price = [10,7,19]
    k = 45
    Output:
    4
    Explanation:
    A customer purchases 1 stock on day 1, 2 stocks on day 2 and 1 stock on day 3 for 10, 7 * 2 = 14 and 19 respectively. Hence, total amount is 10 + 14 + 19 = 43 and number of stocks purchased is 4.
    Example 2:

    Input:
    price = [7,10,4]
    k = 100
    Output:
    6
    Explanation:
    Buy on all 3 days.
    Your Task:
    You don't need to read input or print anything. Your task is to complete the function buyMaximumProducts() which takes an array price and an integer k and returns an integer as output.

    Expected Time Complexity: O(NlogN)
    Expected Auxiliary Space: O(N)

    Constraints:
    1 <= N <= 10^4
    1 <= price[i] <= 10^4
    1 <= k <= 10^4

     */

    static class Product {
        int price;
        int quantity;

        public Product(int price, int quantity) {
            this.price = price;
            this.quantity = quantity;
        }
    }

    public static int buyMaximumProducts(int n, int budget, int[] prices) {
        ArrayList<Product> productList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            productList.add(new Product(prices[i], i + 1));
        }

        Collections.sort(productList, (a, b) -> {
            if (a.price - b.price == 0) {
                return b.quantity - a.quantity;
            }
            return a.price - b.price;
        });

        int totalQuantity = 0;

        for (Product product : productList) {
            int cost = product.price;
            int availableQuantity = product.quantity;

            if (cost * availableQuantity <= budget) {
                totalQuantity += availableQuantity;
                budget -= cost * availableQuantity;
            } else {
                totalQuantity += budget / cost;
                budget -= cost * (budget / cost);
            }
        }

        return totalQuantity;
    }


    public static void main(String[] args) {
        int noOfDays = 3;
        int[] stockPrices = {10,7,19};
        int money = 45;
        System.out.println(
                MaximumStocksThatCanBeBoughtIfIStocksCanBeBoughtOnIthDay.buyMaximumProducts(noOfDays, money, stockPrices));
    }

}
