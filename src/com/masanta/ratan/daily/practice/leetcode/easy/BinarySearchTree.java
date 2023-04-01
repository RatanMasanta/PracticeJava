package com.masanta.ratan.daily.practice.leetcode.easy;

public class BinarySearchTree {

    /**
     * 704. Binary Search
     *
     * Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to search target in nums. If target exists, then return its index. Otherwise, return -1.
     *
     * You must write an algorithm with O(log n) runtime complexity
     *
     * Input: nums = [-1,0,3,5,9,12], target = 9
     * Output: 4
     * Explanation: 9 exists in nums and its index is 4
     *
     * @param nums number array in ascending order
     * @param target number to be searched
     * @return index of the target in the array
     */
    public int search(int[] nums, int target) {
        // binary search logic is to divide by half and check
        int arrayLength = nums.length, start = 0, end = arrayLength - 1;
        if(arrayLength == 0) return -1;
        while(start <= end){
            int middle = start + (end - start)/ 2;
            int numberAtMiddle = nums[middle];
            if(target == numberAtMiddle){
                return middle;
            } else if(target > numberAtMiddle){
                start = middle + 1;
            } else if (target < numberAtMiddle){
                end = middle - 1;
            }
        }
        return -1;

    }

    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        int[] numsArray = {-1,0,1,2,3,4,6,7,8,9,10,100,123,1234};
        int target = 5;
        int target2 = 123;
        System.out.println(binarySearchTree.search(numsArray, target));
        System.out.println(binarySearchTree.search(numsArray, target2));
    }



}
