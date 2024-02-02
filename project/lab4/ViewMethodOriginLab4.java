package project.lab4;

import project.lab1.Point;
import project.lab1.ViewMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * 二次函数
 */
public class ViewMethodOriginLab4 implements ViewMethod {


    private float a;

    private float b;


    private List<Point> xy;

    private Float xMax;

    private Float xMin;

    private Float yMax;

    private Float yMin;

    public ViewMethodOriginLab4(float a, float b) {
        this.a = a;
        this.b = b;
    }

    public float p(float x) {
        return f(x);
    }

    public Float getXMax() {
        return xMax;
    }

    public Float getXMin() {
        return xMin;
    }

    public Float getYMax() {
        return yMax;
    }

    public Float getYMin() {
        return yMin;
    }

    public List<Point> getXy() {
        return xy;
    }


    @Override
    public void generate(float xMin, float xMax) {


        this.xMax = xMax;
        this.xMin = xMin;
        yMin = null;
        yMax = null;

        xy = new ArrayList();

        for (float i = a; i <= b; i+=0.01) {
            float y = p(i);
            Point point = new Point(i, y);
            xy.add(point);
            if (yMax == null || y > yMax){
                yMax = y;
            }
            if (yMin == null || y < yMin){
                yMin = y;
            }
            xy.add(point);
        }

    }


    private float f(float x){
        return (float) (
                x - 2 * Math.sin(x)
                );
    }
}
