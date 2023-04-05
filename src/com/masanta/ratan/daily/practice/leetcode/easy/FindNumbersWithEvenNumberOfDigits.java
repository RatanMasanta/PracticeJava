package com.masanta.ratan.daily.practice.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

public class FindNumbersWithEvenNumberOfDigits {

    /**
     * 1295. Find Numbers with Even Number of Digits
     *
     * Given an array nums of integers, return how many of them contain an even number of digits.
     *
     * Input: nums = [12,345,2,6,7896]
     * Output: 2
     * Explanation:
     * 12 contains 2 digits (even number of digits).
     * 345 contains 3 digits (odd number of digits).
     * 2 contains 1 digit (odd number of digits).
     * 6 contains 1 digit (odd number of digits).
     * 7896 contains 4 digits (even number of digits).
     * Therefore only 12 and 7896 contain an even number of digits.
     *
     * @param nums integer array of numbers
     * @return number of numbers with even digits
     */
    public int findNumbersUsingLog(int[] nums) {
        int count = 0;
        for(int num : nums){
            if( (int) (Math.log10(num) + 1) % 2 == 0){
                count++;
            }
        }
        return count;
    }

    /**
     *
     * @param nums integer array of numbers
     * @return number of numbers with even digits
     */
    public int findNumbersUsingstring(int[] nums) {
        List<String> list = new ArrayList<>();
        for (int num : nums) {
            if (String.valueOf(num).length() % 2 == 0) {
                list.add(String.valueOf(num));
            }
        }
        return list.size();
    }
}
