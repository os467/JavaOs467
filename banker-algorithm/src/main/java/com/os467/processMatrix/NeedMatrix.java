package com.os467.processMatrix;

import com.os467.AvailableMatrix;

import java.util.Random;

public class NeedMatrix {
    private Integer[][] resources;
    public NeedMatrix(int processNum,int resourceTotal){
        resources = new Integer[processNum][resourceTotal];
    }

    public NeedMatrix(NeedMatrix need, Integer resourceTotal) {
        this.resources = new Integer[need.resources.length][resourceTotal];
        for (int i = 0; i < need.resources.length; i++) {
            for (int j = 0; j < resourceTotal; j++) {
                this.resources[i][j] = need.resources[i][j];
            }
        }
    }

    public void register(int i, int j, int parseInt) {
        resources[i][j] = parseInt;
    }

    public Integer[] getRandomResourcesRequest(Integer index,Integer resourceTotal) {
        Integer[] randomResourcesRequest = new Integer[resourceTotal];
        Random random = new Random();
        for (int i = 0; i < resourceTotal; i++) {
            Integer needNum = resources[index][i];
            if (needNum > 0){
                randomResourcesRequest[i] = 1 + random.nextInt(resources[index][i]);
            }else {
                randomResourcesRequest[i] = 0;
            }
        }
        return randomResourcesRequest;
    }

    /**
     * 判断available能否满足对应index进程的need
     * @param index
     * @param availableMatrix
     * @return
     */
    public boolean satisfy(int index, AvailableMatrix availableMatrix) {
        int length = resources[index].length;
        for (int i = 0; i < length; i++) {
            if (resources[index][i] > availableMatrix.get(i)){
                return false;
            }
        }
        return true;
    }

    public void remove(int processIndex, Integer[] request) {
        int length = this.resources[processIndex].length;
        for (int i = 0; i < length; i++) {
            this.resources[processIndex][i] -= request[i];
        }
    }

    public void printNeed(int i) {
        int length = resources[i].length;
        for (int j = 0; j < length-1; j++) {
            System.out.print(resources[i][j]+",");
        }
        System.out.print(resources[i][length-1]);
    }
}
