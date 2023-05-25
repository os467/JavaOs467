package com.os467.management.servicePage.impl;

import com.os467.management.Constant;
import com.os467.management.dataStruct.MyComparator;
import com.os467.management.entity.Team;
import com.os467.management.servicePage.EventPageBuilder;
import com.os467.management.servicePage.FileService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class EventPageBuilderImpl implements EventPageBuilder {

    private FileService fileService;

    private Boolean flag;

    @Override
    public void buildPage(JPanel page) {
        page.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(3,3));
        for (int i = 0; i < 4; i++) {
            buttonPanel.add(new JPanel());
        }

        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        JButton button = new JButton("赛事模拟");
        button.setBackground(new Color(223, 223, 223));
        flag = new Boolean(true);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String context = fileService.getContext();
                String[] rows = context.split("\n");

                //用于分组
                HashMap<Integer, List<Team>> hashMap = new HashMap<>();

                //获取到队伍列表
                for (int i = 1; i < rows.length; i++) {
                    Team team = new Team(rows[i].split("\t#\t"));
                    int categoryIndex = Constant.getCategoryIndex(team.getEventCategory());
                    List<Team> teams = hashMap.get(categoryIndex);
                    if (teams == null){
                        teams = new ArrayList<>();
                    }
                    teams.add(team);
                    hashMap.put(categoryIndex,teams);
                }

                Set<Integer> keySet = hashMap.keySet();

                StringBuilder stringBuilder = new StringBuilder();
                flag = true;
                int totalSpace = 35;
                MyComparator myComparator = new MyComparator();
                for (int i = 0; i < keySet.size(); i++) {
                    List<Team> teams = hashMap.get(i);
                    teams.sort(myComparator);
                    stringBuilder.append("-------第");
                    stringBuilder.append((i+1));
                    stringBuilder.append("组入场次序-------\n");
                    for (int j = 0; j < teams.size(); j++) {
                        if (!flag){
                            return;
                        }
                        stringBuilder.append(teams.get(j).getTid());
                        stringBuilder.append("\t");
                        String eventCategory = teams.get(j).getEventCategory();
                        eventCategory.replace(" ","");
                        int eL = eventCategory.length();
                        stringBuilder.append(eventCategory);
                        for (int k = 0; k < totalSpace - eL*2; k++) {
                            stringBuilder.append(" ");
                        }
                        String student = teams.get(j).getStudent();
                        student.replace(" ","");
                        eL = student.length();
                        stringBuilder.append(student);
                        for (int k = 0; k < totalSpace - eL*2; k++) {
                            stringBuilder.append(" ");
                        }
                        stringBuilder.append("准备入场\n");
                        textArea.setText(stringBuilder.toString());
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                    }
                }
            }
        });
        buttonPanel.add(button);
        for (int i = 0; i < 4; i++) {
            buttonPanel.add(new JPanel());
        }
        page.add(buttonPanel,BorderLayout.NORTH);
        page.add(textArea,BorderLayout.CENTER);
    }

    @Override
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

}
