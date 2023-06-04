package com.masanta.ratan.leetcode.weeklycontests.june042023;

import java.util.Arrays;

public class CountOfIntegers {

    /*
    2719. Count of Integers

        You are given two numeric strings num1 and num2 and two integers max_sum and min_sum. We denote an integer x to be good if:

        num1 <= x <= num2
        min_sum <= digit_sum(x) <= max_sum.
        Return the number of good integers. Since the answer may be large, return it modulo 109 + 7.

        Note that digit_sum(x) denotes the sum of the digits of x.

     */

        private int MOD = 1000000007;
        public  int digitSum(String x) {
            int sum = 0;
            for(int i=0;i<x.length();i++) sum += (x.charAt(i)-'0');
            return sum;
        }
        public int count(String num1, String num2, int min_sum, int max_sum) {
            int n1 = num1.length();
            int n2 = num2.length();


            int [][][] dp = new int[101][500][2];
            for(int i=0;i<101;i++){
                for(int j=0;j<500;j++){
                    Arrays.fill(dp[i][j],-1);
                }
            }
            int result = solve(num2,n2,min_sum,max_sum,true,0,dp);
            for(int i=0;i<101;i++){
                for(int j=0;j<500;j++){
                    Arrays.fill(dp[i][j],-1);
                }
            }
            result -= solve(num1,n1,min_sum,max_sum,true,0,dp);
            int  v = digitSum(num1);
            if(v<=max_sum && v>=min_sum) result++;
            result %= MOD;

            return (result + MOD) % MOD;
        }


        public int solve(String num, int n, int min, int max, boolean tight,int cursum,int dp[][][]){

            if(n==0){
                if(cursum>=min && cursum<=max) return 1;
                else return 0;
            }

            if(dp[n][cursum][tight==true?1:0]!=-1) return dp[n][cursum][tight==true?1:0];

            int ans=0;
            int end = (tight==true)?(num.charAt(num.length()-n)-'0'):9;
            for(int i=0;i<=end;i++){
                ans += solve(num,n-1,min,max,(tight & (i==end)) ,cursum+i,dp);
                ans %= MOD;
            }

            return dp[n][cursum][tight==true?1:0] = ans;
        }



        public static void main (String[] args){
            CountOfIntegers countOfIntegers = new CountOfIntegers();
            String nums1 =  "1";
            String nums2 ="12";
            int min_sum = 1, max_sum = 8;
            System.out.println(countOfIntegers.count(nums1, nums2, min_sum, max_sum));

        }
}
