package com.masanta.ratan.daily.practice.leetcode.easy;

import java.util.*;
import java.util.stream.Stream;

public class FindTheDifferenceBetweenTwoArrays {

    List<Integer> getElementsOnlyInFirstList(int[] nums1, int[] nums2) {
        Set<Integer> onlyInNums1 = new HashSet<>();

        // Store nums2 elements in an unordered set.
        Set<Integer> existsInNums2 = new HashSet<> ();
        for (int num : nums2) {
            existsInNums2.add(num);
        }

        // Iterate over each element in the list nums1.
        for (int num : nums1) {
            if (!existsInNums2.contains(num)) {
                onlyInNums1.add(num);
            }
        }

        // Convert to vector.
        return new ArrayList<>(onlyInNums1);
    }

    /**
     * 2215. Find the Difference of Two Arrays
     * Given two 0-indexed integer arrays nums1 and nums2, return a list answer of size 2 where:
     *
     * answer[0] is a list of all distinct integers in nums1 which are not present in nums2.
     * answer[1] is a list of all distinct integers in nums2 which are not present in nums1.
     * Note that the integers in the lists may be returned in any order.
     *
     * Approach 2: HashSet
     * Intuition
     *
     * Instead of iterating over each element in the second array to check if it exists in the list or not, we can store the elements in a HashSet. Then we can find if an element exists in the list or not in
     * O(1) time compared to O(N) time in the previous approach.
     *
     * In this approach, we follow the above intuition. To find the elements that only exist in nums1, we first store the elements in nums2 in the HashSet. Then we iterate over each element in the list nums1, and for each element, we check if it's there in the HashSet; if yes, we skip the element; otherwise, we store it in the list onlyInNums1.
     *
     * Algorithm
     *
     * Define method getElementsOnlyInFirstList. This method accepts two lists of integers and returns the elements that are present only in the first argument:
     *
     * Iterate over the elements in the second argument nums2 and store each in the Hashset existsInNums2.
     * Iterate over the elements in the first argument nums1 and for each element check if it's present in the set existsInNums2. If yes, skip to the next element; otherwise, store it in the set onlyInNums1.
     * Call the method getElementsOnlyInFirstList once for the param (nums1, nums2) and then again for (nums2, nums1).
     *
     * Implementation
     *
     * @param nums1 Integer array 1
     * @param nums2 Integer array 2
     * @return Array of 2 wit hList of values in them missing from the other one
     */
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        return Arrays.asList(getElementsOnlyInFirstList(nums1, nums2), getElementsOnlyInFirstList(nums2, nums1));
    }

    public static void main(String[] args) {
        FindTheDifferenceBetweenTwoArrays findTheDifferenceBetweenTwoArrays = new FindTheDifferenceBetweenTwoArrays();
        int[] num1 = {1,2,3,3};
        int[] num2 = {1,1,2,2};
        Stream.of(findTheDifferenceBetweenTwoArrays.findDifference(num1, num2)).forEach(x -> System.out.println(Arrays.toString(x.toArray())));
    }
}
