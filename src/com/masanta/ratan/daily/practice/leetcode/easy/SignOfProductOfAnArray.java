package com.masanta.ratan.daily.practice.leetcode.easy;

public class SignOfProductOfAnArray {

    /**
     * 1822. Sign of the Product of an Array
     *
     * There is a function signFunc(x) that returns:
     *
     * 1 if x is positive.
     * -1 if x is negative.
     * 0 if x is equal to 0.
     * You are given an integer array nums. Let product be the product of all values in the array nums.
     *
     * Return signFunc(product).
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [-1,-2,-3,-4,3,2,1]
     * Output: 1
     * Explanation: The product of all values in the array is 144, and signFunc(144) = 1
     *
     * @param nums integer array provided
     * @return sign of the product of the array
     */
    public int arraySign(int[] nums) {
        int numberOfNegatives = 0;
        boolean hasZero = false;
        for(int number: nums){
            if(number < 0){
                numberOfNegatives += 1;
            } else if( number == 0){
                hasZero = true;
            }
        }

        if(hasZero){
            return 0;
        } else if(numberOfNegatives % 2 == 0){
            return 1;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        SignOfProductOfAnArray signOfProductOfAnArray = new SignOfProductOfAnArray();
        int[] numArray  = {-1,1,-1,1,-1};
        System.out.println(signOfProductOfAnArray.arraySign(numArray));
    }

}
