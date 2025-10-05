package com.masanta.ratan.daily.practice.leetcode.easy;

public class Largest3SameDigitNumberInString {
    void main(){
        System.out.println(largestGoodInteger("222"));
    }

    public static String largestGoodInteger(String num) {
        String maxResult = "";
        int n = num.length(), max  = -1;
        for(int i = 0; i <= n - 3; i++){
            if(num.charAt(i) == num.charAt(i+1) && num.charAt(i+1) == num.charAt(i+2)){
                int temp = Integer.valueOf(num.substring(i, i+3));
                if( temp > max ){
                    // System.out.println("Temp value is: " + temp);
                    max = temp;
                    maxResult = num.substring(i, i+3);
                }
            }
        }
        return maxResult;
    }
}
