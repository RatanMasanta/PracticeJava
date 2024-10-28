package com.masanta.ratan.daily.practice.dsa.practice.geeksforgeeks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicatesInArray {

    void main(){
        int nums[] = {1,2,7,5,1,2,3,8};
        System.out.println(STR."Array after removing ducplicates is: \{Arrays.toString(removeDuplicate(nums).toArray())}");
    }

    ArrayList<Integer> removeDuplicate(int arr[]) {
        // code here
        Set<Integer> set = new HashSet<>();
        ArrayList<Integer> res = new ArrayList<>();
        for(int a: arr){
            if(!set.contains(a)){
                set.add(a);
                res.add(a);
            }
        }
        return res;
    }
}
