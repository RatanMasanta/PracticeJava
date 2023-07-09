package com.masanta.ratan.daily.practice.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

public class MaximizeTheConfusionOfAnExam {

    /*
    2024. Maximize the Confusion of an Exam
        Medium
        2.5K
        34
        Companies
        A teacher is writing a test with n true/false questions, with 'T' denoting true and 'F' denoting false. He wants to confuse the students by maximizing the number of consecutive questions with the same answer (multiple trues or multiple falses in a row).

        You are given a string answerKey, where answerKey[i] is the original answer to the ith question. In addition, you are given an integer k, the maximum number of times you may perform the following operation:

        Change the answer key for any question to 'T' or 'F' (i.e., set answerKey[i] to 'T' or 'F').
        Return the maximum number of consecutive 'T's or 'F's in the answer key after performing the operation at most k times.



        Example 1:

        Input: answerKey = "TTFF", k = 2
        Output: 4
        Explanation: We can replace both the 'F's with 'T's to make answerKey = "TTTT".
        There are four consecutive 'T's.
     */


    /**
     *
     * Approach 1: Binary Search + Fixed Size Sliding Window
     * Intuition
     * Since we are asked to find the longest sequence of identical answers. We could first set up a target length max_size, then we shall iterate over answerKey to look for each substring of length max_size. If we could flip at most k answers in a substring to make all answers identical, then this substring is valid and we can make a confusion of at least max_size.
     *
     * In order to make a string valid, we can either:
     *
     * Flip every T to F in the string so that it is all F.
     * Flip every F to T in the string so that it is all T.
     * However, if both F and T in the substring are more than k, we can never make it valid by k flips. Therefore, we can determine if a substring is valid by comparing k with the smaller value between the count of F and the count of T in it.
     *
     * If min(count(T), count(F)) <= k, this substring is valid.
     * If min(count(T), count(F)) > k, this substring is invalid.
     * The method described in this solution is also known as the sliding window algorithm. During the process from left to right, we ensure that the length of the subsequence remains unchanged as max_size, just like moving a window of fixed length. As shown in the pictures below, if we set the window length to m = 3, we can find some valid windows.
     *
     * img
     *
     * However, if we set the length to m = 7, we will not find any valid windows, since the only two windows of size 7 contain more than one T and more than one F.
     *
     * img
     *
     *
     * During the iteration, when we move the right boundary of the window from right - 1 to right, we don't need to recalculate the count of each answer over again, note that two adjacent windows only differ by two answers (answerKey[right], answerKey[right - m]). We only need to increment the count of answerKey[right] by 1 and decrement the count of answerKey[right - m] by 1, based on the result of the previous window.
     *
     *
     * To quickly find the maximum valid window length, we can use binary search. To begin, we need to define a search space that ensures the maximum window length we are looking for is within this range. We can set the left boundary of the search space to left = 1, which represents the smallest window length, and the right boundary to right = n, which is the maximum possible window length.
     *
     * Next, we perform a binary search within the interval [left, right]. At each iteration, we find the midpoint of the interval, denoted as mid, and slide a window of length mid using the previous approach to check whether there exists at least one valid window. If such a window exists, we continue to search for a larger window length in [mid, right], the right half of the interval. Otherwise, if mid is still too large, we continue our search in [left, mid - 1], the left half of the search space.
     *
     *
     * Algorithm
     * Initialize the search space as left = 1, right = n.
     *
     * Define a function isValid to help verify if a window of size size is valid:
     *
     * Count the number of T and F in answerKey[:size] in a counter count, return true if min(counter[T], counter[F]) <= k
     *
     * Iterate the index of the right boundary of the window from size - 1 to n. At each step i, increment counter[answerKey[i]] by 1 and decrement counter[answerKey[i - size]] by 1, return true if min(counter[T], counter[F]) <= k at any point in this iteration
     *
     * Return false if we finish iterating without finding a valid window.
     *
     * While left < right:
     *
     * Find the middle value as mid = right - (right - left) / 2.
     * Check if the window of size mid is valid.
     * If isValid(mid) is true, let left = mid and repeat step 3.
     * If isValid(mid) is false, let right = mid - 1 and repeat step 3.
     * Return left once the search ends.
     *
     * @param answerKey String of answers for the questions
     * @param k you are given an integer k, the maximum number of times you may perform the following operation: Change the answer key for any question to 'T' or 'F' (i.e., set answerKey[i] to 'T' or 'F').
     * @return maximum number of consecutive 'T's or 'F's in the answer key after performing the operation at most k times.
     */
    public int maxConsecutiveAnswers(String answerKey, int k) {
        int n = answerKey.length();
        int left = k, right = n;

        while (left < right) {
            int mid = (left + right + 1) / 2;

            if (isValid(answerKey, mid, k)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    private boolean isValid(String answerKey, int size, int k) {
        int n = answerKey.length();
        Map<Character, Integer> counter = new HashMap<>();

        for (int i = 0; i < size; i++) {
            char c = answerKey.charAt(i);
            counter.put(c, counter.getOrDefault(c, 0) + 1);
        }

        if (Math.min(counter.getOrDefault('T', 0), counter.getOrDefault('F', 0)) <= k) {
            return true;
        }

        for (int i = size; i < n; i++) {
            char c1 = answerKey.charAt(i);
            counter.put(c1, counter.getOrDefault(c1, 0) + 1);
            char c2 = answerKey.charAt(i - size);
            counter.put(c2, counter.getOrDefault(c2, 0) - 1);

            if (Math.min(counter.getOrDefault('T', 0), counter.getOrDefault('F', 0)) <= k) {
                return true;
            }
        }

        return false;
    }


    public static void main(String[] args) {
        MaximizeTheConfusionOfAnExam maximizeTheConfusionOfAnExam =
                new MaximizeTheConfusionOfAnExam();
        String answerKey = "TTFTTFTT";
        int numberOfPossibleOperations = 1 ;
        System.out.println(
                maximizeTheConfusionOfAnExam
                        .maxConsecutiveAnswers(answerKey, numberOfPossibleOperations));
        /*
        Input: answerKey = "TTFTTFTT", k = 1
        Output: 5
        Explanation: We can replace the first 'F' to make answerKey = "TTTTTFTT"
        Alternatively, we can replace the second 'F' to make answerKey = "TTFTTTTT".
        In both cases, there are five consecutive 'T's
         */
    }
}
