package com.masanta.ratan.companyInterviewQuestions;

import java.util.Arrays;

public class AirtelTest {


    public static void main(String[] args) {
        int nums[] = {0, 1, 0, 2, 0, 3, 0, 0, 4, 0};
        /*System.out.println(Arrays.toString(moveZeroesToEnd(nums)));
        System.out.println(Arrays.toString(moveZeroesToEnd2(nums)));
*/

        int arr[][] = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};

        System.out.println(isElementPresent(arr, 5));
        System.out.println(isElementPresent(arr,27));
    }

    //[[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]]

    /**
     *     array having repeating integers having zeroes and non zero numbers and push them to the end of the array
     *
     *     Example:
     *
     *     0 1 0 2 0 3 0 0 4 0
     *      1 2 3 4 0 0 0 0 0 0
     */

    /*
        We have a 2D array
        It's sorted left to right
        It's sorted top to bottom

        1 2 3 4
        2 3 4 5
        3 4 5 6
        4 5 6 7

        Write a method to return true if the element mentioned is present in the 2D array

     */


    /**
     *
     * @param arr
     * @return
     */
    public static boolean isElementPresent(int[][] arr, int target){
        int rows = arr.length;
        for(int i = 0; i < rows; i++){
            // Find target in each row through binary search
            if(binarySearch(arr[i], target)) return true;
        }
        return false;
    }

    private static boolean binarySearch(int[] nums, int target){
        int size = nums.length;
        int start = 0, end  = size - 1;
        if(nums[start] == target || nums[end] == target){return true;}
        while(start <= end){
            int mid = (end - start)/2 + start;
            if(nums [mid] == target) {
                return true;
            } else if(nums[mid] < target){
                start = mid + 1;
            } else {
               end = mid - 1;
            }
        }
        return false;
    }





    /**
     *
     *
     *
     * @param nums
     * @return
     */
    public static int[] moveZeroesToEnd(int[] nums){
        if(nums  == null || nums.length == 0){
            return nums;
        }
        int n = nums.length;
        int res[] = new int[n];
        int lastNonZeroIndex = 0;
        for(int i = 0; i < n; i++){
            if(nums[i] != 0){
                res[lastNonZeroIndex++] = nums[i];
            }
        }
        while(lastNonZeroIndex != -1 && lastNonZeroIndex < n){
            res[lastNonZeroIndex++] = 0;
        }
        return res;
    }


    public static int[] moveZeroesToEnd2(int[] nums){
        if(nums == null || nums.length == 0){ return nums;}
        int n = nums.length;
        int lastNonZeroIndex = 0;
        for(int i = 0 ; i < n; i++){
            if(nums[i] != 0){
                nums[lastNonZeroIndex++] = nums[i];
                nums[i] = 0;
            }
        }
        return nums;
    }



}
