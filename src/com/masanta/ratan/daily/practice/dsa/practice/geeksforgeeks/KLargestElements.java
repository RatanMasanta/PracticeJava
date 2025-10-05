package com.masanta.ratan.daily.practice.dsa.practice.geeksforgeeks;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 *
 * K Largest Elements
 *
 * Given an array arr[] of positive integers and an integer k, Your task is to return k largest elements in decreasing order.
 *
 * Examples:
 *
 * Input: arr[] = [12, 5, 787, 1, 23], k = 2
 * Output: [787, 23]
 * Explanation: 1st largest element in the array is 787 and second largest is 23.
 * Input: arr[] = [1, 23, 12, 9, 30, 2, 50], k = 3
 * Output: [50, 30, 23]
 * Explanation: Three Largest elements in the array are 50, 30 and 23.
 * Input: arr[] = [12, 23], k = 1
 * Output: [23]
 * Explanation: 1st Largest element in the array is 23.
 * Constraints:
 * 1 ≤ k ≤ arr.size() ≤ 106
 * 1 ≤ arr[i] ≤ 106
 *
 * Company Tags
 * Amazon Microsoft Samsung Walmart Google
 *
 */
public class KLargestElements {

    static void main(String[] args){
        int arr[] = {1, 23, 12, 9, 30, 2, 50};
        int k = 4;
        System.out.println(k + " largest elements of arr: " + Arrays.toString(arr)+"is : " + kLargest(arr, k));
    }

    public static ArrayList<Integer> kLargest(int[] arr, int k) {
        // Your code here
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b.compareTo(a));
        int n = arr.length;
        for(int i = 0; i < n; i++ ){
            pq.add(arr[i]);
        }
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < k; i++){
            list.add(pq.poll());
        }
        return list;
    }
}
