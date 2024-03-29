package com.masanta.ratan.daily.practice.leetcode.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MinimumCostForTickets {

    /**
     * 983. Minimum Cost For Tickets
     *You have planned some train traveling one year in advance. The days of the year in which you will travel are given as an integer array days. Each day is an integer from 1 to 365.
     *
     * Train tickets are sold in three different ways:
     *
     * a 1-day pass is sold for costs[0] dollars,
     * a 7-day pass is sold for costs[1] dollars, and
     * a 30-day pass is sold for costs[2] dollars.
     * The passes allow that many days of consecutive travel.
     *
     * For example, if we get a 7-day pass on day 2, then we can travel for 7 days: 2, 3, 4, 5, 6, 7, and 8.
     * Return the minimum number of dollars you need to travel every day in the given list of days.
     *
     * Input: days = [1,4,6,7,8,20], costs = [2,7,15]
     * Output: 11
     * Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
     * On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
     * On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
     * On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
     * In total, you spent $11 and covered all the days of your travel.
     *
     * @param days travel days in the year
     * @param costs array of costs for daily, weekly and monthly
     * @return minimum number of dollars to travel every day in the given dalys of the list
     */
    public int mincostTickets(int[] days, int[] costs) {
        int n = days.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + costs[0]; // 1-day pass for current day

            int j = i - 1;
            while (j >= 0 && days[i - 1] - days[j] < 7) j--;
            dp[i] = Math.min(dp[i], dp[j + 1] + costs[1]); // 7-day pass for current day

            j = i - 1;
            while (j >= 0 && days[i - 1] - days[j] < 30) j--;
            dp[i] = Math.min(dp[i], dp[j + 1] + costs[2]); // 30-day pass for current day
        }

        return dp[n];
    }

    /**
     *
     *
     * This method does the change in O(n);
     * We use a DP Equation usig Bottom Up approach to check for the Min value to be used.
     * travelCost[i] = Math.min((travelCost[i-1] + costs[0]),
     * Math.min((travelCost[i-7] + costs[1]), (travelCost[i-30]+ cost[2])))
     *
     * @param days travel days in the year
     * @param costs array of costs for daily, weekly and monthly
     * @return minimum number of dollars to travel every day in the given dalys of the list
     */
    public int mincostTicketsPart2(int[] days, int[] costs) {
        int[] travelCost = new int[days[days.length-1]+1];

        // Set for identifying travel dates
        Set<Integer> set = new HashSet<>();
        for(int day: days){
            set.add(day);
        }

        for(int i=1;i<travelCost.length;i++){
            if(!set.contains(i)) {
                // Non travel day simply copy the value from previous day
                travelCost[i] =travelCost[i-1];
            } else{
                int priceUsingDailyPass = travelCost[i-1]+ costs[0];

                int priceUsingWeeklyPass = costs[1];
                if(i>=7){
                    priceUsingWeeklyPass = travelCost[i-7]+ costs[1];
                }


                int priceUsingMonthlyPass = costs[2];
                if(i>=30){
                    priceUsingMonthlyPass = travelCost[i-30]+ costs[2];
                }
                // comparison between the possible 3 values
                travelCost[i]= Math.min(priceUsingMonthlyPass, Math.min(priceUsingDailyPass,priceUsingWeeklyPass));
            }
        }

        return travelCost[travelCost.length-1];
    }


    public static void main(String[] args) {
        MinimumCostForTickets minimumCostForTickets = new MinimumCostForTickets();
        int[] days = {1,2,3,4,5,6,7,8,9,10,30,31};
        int[] costs = {2,7,15};
        System.out.println(minimumCostForTickets.mincostTickets(days, costs));
        System.out.println(minimumCostForTickets.mincostTicketsPart2(days, costs));
    }

    /*
    https://leetcode.com/problems/minimum-cost-for-tickets/solutions/3349729/java-python-and-c-easy-solutions-with-exaplanation-look-at-once/
     */

}
