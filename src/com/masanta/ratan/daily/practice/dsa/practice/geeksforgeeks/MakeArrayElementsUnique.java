package com.masanta.ratan.daily.practice.dsa.practice.geeksforgeeks;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Problem statement:
 *
 * Given an array arr[ ], your task is to find the minimum number of increment operations required to make all the elements of the array unique. i.e.- no value in the array should occur more than once. In one operation, a value can be incremented by 1 only.
 *
 * Examples :
 *
 * Input: arr[] = [1, 2, 2]
 * Output: 1
 * Explanation: If we increase arr[2] by 1 then the resulting array becomes {1, 2, 3} and has all unique values.Hence, the answer is 1 in this case.
 * Input: arr[] = [1, 1, 2, 3]
 * Output: 3
 * Explanation: If we increase arr[0] by 3, then all array elements will be unique. Hence, the answer is 3 in this case.
 * Input: arr[] = [5, 4, 3, 2, 1]
 * Output: 0
 * Explanation: All elements are unique.
 * Constraints:
 * 1 ≤ arr.size() ≤ 106
 * 0 ≤ arr[i] ≤ 106
 *
 * Company: Dunzo
 *
 */
public class MakeArrayElementsUnique {

    void main(){
        System.out.println(STR."The minimum number of increment operations for  {1, 1, 2, 3} is \{minIncrements(new int[] {1, 1, 2, 3})}");
    }

    /**
     *
     * Java 0(n.log(n)) solution
     *
     * Approach
     *
     * 1.The array has to be sorted.
     *
     * 2.hashset is used to store the values(space complexity  = 0(n))
     *
     * 3.we have to iterate thru the array and add the unique elements to the hashSet.
     *
     * 4.while looping thru the elements, when we come across same element twice,we have to add 1 and subtract the current repeating ith value from max value that we have came across so far. for example arr=[1,2,3,4,5,6,5,7], in this array, till the arr[5], all the elements are unique and 6 is the max value. But arr[6]=5 which is repeated. so we have to add max+1-arr[6] (6+1-5=2) to the arr[6] to make it unique. now 2 is added to the arr[6] therefore 5 becomes 7. now the 7 is added to the hashset.
     *
     * 5.The hashSet will look like this after adding 7 to it.set=[1,2,3,4,5,6,7] and max=7
     *
     * 6.Now the iteration continues and arr[7]=7 which is already present in the set. so the max+1-arr[7] is performed which is nothing but (7+1-7=1) now the 7 becomes 8 and 8 is added to the hashset and the max=8.
     *
     * 7. it can be noted that we first added 2 to arr[6] and 1 to arr[7] to make all the elements unique. so totally we added 3 to the array. so we have to return 3.
     *
     * 8.after all the changes the set looks like this [1,2,3,4,5,6,5-->7,7-->8] which is [1,2,3,4,5,6,7,8]
     *
     * Steps:
     *
     * 1. Sort the array(time complexity = n.log(n)
     *
     * 2. Create two integer variable sum and max. the sum will contain the value to be added which will be returned and max will have the maximum value that we came across so far.
     *
     * 3. Iterate through the array and store the maximum value that we have came across so far in the max variable.
     *
     * 4. If the set contains arr[i], perform max+1-arr[i] adn store it in sum. add one to the max as well. and if arr[i] is not found in set just add arr[i] to the set.
     *
     * 5. Return the value of sum(which has the value of no of number which has to added to the repeated element to make it unique).
     *
     * Time complexity
     *
     * though the time complexity inside the for loop is 0(n), the sort() method's time complexity is 0(n.log(n)) and so the time complexity is 0(n.log(n)+n)=O(n.log(n))
     *
     * space complexity
     *
     * the size of the hashset can increase upto n in the worst case so the space complexity  = O(n).
     *
     *
     * @param arr
     * @return
     */
    public static int minIncrements(int[] arr) {
        // Code here
        Arrays.sort(arr);

        HashSet<Integer> set = new HashSet<>();

        int sum = 0;

        int max = 0;

        for(int i=0;i<arr.length;i++){

            max = Math.max(arr[i],max);

            if(set.contains(arr[i])){

                sum+=max+1-arr[i];

                max++;

                set.add(max);

            }

            else{

                set.add(arr[i]);

            }

        }

        return sum;
    }

}
