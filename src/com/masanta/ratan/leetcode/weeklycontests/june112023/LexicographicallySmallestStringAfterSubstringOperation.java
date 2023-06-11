package com.masanta.ratan.leetcode.weeklycontests.june112023;

public class LexicographicallySmallestStringAfterSubstringOperation {
/*
You are given a string s consisting of only lowercase English letters. In one operation, you can do the following:

Select any non-empty substring of s, possibly the entire string, then replace each one of its characters with the previous character of the English alphabet. For example, 'b' is converted to 'a', and 'a' is converted to 'z'.
Return the lexicographically smallest string you can obtain after performing the above operation exactly once.

A substring is a contiguous sequence of characters in a string.

A string x is lexicographically smaller than a string y of the same length if x[i] comes before y[i] in alphabetic order for the first position i such that x[i] != y[i].


Example 1:

Input: s = "cbabc"
Output: "baabc"
Explanation: We apply the operation on the substring starting at index 0, and ending at index 1 inclusive.
It can be proven that the resulting string is the lexicographically smallest.
Example 2:

Input: s = "acbbc"
Output: "abaab"
Explanation: We apply the operation on the substring starting at index 1, and ending at index 4 inclusive.
It can be proven that the resulting string is the lexicographically smallest.
Example 3:

Input: s = "leetcode"
Output: "kddsbncd"
Explanation: We apply the operation on the entire string.
It can be proven that the resulting string is the lexicographically smallest.


Constraints:

1 <= s.length <= 3 * 105
 */

   /* Explanation
    a in prefix is good, skip all a in the beginning.
    If all characters are a, change the last char to z.
    From the first char not a, decrese them, until next a.
    */
   public String smallestString(String s) {
       int i = 0, n = s.length();
       char[] A = s.toCharArray();
       while (i < n && A[i] == 'a')
           i++;
       if (i == n)
           A[n - 1] = 'z';
       while (i < n && A[i] != 'a')
           --A[i++];
       return String.valueOf(A);
   }

    public String smallestString2(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        boolean replace = false;
        for (int i = 0; i < n; i++) {
            if(chars[i]=='a'){
                if(replace){
                    break;
                }
                continue;
            }
            replace = true;
            chars[i] = (char) (chars[i]-1);
        }
        if(!replace){
            chars[n-1] = 'z';
        }
        return new String(chars);
    }

    /*
    Approach
    First Check a is present, then skip it here like increment i++.
    If we fond our i is currntly at last index then that value becomes z. because I already shown if a is present in string then keep increment your index, So obiously at last index will be 'a' and z will come before a, so thats why its set to z character.
    In while loop it checking current character its not 'a' then then set previous character to whatever character is present in current index in stringbuilder .
    Then remember Stringbuilder is not string means Stringbuilder is mutable. so we have to convert it toString.
     */
    public String smallestString3(String s) {
        StringBuilder sb = new StringBuilder(s);

        int i = 0;
        while (i < s.length() && sb.charAt(i) == 'a') i++;

        if (i == s.length()) sb.setCharAt(s.length() - 1, 'z');

        else {
            while (i < s.length() && sb.charAt(i) != 'a') {
                sb.setCharAt(i, (char)(sb.charAt(i) - 1));
                i++;
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String str1 = "aaaaaaa";
        String str2 = "aab";
        String str3 = "a";
        String str4 = "leetcode";
        LexicographicallySmallestStringAfterSubstringOperation lexicographicallySmallestStringAfterSubstringOperation
                = new LexicographicallySmallestStringAfterSubstringOperation();
        System.out.println(lexicographicallySmallestStringAfterSubstringOperation.smallestString(str1));
        System.out.println(lexicographicallySmallestStringAfterSubstringOperation.smallestString(str2));
        System.out.println(lexicographicallySmallestStringAfterSubstringOperation.smallestString(str3));
        System.out.println(lexicographicallySmallestStringAfterSubstringOperation.smallestString(str4) + "\n");

        System.out.println(lexicographicallySmallestStringAfterSubstringOperation.smallestString2(str1));
        System.out.println(lexicographicallySmallestStringAfterSubstringOperation.smallestString2(str2));
        System.out.println(lexicographicallySmallestStringAfterSubstringOperation.smallestString2(str3));
        System.out.println(lexicographicallySmallestStringAfterSubstringOperation.smallestString2(str4) + "\n");

        System.out.println(lexicographicallySmallestStringAfterSubstringOperation.smallestString3(str1));
        System.out.println(lexicographicallySmallestStringAfterSubstringOperation.smallestString3(str2));
        System.out.println(lexicographicallySmallestStringAfterSubstringOperation.smallestString3(str3));
        System.out.println(lexicographicallySmallestStringAfterSubstringOperation.smallestString3(str4) + "\n");
    }
}
