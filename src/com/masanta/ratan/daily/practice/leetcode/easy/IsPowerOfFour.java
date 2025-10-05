package com.masanta.ratan.daily.practice.leetcode.easy;

public class IsPowerOfFour {

    void main(){
        System.out.println(isPowerOfFour(16));
        System.out.println(isPowerOfFour(512));

    }

    public static boolean isPowerOfFour(int n) {
        double logBaseE = Math.log(n);
        double logBase4 = Math.log(4);
        double rem = logBaseE / logBase4;
        return (rem - (int) rem)  == 0? true: false;
    }


}
