package com.masanta.ratan.daily.practice.design.algorithms.recursion;

public class ReverseString {
    
    public static void main(String[] args){
        String input = "Collection";
        System.out.println(reverse(input));
    }

    public static String reverse(String str)
    {
        if(str.isEmpty()) return str;
        return reverse(str.substring(1)) + str.charAt(0);
    }
}
