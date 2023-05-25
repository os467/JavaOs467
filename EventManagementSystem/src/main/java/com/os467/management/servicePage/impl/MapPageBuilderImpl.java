package com.os467.management.servicePage.impl;

import com.os467.management.Constant;
import com.os467.management.dataStruct.MGraph;
import com.os467.management.entity.View;
import com.os467.management.servicePage.MapPageBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.Map;


public class MapPageBuilderImpl implements MapPageBuilder {
    @Override
    public void buildPage(JPanel page) {
        page.setLayout(new BorderLayout());
        JPanel mapSearchPanel = new JPanel(new GridLayout(1,5));
        JPanel sPanel = new JPanel(new BorderLayout());
        JPanel tPanel = new JPanel(new BorderLayout());
        JLabel sLabel = new JLabel("出发地");
        JLabel tLabel = new JLabel("目的地");

        JComboBox<String> source = new JComboBox<>();
        source.setBackground(new Color(233, 233, 233));
        View[] views = Constant.views;
        for (int i = 0; i < views.length; i++) {
            source.addItem(views[i].getName());
        }

        JComboBox<String> target = new JComboBox<>();
        for (int i = 0; i < views.length; i++) {
            target.addItem(views[i].getName());
        }
        target.setBackground(new Color(233, 233, 233));

        sPanel.add(sLabel,BorderLayout.WEST);
        sPanel.add(source,BorderLayout.CENTER);
        tPanel.add(tLabel,BorderLayout.WEST);
        tPanel.add(target,BorderLayout.CENTER);
        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        JButton search = new JButton("查询");
        search.setBackground(new Color(144, 237, 149));
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MGraph mGraph = new MGraph();
                StringBuilder stringBuilder = new StringBuilder();
                //获取到选定的地点
                String sP = (String)source.getSelectedItem();
                String tP = (String)target.getSelectedItem();

                String way = "";
                if (!sP.equals(tP)){
                    //获取两点直接的最短路径流
                    way = mGraph.getWay(sP,tP);
                }
                stringBuilder.append(way);
                stringBuilder.append("\n\n");
                stringBuilder.append("目的地介绍\n\n");
                String desc = mGraph.getTargetInfo(tP);
                stringBuilder.append(tP);
                stringBuilder.append(":");
                stringBuilder.append(desc);

                textArea.setText(stringBuilder.toString());
            }
        });

        mapSearchPanel.add(new JPanel());
        mapSearchPanel.add(sPanel);
        mapSearchPanel.add(tPanel);
        mapSearchPanel.add(search);
        mapSearchPanel.add(new JPanel());



        page.add(mapSearchPanel,BorderLayout.NORTH);
        page.add(textArea,BorderLayout.CENTER);
    }
}
