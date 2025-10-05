package com.masanta.ratan.daily.practice.dsa.practice.geeksforgeeks;

import java.util.ArrayList;
import java.util.Arrays;

public class ModifyTheArray {

    /*
        Problem statement:

        Given an array arr. Return the modified array in such a way that if the current and next numbers are valid numbers and are equal then double the current number value and replace the next number with 0. After the modification, rearrange the array such that all 0's are shifted to the end.

        Note:

        Assume ‘0’ as the invalid number and all others as a valid number.
        The sequence of the valid numbers is present in the same order.
        Example:

        Input: arr[] = [2, 2, 0, 4, 0, 8]
        Output: [4, 4, 8, 0, 0, 0]
        Explanation: At index 0 and 1 both the elements are the same. So, we will change the element at index 0 to 4 and the element at index 1 is 0 then we will shift all the zeros to the end of the array. So, the array will become [4, 4, 8, 0, 0, 0].
        Input: arr[] = [0, 2, 2, 2, 0, 6, 6, 0, 0, 8]
        Output: [4, 2, 12, 8, 0, 0, 0, 0, 0, 0]
        Explanation: At index 5 and 6 both the elements are the same. So, we will change the element at index 5 to 12 and the element at index 6 is 0. We will change the element at index 1 to 4 and the element at index 2 is 0. Then we shift all the zeros to the end of the array. So, array will become [4, 2, 12, 8, 0, 0, 0, 0, 0, 0].
        Expected Time Complexity: O(n)
        Expected Auxiliary Space: O(n)

        Constraints:
        1 ≤ arr.size() ≤ 105
        1 ≤ arr[i] ≤ 106


        Company tags: Zoho, Microsoft
     */


    public static void main(String[] args) {
        int arr1[] = new int[] {0, 2, 2, 2, 0, 6, 6, 0, 0, 8};
        int arr2[] = new int[] {2, 2, 0, 4, 0, 8};
        System.out.println("Modified and rearranged array for array 1 " + Arrays.toString(arr1) + " is " + modifyAndRearrangeArr(arr1));
        System.out.println("Modified and rearranged array for array 2 " + Arrays.toString(arr2) + "is " + modifyAndRearrangeArr(arr2));

    }

    /**
     * We check if the current and the next value is same and not 0, if yes, we add value to our list.
     * If we encounter a zero, we add the zerocounter which will add the zeros at the end.
     *
     * @param arr array to be modified and rearranged
     * @return the modified and rearranged array
     */
    private static ArrayList<Integer> modifyAndRearrangeArr(int arr[]) {
        int zeroCount = 0;
        ArrayList<Integer> res = new ArrayList<>();
        for(int i = 0; i < arr.length - 1; i++){
            if(arr[i] == arr[i+1] && arr[i] != 0){
                arr[i] = 2 * arr[i];
                res.add(arr[i]);
                arr[i+1] = 0;
            } else if(arr[i] != 0) {
                res.add(arr[i]);
            } else if (arr[i] == 0) {
                zeroCount++;
            }
        }
        if(arr[arr.length - 1] != 0){
            res.add(arr[arr.length - 1]);
        } else {
            zeroCount++;
        }
        for(int i = 0; i< zeroCount; i++) res.add(0);
        return res;
    }

}
