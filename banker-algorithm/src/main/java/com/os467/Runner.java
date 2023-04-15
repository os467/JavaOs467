package com.os467;

import com.os467.processMatrix.AllocationMatrix;
import com.os467.processMatrix.MaxMatrix;
import com.os467.processMatrix.NeedMatrix;

import java.util.Random;
import java.util.Scanner;

public class Runner {

    //进程最大资源
    private MaxMatrix max;

    //进程已获得资源
    private AllocationMatrix allocation;

    //进程需要资源
    private NeedMatrix need;

    //当前系统资源
    private AvailableMatrix available;

    //资源类型总数
    private Integer resourceTotal;

    //进程数量
    private Integer processNum;

    //已完成进程数量
    private Integer finishProcessNum = 0;

    //进程结束标识位(false未结束/true结束)
    private Boolean[] finishStatus;

    private Integer systemRunTime = 0;

    //初始化
    public void init(){
        Scanner scanner = new Scanner(System.in);
        register(scanner);
    }

    public void run(){
        Random random = new Random(processNum);
        while (true){

            //进程结束
            if (finishProcessNum.equals(processNum)){
                break;
            }
            //随机获取一个进程
            int processIndex = random.nextInt(processNum);

            //判断进程是否结束
            if (finishStatus[processIndex]){
                continue;
            }

            System.out.println("====================================");
            System.out.println("当前系统进程资源分配情况");
            System.out.println("进程名称        Max               Allocation               Need");
            for (int i = 0; i < processNum; i++) {
                System.out.print("进程"+(i+1)+"：         ");
                max.printMax(i);
                for (int j = 0; j < 19 - resourceTotal * 2; j++) {
                    System.out.print(" ");
                }
                allocation.printAllocation(i);
                for (int j = 0; j < 26 - resourceTotal * 2; j++) {
                    System.out.print(" ");
                }
                need.printNeed(i);
                System.out.println();
            }
            if (!requestCheck(processIndex)){
                //无法满足该进程需求
                continue;
            }
            System.out.println("====================================\n");

            if (finish(finishStatus)){
                break;
            }

            systemRunTime++;
            if (systemRunTime > 1000){
                throw new RuntimeException("系统运行超时");
            }
        }
        System.out.println("模拟结束");
        System.out.print("系统目前资源数量：");
        Integer[] resources = available.getResources();
        for (Integer resource : resources) {
            System.out.print(resource+";");
        }
    }

    /**
     * 资源申请检查
     * @param processIndex
     */
    private boolean requestCheck(int processIndex) {
        Integer[] request = need.getRandomResourcesRequest(processIndex, resourceTotal);

        System.out.print("进程"+(processIndex+1)+"向操作系统申请资源：");
        for (int i = 0; i < request.length; i++) {
            System.out.print(request[i]+";");
        }
        System.out.println();

        System.out.print("系统目前资源数量：");
        Integer[] resources = available.getResources();
        for (Integer resource : resources) {
            System.out.print(resource+";");
        }
        //判断是否可以满足此次申请
        if (!requestAvailable(available,request)){
            System.out.println(" 拒绝受理");
            return false;
        }else {
            System.out.println(" 可满足");
        }

        System.out.println("---------------系统将检查本次分配后的安全状态---------------");
        //判断满足此次请求后系统是否安全
        if (!checkSafety(processIndex, request)){
            return false;
        }
        System.out.println("---------------------------------------------------------");

        //可以满足，且系统安全
        request(processIndex,request);
        return true;
    }

    /**
     * 满足进程随机申请的资源
     * @param processIndex
     * @param request
     */
    private void request(int processIndex,Integer[] request){
        this.need.remove(processIndex,request);
        this.allocation.add(processIndex,request);
        if (allocation.satisfy(processIndex,max,resourceTotal)){
            allocation.releaseAllocation(processIndex,available);
            finishStatus[processIndex] = true;
        }
        available.remove(request);
    }

