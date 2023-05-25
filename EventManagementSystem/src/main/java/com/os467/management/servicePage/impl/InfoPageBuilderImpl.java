package com.os467.management.servicePage.impl;

import JFrameBuilder.component.table.TableCellButton;
import com.os467.management.servicePage.FileService;
import com.os467.management.servicePage.InfoPageBuilder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoPageBuilderImpl implements InfoPageBuilder {

    private FileService fileService;

    private JPanel page;

    @Override
    public void buildPage(JPanel page) {
        this.page = page;

        page.setLayout(new BorderLayout());
        String context = fileService.readFile();

        String[] split = context.split("\n");
        String[] cols = split[0].split("\t#\t");

        fileService.setTitle(split[0]+"\n");

        //创建表单
        DefaultTableModel tableModel  = new DefaultTableModel();
        for (String s : cols) {
            tableModel.addColumn(s);
        }
        tableModel.addColumn("");
        tableModel.addColumn("");

        String[] strings = null;
        for (int i = 1; i < split.length; i++) {
            strings = split[i].split("\t#\t");
            tableModel.addRow(strings);
        }
        System.out.println("Total:"+(split.length - 1));

        fileService.setjTable(new JTable(tableModel));
        TableCellButton tableCellButton = new TableCellButton(strings.length,tableModel.getRowCount(), fileService.getjTable());
        tableCellButton.registerOperation("修改",new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileService.writeFileWithJTable();
            }
        },new Color(236, 195, 114));

        //绑定表单项
        tableCellButton = new TableCellButton(strings.length+1,tableModel.getRowCount(), fileService.getjTable());
        tableCellButton.registerOperation("删除",new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String actionCommand = e.getActionCommand();
                DefaultTableModel tableModel = (DefaultTableModel)fileService.getjTable().getModel();
                tableModel.removeRow(Integer.parseInt(actionCommand));
                fileService.writeFileWithJTable();
            }
        },new Color(255, 88, 88));


        JScrollPane jScrollPane = new JScrollPane(fileService.getjTable());
        page.add(jScrollPane,BorderLayout.CENTER);
    }

    @Override
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public void reBuildPage() {
        page.removeAll();
        buildPage(page);
    }
}
