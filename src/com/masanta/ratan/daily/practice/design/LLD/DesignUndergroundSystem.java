package com.masanta.ratan.daily.practice.design.LLD;

import java.util.HashMap;
import java.util.Map;

public class DesignUndergroundSystem {

    /*
        1396. Design Underground System

        An underground railway system is keeping track of customer travel times between different stations. They are using this data to calculate the average time it takes to travel from one station to another.

        Implement the UndergroundSystem class:

        void checkIn(int id, string stationName, int t)
        A customer with a card ID equal to id, checks in at the station stationName at time t.
        A customer can only be checked into one place at a time.
        void checkOut(int id, string stationName, int t)
        A customer with a card ID equal to id, checks out from the station stationName at time t.
        double getAverageTime(string startStation, string endStation)
        Returns the average time it takes to travel from startStation to endStation.
        The average time is computed from all the previous traveling times from startStation to endStation that happened directly, meaning a check in at startStation followed by a check out from endStation.
        The time it takes to travel from startStation to endStation may be different from the time it takes to travel from endStation to startStation.
        There will be at least one customer that has traveled from startStation to endStation before getAverageTime is called.
        You may assume all calls to the checkIn and checkOut methods are consistent. If a customer checks in at time t1 then checks out at time t2, then t1 < t2. All events happen in chronological order.



        Example 1:

        Input
        ["UndergroundSystem","checkIn","checkIn","checkIn","checkOut","checkOut","checkOut","getAverageTime","getAverageTime","checkIn","getAverageTime","checkOut","getAverageTime"]
        [[],[45,"Leyton",3],[32,"Paradise",8],[27,"Leyton",10],[45,"Waterloo",15],[27,"Waterloo",20],[32,"Cambridge",22],["Paradise","Cambridge"],["Leyton","Waterloo"],[10,"Leyton",24],["Leyton","Waterloo"],[10,"Waterloo",38],["Leyton","Waterloo"]]

        Output
        [null,null,null,null,null,null,null,14.00000,11.00000,null,11.00000,null,12.00000]

        Explanation
        UndergroundSystem undergroundSystem = new UndergroundSystem();
        undergroundSystem.checkIn(45, "Leyton", 3);
        undergroundSystem.checkIn(32, "Paradise", 8);
        undergroundSystem.checkIn(27, "Leyton", 10);
        undergroundSystem.checkOut(45, "Waterloo", 15);  // Customer 45 "Leyton" -> "Waterloo" in 15-3 = 12
        undergroundSystem.checkOut(27, "Waterloo", 20);  // Customer 27 "Leyton" -> "Waterloo" in 20-10 = 10
        undergroundSystem.checkOut(32, "Cambridge", 22); // Customer 32 "Paradise" -> "Cambridge" in 22-8 = 14
        undergroundSystem.getAverageTime("Paradise", "Cambridge"); // return 14.00000. One trip "Paradise" -> "Cambridge", (14) / 1 = 14
        undergroundSystem.getAverageTime("Leyton", "Waterloo");    // return 11.00000. Two trips "Leyton" -> "Waterloo", (10 + 12) / 2 = 11
        undergroundSystem.checkIn(10, "Leyton", 24);
        undergroundSystem.getAverageTime("Leyton", "Waterloo");    // return 11.00000
        undergroundSystem.checkOut(10, "Waterloo", 38);  // Customer 10 "Leyton" -> "Waterloo" in 38-24 = 14
        undergroundSystem.getAverageTime("Leyton", "Waterloo");    // return 12.00000. Three trips "Leyton" -> "Waterloo", (10 + 12 + 14) / 3 = 12
     */

    private Map<Integer, CheckInInfo> checkIns;
    private Map<String, TravelInfo> travelTimes;

    public DesignUndergroundSystem() {
        checkIns = new HashMap<>();
        travelTimes = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkIns.put(id, new CheckInInfo(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        CheckInInfo checkInInfo = checkIns.remove(id);
        String travel = checkInInfo.stationName + "," + stationName;
        int travelTime = t - checkInInfo.checkInTime;

        if (travelTimes.containsKey(travel)) {
            TravelInfo travelInfo = travelTimes.get(travel);
            travelInfo.totalTime += travelTime;
            travelInfo.count++;
        } else {
            travelTimes.put(travel, new TravelInfo(travelTime, 1));
        }
    }

    public double getAverageTime(String startStation, String endStation) {
        String travel = startStation + "," + endStation;
        TravelInfo travelInfo = travelTimes.get(travel);
        return (double) travelInfo.totalTime / travelInfo.count;
    }

    private class CheckInInfo {
        String stationName;
        int checkInTime;

        public CheckInInfo(String stationName, int checkInTime) {
            this.stationName = stationName;
            this.checkInTime = checkInTime;
        }
    }

    private class TravelInfo {
        int totalTime;
        int count;

        public TravelInfo(int totalTime, int count) {
            this.totalTime = totalTime;
            this.count = count;
        }
    }

    public static void main(String[] args) {
        DesignUndergroundSystem undergroundSystem = new DesignUndergroundSystem();
        undergroundSystem.checkIn(10, "Leyton", 3);
        undergroundSystem.checkOut(10, "Paradise", 8); // Customer 10 "Leyton" -> "Paradise" in 8-3 = 5
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Paradise")); // return 5.00000, (5) / 1 = 5
        undergroundSystem.checkIn(5, "Leyton", 10);
        undergroundSystem.checkOut(5, "Paradise", 16); // Customer 5 "Leyton" -> "Paradise" in 16-10 = 6
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Paradise")); // return 5.50000, (5 + 6) / 2 = 5.5
        undergroundSystem.checkIn(2, "Leyton", 21);
        undergroundSystem.checkOut(2, "Paradise", 30); // Customer 2 "Leyton" -> "Paradise" in 30-21 = 9
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Paradise")); // return 6.66667, (5 + 6 + 9) / 3 = 6.66667
    }

    /**
     *
     * Approach:
     * To design the "Underground System," we can use two data structures: one to store the check-in information and another to store the travel times. Here is the overall approach:
     *
     * For check-in:
     * When a customer checks in, store their ID along with the station name and check-in time in a dictionary or map. This will allow us to retrieve the check-in information later when the customer checks out.
     * For check-out:
     * When a customer checks out, retrieve their check-in information from the dictionary using their ID.
     * Calculate the travel time by subtracting the check-in time from the current check-out time.
     * Update the travel times dictionary:
     * If the (start_station, end_station) pair already exists in the dictionary, update the total time and increment the count.
     * Otherwise, create a new entry in the dictionary with the initial travel time and count.
     * For calculating average travel time:
     * Retrieve the total time and count from the travel times dictionary for the given (startStation, endStation) pair.
     * Calculate and return the average travel time by dividing the total time by the count.
     * Intuition:
     * The approach leverages the concept of storing relevant information at check-in and using it to calculate the travel times at check-out. By storing the check-in information in a dictionary or map, we can easily retrieve it when needed. Similarly, the travel times are stored in another dictionary, where the (start_station, end_station) pair serves as the key, and the total time and count are stored as values.
     *
     * The use of these data structures allows for efficient retrieval and update operations. The solution optimizes both time and space complexity by removing unnecessary check-in information once the customer has checked out and by utilizing appropriate data structures to track travel times efficiently.
     *
     *
     */


}
