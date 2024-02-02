package project.lab2;

import project.lab1.Point;
import project.lab1.ViewMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * 梯形法
 */
public class ViewMethodT implements ViewMethod {

    //积分结果
    private float tN;

    //生成的左边界
    private float a;

    //生成的右边界
    private float b;

    //n不用
    private int n;

    //xy就是梯形在函数上的点集合
    private List<Point> xy;

    private Float xMax;

    private Float xMin;

    private Float yMax;

    private Float yMin;

    public ViewMethodT(float a,float b) {
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

        n = 0;
        //计算n
        while (true){
            n++;
            float t = t(n);
            float t1 = t(n + 1);
            if (Math.abs(t1 - t) < 0.0001){
                break;
            }
        }

        tN = t(n+1);

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

    //x1 = xk , x2 = xk+1
    //n为分割的份数
    //梯形化公式
    private float t(int n) {
        float h = (b - a) /n;
        float tn = 0;
        for (int i = 0; i <= n-1 ; i++) {
            //上底加下底乘高
            tn += h/2 *(f(a + i * h) + f(a + (i+1) * h));
        }

        return tn;
    }

    private float f(float x){
        return (float) Math.sin(x)/x;
    }
}
