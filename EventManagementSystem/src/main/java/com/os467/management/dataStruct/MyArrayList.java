package com.os467.management.dataStruct;

import java.util.ArrayList;
import java.util.Comparator;

public class MyArrayList<E> {

    private ArrayList<E> arrayList;

    private Comparator comparator;

    public MyArrayList() {
        this.arrayList = new ArrayList<>();
    }

    public void add(E e){
        arrayList.add(e);
    }

    public E get(Integer i){
        return arrayList.get(i);
    }

    /**
     * ʹ�ö�·�鲢������������ʱ�临�Ӷ���ԽϺ�����ͬ O(n*log_2(n))���Ƚ��ȶ�
     * @param comparator
     */
    public void sort(Comparator<E> comparator) {
        this.comparator = comparator;
        Object[] r = new Object[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            r[i] = arrayList.get(i);
        }
        Object[] r1 = new Object[arrayList.size()];

        //�����㷨
        mergeSort(r,r1,0,r.length-1);


        for (int i = 0; i < r.length; i++) {
            arrayList.set(i, (E) r[i]);
        }
    }


    /**
     * �ݹ��·�鲢
     * @param r
     * @param r1
     * @param left
     * @param right
     */
    private void mergeSort(Object[] r, Object[] r1, int left, int right) {
        if (left < right){
            int mid = (left + right) /2;
            mergeSort(r,r1,left,mid);
            mergeSort(r,r1,mid+1,right);
            merge(r,r1,left,mid,right);
            for (int j = left; j < right; j++) {
                r[j] = r1[j];
            }
        }
    }

    /**
     * ���������ι鲢
     * @param r
     * @param r1
     * @param s
     * @param m
     * @param t
     */
    private void merge(Object[] r, Object[] r1, int s, int m, int t) {
        //r[s~m]
        int i = s, j = m + 1, k = s;
        while (i <= m && j <= t){
            //r[i] <= r[j]
            if (comparator.compare(r[i],r[j]) != 1){
                r1[k++] = r[i++];
            }else {
                r1[k++] = r[j++];
            }
        }
        while (i <= m){
            r1[k++] = r[i++];
        }
        while (j <= t){
            r1[k++] = r[j++];
        }
    }

    public int size() {
        return arrayList.size();
    }
}
