package com.masanta.ratan.companyInterviewQuestions;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class ZopSmart {

    /*
    Given the arrival and departure times of all trains that reach a railway station, the task is to find the minimum number of platforms required for the railway station so that no train waits.
We are given two arrays that represent the arrival and departure times of trains that stop.

Input: arr[] = {9:00, 9:40, 9:50, 11:00, 15:00, 18:00}
dep[] = {9:10, 12:00, 11:20, 11:30, 19:00, 20:00}
Output: 3
Explanation: There are at-most three trains at a time (time between 11:00 to 11:20)
Input: arr[] = {9:00, 9:40}
dep[] = {9:10, 12:00}
Output: 1
Explanation: Only one platform is needed.


Constraints: max number of trains: 100
We take the inputs as integer array
Sorted dep[] =  {9:10,11:20, 11:30, 12:00 19:00, 20:00}


Public int getMaxNoOfPlatformsRequired(int[] arrival, int[] departure){
	Int max = 0, noOfTrains = arrival.length;
	for(int i = 0; i < noOfTrains; i++){
		Int departureTime = departure[i];
		Int numberOfTrainsIncoming = trainCounter(departureTime, arrival);
		Int numberofTrainsDeparting = trainCounter(departureTime, departure);
		Int maxPresent = numberOfTrainsIncoming  - numberofTrainsDeparting;
		Max = Math.max(maxPresent, max);
	}
Return max;

}

// returning the number less than the value passed
Private int trainCounter(int target, int[] arr){
	Int count = 0;
	for(int j = 0; j < arr.length; j++){
		if(target >= arr[i]){
			count++;
		}
}
Return count;
}

     */

    /*
    Problem solution link: https://www.geeksforgeeks.org/minimum-number-platforms-required-railwaybus-station/
     */

    //MinHeap
    private static class TrainSchedule {
        int arrivalTime, deptTime;
        TrainSchedule(int arrivalTime, int deptTime)
        {
            this.arrivalTime = arrivalTime;
            this.deptTime = deptTime;
        }
        public String toString()
        {
            return "(" + this.arrivalTime + ","
                    + this.deptTime + ")";
        }
    }

    private static class SortByArrival
            implements Comparator<TrainSchedule> {
        @Override
        public int compare(TrainSchedule o1,
                           TrainSchedule o2)
        {
            return o1.arrivalTime - o2.arrivalTime;
        }
    }
    // Function to find the minimum number
    // of platforms required
    public static int countPlatforms(int[] arr, int[] dep)
    {
        TrainSchedule[] trains
                = new TrainSchedule[arr.length];
        // Store the arrival and departure time
        for (int i = 0; i < arr.length; i++) {
            trains[i] = new TrainSchedule(arr[i], dep[i]);
        }
        // Sort trains based on arrival time
        Arrays.sort(trains, new SortByArrival());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(trains[0].deptTime);
        int count = 1;
        for (int i = 1; i < arr.length; i++) {
            TrainSchedule curr = trains[i];
            // Check if arrival time of current train
            // is less than or equals to departure time
            // of previous train
            if (curr.arrivalTime <= pq.peek()) {
                count++;
            }
            else {
                pq.poll();
            }
            pq.add(curr.deptTime);
        }
        // return the count of number of platforms required
        return count;
    }

    public static void main(String[] args)
    {
        int[] arr = { 900, 940, 950, 1100, 1500, 1800 };
        int[] dep = { 910, 1200, 1120, 1130, 1900, 2000 };
        int res = countPlatforms(arr, dep);
        System.out.println(res);
    }

}
