package project.lab2;

import project.lab1.Point;
import project.lab1.View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;



public class SumPanel extends JPanel {



    private List<View> views = new ArrayList();

    private int xS;
    private int yS;

    private float wB;
    private float hB;

    private Float yMax;
    private Float yMin;
    private Float xMax;
    private Float xMin;



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

        wB = 1920 * (1F/2F)/ (xMax - xMin);
        hB = 1080 *(1F/2F)/ (yMax - yMin);
    }

    public SumPanel() {
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
        drawLine(g);
    }

    private void reSize() {
        this.xS = (int) (getWidth()*0.2);
        this.yS = (int) (getHeight()*0.8);
        wB = getWidth() * (2F/3F)/ (xMax - xMin);
        hB = getHeight() *(2F/3F)/ (yMax - yMin);
    }

    private void drawLine(Graphics g) {
        g.drawLine(xS, yS,castX(xMax),yS);
        g.drawLine(xS, castY(yMax),xS,castY(yMin));
    }


    private void drawView(Graphics g, View view) {

        float tN = view.getResult();

        Font font = g.getFont();
        g.setFont(new Font("",10,20));
        g.drawString("积分= "+tN,100,100);

        g.setFont(font);

        List<Point> xy = view.getXy();

        int x1 = 0;
        int y1 = 0;

        g.setColor(view.getColor());
        //f(x)
        for (int i = 0; i < xy.size(); i++) {
            int x = castX(xy.get(i).x);
            int y = castY(xy.get(i).y);
            //做垂线
            g.drawLine(x,y,x,yS);
            String mark = (xy.get(i).x+"");
            g.drawString((mark).substring(0,3),x,yS);
            //g.fillOval(x,yS,5,5);
            if (x1 != 0 && y1 != 0){
                g.drawLine(x1,y1,x,y);
            }
            x1 = x;
            y1 = y;
        }
        g.setColor(new Color(0, 0, 0));

        drawYLine(g);


    }



    private void drawYLine(Graphics g) {
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

        int up = (int) (Math.abs(castY(yMax) - yS) / 10);

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
