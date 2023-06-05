package com.os467.management.servicePage.impl;

import com.os467.management.dataStruct.MyArrayList;
import com.os467.management.dataStruct.MyComparator;
import com.os467.management.dataStruct.MyTreeMap;
import com.os467.management.entity.Team;
import com.os467.management.servicePage.FileService;
import com.os467.management.servicePage.SearchPageBuilder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SearchPageBuilderImpl implements SearchPageBuilder {

    private FileService fileService;


    @Override
    public void buildPage(JPanel page) {

        page.setLayout(new BorderLayout());

        TextField field = new TextField();
        JButton searchButton = new JButton("队号查询");
        searchButton.setBackground(new Color(190, 252, 233));

        TextField field1 = new TextField();
        JButton schoolButton = new JButton("学校查询");
        schoolButton.setBackground(new Color(191, 245, 137));

        TextArea textArea = new TextArea();
        JTable jTable = new JTable();
        DefaultTableModel tableModel = (DefaultTableModel)jTable.getModel();
        String[] titles = fileService.getTitle().split("\t#\t");
        for (String title : titles) {
            tableModel.addColumn(title);
        }

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.setRowCount(0);
                String text = field.getText();
                field1.setText("");
                MyTreeMap<Integer, String> treeMap = new MyTreeMap<>();
                String[] split = fileService.getContext().split("\n");
                List<String> values = new ArrayList<>();
                List<Integer> keys = new ArrayList<>();
                for (int i = 1; i < split.length; i++) {
                    keys.add(Integer.parseInt(split[i].split("\t#\t")[0]));
                    values.add(split[i]);
                }
                treeMap.create(keys,values);
                double aslSuccess = treeMap.getAslSuccess();
                double aslFailed = treeMap.getAslFailed();
                String result = null;
                try {
                    result = treeMap.get(Integer.parseInt(text));
                    if (result != null){
                        tableModel.addRow(result.split("\t#\t"));
                    }
                }catch (NumberFormatException numberFormatException){
                    textArea.setText("数据格式非法!"+"\nASL_SUCCESS = "+aslSuccess+"\tASL_FAILED = "+aslFailed);
                    return;
                }
                if (result != null){
                    textArea.setText("查找成功!"+"\nASL_SUCCESS = "+aslSuccess+"\tASL_FAILED = "+aslFailed);
                }else {
                    textArea.setText("查找失败!"+"\nASL_SUCCESS = "+aslSuccess+"\tASL_FAILED = "+aslFailed);
                }
            }
        });

        schoolButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.setRowCount(0);
                String text = field1.getText();
                field.setText("");
                String[] split = fileService.getContext().split("\n");
                MyArrayList<Team> teams = new MyArrayList<>();
                //遍历表
                for (int i = 1; i < split.length; i++) {
                    String[] teamInfos = split[i].split("\t#\t");
                    //学校名称匹配
                    if (text.equals(teamInfos[2])){
                        //加入队列
                        teams.add(new Team(teamInfos));
                    }
                }
                teams.sort(new MyComparator());

                //反馈内容
                int size = teams.size();
                for (int i = 0; i < size; i++) {
                    tableModel.addRow(teams.get(i).getTeamInfos());
                }
            }
        });

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        jPanel.add(field,BorderLayout.CENTER);
        jPanel.add(searchButton,BorderLayout.EAST);
        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new BorderLayout());
        jPanel1.add(field1,BorderLayout.CENTER);
        jPanel1.add(schoolButton,BorderLayout.EAST);

        JPanel jPanel2 = new JPanel();
        jPanel2.setLayout(new GridLayout(2,1));
        jPanel2.add(jPanel);
        jPanel2.add(jPanel1);
        page.add(jPanel2,BorderLayout.NORTH);


        textArea.setEditable(false);
        page.add(textArea,BorderLayout.SOUTH);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        page.add(jScrollPane,BorderLayout.CENTER);
    }

    @Override
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

}
