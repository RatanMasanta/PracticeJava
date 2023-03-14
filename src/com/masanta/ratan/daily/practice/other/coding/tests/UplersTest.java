package com.masanta.ratan.daily.practice.other.coding.tests;

import java.util.Arrays;

public class UplersTest {

    public static void main(String[] args){
        int[] array = {1, 3, 6, 4, 1, 2};
        int[] array1 = {-1, -3};
        int[] array2 = {};
        int[] array3 = {1};
        int[] array4 = {-3};
//        System.out.println(solution(array));
//        System.out.println(solution(array1));
//        System.out.println(solution(array2));
//        System.out.println(solution(array3));
//        System.out.println(solution(array4));
        System.out.println(check(null));
    }

    public static int solution(int[] A) {
        // Implement your solution here
        if(A==null) return 1;
        int arrayLength = A.length;
        if(arrayLength == 0) return 1;
        if(arrayLength == 1 && A[0] > 0) return A[0]+1;
        if(arrayLength == 1 && A[0] < 0) return 1;

        boolean areAllNegative = false;
        boolean isNumberMissing = false;
        int missingNumber = 0;
        int[] B = A;
        Arrays.sort(B);
        for(int num = 0; num < arrayLength - 1; num++){
            if(B[num] < 0){
                areAllNegative = true;
            } else {
                areAllNegative = false;
                if(B[num+1] - B[num] > 1){
                    isNumberMissing = true;
                    missingNumber = B[num] + 1;
                    return missingNumber;
                }
            }
        }
        if(areAllNegative) return 1;
        if(!isNumberMissing) return B[arrayLength - 1] + 1;
        return missingNumber;
    }

    static String check(String check){
        if(check == null){
            return "EMPTY";
        }
        else return "Not Null";
    }
    
    
    

}
