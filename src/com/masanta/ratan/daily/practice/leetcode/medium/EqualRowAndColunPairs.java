package com.masanta.ratan.daily.practice.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

public class EqualRowAndColunPairs {
    /*
    2352. Equal Row and Column Pairs
    Medium
    1.7K
    91
    Companies
    Given a 0-indexed n x n integer matrix grid, return the number of pairs (ri, cj) such that row ri and column cj are equal.

    A row and column pair is considered equal if they contain the same elements in the same order (i.e., an equal array).



    Example 1:


    Input: grid = [[3,2,1],[1,7,6],[2,7,7]]
    Output: 1
    Explanation: There is 1 equal row and column pair:
- (Row 2, Column 1): [2,7,7]

     */

    public int equalPairs(int[][] grid) {
        Trie myTrie = new Trie();
        int count = 0, n = grid.length;

        for (int[] row : grid) {
            myTrie.insert(row);
        }

        for (int c = 0; c < n; ++c) {
            int[] colArray = new int[n];
            for (int r = 0; r < n; ++r) {
                colArray[r] = grid[r][c];
            }
            count += myTrie.search(colArray);
        }

        return count;
    }

    class TrieNode {
        int count;
        Map<Integer, TrieNode> children;

        public TrieNode() {
            this.count = 0;
            this.children = new HashMap<>();
        }
    }

    class Trie {
        TrieNode trie;

        public Trie() {
            this.trie = new TrieNode();
        }

        public void insert(int[] array) {
            TrieNode myTrie = this.trie;
            for (int a : array) {
                if (!myTrie.children.containsKey(a)) {
                    myTrie.children.put(a, new TrieNode());
                }
                myTrie = myTrie.children.get(a);
            }
            myTrie.count += 1;
        }

        public int search(int[] array) {
            TrieNode myTrie = this.trie;
            for (int a : array) {
                if (myTrie.children.containsKey(a)) {
                    myTrie = myTrie.children.get(a);
                } else {
                    return 0;
                }
            }
            return myTrie.count;
        }
    }

    public static void main(String[] args) {
        int[][] grid = {{3,1,2,2},{1,4,4,5},{2,4,2,2},{2,4,2,2}};
        EqualRowAndColunPairs equalRowAndColunPairs = new EqualRowAndColunPairs();
        System.out.println(equalRowAndColunPairs.equalPairs(grid));
    }

}
