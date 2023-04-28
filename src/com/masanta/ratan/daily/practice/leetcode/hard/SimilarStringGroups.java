package com.masanta.ratan.daily.practice.leetcode.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimilarStringGroups {


    private void dfs(int node, Map<Integer, List<Integer>> adj, boolean[] visit) {
        visit[node] = true;
        if (!adj.containsKey(node)) {
            return;
        }
        for (int neighbor : adj.get(node)) {
            if (!visit[neighbor]) {
                visit[neighbor] = true;
                dfs(neighbor, adj, visit);
            }
        }
    }

    private boolean isSimilar(String a, String b) {
        int diff = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                diff++;
            }
        }
        return diff == 0 || diff == 2;
    }

    /**
     * 839. Similar String Groups
     *
     * Two strings X and Y are similar if we can swap two letters (in different positions) of X, so that it equals Y. Also two strings X and Y are similar if they are equal.
     *
     * For example, "tars" and "rats" are similar (swapping at positions 0 and 2), and "rats" and "arts" are similar, but "star" is not similar to "tars", "rats", or "arts".
     *
     * Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}.  Notice that "tars" and "arts" are in the same group even though they are not similar.  Formally, each group is such that a word is in the group if and only if it is similar to at least one other word in the group.
     *
     * We are given a list strs of strings where every string in strs is an anagram of every other string in strs. How many groups are there?
     *
     *
     *
     * Example 1:
     *
     * Input: strs = ["tars","rats","arts","star"]
     * Output: 2
     *
     * Overview
     * The problem states that two strings X and Y are similar if two letters (in different positions) of X can be swapped to form string Y. Also, if two strings X and Y are equal, they are similar.
     *
     * We are given an array of strings called strs, where each string is an anagram of every other string in the array.
     *
     * Our task is to group the strings together, where strings in the same group are similar to at least one other string in the group.
     *
     * We have to return the number of such groups that will be formed.
     *
     * Approach 1: Depth First Search
     * Intuition
     * We can see that two words A and B belong to the same group if they are similar or equal, or if there are some words in the group such as X1, X2,... XN, such that A is similar to X1, X1 is similar to X2,... XN is similar to B. It means that if we can create a path from A to B using words from the group, then A and B are also members of that group.
     *
     * This prompts us to think about the problem in terms of graphs.
     *
     * Each word in strs can be viewed as a node. We add an undirected edge between each pair of similar words. If there is a path in this graph from words A to B, then A and B belong to the same group. Because the graph is undirected, A and B belong to the same group if and only if they belong to the same component of the graph.
     *
     * The number of required groups is the number of connected components formed in such a graph.
     *
     * To make the graph, we use a hash map called adj with an integer as the key and a list of integers as the value to map the index of the word to a list of indices of words that are similar to it. We are using the indices as the nodes instead of the strings themselves because dealing with integers is faster than strings.
     *
     * For a pair of similar words A and B with indices i and j in strs, we add j to the adj[i] map and i to the adj[j] map. This adds an undirected edge between nodes i and j. We iterate over all the pairs of words that can be formed in strs, see if they are similar, and then add an edge between them. This forms our graph.
     *
     * To check the number of connected components in a graph, we can use a graph traversal algorithm like depth first search (DFS).
     *
     * We use the dfs method, which takes node, adj, visit as parameters. The parameter node is the index of the word from which we start our path. visit is used to keep track of visited indices. adj is the adjacency list.
     *
     * In the dfs method, we mark node as visited. We then iterate over all the neighbors of node and recursively visit them to cover all the nodes in the current connected component.
     *
     * To figure out how many connected components there are in the graph, we first mark all nodes as unvisited. We create a variable called count to count the number of connected components in the graph and initialize it to 0.
     *
     * We iterate through all the nodes from 0 to n - 1, checking whether each node has been visited or not. If node is not visited, we begin a DFS traversal from node and increment count by 1 (a new connected component is discovered). The DFS traversal would visit all of the nodes in the component to which node belongs.
     *
     * As an answer, we return count.
     *
     *
     * Algorithm
     * Create an integer variable n which stores the number of words in strs.
     * Create an adjacency list of size n using strs where adj[x] contains all the indices of words similar to word str[x].
     * We iterate over all pairs of words that can be formed by selecting any two words from str to generate the adjacency list.
     * For any two words at index i and j, we check whether the words strs[i] and strs[j] are similar or not by iterating over all the letters of the words. If the words are equal or they differ at two indices only, the words are similar.
     * If they are similar, we add j to the adj[i] and i to the adj[j] map.
     * Create a visit array of length n to keep track of nodes that have been visited.
     * Create an integer count which stores the number of connected components in the graph. Initialize it to 0.
     * Iterate through all of the nodes, and for each node i check if it has been visited or not. If node i is not visited, we increment count by 1 and start a DFS traversal:
     * We use the dfs function to perform the traversal. For each call, pass node, adj, and visit as the parameters. We start with node i.
     * We mark node as visited.
     * We iterate over all the neighbors of node. If any neighbor has not yet been visited, we recursively call dfs with neighbor as the node.
     * Return count.
     *
     * @param strs Array of Strings passed
     * @return count of strings which are equal or they can be made equal with a swap of two letters
     */
    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        Map<Integer, List<Integer>> adj = new HashMap<>();
        // Form the required graph from the given strings array.
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isSimilar(strs[i], strs[j])) {
                    adj.computeIfAbsent(i, k -> new ArrayList<Integer>()).add(j);
                    adj.computeIfAbsent(j, k -> new ArrayList<Integer>()).add(i);
                }
            }
        }

        boolean[] visit = new boolean[n];
        int count = 0;
        // Count the number of connected components.
        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                dfs(i, adj, visit);
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        SimilarStringGroups similarStringGroups = new SimilarStringGroups();
        String[] stringArray = {"tars","rats","arts","star"};
        System.out.println(similarStringGroups.numSimilarGroups(stringArray));
    }
}
