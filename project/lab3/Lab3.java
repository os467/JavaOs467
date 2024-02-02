package project.lab3;

import JFrameBuilder.WindowBuilder;
import project.lab1.View;
import project.lab2.SumPanel;
import project.lab2.ViewMethodT;

import javax.swing.*;
import java.awt.*;

public class Lab3 {

    public static void main(String[] args) {

        Lab3 lab3 = new Lab3();

        lab3.start();

    }

    private void start() {

        buildFrame1();
        buildFrame2();
        buildFrame3();

    }

    private void buildFrame3() {
        JFrame frame = new WindowBuilder().getFrame();

        frame.setTitle("四阶龙贝格");

        Container contentPane = frame.getContentPane();

        ViewMethodOrigin viewMethodOrigin = new ViewMethodOrigin(0F, 2F);
        viewMethodOrigin.generate(0F,2F);
        View vOrigin = new View(viewMethodOrigin);
        vOrigin.setColor(new Color(1,1,1));


        ViewMethodLBG viewMethodLBG = new ViewMethodLBG(0F, 2F);
        viewMethodLBG.generate(0F,2F);
        View vLBG = new View(viewMethodLBG);
        vLBG.setColor(new Color(255, 0, 0));

        BJPanel bjPanel = new BJPanel();

        bjPanel.addView(vOrigin);
        bjPanel.addView(vLBG);

        contentPane.add(bjPanel);
    }

    private void buildFrame2() {
        JFrame frame = new WindowBuilder().getFrame();

        frame.setTitle("改进欧拉");

        Container contentPane = frame.getContentPane();

        ViewMethodOrigin viewMethodOrigin = new ViewMethodOrigin(0F, 2F);
        viewMethodOrigin.generate(0F,2F);
        View vOrigin = new View(viewMethodOrigin);
        vOrigin.setColor(new Color(1,1,1));

        ViewMethodGJOL viewMethodGJOL = new ViewMethodGJOL(0F, 2F);
        viewMethodGJOL.generate(0F,2F);
        View vGJOL = new View(viewMethodGJOL);
        vGJOL.setColor(new Color(255, 0, 0));

        BJPanel bjPanel = new BJPanel();

        bjPanel.addView(vOrigin);
        bjPanel.addView(vGJOL);

        contentPane.add(bjPanel);
    }

    private void buildFrame1() {
        JFrame frame = new WindowBuilder().getFrame();

        frame.setTitle("欧拉法");

        Container contentPane = frame.getContentPane();

        ViewMethodOrigin viewMethodOrigin = new ViewMethodOrigin(0F, 2F);
        viewMethodOrigin.generate(0F,2F);
        View vOrigin = new View(viewMethodOrigin);
        vOrigin.setColor(new Color(1,1,1));


        ViewMethodOL viewMethodOL = new ViewMethodOL(0F, 2F);
        viewMethodOL.generate(0F,2F);
        View vOL = new View(viewMethodOL);
        vOL.setColor(new Color(255, 0, 0));

        BJPanel bjPanel = new BJPanel();

        bjPanel.addView(vOrigin);
        bjPanel.addView(vOL);

        contentPane.add(bjPanel);
    }


}
