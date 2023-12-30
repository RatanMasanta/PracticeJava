package com.masanta.ratan.daily.practice.dsa.practice.geeksforgeeks;

import java.util.Arrays;
import java.util.HashMap;

public class WinnerOfAnElection {

    /*
            Given an array of n names arr of candidates in an election, where each name is a string of lowercase characters. A candidate name in the array represents a vote casted to the candidate. Print the name of the candidate that received the maximum count of votes. If there is a draw between two candidates, then print lexicographically smaller name.

            Example 1:

            Input:
            n = 13
            arr[] = {john,johnny,jackie,johnny,john
            jackie,jamie,jamie,john,johnny,jamie,
            johnny,john}
            Output: john 4
            Explanation: john has 4 votes casted for
            him, but so does johny. john is
            lexicographically smaller, so we print
            john and the votes he received.
            Example 2:

            Input:
            n = 3
            arr[] = {andy,blake,clark}
            Output: andy 1
            Explanation: All the candidates get 1
            votes each. We print andy as it is
            lexicographically smaller.
            Your Task:
            You only need to complete the function winner() that takes an array of strings arr, and length of arr n as parameters and returns an array of string of length 2. First element of the array should be the name of the candidate and second element should be the number of votes that candidate got in string format.

            Expected Time Complexity: O(n)
            Expected Auxiliary Space: O(n)

            Constraints:
            1 <= n <= 10^5
            1 <= |arr[i]| <= 10^5
    */

    public static String[] winner(String arr[], int n)
    {
        // add your code
        HashMap<String, Integer> hm = new HashMap<>();
        String res[] = new String[2];
        int max = 0;

        for(int i=0; i<n; i++){
            hm.put(arr[i], hm.getOrDefault(arr[i],0)+1);
            max = Math.max (max, hm.get(arr[i]));
        }

        for (String key: hm.keySet()){
            if(hm.get(key)==max) {
                if(res[0] ==null) res [0]=key;
                else{
                    if(res[0].compareTo (key)>0) res[0] = key;
                }
            }
        }
        res[1] = String.valueOf(max);
        return res;
    }

    public static void main(String[] args) {
        String[] votes = {"john","johnny","jackie","johnny","john","jackie","jamie","jamie","john","johnny",
                "jamie","johnny","john"};
        int n = 13;
        System.out.println(Arrays.toString(WinnerOfAnElection.winner(votes, n)));
    }

}
