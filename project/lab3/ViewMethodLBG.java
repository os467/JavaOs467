package project.lab3;

import project.lab1.Point;
import project.lab1.ViewMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * 龙贝格法
 */
public class ViewMethodLBG implements ViewMethod {


    private float a;

    private float b;

    private int n;

    private float h = 0.05F;

    private List<Point> xy;

    private Float xMax;

    private Float xMin;

    private Float yMax;

    private Float yMin;

    public ViewMethodLBG(float a, float b) {
        this.n = n;
        this.a = a;
        this.b = b;
    }

    public float p(float x) {
        return 0;
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

        for (int k = 0; k < 40; k++) {
            float yk = getY(k);
            float xk = getX(k);
            Point point = new Point(xk, yk);
            xy.add(point);
            if (yMax == null || yk > yMax){
                yMax = yk;
            }
            if (yMin == null || yk < yMin){
                yMin = yk;
            }
            xy.add(point);
        }

    }

    private float getY(int k) {
        if (k == 0) return 1;
        float yk_1 = getY(k-1);
        float xk_1 = getX(k-1);

        //四阶龙贝格库塔法的经典格式
        //k* x y为区间上的平均斜率
        float k1 = f(xk_1,yk_1);

        float k2 = f(xk_1+h/2F,yk_1 + h/2F * k1);

        float k3 = f(xk_1+h/2F,yk_1 + h/2F * k2);

        float k4 = f(getX(k),yk_1 + h * k3);

        float v = yk_1 + h / 6F * (k1 + 2 * k2 + 2 * k3 + k4);
        return v;
    }

    //李普希兹条件下的导数 -y -x * y² ( x∈ [0,2])
    private float f(float x, float y) {
        return -y - x * y * y;
    }

    private float getX(int k) {
        if (k == 0) return 0;
        return getX(k-1) + h;
    }


}
