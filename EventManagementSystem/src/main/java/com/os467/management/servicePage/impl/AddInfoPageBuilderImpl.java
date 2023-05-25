package com.os467.management.servicePage.impl;

import com.os467.management.servicePage.AddInfoPageBuilder;
import com.os467.management.servicePage.FileService;
import com.os467.management.servicePage.InfoPageBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddInfoPageBuilderImpl implements AddInfoPageBuilder {

    private FileService fileService;

    private InfoPageBuilder infoPageBuilder;

    @Override
    public void buildPage(JPanel page) {

        page.setLayout(new GridLayout(3,1));

        JPanel jPanel = new JPanel(new GridLayout(6,1));

        JPanel subjectPanel = new JPanel(new GridLayout(1,3));
        JLabel subjectName = new JLabel("参赛作品名称");
        subjectName.setHorizontalAlignment(SwingConstants.RIGHT);
        subjectPanel.add(subjectName);
        TextField subjectField = new TextField();
        subjectPanel.add(subjectField);
        subjectPanel.add(new JPanel());



        JPanel schoolPanel = new JPanel(new GridLayout(1,3));
        JLabel schoolName = new JLabel("参赛学校");
        schoolName.setHorizontalAlignment(SwingConstants.RIGHT);
        schoolPanel.add(schoolName);
        TextField schoolField = new TextField();
        schoolPanel.add(schoolField);
        schoolPanel.add(new JPanel());

        JPanel eventPanel = new JPanel(new GridLayout(1,3));
        JLabel event = new JLabel("赛事类别");
        event.setHorizontalAlignment(SwingConstants.RIGHT);
        eventPanel.add(event);
        TextField eventField = new TextField();
        eventPanel.add(eventField,BorderLayout.CENTER);
        eventPanel.add(new JPanel());

        JPanel contestantPanel = new JPanel(new GridLayout(1,3));
        JLabel contestant = new JLabel("参赛者");
        contestant.setHorizontalAlignment(SwingConstants.RIGHT);
        contestantPanel.add(contestant);
        TextField contestantField = new TextField();
        contestantPanel.add(contestantField,BorderLayout.CENTER);
        contestantPanel.add(new JPanel());

        JPanel teacherPanel = new JPanel(new GridLayout(1,3));
        JLabel teacher = new JLabel("指导老师");
        teacher.setHorizontalAlignment(SwingConstants.RIGHT);
        teacherPanel.add(teacher);
        TextField teacherField = new TextField();
        teacherPanel.add(teacherField,BorderLayout.CENTER);
        teacherPanel.add(new JPanel());

        JPanel buttonPanel = new JPanel(new GridLayout(1,3));
        buttonPanel.add(new JPanel());
        JButton addButton = new JButton("登记");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String context = fileService.getContext();

                //获取到当前最大序列号
                String[] split = context.split("\n");
                int max = 0;

                for (int i = 1; i < split.length; i++) {
                    int num = Integer.parseInt(split[i].split("\t#\t")[0]);
                    if (num > max){
                        max = num;
                    }
                }
                //序列号增加
                ++max;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(context);
                stringBuilder.append(max);
                stringBuilder.append("\t#\t");
                stringBuilder.append(subjectField.getText());
                subjectField.setText("");
                stringBuilder.append("\t#\t");
                stringBuilder.append(schoolField.getText());
                schoolField.setText("");
                stringBuilder.append("\t#\t");
                stringBuilder.append(eventField.getText());
                eventField.setText("");
                stringBuilder.append("\t#\t");
                stringBuilder.append(contestantField.getText());
                contestantField.setText("");
                stringBuilder.append("\t#\t");
                stringBuilder.append(teacherField.getText());
                teacherField.setText("");
                stringBuilder.append("\t#\t");
                stringBuilder.append("\n");
                fileService.writeFileWithContext(stringBuilder.toString());
                infoPageBuilder.reBuildPage();
            }
        });
        addButton.setBackground(new Color(249, 229, 229));
        addButton.setHorizontalAlignment(SwingConstants.CENTER);
        buttonPanel.add(addButton);
        buttonPanel.add(new JPanel());

        //加入主控面板
        jPanel.add(subjectPanel);
        jPanel.add(schoolPanel);
        jPanel.add(eventPanel);
        jPanel.add(contestantPanel);
        jPanel.add(teacherPanel);
        jPanel.add(buttonPanel);

        JPanel titlePanel = new JPanel();
        page.add(titlePanel);
        page.add(jPanel);

    }

    @Override
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public void setInfoPageBuilder(InfoPageBuilder infoPageBuilder) {
        this.infoPageBuilder = infoPageBuilder;
    }
}
