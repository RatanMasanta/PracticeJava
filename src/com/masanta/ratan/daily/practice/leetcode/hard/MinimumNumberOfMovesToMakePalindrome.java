package com.masanta.ratan.daily.practice.leetcode.hard;

public class MinimumNumberOfMovesToMakePalindrome {

    /**
     * 2193. Minimum Number of Moves to Make Palindrome
     *
     * You are given a string s consisting only of lowercase English letters.
     *
     * In one move, you can select any two adjacent characters of s and swap them.
     *
     * Return the minimum number of moves needed to make s a palindrome.
     *
     * Note that the input will be generated such that s can always be converted to a palindrome.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "aabb"
     * Output: 2
     * Explanation:
     * We can obtain two palindromes from s, "abba" and "baab".
     * - We can obtain "abba" from s in 2 moves: "aabb" -> "abab" -> "abba".
     * - We can obtain "baab" from s in 2 moves: "aabb" -> "abab" -> "baab".
     * Thus, the minimum number of moves needed to make s a palindrome is 2.
     *
     *
     * Approach:
     *
     * Intially, steps = 0.
     *	We will use 2 index pointers initially - "l" as Left and "r" as right.
     *	'l' will move to right and 'r' will move to left in each iteration. ( l -->    <-- r )
     *	Each time, we want to check whether characters at "l" & "r" are equal.
     * 	If yes, then we dont need to do anything as both characters on left & right boundry already are palindrome. Hence, we do l++ & r--.
     * 	Else, we assign a index pointer "k" at same index as "r". It's purpose is to find kth index of character matching with (equal to)
     *	character at lth index. So,  keep moving this pointer towards left until we find the character.  We did this in code using
     *   method findKthIndexMatchingwithLthIndex().
     *	Else, if found, now we want to bring this character to the index of "r". Hence keep swapping while(k < r).
     *	If character not found, k index will reach at index of l.  (k==l).
     *	Repeat above steps.
     *
     * @param s Given String
     * @return minimum number of moves to make the string palindrome
     */
    public int minMovesToMakePalindrome(String s) {
        int len = s.length();
        char[] strArr = s.toCharArray();
        int steps = 0;
        int l = 0, r = len-1;                                           // use two pointers l for left and r for right.

        while(l < r){
            if(strArr[l] == strArr[r]){                                 // Both characters are equal. so keep going futher.
                l++; r--;
            }else{                                                      // Both characters are not equal.
                int k = r;
                k = findKthIndexMatchingwithLthIndex(strArr, l, k);     // loop through k, until char at index k = char at index l

                if(k == l){                                             // we did not find any char at k = char at index l
                    swap(strArr, l);
                    steps++;
                }else{
                    while(k < r){
                        swap(strArr, k);
                        steps++;
                        k++;
                    }
                    l++; r--;
                }
            }// end of else

        }   // end of while
        System.out.println("palindrome: "+String.valueOf(strArr));
        return steps;

    }

    public int findKthIndexMatchingwithLthIndex(char[] strArr, int l, int k){
        while(k > l){
            if(strArr[k] == strArr[l]){  return k;  }
            k--;
        }
        return k;
    }

    public void swap(char[] strArr, int l){
        if(l+1 < strArr.length){
            char tempCh = strArr[l];
            strArr[l] = strArr[l+1];
            strArr[l+1] = tempCh;
        }
    }

    public static void main(String[] args) {
        MinimumNumberOfMovesToMakePalindrome minimumNumberOfMovesToMakePalindrome =
                new MinimumNumberOfMovesToMakePalindrome();
        String givenSampleString = "letelt";
        System.out.println(minimumNumberOfMovesToMakePalindrome.minMovesToMakePalindrome(givenSampleString));
    }

    /*
    Leetcide discuss: https://leetcode.com/problems/minimum-number-of-moves-to-make-palindrome/solutions/1823173/java-c-2-pointers-step-by-step-dry-run-for-easy-explanation/
     */

}
