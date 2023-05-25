package com.os467.management.servicePage;

import javax.swing.*;
import java.io.*;

public class FileService {

    private String title;

    private String context;

    private JTable jTable;

    public String readFile() {
        //读取文件
        String property = System.getProperty("user.dir") + "\\src\\main\\resources\\team.txt";

        StringBuilder stringBuilder = null;
        BufferedReader bufferedReader = null;
        try {
            FileReader fileReader = new FileReader(property);
            bufferedReader = new BufferedReader(fileReader);
            stringBuilder = new StringBuilder();
            int read;
            while ((read = bufferedReader.read()) != -1){
                stringBuilder.append((char)read);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String context = stringBuilder.toString();
        this.context = context;
        return context;
    }

    public void writeFileWithJTable() {
        int rowCount = jTable.getRowCount();
        int columnCount = jTable.getColumnCount() - 2;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(title);
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount - 1; j++) {
                Object s = jTable.getValueAt(i, j);
                stringBuilder.append(s);
                stringBuilder.append("\t#\t");
            }
            Object s = jTable.getValueAt(i, columnCount - 1);
            stringBuilder.append(s);
            stringBuilder.append("\n");
        }
        String context = stringBuilder.toString();

        writeFileWithContext(context);
    }

    public void writeFileWithContext(String context){
        //文件位置
        String property = System.getProperty("user.dir") + "\\src\\main\\resources\\team.txt";

        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(property));
            bufferedWriter.write(context);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                this.context = context;
                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public JTable getjTable() {
        return jTable;
    }

    public void setjTable(JTable jTable) {
        this.jTable = jTable;
    }
}
