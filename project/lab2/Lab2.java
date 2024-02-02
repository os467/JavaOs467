package project.lab2;

import JFrameBuilder.WindowBuilder;
import project.lab1.View;

import javax.swing.*;
import java.awt.*;

public class Lab2 {

    public static void main(String[] args) {

        Lab2 lab2 = new Lab2();

        lab2.start();

    }

    private void start() {

        buildFrame1();

        buildFrame2();

    }

    private void buildFrame1() {
        JFrame frame = new WindowBuilder().getFrame();

        frame.setTitle("梯形积分");

        Container contentPane = frame.getContentPane();

        ViewMethodT viewMethodT = new ViewMethodT(0.001F,6.001F);

        viewMethodT.generate(0.001F,6.001F);
        View v = new View(viewMethodT);
        v.setResult(viewMethodT.gettN());
        v.setColor(new Color(1,1,1));

        SumPanel sumPanel = new SumPanel();

        sumPanel.addView(v);

        contentPane.add(sumPanel);
    }

    private void buildFrame2() {
        JFrame frame = new WindowBuilder().getFrame();

        frame.setTitle("龙贝格");

        Container contentPane = frame.getContentPane();

        ViewMethodL viewMethodL = new ViewMethodL(0.001F,6.001F);

        viewMethodL.generate(0.001F,6.001F);
        View v1 = new View(viewMethodL);
        v1.setResult(viewMethodL.gettN());
        v1.setColor(new Color(210, 25, 25));

        SumPanel sumPanel = new SumPanel();

        sumPanel.addView(v1);

        contentPane.add(sumPanel);
    }


}
