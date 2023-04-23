package com.masanta.ratan.daily.practice.leetcode.hard;

import java.util.HashMap;
import java.util.Map;

public class MinimumNumberofOperationsToMakeAStringSorted {

    public int modulo = 1000000007;
    Map<Integer, Long> factorMemo = new HashMap();

    /**
     *
     * 1830. Minimum Number of Operations to Make String Sorted
     *
     * You are given a string s (0-indexed). You are asked to perform the following operation on s until you get a sorted string:
     *
     * Find the largest index i such that 1 <= i < s.length and s[i] < s[i - 1].
     * Find the largest index j such that i <= j < s.length and s[k] < s[i - 1] for all the possible values of k in the range [i, j] inclusive.
     * Swap the two characters at indices i - 1 and j.
     * Reverse the suffix starting at index i.
     * Return the number of operations needed to make the string sorted. Since the answer can be too large, return it modulo 109 + 7.
     *
     * Example:
     * Input: s = "cba"
     * Output: 5
     * Explanation: The simulation goes as follows:
     * Operation 1: i=2, j=2. Swap s[1] and s[2] to get s="cab", then reverse the suffix starting at 2. Now, s="cab".
     * Operation 2: i=1, j=2. Swap s[0] and s[2] to get s="bac", then reverse the suffix starting at 1. Now, s="bca".
     * Operation 3: i=2, j=2. Swap s[1] and s[2] to get s="bac", then reverse the suffix starting at 2. Now, s="bac".
     * Operation 4: i=1, j=1. Swap s[0] and s[1] to get s="abc", then reverse the suffix starting at 1. Now, s="acb".
     * Operation 5: i=2, j=2. Swap s[1] and s[2] to get s="abc", then reverse the suffix starting at 2. Now, s="abc".
     *
     * Before starting
     * What leetcode is providing us with the strings is a number. It is just a number expressed with base-26 digits. I will consider it for the rest of this article, in which I will deal with digits for making it more easy to understand. So please remember that their string is nothing else but a number expressed in base-26.
     *
     * Explanation without code
     * Decreasing
     * Let's start with a basic problem. I give you the number 15324. I want you to give me the highest number following those two conditions :
     *
     * It should be lower than the original
     * It should contain exactly the same digits
     * The answer is 15243. How did I find it? I start from the end, and I'm looking for the first digit which will not follow the decreasing order (from right remember). So 4-> 2 -> 3. OK, we found 3. I then switch with the highest value lower than 3, which is 2. 15234, and then, I revert everything on the right of the 2 we just put, so 15243.
     *
     * Feel free to read the explanation of the solution of this problem : https://leetcode.com/problems/next-permutation/ (it's the exact opposite problem).
     *
     * What about the algorithm I just described ? It is exactly the algorithm that leetcode is using in the problem. So what they do is just getting the highest lower number formed with those digits.
     *
     * Counting (with distinct digits)
     * Now, let's consider we have 4 digits: 1, 2, 3 and 4. I would like to know the number of combination I can make with those digits. It's 4! (4×3×2×1) = 24. Now, let's list them all here in ascending order :
     *
     * 1234
     * 1243
     * 1324
     * 1342
     * 1423
     * 1432
     * 2134
     * 2143
     * 2314
     * 2341
     * 2413
     * 2431
     * 3124
     * 3142
     * 3214
     * 3241
     * 3412
     * 3421
     * 4123
     * 4132
     * 4213
     * 4231
     * 4312
     * 4321
     * So now, let's assume I give you the number 3412, and I ask you how much permutations do I need to make it sorted. Solution is quite easy, you just have to look at the list above, it's 17. Did you fall in the trap? :-) Actually it is 16, because the first one in the list with rank 1 has a score of 0 in the problem we are trying to solve. But how could we find 16 without that list ?
     *
     * 4×3×2×1 possibilities. Let's call this number f.
     * And let's assume we have an array of the digits we are using : [1,2,3,4]
     * Let's count the number of non zero elements in the array : 4. Let's call this value p
     * First, we are using the 3rd digit which is not 0 of the array. So in order to get his rank, we need to do (f/p)×(3-1) = 6×2 = 12
     *
     * Now, let's divide f by the number of non 0 elements in the array (4) and let's replace the 3 by a zero.
     * Now, f=6 and array is [1,2,0,4] and p = 3.
     * Now, let's do this again, this time, 4 is the 3rd digit which is not 0 in the array. So once again (f/p)×(3-1) = 2×2 = 4
     *
     * Let's do it again, with f=2, array=[1,2,0,0] and p = 2. (f/p)×(1-1) = 1×0 = 0
     * Let's do it again with f=1, array=[0,2,0,0] and p = 1. (f/p)×(1-1) = 1×0 = 0
     *
     * Ok, now, let's sum up all what we have : 12+4+0+0 = 16, which is exactly the result we were looking for.
     *
     * So now, we know how to solve the leetcode problem for a string which would only contain distinct characters. Now, let's see how to solve this with characters that can be duplicated.
     *
     * Counting (with digits that can appear many times)
     * Now, let's make the problem more difficult. What is the position of the number 1223 in increasing order 😂😂😂 ? Ok, I'm just kidding, what is the position of the number 2312 in the list. Then, the previous method cannot be useful, as we have 2 which appears 2 times. Let's try, same as before, to do the list of all numbers with those digits, in increasing order :
     *
     * 1223
     * 1232
     * 1322
     * 2123
     * 2132
     * 2213
     * 2231
     * 2312
     * 2321
     * 3122
     * 3212
     * 3221
     * How many possibilities we have ? Actually, it seems to be 4!/(product of number of occurrences of all digits). 4! / 2 = 12, which seems to work. So, now, let's try with 12333. With our formula, we can predict that we will have 5! / 3 = 40 results. Let's list them all :
     *
     * 12333
     * 13233
     * 13323
     * 13332
     * 21333
     * 23133
     * 23313
     * 23331
     * 31233
     * 31323
     * 31332
     * 32133
     * 32313
     * 32331
     * 33123
     * 33132
     * 33213
     * 33231
     * 33312
     * 33321
     * Oooops, we actually have only 20, not 40. It is because the result is 5! / ([numberOfOne]!x[numberOfTwo]!x[numberOfThree]!) = 120 / 6 = 20.
     *
     * OK, this will be our last listing (I promess), let's try with 11223 :
     *
     * 11223
     * 11232
     * 11322
     * 12123
     * 12132
     * 12213
     * 12231
     * 12312
     * 12321
     * 13122
     * 13212
     * 13221
     * 21123
     * 21132
     * 21213
     * 21231
     * 21312
     * 21321
     * 22113
     * 22131
     * 22311
     * 23112
     * 23121
     * 23211
     * 31122
     * 31212
     * 31221
     * 32112
     * 32121
     * 32211
     * So we can see that we have 30 results, which match our prediction : 5! / (2!x2!x1!) = 120 / 4 = 30. Now, and it is the leetcode problem, how to know the rank of 23121 (we know from the list it is 22).
     *
     * As we can see, the numbers with 1 and 2 appears 2 times more in the first position than the number 3. Because we have 2 occurrences of 2, it seems that it appears 2! times more. Sorry, but not this time. That is why it is important to run with multiple examples in algorithms. We can see in the previous list (12333) that numbers starting with a 3 appears 3 times more than numbers starting with a 2 or a 1.
     *
     * 5! / (2! x 2! x 1!)
     *
     * Let's do first an array of pairs (value, occurrences) = [[1,2], [2,2], [3,1]]. So we start with n, which is the number of possibilities :
     * n / (sum of occurrences) * (sum of occurrences before). For 2, we have, (30 / 5) * 2 = 6 * 2 = 12
     * Now, we have [[1,2], [2,1], [3,1]], and number of possibilities is 4! / (2! x 1! x 1!) = 12
     * Next digit is a 3, so (12 / 4) * (3) = 9
     * Now we have [[1,2], [2,1], [3,0]], and number of possibilities is 3! / (2! x 1! x 0!) = 3
     * Next digit is a 1, so (3 / 3) * (0) = 0
     * Now we have [[1,1], [2,1], [3,0]] and number of possibilities is 2! / (1! x 1! x 0!) = 2
     * Next digit is a 2, so (2/2) * 1 = 1
     * Now we have [[1,1], [2,0], [3,0]] and number of possibilities is 1! / (1! x 0! x 0!) = 1
     * Next digit is a 1, so (1/1) * 0 = 0
     * Let's sum all numbers we got : 12 + 9 + 0 + 1 + 0 : 22. Which is the result we expected.
     *
     * That's it on how to solve the leetcode problem. We come up with a solution, in n x m complexity (where m is the number of characters allowed and n the number of characters).
     *
     *
     * @param s String provided
     * @return number of operations needed to sort the string
     */
    public int makeStringSorted(String s) {
        int[] occurrences = new int[26];
        populateOccurrences(occurrences, s);
        int totalLength = s.length();

        long result = 0;
        long possibilities = numberOfPossibilities(occurrences, totalLength, modulo);
        while(totalLength > 1) {
            // We start calculating after the first step
            if(totalLength < s.length()) {
                int previousOccurrences = occurrences[s.charAt(s.length() - totalLength - 1) - 'a'] + 1;
                possibilities = modDivide((possibilities * previousOccurrences) % modulo, totalLength+1, modulo);
            }
            int numberOfOccurrencesBefore = numberOfOccurrencesBefore(occurrences, s.charAt(s.length() - totalLength));
            long currentResult = modDivide((possibilities * numberOfOccurrencesBefore) % modulo, totalLength, modulo);
            occurrences[s.charAt(s.length() - totalLength) - 'a'] -= 1;
            result = (result + currentResult) % modulo;
            totalLength--;
        }

        return (int) result;
    }

