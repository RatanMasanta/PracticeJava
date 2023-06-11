package com.masanta.ratan.daily.practice.design.LLD;

import java.util.TreeMap;

public class SnapShotClass {

    int snapId = 0;
    TreeMap<Integer, Integer>[] historyRecords;

    public SnapShotClass(int length) {
        historyRecords = new TreeMap[length];
        for (int i = 0; i < length; i++) {
            historyRecords[i] = new TreeMap<Integer, Integer>();
            historyRecords[i].put(0, 0);
        }
    }

    public void set(int index, int val) {
        historyRecords[index].put(snapId, val);
    }

    public int snap() {
        return snapId++;
    }

    public int get(int index, int snapId) {
        return historyRecords[index].floorEntry(snapId).getValue();
    }

    public static void main(String[] args) {
        SnapShotClass  snapshotArr = new SnapShotClass(3); // set the length to be 3
        snapshotArr.set(0,5);  // Set array[0] = 5
        System.out.println(snapshotArr.snap());  // Take a snapshot, return snap_id = 0
        snapshotArr.set(0,6);
        System.out.println(snapshotArr.get(0,0));  // Get the value of array[0] with snap_id = 0, return 5
    }
}
