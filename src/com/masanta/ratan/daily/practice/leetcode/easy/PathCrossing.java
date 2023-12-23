package com.masanta.ratan.daily.practice.leetcode.easy;

import java.util.*;

public class PathCrossing {

    /*
    Given a string path, where path[i] = 'N', 'S', 'E' or 'W', each representing moving one unit north, south, east, or west, respectively. You start at the origin (0, 0) on a 2D plane and walk on the path specified by path.

Return true if the path crosses itself at any point, that is, if at any time you are on a location you have previously visited. Return false otherwise.
     */
    public boolean isPathCrossing(String path) {
        // Initialize current position (0,0) and a set to track visited positions
        int x = 0, y = 0;
        Set<String> visited = new HashSet<>();
        visited.add("0,0");

        // Traverse each character in the path
        for (char direction : path.toCharArray()) {
            // Update current position based on the direction
            switch (direction) {
                case 'N':
                    y++;
                    break;
                case 'S':
                    y--;
                    break;
                case 'E':
                    x++;
                    break;
                case 'W':
                    x--;
                    break;
            }

            // Construct a string representing the current position
            String currentPos = x + "," + y;

            // Check if the current position has been visited before
            if (!visited.add(currentPos)) {
                return true; // If already visited, return true
            }
        }

        return false; // If no crossing is found, return false
    }


    private class Coordinate{
        int x =0;
        int y=0;
        Coordinate(int x,int y){
            this.x =x;
            this.y = y;
        }
        Coordinate(){
        }
        //Override method of equating two objects
        public boolean equals(Object obj){
            Coordinate s = (Coordinate) obj;
            return (x == s.x && y == s.y);
        }

        //Create custom hashcode
        public int hashCode(){
            return x * 100000 + y;
        }

        //Create custom toString method
        public String toString(){
            return "("+x +" ,  "+y+")";
        }
    }

    public boolean isPathCrossingUsingCustomClass(String path) {
        int tbC = 0;
        int lrC = 0;
        Map<Coordinate, Boolean> map = new HashMap<>();
        Coordinate s = new Coordinate(0,0);
        map.put(s,true);
        for(char c: path.toCharArray()){
            if(c == 'N'){
                tbC++;
            }else if(c == 'S'){
                tbC--;
            }else if(c == 'E'){
                lrC++;
            }else{
                lrC--;
            }
            s = new Coordinate(lrC,tbC);
            if(map.get(s) != null){
                return true;
            }
            map.put(s,true);
        }

        return false;
    }

    public static void main(String[] args) {
        String path = "NEEENNSSSWWWWE";
        PathCrossing pathCrossing = new PathCrossing();
        System.out.println(new Date().getTime());
        System.out.println(pathCrossing.isPathCrossing(path));
        System.out.println(new Date().getTime());
        System.out.println(pathCrossing.isPathCrossingUsingCustomClass(path));
        System.out.println(new Date().getTime());
    }


}
