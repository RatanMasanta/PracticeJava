package com.masanta.ratan.companyInterviewQuestions;

public class VolkswagenInterview {

    /*
    Problem Statement:  Swap two strings without a third variable
     */

    public static void main(String[] args) {
        String s1 = "Ratan";
        String s2 = "Masanta";
        int len1 = s1.length();
        s1 = s1 + s2;
        s2 = s1.substring(0, len1);
        System.out.println(s1);
        s1 = s1.substring(len1);
        System.out.println("After swapping the values are: s1 = " + s1 + " and s2 = " + s2);


    }

}
