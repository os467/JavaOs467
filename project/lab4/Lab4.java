package project.lab4;

import JFrameBuilder.WindowBuilder;
import project.lab1.Point;
import project.lab1.View;


import javax.swing.*;
import java.awt.*;

public class Lab4 {

    public static void main(String[] args) {

        Lab4 lab4 = new Lab4();

        lab4.buildFrame1();

        lab4.buildFrame3();

        lab4.buildFrame2();

    }

    private void buildFrame3() {
        JFrame frame = new WindowBuilder().getFrame();

        frame.setTitle("弦截法");

        Container contentPane = frame.getContentPane();

        ViewMethodOriginLab4 viewMethodOrigin = new ViewMethodOriginLab4(0F, 3F);
        viewMethodOrigin.generate(0F,6F);
        View vOrigin = new View(viewMethodOrigin);
        vOrigin.setColor(new Color(1,1,1));


        ViewMethodXJ viewMethodXJ = new ViewMethodXJ(0F, 3F);
        viewMethodXJ.generate(0F,3F);
        View view = new View(viewMethodXJ);
        view.setResult(viewMethodXJ.getGen());
        QGPanel qgPanel = new QGPanel();

        qgPanel.addView(vOrigin);
        qgPanel.addView(view);

        contentPane.add(qgPanel);
    }

    private void buildFrame2() {
        JFrame frame = new WindowBuilder().getFrame();

        frame.setTitle("牛顿法");

        Container contentPane = frame.getContentPane();

        ViewMethodOriginLab4 viewMethodOrigin = new ViewMethodOriginLab4(0F, 3F);
        viewMethodOrigin.generate(0F,6F);
        View vOrigin = new View(viewMethodOrigin);
        vOrigin.setColor(new Color(1,1,1));


        ViewMethodNewTon viewMethodNewTon = new ViewMethodNewTon(0F, 3F);
        viewMethodNewTon.generate(0F,3F);
        View view = new View(viewMethodNewTon);
        view.setResult(viewMethodNewTon.getGen());
        QGQXPanel qgPanel = new QGQXPanel();
        qgPanel.setQx1(new Point(3F,(float) (3-2*Math.sin(3))));
        qgPanel.setQX2(new Point(2.0879F,0F));


        qgPanel.addView(vOrigin);
        qgPanel.addView(view);

        contentPane.add(qgPanel);
    }

    private void buildFrame1() {
        JFrame frame = new WindowBuilder().getFrame();

        frame.setTitle("二分法");

        Container contentPane = frame.getContentPane();

        ViewMethodOriginLab4 viewMethodOrigin = new ViewMethodOriginLab4(0F, 3F);
        viewMethodOrigin.generate(0F,6F);
        View vOrigin = new View(viewMethodOrigin);
        vOrigin.setColor(new Color(1,1,1));


        ViewMethodDiv viewMethodDiv = new ViewMethodDiv(0F, 3F);
        viewMethodDiv.generate(0F,3F);
        View view = new View(viewMethodDiv);
        view.setResult(viewMethodDiv.getGen());
        QGPanel qgPanel = new QGPanel();

        qgPanel.addView(vOrigin);
        qgPanel.addView(view);

        contentPane.add(qgPanel);
    }


}
