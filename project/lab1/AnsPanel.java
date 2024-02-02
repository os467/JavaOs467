package project.lab1;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AnsPanel extends JPanel {



    private List<View> views = new ArrayList();

    private List<Point> marks;

    private int xS;
    private int yS;

    private float wB;
    private float hB;

    private Float yMax;
    private Float yMin;
    private Float xMax;
    private Float xMin;

    public void addMarks(List<Point> marks) {
        this.marks = marks;
    }

    public void addView(View view){
        views.add(view);
        for (int i = 0; i < views.size(); i++) {
            View v = views.get(i);
            if (yMax == null || v.getYMax() > yMax){
                yMax = v.getYMax();
            }
            if (yMin == null || v.getYMin() < yMin){
                yMin = v.getYMin();
            }
            if (xMax == null || v.getXMax() > xMax){
                xMax = v.getXMax() ;
            }
            if (xMin == null || v.getXMin() < xMin){
                xMin = v.getXMin();
            }
        }
        wB = 1920 * (1F/3F)/ (xMax - xMin);
        hB = 1080 *(1F/3F)/ (yMax - yMin);
    }

    public AnsPanel() {
        this.xS = 200;
        this.yS = 600;
    }


    @Override
    public void paint(Graphics g) {
        reSize();
        super.paint(g);
        for (int i = 0; i < views.size(); i++) {
            View view = views.get(i);
            drawView(g,view);
        }
        drawMarks(g);

        drawXYLine(g);
    }

    private void drawXYLine(Graphics g) {
        //line
        g.drawLine(xS, castY(0F),castX(xMax),castY(0F));
        if (castY(yMin) < castY(0F)){
            g.drawLine(xS, castY(yMax),xS,castY(0F));
        }else {
            g.drawLine(xS, castY(yMax),xS,castY(yMin));
        }
    }

    private void drawMarks(Graphics g) {
        //marks
        g.setColor(new Color(236, 0, 0));
        for (int i = 0; i < marks.size(); i++) {
            int x = castX(marks.get(i).x);
            int y = castY(marks.get(i).y);
            g.fillOval(x-2,y-2,5,5);
            g.drawString("("+marks.get(i).x+","+marks.get(i).y+")",x-10,y+10);
        }
        g.setColor(new Color(0,0,0));

    }

    private void reSize() {
        this.xS = (int) (getWidth()*0.1);
        this.yS = (int) (getHeight()*0.9);
        wB = getWidth() * (3F/4F)/ (xMax - xMin);
        hB = getHeight() *(3F/4F)/ (yMax - yMin);
    }

    private void drawView(Graphics g, View view) {
        List<Point> xy = view.getXy();

        int x1 = 0;
        int y1 = 0;

        g.setColor(view.getColor());
        //f(x)
        for (int i = 0; i < xy.size(); i++) {
            int x = castX(xy.get(i).x);
            int y = castY(xy.get(i).y);
            if (x1 != 0 && y1 != 0){
                g.drawLine(x1,y1,x,y);
            }
            x1 = x;
            y1 = y;
        }
        g.setColor(new Color(0, 0, 0));



        for (float i = 0; i <= xMax; i+=1) {
            g.drawString(i+"",castX((float)i)-2,yS-2);
            g.fillOval(castX((float)i)-2,yS-2,5,5);
        }

        drawYPoint(g);

    }

    private void drawYPoint(Graphics g) {
        /*float yUp = 0.2F;

        int up = (int) (Math.abs(castY(yMax) - yS) / (int)Math.abs(yMax) * yUp);

        for (float i = yS,j = 0; i >= castY(yMax); i-=up,j+=yUp) {
            if (j == 0){
                continue;
            }
            g.drawString(j+"",castX(0F)+2,(int) i);
            g.fillOval(castX(0F)-2,(int) i,5,5);
        }

        for (float i = yS,j = 0; i <= castY(yMin); i+=up,j-=yUp) {
            if (j == 0){
                continue;
            }
            g.drawString(j+"",castX(0F)+2,(int) i);
            g.fillOval(castX(0F)-2,(int) i,5,5);
        }*/

        //n默认为10等分
        float yUp = yMax/10;

        int up = (Math.abs(castY(yMax) - yS) / 10);

        for (float i = yS,j = 0; i >= castY(yMax); i-=up,j+=yUp) {
            if (j == 0){
                continue;
            }
            String yString = j+"";
            if (yString.length() >= 4){
                yString = yString.substring(0,4);
            }
            g.drawString(yString+"",castX(0F)+2,(int) i);
            g.fillOval(castX(0F)-2,(int) i,5,5);
        }

        for (float i = yS,j = 0; i <= castY(yMin); i+=up,j-=yUp) {
            if (j == 0){
                continue;
            }
            String yString = j+"";
            if (yString.length() >= 4){
                yString = yString.substring(0,4);
            }
            g.drawString(yString+"",castX(0F)+2,(int) i);
            g.fillOval(castX(0F)-2,(int) i,5,5);
        }
    }

    private int castX(Float x) {
        return (int) (x * wB) + xS;
    }

    private int castY(Float y){
        return (int) (yS - (y * hB));
    }

}