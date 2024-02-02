package project.lab1;

import java.awt.*;
import java.util.List;

public class View{

    private Color color;


    private Float result;
    private List<Point> xy;
    private int n;
    private float yMax;
    private float yMin;
    private float xMin;
    private float xMax;



    public View(List<Point> xy,Float yMax, Float yMin,Float xMax,Float xMin){
        this.xy = xy;
        this.xMax = xMax;
        this.xMin = xMin;
        this.yMax = yMax;
        this.yMin = yMin;
    }

    public View(ViewMethod viewMethod) {
        this.xy = viewMethod.getXy();
        this.xMax = viewMethod.getXMax();
        this.xMin = viewMethod.getXMin();
        this.yMax = viewMethod.getYMax();
        this.yMin = viewMethod.getYMin();
    }

    public View(List<Point> xy) {
        this.xy = xy;
    }

    public List<Point> getXy() {
        return xy;
    }

    public void setXy(List<Point> xy) {
        this.xy = xy;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public float getYMax() {
        return yMax;
    }

    public void setYMax(float yMax) {
        this.yMax = yMax;
    }

    public float getYMin() {
        return yMin;
    }

    public void setYMin(float yMin) {
        this.yMin = yMin;
    }

    public float getXMin() {
        return xMin;
    }

    public void setXMin(float xMin) {
        this.xMin = xMin;
    }

    public float getXMax() {
        return xMax;
    }

    public void setXMax(float xMax) {
        this.xMax = xMax;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Float getResult() {
        return result;
    }

    public void setResult(Float result) {
        this.result = result;
    }
}