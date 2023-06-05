package com.os467.management.entity;

public class View {
    //����ı��
    private int id;

    //���������
    private String name;

    //����Ľ���
    private String introduction;

    //��ȷxλ��
    private int x;
    //��ȷyλ��
    private int y;

    //���췽��
    public View(int id, String name, String introduction) {
        this.id = id;
        this.name = name;
        this.introduction = introduction;
    }

    public View(int id, String name, String introduction,int x,int y) {
        this.id = id;
        this.name = name;
        this.introduction = introduction;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}