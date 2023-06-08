package com.masanta.ratan.daily.practice.leetcode.easy;

public class CountNegativeNumbersInASortedMatrix {

    /*
        1351. Count Negative Numbers in a Sorted Matrix
        Easy
        Companies
        Given a m x n matrix grid which is sorted in non-increasing order both row-wise and column-wise, return the number of negative numbers in grid.



        Example 1:

        Input: grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
        Output: 8
        Explanation: There are 8 negatives number in the matrix.
     */

    //Brute Force
     public int countNegativesBruteForce(int[][] grid) {
         int negCount = 0;
         for(int i = grid.length - 1; i >= 0; i-- ){
             for(int j = grid[0].length - 1; j >= 0; j--){
                 if(grid[i][j] >= 0){
                     break;
                 } else {
                     negCount++;
                 }
             }
         }
         return negCount;
     }

// Binary Search
     public int countNegativesBinarySearch(int[][] grid) {
         int count = 0;
         int n = grid[0].length;
         // Iterate on all rows of the matrix one by one.
         for (int[] row : grid) {
             // Using binary search find the index
             // which has the first negative element.
             int left = 0, right = n - 1;
             while (left <= right) {
                 int mid = (right + left) / 2;
                 if (row[mid] < 0) {
                     right = mid - 1;
                 } else {
                     left = mid + 1;
                 }
             }
             // 'left' points to the first negative element,
             // which means 'n - left' is the number of all negative elements.
             count += (n - left);
         }
         return count;
     }

    // Linear Traversal
    public int countNegativesLinearTraversal(int[][] grid) {
        int count = 0;
        int n = grid[0].length;
        int currRowNegativeIndex = n - 1;

        // Iterate on all rows of the matrix one by one.
        for (int[] row : grid) {
            // Decrease 'currRowNegativeIndex' so that it points to current row's last positive element.
            while (currRowNegativeIndex >= 0 && row[currRowNegativeIndex] < 0) {
                currRowNegativeIndex--;
            }
            // 'currRowNegativeIndex' points to the last positive element,
            // which means 'n - (currRowNegativeIndex + 1)' is the number of all negative elements.
            count += (n - (currRowNegativeIndex + 1));
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] matrix = {{3,2},{1,0}};
        int[][] matrix2 = {{4,3,2,-1},{3,2,1,-1},{1,1,-1,-2},{-1,-1,-2,-3}};
        CountNegativeNumbersInASortedMatrix countNegativeNumbersInASortedMatrix =
                new CountNegativeNumbersInASortedMatrix();
        System.out.println(countNegativeNumbersInASortedMatrix.countNegativesBruteForce(matrix));
        System.out.println(countNegativeNumbersInASortedMatrix.countNegativesBinarySearch(matrix));
        System.out.println(countNegativeNumbersInASortedMatrix.countNegativesLinearTraversal(matrix));
        System.out.println(countNegativeNumbersInASortedMatrix.countNegativesBruteForce(matrix2));
        System.out.println(countNegativeNumbersInASortedMatrix.countNegativesBinarySearch(matrix2));
        System.out.println(countNegativeNumbersInASortedMatrix.countNegativesLinearTraversal(matrix2));

    }
    /*
    https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix/editorial/
     */


}
