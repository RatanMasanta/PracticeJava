package com.masanta.ratan.daily.practice.leetcode.easy;

import java.util.ArrayList;

public class MaximumAreaOfLongestDiagonalRectangle {

    void main(){
        int[][] dimensions = {{9,3},{8,6}};
        System.out.println(areaOfMaxDiagonal(dimensions));
        System.out.println(areaOfMaxDiagonalApproach2(dimensions));
    }


    public int areaOfMaxDiagonal(int[][] dimensions) {
        int max = Integer.MIN_VALUE;
        double diagonalLength = -1d;
        for(int [] pair : dimensions){
            double diagonalSize = (pair[0] * pair[0]) + (pair[1]*pair[1]);
            if(diagonalLength < diagonalSize){
                diagonalLength = diagonalSize;
                max = pair[0]*pair[1];
            } else if (diagonalLength == diagonalSize){
                max = Math.max(max, pair[0]*pair[1]);
            }
        }
        return max;
    }

    public int areaOfMaxDiagonalApproach2(int[][] dimensions) {
        int index = -1, n = dimensions.length, area = 0;
        int maxDiagonalSquared = Integer.MIN_VALUE;
        ArrayList<Integer> sameDiagonalSizeList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int length = dimensions[i][0];
            int breadth = dimensions[i][1];
            int diagonalSquared = (breadth * breadth) + (length * length);

            if (diagonalSquared > maxDiagonalSquared) {
                maxDiagonalSquared = diagonalSquared;
                index = i;
                sameDiagonalSizeList.clear();
                sameDiagonalSizeList.add(i); // add the new max index
            } else if (diagonalSquared == maxDiagonalSquared) {
                sameDiagonalSizeList.add(i);
            }
        }

        if (sameDiagonalSizeList.size() == 1) {
            return dimensions[index][0] * dimensions[index][1];
        } else {
            for (int idx : sameDiagonalSizeList) {
                int currArea = dimensions[idx][0] * dimensions[idx][1];
                area = Math.max(area, currArea);
            }
            return area;
        }
    }

}
