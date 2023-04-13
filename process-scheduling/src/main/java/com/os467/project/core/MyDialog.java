package com.os467.project.core;

import javax.swing.*;
import java.awt.*;

/**
 * 运行结果弹窗
 */
public class MyDialog extends JDialog {
    public MyDialog(Frame owner, String title, String msg) {
        super(owner, title);// 调用JDialog父类的构造方法
        setVisible(true);
        // 创建一个具有指定标题和指定所有者的对话框。
        JTextArea jTextArea = new JTextArea();
        jTextArea.setEditable(false);
        jTextArea.setText(msg);
        jTextArea.setFont(new Font("dialog",0,18));
        JScrollPane jsp = new JScrollPane(jTextArea);
        //设置矩形大小.参数依次为(矩形左上角横坐标x,矩形左上角纵坐标y，矩形长度，矩形宽度)
        jsp.setBounds(13, 10, owner.getWidth()/3, owner.getHeight());
        add(jsp);
        setSize(owner.getWidth()/3, owner.getHeight());//大小
        // setModal(true);//设置为模态
        setLocationRelativeTo(null);//居中
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);//当点击对话框右上角的关闭按钮时,销毁该对话框

    }
}
