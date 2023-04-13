package com.os467.project.jFrameBuild;

import JFrameBuilder.JFrameContentMaker;
import JFrameBuilder.component.LeftSelectMenu;
import com.os467.project.core.SystemMonitorX;
import com.os467.project.core.SystemMonitorY;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class JFrameContentMakerImpl implements JFrameContentMaker {


    //线程数量
    private Integer num = 4;

    //时间片
    private Integer runTime = 1;

    private LeftSelectMenu leftSelectMenu;

    private JPanel systemXPage;

    private JPanel systemYPage;

    private Thread runMonitor;

    @Override
    public void make(JFrame jFrame) {

        jFrame.getContentPane();

        leftSelectMenu = new LeftSelectMenu(jFrame, 3);

        leftSelectMenu.setDefaultPage(getDefaultPage());

        leftSelectMenu.name(0,"配置界面");
        leftSelectMenu.name(1,"优先权法");
        leftSelectMenu.name(2,"轮转法");

        systemXPage = leftSelectMenu.getPage(1);
        systemYPage = leftSelectMenu.getPage(2);

        systemPageBuild(leftSelectMenu.getPage(0));

    }

    /**
     * 创建介绍页面
     * @return
     */
    private JPanel getDefaultPage() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setBackground(new Color(234, 234, 234));
        JTextPane jTextPane = new JTextPane();
        jTextPane.setBounds(50,50,300,300);
        jTextPane.setFont(new Font("dialog",0,25));
        jTextPane.setText("  点击左侧菜单栏配置界面，配置完毕后再点击对应的方法");
        jTextPane.setEnabled(false);
        jPanel.add(jTextPane);
        return jPanel;
    }

    /**
     * 配置界面
     * @param page
     */
    private void systemPageBuild(JPanel page) {
        int width = page.getWidth();
        int height = page.getHeight();

        page.setLayout(null);

        JTextField numField = new JTextField("4");
        numField.setBackground(new Color(255, 255, 255));
        numField.setBounds(10,height/(num+2),width/10,height/10);
        page.add(numField);

        JButton startButton = new JButton("设置线程数量");
        startButton.setBackground(new Color(255, 255, 255));
        startButton.setBounds(width/10 + 10,height/(num+2),width/7,height/10);

        JTextField runTimeField = new JTextField("1");
        runTimeField.setBackground(new Color(255, 255, 255));
        runTimeField.setBounds(10,height/(num+2)*2,width/10,height/10);
        page.add(runTimeField);

        JButton runTimeButton = new JButton("设置时间片");
        runTimeButton.setBackground(new Color(255, 255, 255));
        runTimeButton.setBounds(width/10 + 10,height/(num+2)*2,width/7,height/10);


        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numFieldText = numField.getText();
                String runTimeText = runTimeField.getText();
                num = Integer.parseInt(numFieldText);
                runTime = Integer.parseInt(runTimeText);
                systemPageClear(systemXPage);
                systemXPageBuild(systemXPage);

                systemPageClear(systemYPage);
                systemYPageBuild(systemYPage);
            }
        };

        //线程数更新监听事件
        startButton.addActionListener(actionListener);
        runTimeButton.addActionListener(actionListener);
        page.add(startButton);
        page.add(runTimeButton);

    }

    /**
     * 清除页面
     * @param page
     */
    public void systemPageClear(JPanel page) {
        page.removeAll();
    }

    /**
     * 优先权法渲染界面实现
     * @param page
     */
    public void systemXPageBuild(JPanel page) {

        int width = page.getWidth();
        int height = page.getHeight();

        page.setLayout(null);

        for (int i = 0; i < num; i++) {
            JLabel homeWorkLabel = new JLabel(""+(char)(i+48+17));
            homeWorkLabel.setBounds(10,height/(num+2)*i,width,height/(num+2));
            page.add(homeWorkLabel);
        }

        JButton startButton = new JButton("开始");
        startButton.setBackground(new Color(255, 255, 255));
        startButton.setBounds(10,height/(num+2)*num,width/10,height/10);
        page.add(startButton);


        //绑定监听事件
        startButton.addActionListener(new SystemXStarter());


    }

    /**
     * 系统Y执行监听事件
     */
    class SystemYStarter implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (runMonitor != null && runMonitor.isAlive()){
                runMonitor.stop();
            }
            systemPageClear(systemYPage);
            systemYPageBuild(systemYPage);
            SystemMonitorY systemMonitorY = new SystemMonitorY();
            runMonitor = systemMonitorY;
            systemMonitorY.setPage(systemYPage);
            systemMonitorY.setNum(num);
            systemMonitorY.setRunTime(runTime);

            //启动
            systemMonitorY.start();
        }
    }

    /**
     * 系统X执行监听事件
     */
    class SystemXStarter implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (runMonitor != null && runMonitor.isAlive()){
                runMonitor.stop();
            }
            systemPageClear(systemXPage);
            systemXPageBuild(systemXPage);
            SystemMonitorX systemMonitorX = new SystemMonitorX();
            runMonitor = systemMonitorX;
            systemMonitorX.setPage(systemXPage);
            systemMonitorX.setNum(num);

            //启动
            systemMonitorX.start();
        }
    }



    /**
     * 轮转法渲染界面实现
     * @param page
     */
    public void systemYPageBuild(JPanel page) {

        int width = page.getWidth();
        int height = page.getHeight();

        page.setLayout(null);

        for (int i = 0; i < num; i++) {
            JLabel homeWorkLabel = new JLabel(""+(char)(i+48+17));
            homeWorkLabel.setBounds(10,height/(num+2)*i,width,height/(num+2));
            page.add(homeWorkLabel);
        }

        JButton startButton = new JButton("开始");
        startButton.setBackground(new Color(255, 255, 255));
        startButton.setBounds(10,height/(num+2)*num,width/10,height/10);
        page.add(startButton);


        startButton.addActionListener(new SystemYStarter());
    }
}
