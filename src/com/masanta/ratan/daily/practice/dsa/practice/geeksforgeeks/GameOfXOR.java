package com.masanta.ratan.daily.practice.dsa.practice.geeksforgeeks;

public class GameOfXOR {

    /*
        Problem statement:
        Fast-Track your resumes to top tech companies and get the job you deserve! Register for hiring challenge exclusively for Freshers

        banner
        Given an array A of size N. The value of an array is denoted by the bit-wise XOR of all elements it contains. Find the bit-wise XOR of the values of all subarrays of A.

        Example 1:

        Input:
        N = 3
        A = [1,2,3]
        Output:
        2
        Explanation:
        xor[1] = 1
        xor[1, 2] = 3
        xor[2, 3] = 1
        xor[1, 2, 3] = 0
        xor[2] = 2
        xor[3] = 3
        Result : 1 ^ 3 ^ 1 ^ 0 ^ 2 ^ 3 = 2
        Example 2:

        Input:
        N = 2
        A = [1,2]
        Output:
        0
        Explanation:
        xor[1] = 1
        xor[1, 2] = 3
        xor[2] = 2
        Result : 1 ^ 3 ^ 2 = 0
        Your Task:
        You don't need to read input or print anything. Your task is to complete the function gameOfXor() which takes an integer N and array A[] as input parameters and returns the answer.

        Expected Time Complexity: O(N)
        Expected Auxiliary Space: O(1)

        Constraints:
        1 <= N <= 105
        0 <= A[i] <= 109

     */

    public static int gameOfXor(int N , int[] A) {
        // code here
        if(N%2==0)return 0;
        int ans=A[0];
        for(int i=1;i<N;i++){
            if(i%2==0){
                ans=ans^A[i];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int N = 3, N1 = 4;
        int[] arr1 = {1,2,3}, arr2 = {1,2,3,4};
        System.out.println(GameOfXOR.gameOfXor(N, arr2));
        System.out.println(GameOfXOR.gameOfXor(N1, arr2));
    }


}