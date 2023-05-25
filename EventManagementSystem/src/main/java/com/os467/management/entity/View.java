package com.os467.management.entity;

public class View {
    //����ı��
    private int id;

    //���������
    private String name;

    //����Ľ���
    private String introduction;

    //���췽��
    public View(int id, String name, String introduction) {
        this.id = id;
        this.name = name;
        this.introduction = introduction;
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
}