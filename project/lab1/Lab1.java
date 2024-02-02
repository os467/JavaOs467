package project.lab1;

import JFrameBuilder.WindowBuilder;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Lab1 {

    //横坐标数组
    private Float[] x = {0F,1F,2F,3F,4F,5F,6F,7F};

    //纵坐标数组
    private Float[] y = {1F,3F,7F,6F,2F,4F,7F,5F};

    //下标上限
    private int n;

    public static void main(String[] args) {

        Lab1 lab1 = new Lab1();

        lab1.start();

    }

    private void start() {

        n = x.length;

        this.y = new Float[n];

        for (int i = 0; i < n; i++) {
            this.y[i] = (float)(Math.sin(this.x[i])+2);
        }

        System.out.println("n次插值(n="+n+"):");


        JFrame frame = new WindowBuilder().getFrame();

        Container contentPane = frame.getContentPane();


        //p1
        ViewMethod viewMethod1 = new ViewMethod1(n,x,y);
        viewMethod1.generate(0F,7F);

        ViewMethod viewMethod2 = new ViewMethod2(n,x,y);

        viewMethod2.generate(0F,7F);

        ViewMethod viewMethod3 = new ViewMethod3(n,x,y);

        viewMethod3.generate(0F,6.2F);

        List<Point> xy = viewMethod3.getXy();
        for (int i = 0; i < xy.size(); i++) {
            Point point = xy.get(i);
            System.out.println("("+point.x+","+point.y+")");
        }

        List<Point> marks = new ArrayList();

        for (int i = 0; i < n-1; i++) {
            Point point = new Point(x[i], y[i]);
            marks.add(point);
        }


        AnsPanel ansPanel = new AnsPanel();
        ansPanel.addMarks(marks);
        View v1 = new View(viewMethod1);
        v1.setColor(new Color(8, 85, 135));
        ansPanel.addView(v1);
        View v2 = new View(viewMethod2);
        v2.setColor(new Color(156, 9, 9));
        ansPanel.addView(v2);
        View v3 = new View(viewMethod3);
        v3.setColor(new Color(117, 103, 14));
        ansPanel.addView(v3);

        contentPane.add(ansPanel);

    }




}
