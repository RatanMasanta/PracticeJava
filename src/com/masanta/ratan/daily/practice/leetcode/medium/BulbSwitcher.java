package com.masanta.ratan.daily.practice.leetcode.medium;

public class BulbSwitcher {

    /**
     * 319. Bulb Switcher
     * There are n bulbs that are initially off. You first turn on all the bulbs, then you turn off every second bulb.
     *
     * On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on). For the ith round, you toggle every i bulb. For the nth round, you only toggle the last bulb.
     *
     * Return the number of bulbs that are on after n rounds.
     *
     * @param n Number of bulbs
     * @return number of bulls on after n rounds
     */
    public int bulbSwitcher(int n){
        /* Math Solution
        Reasoning:
        The solution is based on the observation that a bulb will be toggled an even number of times (including zero) if it has an even number of divisors, and an odd number of times if it has an odd number of divisors. A bulb will have an odd number of divisors if and only if it is a perfect square, because each divisor less than the square root of the number corresponds to a divisor greater than the square root of the number. Therefore, the number of bulbs that are on after n rounds is equal to the number of perfect squares less than or equal to n, which is equal to the floor of the square root of n.
        Implementation:

         return (int) Math.sqrt(n);
        */
        long low=0;
        long high=n;
        while(low<=high){
            long mid=low+(high-low)/2;
            if((mid*mid)==n)
                return (int)mid;
            else if((mid*mid)<n)
                low=mid+1;
            else
                high=mid-1;
        }
        return (int)low-1;
    }

    public static void main(String[] args) {
        int numberOfBulbs = 36;
        BulbSwitcher bulbSwitcher = new BulbSwitcher();
        System.out.println(bulbSwitcher.bulbSwitcher(numberOfBulbs));
    }

}
