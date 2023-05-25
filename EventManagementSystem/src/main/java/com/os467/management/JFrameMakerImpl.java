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
        jFrame.setTitle("���¹���ϵͳ");
        Menu leftSelectMenu = new LeftSelectMenu(jFrame, 5);

        leftSelectMenu.name(0,"��Ϣ����");
        leftSelectMenu.name(1,"��ѯģ��");
        leftSelectMenu.name(2,"�����Ǽ�");
        leftSelectMenu.name(3,"����ģ��");
        leftSelectMenu.name(4,"��ͼģ��");

        //ע������
        infoPageBuilder.setFileService(fileService);
        searchPageBuilder.setFileService(fileService);
        addInfoPageBuilder.setFileService(fileService);
        addInfoPageBuilder.setInfoPageBuilder(infoPageBuilder);
        eventPageBuilder.setFileService(fileService);

        //����ҳ��
        infoPageBuilder.buildPage(leftSelectMenu.getPage(0));
        searchPageBuilder.buildPage(leftSelectMenu.getPage(1));
        addInfoPageBuilder.buildPage(leftSelectMenu.getPage(2));
        eventPageBuilder.buildPage(leftSelectMenu.getPage(3));
        mapPageBuilder.buildPage(leftSelectMenu.getPage(4));

        //����Ĭ��ҳ
        leftSelectMenu.setDefaultPage(leftSelectMenu.getPage(0));
    }


}


