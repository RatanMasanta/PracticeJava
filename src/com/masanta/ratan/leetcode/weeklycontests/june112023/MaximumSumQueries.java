package com.masanta.ratan.leetcode.weeklycontests.june112023;

import java.util.Arrays;
import java.util.TreeMap;
import java.util.TreeSet;

public class MaximumSumQueries {

    /*
    2736. Maximum Sum Queries
    Hard
    82
    7
    Companies
    You are given two 0-indexed integer arrays nums1 and nums2, each of length n, and a 1-indexed 2D array queries where queries[i] = [xi, yi].

    For the ith query, find the maximum value of nums1[j] + nums2[j] among all indices j (0 <= j < n), where nums1[j] >= xi and nums2[j] >= yi, or -1 if there is no j satisfying the constraints.

    Return an array answer where answer[i] is the answer to the ith query.



    Example 1:

    Input: nums1 = [4,3,1,2], nums2 = [2,4,9,5], queries = [[4,1],[1,3],[2,5]]
    Output: [6,10,7]
    Explanation:
    For the 1st query xi = 4 and yi = 1, we can select index j = 0 since nums1[j] >= 4 and nums2[j] >= 1. The sum nums1[j] + nums2[j] is 6, and we can show that 6 is the maximum we can obtain.

    For the 2nd query xi = 1 and yi = 3, we can select index j = 2 since nums1[j] >= 1 and nums2[j] >= 3. The sum nums1[j] + nums2[j] is 10, and we can show that 10 is the maximum we can obtain.

    For the 3rd query xi = 2 and yi = 5, we can select index j = 3 since nums1[j] >= 2 and nums2[j] >= 5. The sum nums1[j] + nums2[j] is 7, and we can show that 7 is the maximum we can obtain.

    Therefore, we return [6,10,7].
    Example 2:

    Input: nums1 = [3,2,5], nums2 = [2,3,4], queries = [[4,4],[3,2],[1,1]]
    Output: [9,9,9]
    Explanation: For this example, we can use index j = 2 for all the queries since it satisfies the constraints for each query.
    Example 3:

    Input: nums1 = [2,1], nums2 = [2,3], queries = [[3,3]]
    Output: [-1]
    Explanation: There is one query in this example with xi = 3 and yi = 3. For every index, j, either nums1[j] < xi or nums2[j] < yi. Hence, there is no solution.


    Constraints:

    nums1.length == nums2.length
    n == nums1.length
    1 <= n <= 105
    1 <= nums1[i], nums2[i] <= 109
    1 <= queries.length <= 105
    queries[i].length == 2
    xi == queries[i][1]
    yi == queries[i][2]
    1 <= xi, yi <= 109
     */

    /*
Intuition
Let's preprocess the arrays before looking at the queries:

We find all the possible sums and keep them sorted. For each sum, we keep the num1s that made that sum in the sorted order as well.

Then we can look at each query: we are looking for the max sum that satisfies the condition for each query. So we check if the biggest sum satisfies the condition. If yes, we return that sum happily, otherwise, we check the lower sum.

But how do we check if that sum satisfies the condition?

Approach
For each sum, we stored the set of num1s which created that sum in sorted order. We start with the highest num1. If that num1 is bigger than x (q[0] = x), then there's some hope that it is the ideal match. Now we should look at the other condition. We extract num2 = sum - num1. Then check the other condition num2 >= y and as you know: y=q[1]. If that's the case, we cheer loudly and return the winner: sum

In Java, the sorted Map is TreeMap and the sorted set is TreeSet:
TreeMap<Integer, TreeSet<Integer>>

Complexity
Time complexity (preprocessing):
For the preprocessing (creating the TreeMap), it takes us n to go through the nums1 and nums2 arrays. Let's assume we'll have m distinct sum. In that case, we need log m to insert the new sum into the sorted sum Map (or list depending on the implementation). But that's not all: we also need to insert num1 into the corresponding sorted num1 list (TreeSet in Java) which will take another log n. So the preprocessing will be: n log m log n.

Time complexity (Checking Each Query):
Then for each query, in the worst case, we'll look at all m sum and for each sum all n, so on paper the time complexity will be O (q * m * n) but in practice, since the lists are sorted, we will return the output efficiently, unless it doesn't exist.

Space complexity:
O(m * n)
*/
    public int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {
        int[] res = new int[queries.length];
        TreeMap<Integer, TreeSet<Integer>> sumToNum1 = createTreeMap(nums1, nums2);
        for (int i = 0; i < res.length; i++) {
            res[i] = getMax(sumToNum1, queries[i]);
        }
        return res;
    }

    private TreeMap<Integer, TreeSet<Integer>> createTreeMap(int[] nums1, int[] nums2) {
        TreeMap<Integer, TreeSet<Integer>> sumToNum1 = new TreeMap<>();
        for (int i = 0; i < nums1.length; i++) {
            int num1 = nums1[i];
            int num2 = nums2[i];
            int sum = num1 + num2;
            if (!sumToNum1.containsKey(sum)) {
                sumToNum1.put(sum, new TreeSet<>());
            }
            sumToNum1.get(sum).add(num1);
        }
        return sumToNum1;
    }

    private int getMax( TreeMap<Integer, TreeSet<Integer>> treeMap, int[] q) {
        int x = q[0];
        int y = q[1];
        Integer sum = treeMap.lastKey();
        while (sum != null) {
            TreeSet<Integer> num1Set = treeMap.get(sum);
            Integer num1 = num1Set.last();
            while (num1 != null && num1 >= x) {
                int num2 = sum - num1;
                if (num2 >= y) {
                    return sum;
                }
                num1 = num1Set.lower(num1);
            }
            sum = treeMap.lowerKey(sum);
        }
        return -1;
    }

    public static void main(String[] args) {
        MaximumSumQueries maximumSumQueries = new MaximumSumQueries();
        int[] nums1 = {4,3,1,2}, nums2 = {2,4,9,5};
        int[][] queries = {{4,1},{1,3},{2,5}};
        System.out.println(Arrays.toString(maximumSumQueries.maximumSumQueries(nums1, nums2, queries)));

    }

}
