package project.lab4;

import project.lab1.Point;
import project.lab1.ViewMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * 牛顿法
 */
public class ViewMethodNewTon implements ViewMethod {

    private Float gen;

    private float a;

    private float b;

    private List<Point> xy;

    private Float xMax;

    private Float xMin;

    private Float yMax;

    private Float yMin;

    public ViewMethodNewTon(float a, float b) {
        this.a = a;
        this.b = b;
    }

    public float p(float x) {
        return qg(x);
    }

    private float qg(float x) {
        float gen = nt(x);
        return gen;
    }

    private float nt(float x) {
        while (true){
            //迭代公式
            //一阶泰勒展开公式 在P(x) = 0 的条件下 为 f(x) = 0 的近似解
            //求根公式
            float x1 = x - f(x)/df(x);
            //回归条件
            if (Math.abs(x1 - x) < 0.001){
                x = x1;
                break;
            }
            x = x1;
        }

        return x;
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
