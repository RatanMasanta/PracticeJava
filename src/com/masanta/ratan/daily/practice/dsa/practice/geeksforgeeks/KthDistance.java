package com.masanta.ratan.daily.practice.dsa.practice.geeksforgeeks;

import java.util.Arrays;
import java.util.HashMap;

public class KthDistance {

    /**
     * Problem Statement:
     *
     * Given an unsorted array arr and a number k which is smaller than size of the array. Find if the array contains duplicates within k distance.
     *
     * Examples:
     *
     * Input: arr[] = [1, 2, 3, 4, 1, 2, 3, 4] and k = 3
     * Output: false
     * Explanation: All duplicates are more than k distance away.
     * Input: arr[] = [1, 2, 3, 1, 4, 5] and k = 3
     * Output: true
     * Explanation: 1 is repeated at distance 3.
     * Constraints:
     * 1 ≤ arr.size() ≤ 106
     * 1 ≤ k < arr.size()
     * 1 ≤ arr[i] ≤ 105
     *
     * Company tags: Amazon
     *
     */

    void main(){
        int arr[] = {15188, 27870, 12674, 11193, 10459, 24978, 3268, 6682, 22664, 28770, 23577, 14611, 21497, 30523, 21829, 14503, 22324, 7649, 11666, 13128, 9206, 11243, 10181, 21497, 8007, 15, 15953, 10160, 27986, 3231, 15819, 7678, 32628, 32084, 25502, 31985, 18579, 27211, 21760, 19514, 2993, 15437, 548, 31627, 30912, 9811, 18865, 18499, 3000, 22601, 28788, 9805, 25064, 20243, 16721, 12357, 31113, 26062, 27008, 25309, 3993, 3160, 25846, 10112, 4071, 18194, 3872, 29237, 19306, 23524, 11223, 30405, 26838, 20610, 31889, 14624, 17265, 7662, 13780, 31997, 11299, 15679, 26865, 22721, 16589, 29088, 13806, 28578, 19932, 29595, 22980, 5900, 28190, 24984, 27851, 27459, 23764, 6560, 25970};
        int k = 83;
        System.out.println("Are there duplicates in "+ Arrays.toString(arr) + " ? " + checkDuplicatesWithinK(arr, k));
    }

    /**
     *
     * Use a hashmap to keep a check of intial index along with element if the same element appears again we sub tract the appeared index with intial index and check if it lies within range of k.
     *
     * Tc: O(n)
     *
     * SC: O(N)
     *
     * @param arr
     * @param k
     * @return
     */
    private static boolean checkDuplicatesWithinK(int[] arr, int k) {
        // your code
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<arr.length;i++){
            if(map.containsKey(arr[i])){
                if(i-map.get(arr[i])<=k) return true;
            }else{
                map.put(arr[i],i);
            }
        }
        return false;
    }
}
