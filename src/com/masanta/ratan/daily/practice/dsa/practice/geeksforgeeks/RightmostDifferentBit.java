package com.masanta.ratan.daily.practice.dsa.practice.geeksforgeeks;

public class RightmostDifferentBit {

    /*
        Problem statement:

        Given two numbers M and N. The task is to find the position of the rightmost different bit in the binary representation of numbers. If both M and N are the same then return -1 in this case.

        Example 1:

        Input:
        M = 11, N = 9
        Output:
        2
        Explanation:
        Binary representation of the given numbers are: 1011 and 1001, 2nd bit from right is different.
        Example 2:

        Input:
        M = 52, N = 4
        Output:
        5
        Explanation:
        Binary representation of the given numbers are: 110100 and 0100, 5th-bit from right is different.
        User Task:
        The task is to complete the function posOfRightMostDiffBit() which takes two arguments M and N and returns the position of first different bits in M and N from right. If both m and n are the same then return -1 in this case.

        Expected Time Complexity: O(max(log M, log N)).
        Expected Auxiliary Space: O(1).

        Constraints:
        0 <= M, N <= 10^9

     */

    public static int posOfRightMostDiffBit(int m, int n)
    {

        // Your code here
        int t=m^n;
        if(t==0)return -1;
        int cnt=1;
        while(t%2==0 && t>0)
        {
            cnt++;
            t=t>>1;
        }
        return cnt;
    }

    public static void main(String[] args) {
        int num1 = 52, num2 = 4;
        System.out.println(RightmostDifferentBit.posOfRightMostDiffBit(num1, num2));
    }

}
