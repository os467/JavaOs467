package com.os467.project.core;

/**
 * 作业进程
 */
public class HomeWork {

    //抵达时间
    private Integer arriveTime;

    //作业名
    private Character name;

    //当前优先级
    private Integer level;

    //作业所需完成时间
    private Integer endTime;

    //作业已执行时间
    private Integer nowTime = 0;

    //当前状态 0 就绪 1 执行 2 结束
    private Integer status;

    public Integer getArriveTime() {
        return arriveTime;
    }

    public Character getName() {
        return name;
    }

    public void setName(Character name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public Integer getNowTime() {
        return nowTime;
    }

    public void setNowTime(Integer nowTime) {
        this.nowTime = nowTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setArriveTime(Integer arriveTime) {
        this.arriveTime = arriveTime;
    }
}
