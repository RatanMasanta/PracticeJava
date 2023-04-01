package com.masanta.ratan.daily.practice.leetcode.hard;

public class MedianOfTwoSortedArrays {


    /**
     * 4. Median of Two Sorted Arrays
     *
     * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
     *
     * The overall run time complexity should be O(log (m+n)).
     * Input: nums1 = [1,3], nums2 = [2]
     * Output: 2.00000
     * Explanation: merged array = [1,2,3] and median is 2.
     *
     * @param nums1 sorted array 1
     * @param nums2 sorted array 2
     * @return median of two sorted arrays
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        int n = n1 + n2;
        int[] new_arr = new int[n];

        int i=0, j=0, k=0;

        while (i<=n1 && j<=n2) {
            if (i == n1) {
                while(j<n2) new_arr[k++] = nums2[j++];
                break;
            } else if (j == n2) {
                while (i<n1) new_arr[k++] = nums1[i++];
                break;
            }

            if (nums1[i] < nums2[j]) {
                new_arr[k++] = nums1[i++];
            } else {
                new_arr[k++] = nums2[j++];
            }
        }

        if (n%2==0) return (float)(new_arr[n/2-1] + new_arr[n/2])/2;
        else return new_arr[n/2];
    }

    public static void main(String[] args) {
        int[] sortedArrayOne = {0,1,3,5,7,9,11,13,15,17};
        int[] sortedArrayTwo = {0,1,2,3,4,5,6,7,8,9,10,11,120};
        int[] sortedArrayThree ={1,2};
        int[] sortedArrayFour = {3,4};

        MedianOfTwoSortedArrays medianOfTwoSortedArrays = new MedianOfTwoSortedArrays();
        System.out.println(medianOfTwoSortedArrays.findMedianSortedArrays(sortedArrayOne, sortedArrayTwo));
        System.out.println(
                medianOfTwoSortedArrays.findMedianSortedArrays(sortedArrayThree, sortedArrayFour)
        );

    }

    /*
        Leetcode solution: https://leetcode.com/problems/median-of-two-sorted-arrays/solutions/3283266/best-java-solution-beats-100/
        Time complexity: O(m+n)O(m+n)O(m+n)

            Idea
            We create a new array with length that of the sum of the array lengths
            We initialize i & j = 0. [i for nums1 & j for nums2]
            Since the given arrays are already sorted it is easy to compare their elements. We comapre by observing nums1[i] < nums2[j]
            if the element in nums1nums1nums1 at ithi^{th}i
            th
              is less than that of element at jthj^{th}j
            th
              index of nums2nums2nums2, we add nums1[i]nums1[i]nums1[i] to new array and increment i; so as to compare the next element of the array to nums2[j].
            If the opposite case arises, we add nums2[j]nums2[j]nums2[j] to the new array as you can guess. And increment j by 1 for the same reasons we did it with i.
            Depending on the length of new array, we calculate median.
            If the length of array is even, median by rule is the average of the 2 middle elements of the array
            If it is off, it is the middlemost element
     */

}
