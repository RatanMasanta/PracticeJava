package com.masanta.ratan.daily.practice.leetcode.easy;

public class SmallestCharacterGreaterThanTarget {
        /**
         Approach 2: Binary Search
         Intuition
         We are given that the array of characters letters is sorted in non-decreasing order. It means that for an index i, if letter[i] <= target, all the indices smaller than or equal to i would also have characters that are lexicographically smaller than target. Our answer lies in some of the indices from i + 1 to the last index.

         If letter[i] > target, all the indices greater than or equal to i would also have characters that are lexicographically greater than target because letters is sorted. Our answer is either i or some index smaller than it.

         A scenario like this where our task is to search for an element x (or just greater than it) from a given range (left, right) where all values smaller than x do not satisfy a certain condition and all values greater than or equal to x satisfy it (or vice-versa), can be solved optimally with a binary search algorithm. In binary search, we repeatedly divide the solution space where the answer could be in half until the range contains just one element.

         Following the above discussion, we use binary search to solve this problem. We create an integer left and initialize it to the starting index 0. We also create another integer variable right and set it to the last index of letters, i.e., letters.length - 1.

         We get the middle of the range mid = (left + right) / 2 and compare it with target. If letters[mid] <= target, we move to the upper half of the range by setting left = mid + 1. Otherwise, we move to lower half of range by setting right = mid - 1 as all the characters at indices greater or equal to mid would also be greater than target.

         The answer would be within the range (left, right) at any point. All the indices smaller than left would contain characters smaller than target and all characters at indices greater than right would be greater than target. We continue the search until left <= right.

         When left > right, left denotes the index of the smallest character that is lexicographically greater than target. This is because all characters at indices greater than right would be greater than target and character immediately next to index right would be left (or right + 1) after the completion of binary search algorithm.


         Algorithm
         Create three integers left = 0, right = letters.length - 1 and mid to start the binary search algorithm.
         While left <= right:
         Find the midpoint of the range (left, right) in the variable mid = (left + right) / 2.
         Compare the letter at index mid with target. If letters[mid] <= target, it means all the characters at indices smaller or equal to mid would also be smaller than target because the characters in letters are sorted. As a result, we move to upper half of the range by setting left = mid + 1.
         Otherwise, it means all the characters at indices greater or equal to mid would also be greater than target because the characters in letters are sorted. As a result, we move to lower half of the range by setting right = mid - 1.
         At the end of the binary search algorithm, left will store the index of the smallest character that is lexicographically greater than target.
         If left == letters.length, it means there is no character in letters that is lexicographically greater than target. We return letters[0]. Otherwise, we return letters[left] as left holds the smallest character greater than target.
         Implementation
         */
        public char nextGreatestLetter(char[] letters, char target) {
            int low = 0;
            int high = letters.length - 1;
            int resultIndex = 0;
            while(low <= high){
                int mid = (low + high)/2;
                if (letters[mid] > target){
                    high = mid - 1;
                    resultIndex = mid;
                } else {
                    low = mid + 1;
                }
            }
            return low == letters.length ? letters[0] : letters[low];

        }

    public char nextGreatestLetterBruteForce(char[] letters, char target) {
        for (char letter : letters) {
            if (letter > target) {
                return letter;
            }
        }
        return letters[0];
    }

    public static void main(String[] args) {
        char[] charArray = {'a','c','f','h'};
        char target = 'c';
        SmallestCharacterGreaterThanTarget smallestCharacterGreaterThanTarget =
                new SmallestCharacterGreaterThanTarget();
        System.out.println(smallestCharacterGreaterThanTarget.nextGreatestLetter(charArray, target));
        System.out.println(smallestCharacterGreaterThanTarget.nextGreatestLetterBruteForce(charArray, target));
    }

    /*
    744. Find Smallest Letter Greater Than Target
    Easy
    3K
    2K
    Companies
    You are given an array of characters letters that is sorted in non-decreasing order, and a character target. There are at least two different characters in letters.

    Return the smallest character in letters that is lexicographically greater than target. If such a character does not exist, return the first character in letters.



    Example 1:

    Input: letters = ["c","f","j"], target = "a"
    Output: "c"
    Explanation: The smallest character that is lexicographically greater than 'a' in letters is 'c'.

     */

}
