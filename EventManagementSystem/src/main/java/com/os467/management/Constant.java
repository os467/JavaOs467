package com.os467.management;

import com.os467.management.entity.View;

public class Constant {

    //最大顶点数
    public static final int MAX_SIZE = 12;

    //最大权值，即∞
    public static final int MAX_INT = 65535;

    public static final View[] views = new View[MAX_SIZE];

    //图的路径数组
    public static final int[][] pre = new int[MAX_SIZE][MAX_SIZE];

    public static final int EDGE_NUM = 21;

    static {
        //初始化顶点常量
        views[0] = new View(1,"三号组团","学生宿舍楼群");
        views[1] = new View(2,"西苑食堂","二楼的锅盖面味道不错，鸡蛋加面只要六块五");
        views[2] = new View(3,"明德楼","教学楼");
        views[3] = new View(4,"文体中心","运动设施健全");
        views[4] = new View(5,"西操场","不定时有各种活动举行");
        views[5] = new View(6,"笃学楼","爱学习的孩子都住在这里");
        views[6] = new View(7,"东操场","留学生经常在这踢球");
        views[7] = new View(8,"东苑食堂","二楼吃白菜配鸡腿");
        views[8] = new View(9,"图书馆","智能化图书馆，各种书籍应有尽有");
        views[9] = new View(10,"文理大楼","学校地标建筑");
        views[10] = new View(11,"计算机学院","学院楼，可以在这里做实验");
        views[11] = new View(12,"行政大楼","靠近经常出的校门口");

        for (int i = 0; i < pre.length; i++) {
            for (int j = 0; j < pre[0].length; j++) {
                //初始化为全不连通，默认65535
                pre[i][j] = MAX_INT;
            }
        }

        //初始化邻接矩阵和路径矩阵
        //设置路径长度
        link(0,1,100);
        link(0,3,250);
        link(1,3,150);
        link(1,2,80);
        link(1,4,140);
        link(2,4,120);
        link(2,9,110);
        link(3,4,50);
        link(3,6,250);
        link(4,5,100);
        link(4,9,100);
        link(5,6,100);
        link(5,7,130);
        link(5,8,100);
        link(5,9,160);
        link(5,10,250);
        link(6,7,80);
        link(7,8,100);
        link(8,11,280);
        link(9,10,110);
        link(10,11,250);


    }

    private static void link(int x, int y,int w) {
        pre[x][y] = w;
        pre[y][x] = w;
    }

    public static final String[][] categoryList =
            {
                    {"大数据实践","数据可视化","信息图形设计","交互信息设计","数据可视化"},
                    {"人工智能实践赛"},
                    {"动态信息影像（MG动画）","动画","纪录片","数字短片","微电影","新媒体漫画"},
                    {"产品设计","环境设计","平面设计","交互媒体设计","游戏设计","虚拟现实VR与增强现实AR"},
                    {"Web应用与开发","管理信息系统","算法设计与应用","移动应用开发","移动应用开发（非游戏类）"},
                    {"医药卫生","数字生活","运动健身","城市管理","行业应用"},
                    {"汉语言文学","计算机基础与应用类课程微课","虚拟实验平台","中、小学数学或自然科学课程微课"}
            };

    public static int getCategoryIndex(String eventCategory) {
        for (int i = 0; i < categoryList.length; i++) {
            for (int j = 0; j < categoryList[i].length; j++) {
                if (eventCategory.equals(categoryList[i][j])){
                    return i;
                }
            }
        }
        return -1;
    }

    public static View getViewById(String id) {
        for (int i = 0; i < views.length; i++) {
            if (views[i].getId() == Integer.parseInt(id)){
                return views[i];
            }
        }
        return null;
    }
}
