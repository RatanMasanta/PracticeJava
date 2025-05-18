package com.masanta.ratan.daily.practice.dsa.sixteenweekplan.day2;

import java.util.*;

public class GroupAnagrams {


    /*
        49. Group Anagrams

            Given an array of strings strs, group the anagrams together. You can return the answer in any order.

            Example 1:

            Input: strs = ["eat","tea","tan","ate","nat","bat"]

            Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

            Explanation:

            There is no string in strs that can be rearranged to form "bat".
            The strings "nat" and "tan" are anagrams as they can be rearranged to form each other.
            The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to form each other.
            Example 2:

            Input: strs = [""]

            Output: [[""]]

            Example 3:

            Input: strs = ["a"]

            Output: [["a"]]



            Constraints:

            1 <= strs.length <= 10^4
            0 <= strs[i].length <= 100
            strs[i] consists of lowercase English letters.
     */

    public static void main(String[] args) {
        String anagramsArray[] = {"eat","tea","tan","ate","nat","bat",""};
        List<List<String>> result = groupAnagrams(anagramsArray);
        result.forEach(x-> x.forEach(System.out::println));
    }

    /**
     *
     * Keep in mind we will get issues if we try to keep a track of indexes of string.
     * We can return answer in any order so we create keys of sorted String and add the actual string in the map for each key.
     * This way, even for same length, we can have different keys and we don't have to do index tracking or think about the original String.
     *
     * Note: Don't go for String length approach as you'll be stuck there.
     *
     * @param strs Array of Strings
     * @return Group of list of anagrams
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for(String s: strs){
            char[] c = s.toCharArray();
            Arrays.sort(c); // Important to get distinct keys for the map
            String key = new String(c);
            if(map.containsKey(key)){
                map.get(key).add(s);
            } else {
                List<String> strArray = new ArrayList<>();
                strArray.add(s);
                map.put(key, strArray);
            }
        }
        List<List<String>> result = new ArrayList<>();
        for(List<String> valList: map.values()){
            result.add(valList);
        }
        return result;
    }


}
