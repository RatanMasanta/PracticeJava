package com.masanta.ratan.daily.practice.design.LLD;

public class DesignParkingSystem {
    static class ParkingSystem {

        // Number of empty slots for each type of car
        int[] empty;

        public ParkingSystem(int big, int medium, int small) {
            this.empty = new int[]{big, medium, small};
        }

        public boolean addCar(int carType) {

            // If space is available, allocate and return True
            if (this.empty[carType - 1] > 0) {
                this.empty[carType - 1]--;
                return true;
            }

            // Else, return False
            return false;
        }
    }

    static class ParkingSystem2 {

        // Parking limit of each type of car
        int[] limit;

        // Count of cars of each type
        int[] count;

        public ParkingSystem2(int big, int medium, int small) {

            // Parking limit of each type of car
            this.limit = new int[]{big, medium, small};

            // Count of cars of each type
            this.count = new int[]{0, 0, 0};
        }

        public boolean addCar(int carType) {

            // If space is available, allocate and return True
            if (this.count[carType - 1] < this.limit[carType - 1]) {
                this.count[carType - 1]++;
                return true;
            }

            // Else, return False
            return false;
        }
    }

    static class ParkingSystem3 {

        // Parking limit for each type of car
        int bigLimit, mediumLimit, smallLimit;

        // Count of cars of each type
        int bigCount, mediumCount, smallCount;

        public ParkingSystem3(int big, int medium, int small) {

            // Store the parking limit for each type of car
            this.bigLimit = big;
            this.mediumLimit = medium;
            this.smallLimit = small;

            // Count of cars of each type
            this.bigCount = 0;
            this.mediumCount = 0;
            this.smallCount = 0;
        }

        public boolean addCar(int carType) {

            // Depending on carType, decide
            if (carType == 1) {
                if (this.bigCount < this.bigLimit) {
                    this.bigCount++;
                    return true;
                }
                else {
                    return false;
                }
            }
            else if (carType == 2) {
                if (this.mediumCount < this.mediumLimit) {
                    this.mediumCount++;
                    return true;
                }
                else {
                    return false;
                }
            }
            else if (carType == 3) {
                if (this.smallCount < this.smallLimit) {
                    this.smallCount++;
                    return true;
                }
                else {
                    return false;
                }
            }

            // Return False if carType is invalid.
            // Although, this will never happen because of constraints.
            // But we are doing it for completeness.
            return false;
        }
    }

    static class ParkingSystem4 {

        // Parking limit for each type of car
        int bigLimit, mediumLimit, smallLimit;

        // Create an Array to store parked cars
        int[] parkingArray;

        public ParkingSystem4(int big, int medium, int small) {

            // Parking limit for each type of car
            this.bigLimit = big;
            this.mediumLimit = medium;
            this.smallLimit = small;

            // Create an Array to store parked cars
            this.parkingArray = new int[big + medium + small];
            for (int i = 0; i < this.parkingArray.length; i++) {
                this.parkingArray[i] = 0;
            }
        }

        public boolean addCar(int carType) {

            // Depending on carType, store the limit for the type of car
            int limit = 0;
            if (carType == 1) {
                limit = this.bigLimit;
            } else if (carType == 2) {
                limit = this.mediumLimit;
            } else {
                limit = this.smallLimit;
            }

            // Traverse linearly through the array from the left
            int count = 0;
            for (int i = 0; i < this.parkingArray.length; i++) {

                // Count the number of cars parked in the system of that type
                if (this.parkingArray[i] == carType) {
                    count++;
                }

                // Stop if the count becomes equal to the limit
                if (count == limit) {
                    return false;
                }

                // If the count is less than the limit, then add the car
                // to the first available empty slot from the left
                if (this.parkingArray[i] == 0) {
                    this.parkingArray[i] = carType;
                    return true;
                }
            }

            // If no empty slot is found, then return False.
            // However, this line will never be executed if count < limit
            // because slot will be found before count becomes equal to limit
            return false;
        }
    }

    /**
     * https://leetcode.com/problems/design-parking-system/editorial/
     */

    public static void main(String[] args) {
        ParkingSystem parkingSystem1 = new ParkingSystem(1,2,3);
        ParkingSystem2 parkingSystem2 = new ParkingSystem2(11,11,11);
        ParkingSystem3 parkingSystem3 = new ParkingSystem3(12,12,12);
        ParkingSystem4 parkingSystem4 = new ParkingSystem4(13,13,13);

        System.out.println("Medium Car " + parkingSystem1.addCar(2));
        System.out.println("Medium Car " + parkingSystem1.addCar(2));
        System.out.println("Medium Car " + parkingSystem1.addCar(2));
        System.out.println("Big Car " + parkingSystem1.addCar(1));
        System.out.println("Big Car " + parkingSystem1.addCar(1));
        System.out.println("Big Car " + parkingSystem1.addCar(1));
        System.out.println("Small Car " + parkingSystem1.addCar(3));
        System.out.println("Small Car " + parkingSystem1.addCar(3));
        System.out.println("Small Car " + parkingSystem1.addCar(3));
        System.out.println("Small Car " + parkingSystem1.addCar(3));
    }
}
