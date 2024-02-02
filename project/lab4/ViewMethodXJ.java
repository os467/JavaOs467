package project.lab4;

import project.lab1.Point;
import project.lab1.ViewMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * 弦截法
 */
public class ViewMethodXJ implements ViewMethod {

    private Float gen;

    private float a;

    private float b;

    private List<Point> xy;

    private Float xMax;

    private Float xMin;

    private Float yMax;

    private Float yMin;

    public ViewMethodXJ(float a, float b) {
        this.a = a;
        this.b = b;
    }

    public float p(float x) {
        return qg(
                1.6F,1.8F
        );
    }

    private float qg(float x,float x1) {
        float gen = xj(x,x1);
        return gen;
    }


    private float xj(float x,float x1) {
        while (true){
            float x2 = x1 - f(x1)/(f(x1) - f(x) ) * (x1 - x);
            if (Math.abs(x2 - x1) < 0.001){
                return x2;
            }
            x = x1;
            x1 = x2;
        }
    }

    private float f(float x) {
        return (float) (x - 2F * Math.sin(x));
    }

    private float df(float x){
        return (float) (1 - 2F * Math.cos(x));
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

    public Float getGen() {
        return gen;
    }

    public void setGen(float gen) {
        this.gen = gen;
    }

    @Override
    public void generate(float xMin, float xMax) {
        this.xMax = xMax;
        this.xMin = xMin;
        yMin = 0F;
        yMax = 0F;
        xy = new ArrayList();
        gen = p(1.8F);
    }


}