    /**
     * 请求资源是否可满足
     * @return
     */
    private boolean requestAvailable(AvailableMatrix availableMatrix, Integer[] request) {
        return availableMatrix.requestAvailable(request);
    }

    /**
     * 测试系统安全性
     * @param processIndex
     * @param request
     */
    private boolean checkSafety(int processIndex, Integer[] request) {

        AvailableMatrix availableCopy = new AvailableMatrix(available);

        //满足后资源状态
        availableCopy.remove(request);

        //拷贝当前系统状态
        NeedMatrix needCopy = new NeedMatrix(need, resourceTotal);
        AllocationMatrix allocationCopy = new AllocationMatrix(allocation, resourceTotal);

        //更新满足需求后的状态
        needCopy.remove(processIndex,request);
        allocationCopy.add(processIndex,request);

        Boolean[] finishCopy = new Boolean[finishStatus.length];
        for (int i = 0; i < finishCopy.length; i++) {
            finishCopy[i] = finishStatus[i];
        }

        int start = 0;
        int point = 0;
        //检查安全序列
        System.out.print("检查安全序列为：");
        while (true){
            if (!finishCopy[point]){
                //未分配
                if (needCopy.satisfy(point,availableCopy)){
                    //分配
                    finishCopy[point] = true;
                    //获取到allocation
                    allocationCopy.releaseAllocation(point,availableCopy);
                    //记录成功分配的位置
                    start = point;
                    System.out.print("进程"+(point+1)+";");
                }
            }

            if (point == processNum-1){
                point = 0;
            }else {
                point++;
            }

            if (start == point){
                //回到上一次成功分配的位置
                System.out.println("无法找到安全序列,系统不安全");
                return false;
            }

            if (finish(finishCopy)){
                break;
            }
        }

        System.out.println("系统安全，确认资源分配");
        return true;
    }

    /**
     * 是否全部执行完毕
     * @param finishStatus
     * @return
     */
    private boolean finish(Boolean[] finishStatus) {
        for (Boolean f : finishStatus) {
            if (!f){
                return false;
            }
        }
        return true;
    }

    private void register(Scanner scanner){
        System.out.println("请输入资源数量：");
        //注册资源类型数量
        resourceTotal = scanner.nextInt();
        available = new AvailableMatrix(resourceTotal);
        //注册资源对应总数
        System.out.println("请输入系统资源总数(用','隔开)：");
        String s = scanner.next();
        String[] inputs = s.split(",");

        for (int i = 0; i < resourceTotal; i++) {
            available.register(i,Integer.parseInt(inputs[i]));
        }

        while (true){
            System.out.println("请输入进程数量：");
            processNum = scanner.nextInt();
            if (processNum <= 1){
                System.out.println("进程数量过少，请重新输入");
                continue;
            }
            break;
        }
        //最大资源数量
        max = new MaxMatrix(processNum,resourceTotal);
        //已获得资源数量
        allocation = new AllocationMatrix(processNum,resourceTotal);
        //需要资源数量
        need = new NeedMatrix(processNum,resourceTotal);
        //初始化进程标识位
        finishStatus = new Boolean[processNum];
        for (int i = 0; i < processNum; i++) {
            finishStatus[i] = false;
        }
        //输入需要最大资源数量
        for (int i = 0; i < processNum; i++) {
            inputMax: while (true){
                System.out.println("请输入进程"+(i+1)+"需要的资源总数MAX(用','隔开)：");
                String ss = scanner.next();
                String[] maxInput = ss.split(",");
                for (int j = 0; j < resourceTotal; j++) {
                    int parseInt = Integer.parseInt(maxInput[j]);
                    if (available.get(j) < parseInt){
                        System.out.println("大于系统资源总数，请重新输入");
                        continue inputMax;
                    }
                    max.register(i,j,parseInt);
                    allocation.register(i,j,0);
                    need.register(i,j,parseInt);
                }
                break;
            }
        }
    }

}
