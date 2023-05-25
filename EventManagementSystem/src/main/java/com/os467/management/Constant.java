package com.os467.management;

import com.os467.management.entity.View;

public class Constant {

    //��󶥵���
    public static final int MAX_SIZE = 12;

    //���Ȩֵ������
    public static final int MAX_INT = 65535;

    public static final View[] views = new View[MAX_SIZE];

    //ͼ��·������
    public static final int[][] pre = new int[MAX_SIZE][MAX_SIZE];

    public static final int EDGE_NUM = 21;

    static {
        //��ʼ�����㳣��
        views[0] = new View(1,"��������","ѧ������¥Ⱥ");
        views[1] = new View(2,"��Էʳ��","��¥�Ĺ�����ζ��������������ֻҪ������");
        views[2] = new View(3,"����¥","��ѧ¥");
        views[3] = new View(4,"��������","�˶���ʩ��ȫ");
        views[4] = new View(5,"���ٳ�","����ʱ�и��ֻ����");
        views[5] = new View(6,"��ѧ¥","��ѧϰ�ĺ��Ӷ�ס������");
        views[6] = new View(7,"���ٳ�","��ѧ��������������");
        views[7] = new View(8,"��Էʳ��","��¥�԰ײ��伦��");
        views[8] = new View(9,"ͼ���","���ܻ�ͼ��ݣ������鼮Ӧ�о���");
        views[9] = new View(10,"�����¥","ѧУ�ر꽨��");
        views[10] = new View(11,"�����ѧԺ","ѧԺ¥��������������ʵ��");
        views[11] = new View(12,"������¥","������������У�ſ�");

        for (int i = 0; i < pre.length; i++) {
            for (int j = 0; j < pre[0].length; j++) {
                //��ʼ��Ϊȫ����ͨ��Ĭ��65535
                pre[i][j] = MAX_INT;
            }
        }

        //��ʼ���ڽӾ����·������
        //����·������
        link(0,1,100);
        link(0,3,250);
        link(1,3,150);
        link(1,2,80);
        link(1,4,140);
        link(2,4,120);
        link(2,9,110);
        link(3,4,50);
        link(3,6,250);
        link(4,5,100);
        link(4,9,100);
        link(5,6,100);
        link(5,7,130);
        link(5,8,100);
        link(5,9,160);
        link(5,10,250);
        link(6,7,80);
        link(7,8,100);
        link(8,11,280);
        link(9,10,110);
        link(10,11,250);


    }

    private static void link(int x, int y,int w) {
        pre[x][y] = w;
        pre[y][x] = w;
    }

    public static final String[][] categoryList =
            {
                    {"������ʵ��","���ݿ��ӻ�","��Ϣͼ�����","������Ϣ���","���ݿ��ӻ�"},
                    {"�˹�����ʵ����"},
                    {"��̬��ϢӰ��MG������","����","��¼Ƭ","���ֶ�Ƭ","΢��Ӱ","��ý������"},
                    {"��Ʒ���","�������","ƽ�����","����ý�����","��Ϸ���","������ʵVR����ǿ��ʵAR"},
                    {"WebӦ���뿪��","������Ϣϵͳ","�㷨�����Ӧ��","�ƶ�Ӧ�ÿ���","�ƶ�Ӧ�ÿ���������Ϸ�ࣩ"},
                    {"ҽҩ����","��������","�˶�����","���й���","��ҵӦ��"},
                    {"��������ѧ","�����������Ӧ����γ�΢��","����ʵ��ƽ̨","�С�Сѧ��ѧ����Ȼ��ѧ�γ�΢��"}
            };

    public static int getCategoryIndex(String eventCategory) {
        for (int i = 0; i < categoryList.length; i++) {
            for (int j = 0; j < categoryList[i].length; j++) {
                if (eventCategory.equals(categoryList[i][j])){
                    return i;
                }
            }
        }
        return -1;
    }

    public static View getViewById(String id) {
        for (int i = 0; i < views.length; i++) {
            if (views[i].getId() == Integer.parseInt(id)){
                return views[i];
            }
        }
        return null;
    }
}
