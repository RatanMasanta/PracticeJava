package com.masanta.ratan.daily.practice.leetcode.medium;

import java.util.Arrays;

public class BoatsToSavePeople {

    /**
     * 881. Boats to Save People
     *
     * You are given an array people where people[i] is the weight of the ith person, and an infinite number of boats where each boat can carry a maximum weight of limit. Each boat carries at most two people at the same time, provided the sum of the weight of those people is at most limit.
     *
     * Return the minimum number of boats to carry every given person.
     *
     * Input: people = [1,2], limit = 3
     * Output: 1
     * Explanation: 1 boat (1, 2)
     *
     * @param people people weight array
     * @param limit total weight handled by the boat
     * @return number of boats required
     */
    public int numRescueBoats1(int[] people, int limit) {
        int[] count = new int[limit+1];
        for(int p : people) count[p]++;

        int idx = 0;
        for(int val=1; val<=limit; val++){
            while(count[val]-- > 0)
                people[idx++] = val;
        }

        int boatCount = 0, left = 0, right = people.length - 1;
        while(left <= right){
            if(people[left] + people[right] <= limit){
                left++;
                right--;
            }
            else{
                right--;
            }
            boatCount++;
        }
        return boatCount;
    }

    /**
     *
     * @param people people weight array
     * @param limit total weight handled by the boat
     * @return number of boats required
     */
    public int numRescueBoats2(int[] people, int limit) {
        Arrays.sort(people);

        int boatCount = 0, left = 0, right = people.length - 1;
        while(left <= right){
            if(people[left] + people[right] <= limit){
                left++;
                right--;
            }
            else{
                right--;
            }
            boatCount++;
        }
        return boatCount;
    }

    /**
     *
     * @param people people weight array
     * @param limit total weight handled by the boat
     * @return number of boats required
     */
    public int numRescueBoatsGreedyTwoPointer(int[] people, int limit) {
        Arrays.sort(people);
        int i = 0, j = people.length - 1;
        int ans = 0;

        while (i <= j) {
            ans++;
            if (people[i] + people[j] <= limit)
                i++;
            j--;
        }

        return ans;
    }

    public static void main(String[] args) {
        BoatsToSavePeople boatsToSavePeople = new BoatsToSavePeople();
        int[] personWeight = {3,5,3,4};
        int limit = 5;
        System.out.println(boatsToSavePeople.numRescueBoats1(personWeight, limit));
        System.out.println(boatsToSavePeople.numRescueBoats2(personWeight, limit));
        System.out.println(boatsToSavePeople.numRescueBoatsGreedyTwoPointer(personWeight, limit));
    }



    /*
        Leetcode solution:
        https://leetcode.com/problems/boats-to-save-people/solutions/1877945/java-c-a-very-easy-explanation-trust-me/
        https://leetcode.com/problems/boats-to-save-people/solutions/3372503/image-explanation-brute-better-optimal-c-java-python/
        Leetcode Editorial: https://leetcode.com/problems/boats-to-save-people/editorial/

     */
}
