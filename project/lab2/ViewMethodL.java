package project.lab2;

import project.lab1.Point;
import project.lab1.ViewMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * 龙贝格算法
 */
public class ViewMethodL implements ViewMethod {

    private float a;

    private float b;

    private int n;

    private float tN;

    private List<Point> xy;

    private Float xMax;

    private Float xMin;

    private Float yMax;

    private Float yMin;

    public ViewMethodL(float a, float b) {
        this.n = n;
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

    public float gettN() {
        return tN;
    }

    public void settN(float tN) {
        this.tN = tN;
    }

    @Override
    public void generate(float xMin, float xMax) {

        int k = 1;
        n = (int) Math.pow(2,k);
        //计算n
        while (true){
            k++;
            n = (int) Math.pow(2,k);
            float r = r(n/2);
            float r1 = r(n);
            //rn - rn/2
            if (Math.abs(r1 - r) < 0.0001){
                break;
            }
        }

        tN = r(n);

        this.xMax = xMax;
        this.xMin = xMin;
        yMin = null;
        yMax = null;

        xy = new ArrayList();

        float h = (b-a)/n;
        //从a到b按照h步长生成 (i,y)点 ，此点为每个小梯形在函数上的点
        for (float i = a; i <= b; i+=h) {
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


    //复化梯形公式
    private float t(int n) {
        float sum = 0;
        float h = (b - a)/n;
        for (int k = 0; k < n; k++) {
            //xk = a + k * h  xk+1 = a + (k+1) * h
            sum += h/2 * ( f((a + k * h)) + f(((a + (k+1) * h))) );
        }
        return sum;
    }


    //三个减误差公式
    //rn - rn/2
    private float r(int n) {
        return  64/63 * c( 2 * n) - 1/63 * c(n);
    }

    //复化科斯特公式
    private float c(int n) {
        return 16/15 * s( 2 * n) - 1/15 * s(n);
    }

    //复化辛普森积分
    private float s(int n) {
        return  4/3 * t(2 * n) - 1/3 * t(n);
    }

    private float f(float x){
        double v = Math.sin(x) / x;
        return (float) v;
    }
}
