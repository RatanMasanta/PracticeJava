package com.masanta.ratan.daily.practice.leetcode.medium;

public class MinimumFlipsRequiredToMakeAORBEqualsToC {

     public int minFlips(int a, int b, int c) {
         int answer = 0;
         while (a != 0 | b != 0 | c != 0) {
             if ((c & 1) == 1) {
                 if ((a & 1) == 0 && (b & 1) == 0) {
                     answer++;
                 }
             } else {
                 answer += (a & 1) + (b & 1);
             }

             a >>= 1;
             b >>= 1;
             c >>= 1;
         }

         return answer;
     }

    public int minFlips2(int a, int b, int c) {
        return Integer.bitCount(c ^= (a | b)) + Integer.bitCount(a & b & c);
    }


    public static void main(String[] args) {
        MinimumFlipsRequiredToMakeAORBEqualsToC minFlips = new
                MinimumFlipsRequiredToMakeAORBEqualsToC();
        int a = 2, b = 6, c = 5;
        System.out.println(minFlips.minFlips(a,b,c));
        System.out.println(minFlips.minFlips2(a,b,c));
    }
}
