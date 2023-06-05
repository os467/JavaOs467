package com.os467.management.servicePage.impl;

import com.os467.management.Constant;
import com.os467.management.dataStruct.MGraph;
import com.os467.management.entity.View;
import com.os467.management.servicePage.MapPageBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;



public class MapPageBuilderImpl implements MapPageBuilder {

    @Override
    public void buildPage(JPanel page) {
        page.setLayout(new BorderLayout());


        JPanel mapSearchPanel = new JPanel(new GridLayout(1,5));
        JPanel sPanel = new JPanel(new BorderLayout());
        JPanel tPanel = new JPanel(new BorderLayout());
        JLabel sLabel = new JLabel("������");
        JLabel tLabel = new JLabel("Ŀ�ĵ�");

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
        JPanel mapDrawPanel = new MapPanel();
        JButton search = new JButton("��ѯ");
        search.setBackground(new Color(144, 237, 149));
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MGraph mGraph = new MGraph();
                StringBuilder stringBuilder = new StringBuilder();
                //��ȡ��ѡ���ĵص�
                String sP = (String)source.getSelectedItem();
                String tP = (String)target.getSelectedItem();

                String way = "";
                if (!sP.equals(tP)){
                    //��ȡ����ֱ�ӵ����·����
                    way = mGraph.getWay(sP,tP);
                }
                stringBuilder.append(way);
                stringBuilder.append("\n\n");
                stringBuilder.append("Ŀ�ĵؽ���\n\n");
                String desc = mGraph.getTargetInfo(tP);
                stringBuilder.append(tP);
                stringBuilder.append(":");
                stringBuilder.append(desc);

                String[] split = way.split(Constant.WAYS_SPLIT_CHAR);
                if (split.length > 1){
                    split[0] = split[0].substring(split[0].indexOf(":")+1);
                    split[split.length-1] = split[split.length-1].substring(0,split[split.length-1].indexOf("\n"));
                    ((MapPanel)mapDrawPanel).markMap(split);
                }else {
                    ((MapPanel)mapDrawPanel).markMap(new String[]{sP});
                }

                textArea.setText(stringBuilder.toString());
            }
        });

        mapSearchPanel.add(new JPanel());
        mapSearchPanel.add(sPanel);
        mapSearchPanel.add(tPanel);
        mapSearchPanel.add(search);
        mapSearchPanel.add(new JPanel());



        page.add(mapSearchPanel,BorderLayout.NORTH);
        page.add(mapDrawPanel,BorderLayout.CENTER);
        page.add(textArea,BorderLayout.SOUTH);
    }


}


class MapPanel extends JPanel{

    private List<Integer> markIds;

    public MapPanel() {
    }

    public void markMap(String[] ways){
        List<Integer> ids = new ArrayList<>();
        for (String way : ways) {
            int id = Constant.getViewByName(way).getId();
            ids.add(id);
        }
        this.markIds = ids;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //��������x��y�㣬����ԲȦ����ȡView�е�xy����
        drawAllView(g);
    }

    //1315 937
    private void drawAllView(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        double wB = width * (5D/6D)/ (double)1315;
        double hB = height *(5D/6D)/ (double)937;
        View[] views = Constant.views;
        for (int i = 0; i < views.length; i++) {
            int x = (int) (views[i].getX() * wB);
            int y = (int)(views[i].getY() * hB);
            if (inMarkIds(views[i])){
                if (isFirstMark(views[i])){
                    g.setColor(new Color(46, 168, 18));
                }else {
                    g.setColor(new Color(236, 68, 68));
                }
                g.fillOval(x-15,y-15,30,30);
                g.drawString(views[i].getName(),x,y-20);
                g.setColor(new Color(0,0,0));
            }else {
                g.drawOval(x-15,y-15,30,30);
                g.drawString(views[i].getName(),x,y-20);
            }
        }
        int[][] pre = Constant.pre;
        for (int i = 0; i < pre.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (pre[i][j] != Constant.MAX_INT){
                    if (inMarkIds(views[i]) && inMarkIds(views[j])){
                        g.setColor(new Color(229, 15, 15));
                        g.drawLine((int) (views[i].getX() * wB),(int)(views[i].getY() * hB),
                                (int) (views[j].getX() * wB),(int)(views[j].getY() * hB));
                        g.setColor(new Color(0,0,0));
                    }else {
                        g.drawLine((int) (views[i].getX() * wB),(int)(views[i].getY() * hB),
                                (int) (views[j].getX() * wB),(int)(views[j].getY() * hB));
                    }
                }
            }
        }
    }

    private boolean isFirstMark(View view) {
        if (markIds != null){
            if (markIds.get(0).equals(view.getId())){
                return true;
            }
        }
        return false;
    }

    private boolean inMarkIds(View view) {
        if (markIds != null){
            for (Integer markId : markIds) {
                if (view.getId() == markId){
                    return true;
                }
            }
        }
        return false;
    }

}
