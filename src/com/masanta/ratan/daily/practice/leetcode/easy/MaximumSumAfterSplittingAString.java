package com.masanta.ratan.daily.practice.leetcode.easy;

public class MaximumSumAfterSplittingAString {

    private static int max = Integer.MIN_VALUE;

    void main(){
        String s = "00";
        System.out.println(maxScore(s));
    }

    public static int maxScore(String s) {
        int n = s.length();
        int sum = 0;
        for(int i = 1; i< n; i++){
            sum = count(s, i, '0') + count(s,i,'1');
            if(sum > max) max = sum;
        }
        return max;
    }

    public static int count(String s, int index, char charToSearch){
        int res = 0;
        System.out.println("index: "+ index + " charToSearch: " + charToSearch + " for string: " + s);
        if(charToSearch == '0'){

            // search number of zeros to the left of index
            for(int i =0; i<index; i++){
                if(s.charAt(i) == charToSearch) res++;
            }
        } else if(charToSearch == '1'){
            for(int i =index; i < s.length(); i++){
                if(s.charAt(i) == charToSearch) res++;
            }
        }
        return res;

    }
}
