package com.masanta.ratan.daily.practice.leetcode.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 *
 1079. Letter Tile Possibilities

 You have n  tiles, where each tile has one letter tiles[i] printed on it.

 Return the number of possible non-empty sequences of letters you can make using the letters printed on those tiles.

 Example 1:

 Input: tiles = "AAB"
 Output: 8
 Explanation: The possible sequences are "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA".
 Example 2:

 Input: tiles = "AAABBC"
 Output: 188
 Example 3:

 Input: tiles = "V"
 Output: 1


 Constraints:

 1 <= tiles.length <= 7
 tiles consists of uppercase English letters.
 *
 */
public class LetterTilePossibilities {



    static void main(String[] args){
        String TILES = "AAABBC";
        System.out.println("Number of possibilities for Tiles: " + TILES + " are: " + numTilePossibilities1(TILES));
    }

    /**
     *
     *
     * Permutations and Combinations
     * Intuition
     * Consider a sequence "ABC". Generating it actually has two steps:
     *
     * Choosing the three tiles 'A', 'B', and 'C'.
     * Arranging them in order to form "ABC".
     * Notice that after step 1, we can create 5 more sequences: "BAC", "CBA", "BCA", "ACB", and "CAB." These are all the permutations of "ABC".
     *
     * The total number of permutations that can be generated from n unique characters is n!. For the characters 'A', 'B', and 'C', the number of unique characters is 3, so 6 sequences can be generated from them.
     *
     * However, we need to account for cases where there are multiple occurrences of the same character. For example, consider the tiles 'A', 'A', and 'B'. This will generate only 3 unique sequences of length 3: "AAB", "ABA", and "BAA". This is because swapping the first and second 'A' doesn’t create a new sequence, so they can’t be counted separately.
     *
     * To account for this, we modify our formula to the following: if we have 3 characters with frequencies n
     * 1
     * ​
     *  , n
     * 2
     * ​
     *  , and n
     * 3
     * ​
     *  , the number of 3 length sequences are:
     *
     * (n
     * 1
     * ​
     *  )!⋅(n
     * 2
     * ​
     *  )!⋅(n
     * 3
     * ​
     *  )!
     * (n
     * 1
     * ​
     *  +n
     * 2
     * ​
     *  +n
     * 3
     * ​
     *  )!
     * ​
     *
     * ​
     *
     * The above formula can be extended to m characters of different frequencies.
     *
     * So now, our task is to generate all combinations of characters from the given tiles. We can use a recursive method to do this. The function iterates over the tiles string and makes two choices at each step: whether to pick the current character or not. This generates all possible combinations of characters, which we then pass to a helper method called countPermutations.
     *
     * The countPermutations method counts the frequency of each character in the generated combination using an array called charCount (similar to the previous approach). It then applies the formula above to calculate all possible permutations of the current combination.
     *
     * The total permutations for each combination are returned by the recursive function. The cumulative sum of all such combinations is our final answer, which we return at the end.
     *
     * Algorithm
     * Initialize a hash set seen to store unique sequences.
     * Convert seen to a sorted string sortedTiles.
     * Call the recursive helper function generateSequences with initial parameters. Subtract 1 from the result and return it.
     * Helper method factorial(n):
     *
     * Check if n is less than or equal to 1:
     * If true, return 1.
     * Initialize a variable result to 1.
     * Loop num from 2 to n:
     * Multiply the result by num.
     * Return the final result.
     * Helper method countPermutations(seq):
     *
     * Initialize an integer array charCount of size 26 for character frequencies.
     * Iterate through each character in the input seq:
     * Increment the count at index (character - 'A') in charCount.
     * Set a variable total as the factorial of the length of seq.
     * Divide the total by the factorial of each character's frequency in charCount.
     * Return the final total.
     * Helper method generateSequences(tiles, current, pos, seen):
     *
     * Check if the current pos has reached the length of tiles. If true and the current sequence is new (added to seen set):
     * Return the number of permutations for the current sequence.
     * If true but the sequence is already seen:
     * Return 0.
     * Make two recursive calls and sum their results:
     * One excluding the current character (same sequence, next position).
     * One including the current character (sequence + current character, next position).
     * Return the sum of both recursive calls.
     *
     *
     * Complexity Analysis
     * Let n be the length of the input string tiles.
     *
     * Time complexity: O(2
     * n
     *  ⋅n)
     *
     * The time complexity is determined by several components:
     *
     * The initial sorting takes O(nlogn) time.
     * In the generateSequences function, we create a binary recursion tree where at each position we have two choices (include or exclude), leading to 2
     * n
     *   possible sequences. For each unique sequence, we calculate permutations which involves iterating over the sequence (O(n)) and performing factorial calculations (O(n)).
     * The factorial calculations themselves are O(n) as they iterate from 1 to at most n.
     * Therefore, the dominant factor is generating and processing all possible sequences, giving us a time complexity of O(2
     * n
     *  ⋅n).
     *
     * Space complexity: O(2
     * n
     *  ⋅n)
     *
     * The space complexity also has multiple components.
     *
     * The recursion stack can go up to depth n, using O(n) space.
     * The hash set seen stores unique combinations of characters. In the worst case, with all distinct characters, we could have 2
     * n
     *   different combinations as each character can either be included or excluded. Each sequence in the set can be up to length n. So, the set uses O(2
     * n
     *  ⋅n) space.
     * The charCount array in countPermutations is constant space O(1) as it's always size 26.
     * Thus, the dominant factor is the space needed for storing unique sequences in the seen set, making the total space complexity O(2
     * n
     *  ⋅n).
     *
     *
     * @param tiles TILES available
     * @return
     */
    public static int numTilePossibilities1(String tiles) {
        Set<String> seen = new HashSet<>();

        // Sort characters to handle duplicates efficiently
        char[] chars = tiles.toCharArray();
        Arrays.sort(chars);
        String sortedTiles = new String(chars);

        // Find all unique sequences and their permutations
        return generateSequences(sortedTiles, "", 0, seen) - 1;
    }

    private static int factorial(int n) {
        if (n <= 1) {
            return 1;
        }

        int result = 1;
        for (int num = 2; num <= n; num++) {
            result *= num;
        }
        return result;
    }

    private static int countPermutations(String seq) {
        // Count frequency of each character
        int[] charCount = new int[26];
        for (char ch : seq.toCharArray()) {
            charCount[ch - 'A']++;
        }

        // Calculate permutations using factorial formula
        int total = factorial(seq.length());
        for (int count : charCount) {
            total /= factorial(count);
        }
        return total;
    }

    private static int generateSequences(
            String tiles,
            String current,
            int pos,
            Set<String> seen
    ) {
        if (pos >= tiles.length()) {
            // If new sequence found, count its unique permutations
            if (seen.add(current)) {
                return countPermutations(current);
            }
            return 0;
        }

        // Try including and excluding current character
        return (
                generateSequences(tiles, current, pos + 1, seen) +
                        generateSequences(tiles, current + tiles.charAt(pos), pos + 1, seen)
        );
    }


}
