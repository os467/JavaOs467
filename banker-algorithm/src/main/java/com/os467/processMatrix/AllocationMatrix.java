package com.os467.processMatrix;

import com.os467.AvailableMatrix;

public class AllocationMatrix {
    private Integer[][] resources;
    public AllocationMatrix(int processNum,int resourceTotal){
        resources = new Integer[processNum][resourceTotal];
    }

    public AllocationMatrix(AllocationMatrix allocation, Integer resourceTotal) {
        this.resources = new Integer[allocation.resources.length][resourceTotal];
        for (int i = 0; i < resources.length; i++) {
            for (int j = 0; j < resourceTotal; j++) {
                this.resources[i][j] = allocation.resources[i][j];
            }
        }
    }

    public void register(int i, int j, int parseInt) {
        resources[i][j] = parseInt;
    }

    /**
     * 释放allocation
     * @param index
     * @param availableCopy
     */
    public void releaseAllocation(int index, AvailableMatrix availableCopy) {
        int length = this.resources[index].length;
        for (int i = 0; i < length; i++) {
            availableCopy.add(i,resources[index][i]);
        }
    }

    public void add(int processIndex, Integer[] request) {
        int length = this.resources[processIndex].length;
        for (int i = 0; i < length; i++) {
            this.resources[processIndex][i] += request[i];
        }
    }

    public boolean satisfy(int processIndex, MaxMatrix max, Integer resourceTotal) {
        Integer[][] maxResources = max.getResources();
        for (int i = 0; i < resourceTotal; i++) {
            if (maxResources[processIndex][i] > this.resources[processIndex][i]){
                return false;
            }
        }
        return true;
    }

    public void printAllocation(int i) {
        int length = resources[i].length;
        for (int j = 0; j < length-1; j++) {
            System.out.print(resources[i][j]+",");
        }
        System.out.print(resources[i][length-1]);
    }
}
