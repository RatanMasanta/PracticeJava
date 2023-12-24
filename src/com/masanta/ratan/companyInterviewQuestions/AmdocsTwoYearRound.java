package com.masanta.ratan.companyInterviewQuestions;

public class AmdocsTwoYearRound {

    /*
        Check if the year is a leap year or not
     */

    public static void main(String[] args) {
        int year1 = 4, year2 = 100, year3 = 400, year4 = 1936, year5 = 2023, year6 = 1900;
        System.out.println(isLeapYear(year1));
        System.out.println(isLeapYear(year2));
        System.out.println(isLeapYear(year3));
        System.out.println(isLeapYear(year4));
        System.out.println(isLeapYear(year5));
        System.out.println(isLeapYear(year6));
    }

    private static boolean isLeapYear(int year){
        return ((year % 100 == 0 && year % 400 == 0) || (year %100 != 0 && year % 4 == 0));
    }

}