    public void populateOccurrences(int[] occurrences, String s) {
        for(char c: s.toCharArray()) {
            occurrences[c - 'a'] += 1;
        }
    }

    public int numberOfOccurrencesBefore(int[] occurrences, char character) {
        int result = 0;
        for(int i=0; i<character-'a'; i++) {
            result += occurrences[i];
        }
        return result;
    }

    public long numberOfPossibilities(int[] occurrences, int totalSum, int mod) {
        long result = modFact(totalSum, mod);
        for(int i=0; i<occurrences.length; i++) {
            result = modDivide(result, modFact(occurrences[i], mod), mod);
        }
        return result;
    }

    public long modDivide(long a, long b, int mod) {
        b = modPow(b, mod-2, mod);
        return ((a%mod) * b)%mod;
    }

    public long modFact(int n, int mod) {
        if(factorMemo.get(n) != null) {
            return factorMemo.get(n);
        }
        long result = 1;
        for(int i=1; i<=n; i++) {
            // We can simplify (i%mod) by i, because i < mod (and will always be).
            result = (result * i) % mod;
            factorMemo.put(i, result);
        }
        factorMemo.put(n, result);
        return result;
    }

    public long modPow(long base, int exponent, int modulus) {
        if(modulus == 1) {
            return 0;
        }
        long result = 1;
        base = base % modulus;

        while(exponent > 0) {
            if(exponent%2 == 1) {
                result = (result * base) % modulus;
            }
            exponent = exponent >> 1;
            base = (base * base) % modulus;
        }
        return result;
    }


    public static void main(String[] args) {
        MinimumNumberofOperationsToMakeAStringSorted minimumNumberofOperationsToMakeAStringSorted =
                new MinimumNumberofOperationsToMakeAStringSorted();
        String givenStrng = "aabaa";
        System.out.println(minimumNumberofOperationsToMakeAStringSorted.makeStringSorted(givenStrng));
    }

}

/**
 * Leetcode Discuss:
 * https://leetcode.com/problems/minimum-number-of-operations-to-make-string-sorted/solutions/1166535/java-66ms-no-complex-maths-100-explained/?languageTags=java
 */
