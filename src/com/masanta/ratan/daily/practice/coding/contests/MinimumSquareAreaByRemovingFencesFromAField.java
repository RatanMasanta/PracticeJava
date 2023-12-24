package com.masanta.ratan.daily.practice.coding.contests;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MinimumSquareAreaByRemovingFencesFromAField {

    /*

        There is a large (m - 1) x (n - 1) rectangular field with corners at (1, 1) and (m, n) containing some horizontal and vertical fences given in arrays hFences and vFences respectively.

        Horizontal fences are from the coordinates (hFences[i], 1) to (hFences[i], n) and vertical fences are from the coordinates (1, vFences[i]) to (m, vFences[i]).

        Return the maximum area of a square field that can be formed by removing some fences (possibly none) or -1 if it is impossible to make a square field.

        Since the answer may be large, return it modulo 109 + 7.

        Note: The field is surrounded by two horizontal fences from the coordinates (1, 1) to (1, n) and (m, 1) to (m, n) and two vertical fences from the coordinates (1, 1) to (m, 1) and (1, n) to (m, n). These fences cannot be removed.

         Example 1:

        Input: m = 4, n = 3, hFences = [2,3], vFences = [2]
        Output: 4
        Explanation: Removing the horizontal fence at 2 and the vertical fence at 2 will give a square field of area 4.

        Example 2:
        Input: m = 6, n = 7, hFences = [2], vFences = [4]
        Output: -1
        Explanation: It can be proved that there is no way to create a square field by removing fences.


        Constraints:

        3 <= m, n <= 109
        1 <= hFences.length, vFences.length <= 600
        1 < hFences[i] < m
        1 < vFences[i] < n
        hFences and vFences are unique.
     */

    public static int maximizeSquareArea(int m, int n, int[] h, int[] v) {
        if (m == n)
            return (m - 1) * (m - 1);

        int[] horizontal = new int[h.length + 2];
        int[] vertical = new int[v.length + 2];

        horizontal[0] = 1;
        horizontal[horizontal.length - 1] = m;
        vertical[0] = 1;
        vertical[vertical.length - 1] = n;

        System.arraycopy(h, 0, horizontal, 1, h.length);
        System.arraycopy(v, 0, vertical, 1, v.length);

        Arrays.sort(horizontal);
        Arrays.sort(vertical);

        Set<Integer> hash = new HashSet<>();
        for (int i = 0; i < horizontal.length; i++)
            for (int j = i + 1; j < horizontal.length; j++)
                hash.add(horizontal[j] - horizontal[i]);

        int maxDiff = Integer.MIN_VALUE;
        for (int k = 0; k < vertical.length; k++) {
            for (int l = k + 1; l < vertical.length; l++) {
                int diff = vertical[l] - vertical[k];
                if (hash.contains(diff))
                    maxDiff = Math.max(maxDiff, diff);
            }
        }

        return (maxDiff != Integer.MIN_VALUE) ? ((int)((1L * maxDiff * maxDiff) % (1000000007))) : -1;
    }

    public static void main(String[] args) {
        int m = 4;
        int n = 3;
        int[] horizontalFences = {2,3};
        int[] verticalFences = {2};
        System.out.println(MinimumSquareAreaByRemovingFencesFromAField.maximizeSquareArea(m, n,horizontalFences, verticalFences));
    }


}
