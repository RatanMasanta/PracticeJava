package com.masanta.ratan.daily.practice.leetcode.easy;

import java.util.Arrays;

public class CanMakeArithmeticProgressionFromSequence {

    public boolean canMakeArithmeticProgression(int[] arr) {
        Arrays.sort(arr);
        for(int i = 2; i < arr.length; i++){
            if(arr[i] - arr[i-1] != arr[i-1] - arr[i-2]){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        CanMakeArithmeticProgressionFromSequence canMakeArithmeticProgressionFromSequence =
                new CanMakeArithmeticProgressionFromSequence();
        int[] array = {1,3,5,9,7,11,13,15,19,17,23,25,29,27,21};
        System.out.println(canMakeArithmeticProgressionFromSequence.canMakeArithmeticProgression(array));
    }
}
