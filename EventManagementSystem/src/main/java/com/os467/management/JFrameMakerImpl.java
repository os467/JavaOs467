package com.os467.management;

import JFrameBuilder.JFrameContentMaker;
import JFrameBuilder.JFrameUtils;
import JFrameBuilder.component.menu.LeftSelectMenu;
import JFrameBuilder.component.menu.Menu;
import com.os467.management.servicePage.*;
import com.os467.management.servicePage.impl.*;

import javax.swing.*;
import java.awt.*;


public class JFrameMakerImpl implements JFrameContentMaker {

    private FileService fileService = new FileService();

    private InfoPageBuilder infoPageBuilder = new InfoPageBuilderImpl();

    private SearchPageBuilder searchPageBuilder = new SearchPageBuilderImpl();

    private AddInfoPageBuilder addInfoPageBuilder = new AddInfoPageBuilderImpl();

    private EventPageBuilder eventPageBuilder = new EventPageBuilderImpl();

    private MapPageBuilder mapPageBuilder = new MapPageBuilderImpl();

    @Override
    public void make(JFrame jFrame) {
        jFrame.setTitle("赛事管理系统");
        Menu leftSelectMenu = new LeftSelectMenu(jFrame, 5);

        leftSelectMenu.name(0,"信息管理");
        leftSelectMenu.name(1,"查询模块");
        leftSelectMenu.name(2,"参赛登记");
        leftSelectMenu.name(3,"赛事模拟");
        leftSelectMenu.name(4,"地图模拟");

        //注入依赖
        infoPageBuilder.setFileService(fileService);
        searchPageBuilder.setFileService(fileService);
        addInfoPageBuilder.setFileService(fileService);
        addInfoPageBuilder.setInfoPageBuilder(infoPageBuilder);
        eventPageBuilder.setFileService(fileService);

        //构建页面
        infoPageBuilder.buildPage(leftSelectMenu.getPage(0));
        searchPageBuilder.buildPage(leftSelectMenu.getPage(1));
        addInfoPageBuilder.buildPage(leftSelectMenu.getPage(2));
        eventPageBuilder.buildPage(leftSelectMenu.getPage(3));
        mapPageBuilder.buildPage(leftSelectMenu.getPage(4));

        //设置默认页
        leftSelectMenu.setDefaultPage(leftSelectMenu.getPage(0));
    }


}


