package com.os467.management.entity;

public class View {
    //景点的编号
    private int id;

    //景点的名称
    private String name;

    //景点的介绍
    private String introduction;

    //精确x位置
    private int x;
    //精确y位置
    private int y;

    //构造方法
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