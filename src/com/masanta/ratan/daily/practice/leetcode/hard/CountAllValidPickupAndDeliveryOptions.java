package com.masanta.ratan.daily.practice.leetcode.hard;

public class CountAllValidPickupAndDeliveryOptions {

    /*
    https://leetcode.com/problems/count-all-valid-pickup-and-delivery-options/description/?envType=daily-question&envId=2023-09-10

    1359. Count All Valid Pickup and Delivery Options
    Hard

    Given n orders, each order consist in pickup and delivery services.

    Count all valid pickup/delivery possible sequences such that delivery(i) is always after of pickup(i).

    Since the answer may be too large, return it modulo 10^9 + 7.

    Example 1:

    Input: n = 1
    Output: 1
    Explanation: Unique order (P1, D1), Delivery 1 always is after of Pickup 1.
    Example 2:

    Input: n = 2
    Output: 6
    Explanation: All possible orders:
    (P1,P2,D1,D2), (P1,P2,D2,D1), (P1,D1,P2,D2), (P2,P1,D1,D2), (P2,P1,D2,D1) and (P2,D2,P1,D1).
    This is an invalid order (P1,D2,P2,D1) because Pickup 2 is after of Delivery 2.
    Example 3:

    Input: n = 3
    Output: 90


    Constraints:

    1 <= n <= 500
     */

    private final static int MOD = 10000007;

    /**
     *
     * @param n number of orders
     * @return total number of possible permutations
     */
    public static int countOrders(int n){
        /* We have a total of 2n entries in the whole array. n pickups and n deliveries.
            Now, we have to get the order before delivering it, so we have 2n - 1 options to set the nth order
            And the number of deliveries have to be set in n ways.
            So for all possible combinations, we have to check the formula (2*n - 1) * n
            So, with every increasing value, we get a multiple here:
        */
        int  totalCount = 1;
        for( int i = 2; i <=n; i++){
            totalCount = (int) (totalCount  * (2 * i -1) * i) % MOD;
        }
        return totalCount;
    }

    public static void main(String[] args) {
        System.out.println(CountAllValidPickupAndDeliveryOptions.countOrders(3));
        System.out.println(CountAllValidPickupAndDeliveryOptions.countOrders(2));
        System.out.println(CountAllValidPickupAndDeliveryOptions.countOrders(1));
        System.out.println(CountAllValidPickupAndDeliveryOptions.countOrders(5));

    }


}
