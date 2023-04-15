package com.os467.processMatrix;

public class MaxMatrix {
    private Integer[][] resources;

    public MaxMatrix(int processNum, int resourceTotal) {
        resources = new Integer[processNum][resourceTotal];
    }

    public void register(int i, int j, Integer maxInput) {
        resources[i][j] = maxInput;
    }

    public Integer[][] getResources() {
        return resources;
    }

    public void printMax(int i) {
        int length = resources[i].length;
        for (int j = 0; j < length-1; j++) {
            System.out.print(resources[i][j]+",");
        }
        System.out.print(resources[i][length-1]);
    }
}
