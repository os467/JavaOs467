package com.os467;


public class AvailableMatrix {
    private Integer[] resources;

    public AvailableMatrix(int resourceTotal) {
        resources = new Integer[resourceTotal];
    }

    public AvailableMatrix(AvailableMatrix available) {
        int length = available.resources.length;
        this.resources = new Integer[length];
        for (int i = 0; i < length; i++) {
            this.resources[i] = available.resources[i];
        }
    }

    public void register(int index,int resourceNum){
        this.resources[index] = resourceNum;
    }

    public Integer get(int i) {
        return resources[i];
    }

    public void remove(Integer[] request) {
        int length = request.length;
        for (int i = 0; i < length; i++) {
            resources[i] -= request[i];
        }
    }

    public void add(int i, Integer num) {
        this.resources[i] += num;
    }

    public boolean requestAvailable(Integer[] request) {
        for (int i = 0; i < this.resources.length; i++) {
            if (this.resources[i] < request[i]){
                return false;
            }
        }
        return true;
    }

    public Integer[] getResources() {
        return resources;
    }
}
