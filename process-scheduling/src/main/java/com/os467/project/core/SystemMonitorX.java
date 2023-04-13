package com.os467.project.core;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class SystemMonitorX extends Thread{

    //等待队列
    private List<HomeWork> waitList;

    //就绪队列
    private List<HomeWork> readyList;

    //执行中的作业
    private HomeWork execution;

    private JPanel systemXPage;

    //作业数量
    private Integer num;

    //单位时间长度
    private Integer unitLength;

    //累计系统运行时间
    private Integer sumSystemRunTime = 0;

    //累计长度
    private Integer sumLength = 20;

    //抵达计数器
    private Integer arriveCount = 0;

    //系统运行时间
    private Integer systemRunTime = 0;

    //消息收集器
    private StringBuilder msg = new StringBuilder();

    int testC = 0;

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
            if (readyList.size() <= 0 && execution == null){
                systemRunTime += WorkContent.SYSTEM_RUN_TIME_UNIT;
                executeMsgList.add(new ExecuteMsg(WorkContent.SYSTEM_RUN_TIME_UNIT,null));
                continue;
            }

            //从就绪队列中获取最高优先级任务
            HomeWork homeWork = selectHighestLevelWork();

            HomeWork executionWorkCache;
            if (systemRunTime % WorkContent.SYSTEM_CHECK_LEVEL_TIME_INTERVAL == 0 && homeWork != null){
                if (execution == null){
                    //将其从就绪队列中删除
                    removeAndExecute(homeWork);
                }else if ( homeWork.getLevel() > execution.getLevel()){
                    //抢占当前任务
                    msg.append("进程-"+homeWork.getName()+",优先级："+homeWork.getLevel()+",抢占进程-"+execution.getName()+",优先级："+execution.getLevel()+"\n");
                    readyList.add(execution);
                    removeAndExecute(homeWork);
                }

            }else if (homeWork != null){
                if (execution == null){
                    //将其从就绪队列中删除
                    removeAndExecute(homeWork);
                }
            }

            executionWorkCache = execution;
            //执行，获取执行时间
            Integer executeTime = execute(execution);

            //增加系统运行时间
            for (int i = 0; i < executeTime; i++) {
                systemRunTime += WorkContent.SYSTEM_RUN_TIME_UNIT;
                checkWaitList();

            }


            executeMsgList.add(new ExecuteMsg(executeTime,executionWorkCache.getName()));


            msg.append("========当前系统时间："+systemRunTime+"========\n");
            if (executionWorkCache.getStatus().equals(WorkContent.FINISH_STATUS)){
                msg.append("进程-"+executionWorkCache.getName()+"已完成，服务时间："+executionWorkCache.getEndTime()+"\n");
            }else if (execution != null){
                msg.append("进程-"+execution.getName()+"，进度：("+execution.getNowTime()+"/"+execution.getEndTime()+")，"+"优先级："+execution.getLevel()+"\n");
            }

            for (HomeWork work : readyList) {
                msg.append("进程-"+work.getName()+"，进度：("+work.getNowTime()+"/"+work.getEndTime()+")，"+"优先级："+work.getLevel()+"\n");
            }

            msg.append("=============================\n");

            //检查是否都执行完毕
            if (readyList.size()<=0 && waitList.size() <= 0 && execution == null){
                break;
            }
        }

        //产生基准长度
        unitLength = (int)((systemXPage.getWidth()*0.85)/(double)(systemRunTime));

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
            SwingUtilities.updateComponentTreeUI(systemXPage);

        }

        JFrame parent = (JFrame)SwingUtilities.getRootPane(systemXPage).getParent();
        new MyDialog(parent,"运行结果",msg.toString());
        System.out.println(msg.toString());

    }

    private void removeAndExecute(HomeWork homeWork) {
        removeFromReadyList(homeWork);
        execution = homeWork;
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

    }

    private void init() {
        //创建作业，加入就绪队列，优先级依次排列
        for (int i = 0; i < num; i++) {
            HomeWork homeWork = new HomeWork();
            homeWork.setArriveTime(arriveCount++*2);
            homeWork.setName((char)(i+48+17));
            homeWork.setEndTime(3+new Random().nextInt(5));

            //产生优先级 0优先级最低
            homeWork.setLevel(10+new Random().nextInt(20));
            //就绪状态
            homeWork.setStatus(WorkContent.READY_STATUS);
            homeWork.setNowTime(0);

            msg.append("进程-"+homeWork.getName()+"创建，预计用时："+homeWork.getEndTime()+"\n");

            //添加到等待队列
            waitList.add(homeWork);
        }

    }

    /**
     * 更新页面数据
     * @param executeTime
     */
    private void updatePageInfo(Integer executeTime, Character workName) {

        sumSystemRunTime += executeTime;

        JPanel page = systemXPage;

        Character name = workName;

        int boxWidth = executeTime * unitLength;
        if (name != null){
            Integer pageIndex = (int)(name)-48-17;

            int height = page.getHeight();

            JButton homeWorkLabel = new JButton();
            homeWorkLabel.setBackground(new Color(0,0,0));
            int boxHeight = height/(num+2)/9;


            int x = sumLength;
            int y = height/(num+2)*(pageIndex)+boxHeight*4;

            homeWorkLabel.setBounds(
                    x,
                    y,
                    boxWidth,
                    boxHeight);
            //添加进度条
            page.add(homeWorkLabel);

            //添加当前系统时间
            JLabel jLabel = new JLabel(sumSystemRunTime.toString());
            jLabel.setFont(new Font("dialog",1,10));
            jLabel.setBounds(
                    x+boxWidth,
                    y+boxHeight,
                    boxWidth*2,
                    boxHeight
            );
            page.add(jLabel);
        }

        sumLength+=boxWidth;

    }

    /**
     * 操作系统为任务执行
     * @param homeWork
     */
    private Integer execute(HomeWork homeWork) {
        //结束时间
        Integer endTime = homeWork.getEndTime();
        //当前执行时间
        Integer nowTime = homeWork.getNowTime();

        //实际执行时间
        Integer executeTime = WorkContent.SYSTEM_RUN_TIME_UNIT;

        //降低优先级
        execution.setLevel(execution.getLevel() - WorkContent.LEVEL_DOWN_UNIT);

        if (nowTime + WorkContent.SYSTEM_RUN_TIME_UNIT >= endTime){
            executeTime = endTime - nowTime;
            //作业完成
            homeWork.setStatus(WorkContent.FINISH_STATUS);
            execution = null;
        }

        //更新当前时间
        homeWork.setNowTime(nowTime + WorkContent.SYSTEM_RUN_TIME_UNIT);

        return executeTime;
    }

    /**
     * 从就绪队列中删除
     * @param homeWork
     */
    private void removeFromReadyList(HomeWork homeWork) {
        readyList.remove(homeWork);
    }



    /**
     * 选择最高优先权的就绪状态作业
     * @return
     */
    private HomeWork selectHighestLevelWork() {
        Integer index = null;
        Integer level = null;
        //获取最高优先权就绪状态任务索引
        for (int i = 0; i < readyList.size(); i++) {
            HomeWork homeWork = readyList.get(i);
            if (index == null && homeWork.getStatus().equals(WorkContent.READY_STATUS)){
                index = i;
                level = homeWork.getLevel();
            }else if (homeWork.getStatus().equals(WorkContent.READY_STATUS)){
                Integer nl = homeWork.getLevel();
                if (nl > level){
                    level = nl;
                    index = i;
                }
            }
        }

        if (index != null){
            return readyList.get(index);
        }else {
            return null;
        }
    }

    public List<HomeWork> getReadyList() {
        return readyList;
    }

    public void setPage(JPanel systemXPage) {
       this.systemXPage = systemXPage;
    }

    public void setNum(Integer num) {
        this.num = num;
    }


}


