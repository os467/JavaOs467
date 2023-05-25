package com.os467.management.dataStruct;

import com.os467.management.entity.Team;

import java.util.Comparator;

public class MyComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        char[] e1;
        char[] e2;
        e1 = ((Team)o1).getEventCategory().toCharArray();
        e2 = ((Team)o2).getEventCategory().toCharArray();
        int len = e1.length;
        int ret = -1;
        if (e1.length > e2.length){
            len = e2.length;
            ret = 1;
        }
        for (int i = 0; i < len; i++) {
            int i1 = e1[i];
            int i2 = e2[i];
            if (i1 == i2){
                continue;
            }else if (i1 > i2){
                return 1;
            }else {
                return -1;
            }
        }
        if (e1.length == e2.length){
            return 0;
        }
        return ret;
    }
}
