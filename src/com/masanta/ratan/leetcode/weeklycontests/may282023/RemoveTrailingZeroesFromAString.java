package com.masanta.ratan.leetcode.weeklycontests.may282023;

import java.math.BigInteger;

public class RemoveTrailingZeroesFromAString {

    /*
        2710. Remove Trailing Zeros From a String
        Given a positive integer num represented as a string, return the integer num without trailing zeros as a string.
        Example 1:

        Input: num = "51230100"
        Output: "512301"
        Explanation: Integer "51230100" has 2 trailing zeros, we remove them and return integer "512301".
        Example 2:

        Input: num = "123"
        Output: "123"
        Explanation: Integer "123" has no trailing zeros, we return integer "123".
     */

    public String removeTrailingZeros(String num) {
        BigInteger n = new BigInteger(num);
        while(n.mod(BigInteger.TEN).equals(BigInteger.ZERO))
            n = n.divide(BigInteger.TEN);
        return n.toString();
    }

    public static void main(String[] args) {
        String num = "124597832145600000000";
        RemoveTrailingZeroesFromAString removeTrailingZeroesFromAString =
                new RemoveTrailingZeroesFromAString();
        System.out.println(removeTrailingZeroesFromAString.removeTrailingZeros(num));
    }

}
