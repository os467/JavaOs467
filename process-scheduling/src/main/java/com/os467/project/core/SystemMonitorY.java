package com.os467.project.core;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class SystemMonitorY extends Thread{

    //等待队列
    private List<HomeWork> waitList;

    //就绪队列
    private List<HomeWork> readyList;

    //执行中的作业
    private HomeWork execution;

    private JPanel systemYPage;

    //作业数量
    private Integer num;

    //时间片
    private Integer runTime = 1;

    //时间进度条基数
    private Integer sumTime=0;

    //单位时间长度
    private Integer unitLength;

    //累计系统运行时间
    private Integer sumSystemRunTime = 0;

    //累计长度
    private Integer sumLength = 20;

    //系统运行时间
    private Integer systemRunTime = 0;

    //抵达计数器
    private Integer arriveCount = 0;

    //消息收集器
    private StringBuilder msg = new StringBuilder();

    private List<ExecuteMsg> executeMsgList = new ArrayList<>();

    class ExecuteMsg{
        Integer executeTime;
        Character workName;

        public ExecuteMsg(Integer executeTime, Character name) {
            this.executeTime = executeTime;
            this.workName = name;
        }
    }

    @Override
    public void run() {

        waitList = new ArrayList<>();
        readyList = new ArrayList<>();

        init();

        //模拟操作系统运作
        while (true){

            //检查等待队列任务
            checkWaitList();

            //检查就绪队列是否存在作业
            if (readyList.size() <= 0){
                systemRunTime += WorkContent.SYSTEM_RUN_TIME_UNIT;
                executeMsgList.add(new ExecuteMsg(WorkContent.SYSTEM_RUN_TIME_UNIT,null));
                continue;
            }

            //从就绪队列中获取队头作业
            HomeWork homeWork = getWork();

            //将其从就绪队列中删除
            removeFromReadyList(homeWork);

            //加入执行位
            execution = homeWork;

            //执行
            Integer executeTime = execute(execution);

            //增加系统运行时间
            for (int i = 0; i < executeTime; i++) {
                checkWaitList();
                systemRunTime += WorkContent.SYSTEM_RUN_TIME_UNIT;
            }

            executeMsgList.add(new ExecuteMsg(executeTime,homeWork.getName()));


            msg.append("========当前系统时间："+systemRunTime+"========\n");

            if (homeWork.getStatus().equals(WorkContent.FINISH_STATUS)){
                msg.append("进程-"+homeWork.getName()+"已完成，服务时间："+homeWork.getEndTime()+"\n");
            }else if (execution != null) {
                msg.append("进程-"+homeWork.getName()+"，进度：("+homeWork.getNowTime()+"/"+homeWork.getEndTime()+")\n");
            }


            for (HomeWork work : readyList) {
                msg.append("进程-"+work.getName()+"，进度：("+work.getNowTime()+"/"+work.getEndTime()+")\n");
            }
            msg.append("=============================\n");

            //检查是否都执行完毕
            if (readyList.size()<=0 && waitList.size() <= 0 && execution == null){
                break;
            }
        }

        //产生基准长度
        unitLength = (int)((systemYPage.getWidth()*0.85)/(double)(systemRunTime));

        for (ExecuteMsg executeMsg : executeMsgList) {

            //构建页面新数据
            updatePageInfo(executeMsg.executeTime,executeMsg.workName);

            //模拟执行时间
            try {
                Thread.sleep(100 * executeMsg.executeTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //更新界面数据
            SwingUtilities.updateComponentTreeUI(systemYPage);

        }


        JFrame parent = (JFrame)SwingUtilities.getRootPane(systemYPage).getParent();
        new MyDialog(parent,"运行结果",msg.toString());
        System.out.println(msg.toString());


    }

    /**
     * 检查等待作业入队
     */
    private void checkWaitList() {
        Iterator<HomeWork> iterator = waitList.iterator();
        while (iterator.hasNext()) {
            HomeWork work = iterator.next();
            if (work.getArriveTime().equals(systemRunTime)) {
                iterator.remove();
                readyList.add(work);
                msg.append("========当前系统时间："+systemRunTime+"========\n");
                msg.append("进程-"+work.getName()+"已抵达\n");
                msg.append("=============================\n");
            }
        }

        if (execution != null){
            readyList.add(execution);
            execution = null;
        }

    }

    /**
     * 更新页面数据
     */
    private void updatePageInfo(Integer executeTime, Character workName) {

        sumSystemRunTime+=executeTime;

        JPanel page = systemYPage;

        Character name = workName;

        int boxWidth = executeTime * unitLength;

        if (name != null) {
            Integer pageIndex = (int) (name) - 48 - 17;

            int height = page.getHeight();

            JButton homeWorkLabel = new JButton();
            homeWorkLabel.setBackground(new Color(0, 0, 0));
            int boxHeight = height / (num + 2) / 9;

            int x = sumLength;
            int y = height / (num + 2) * (pageIndex) + boxHeight * 4;

            homeWorkLabel.setBounds(
                    x,
                    y,
                    boxWidth,
                    boxHeight);
            //添加进度条
            page.add(homeWorkLabel);

            //添加当前系统时间
            JLabel jLabel = new JLabel(sumSystemRunTime.toString());
            jLabel.setFont(new Font("dialog", 1, 10));
            jLabel.setBounds(
                    x + boxWidth,
                    y + boxHeight,
                    boxWidth * 2,
                    boxHeight
            );
            page.add(jLabel);
        }

        sumLength+=boxWidth;

    }

    /**
     * 操作系统为任务执行一个时间片
     * @param homeWork
     */
    private Integer execute(HomeWork homeWork) {
        //结束时间
        Integer endTime = homeWork.getEndTime();
        //当前执行时间
        Integer nowTime = homeWork.getNowTime();

        //实际执行时间
        Integer executeTime = 0;

        if (nowTime + runTime >= endTime){
            executeTime = endTime - nowTime;
            //作业完成
            homeWork.setStatus(WorkContent.FINISH_STATUS);
            execution = null;
        }else {
            //时间片用完
            executeTime = runTime;
        }

        //更新当前时间
        homeWork.setNowTime(nowTime + runTime);

        return executeTime;
    }

    /**
     * 获取队头任务
     * @return
     */
    private HomeWork getWork() {
        return readyList.get(0);
    }

    /**
     * 从就绪队列中删除
     * @param homeWork
     */
    private void removeFromReadyList(HomeWork homeWork) {
        readyList.remove(homeWork);
    }

    private void init() {
        //创建作业，加入就绪队列
        for (int i = 0; i < num; i++) {
            HomeWork homeWork = new HomeWork();
            homeWork.setArriveTime(arriveCount++*runTime);
            homeWork.setName((char)(i+48+17));
            homeWork.setEndTime(3+new Random().nextInt(5));

            sumTime+=homeWork.getEndTime();

            //就绪状态
            homeWork.setStatus(WorkContent.READY_STATUS);
            homeWork.setNowTime(0);

            msg.append("进程-"+homeWork.getName()+"创建，预计用时："+homeWork.getEndTime()+"\n");

            //添加到等待队列
            waitList.add(homeWork);
        }

        //产生基准长度
        unitLength = (int)((systemYPage.getWidth()*0.85)/(double)(sumTime));
    }

    public void setPage(JPanel systemYPage) {
        this.systemYPage = systemYPage;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public void setRunTime(Integer runTime) {
        this.runTime = runTime;
    }
}
