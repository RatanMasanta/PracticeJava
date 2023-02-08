package com.masanta.ratan.daily.practice.design.algorithms.recursion;

public class IsPalindrome {

    public static void main(String[] args) {
        String actualString = "racecar";
        System.out.println("Check if this string " + actualString + " is a palindrome: " + isPalindrome(actualString));
    }

    public static boolean isPalindrome(String str) {
        String actualString = str;
        String reverseString = reverse(str);
        System.out.println("Actual string : " + actualString + " Reverse String: " + reverseString);
        return reverseString.equals(actualString);
    }

    public static String reverse(String str) {
        if(str.isEmpty()) return str;
        return reverse(str.substring(1)) + str.charAt(0);
    }

}
