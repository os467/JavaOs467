package project.lab3;

import project.lab1.Point;
import project.lab1.ViewMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * 改进欧拉法
 */
public class ViewMethodGJOL implements ViewMethod {


    private float a;

    private float b;

    private int n;

    private float h = 0.05F;

    private List<Point> xy;

    private Float xMax;

    private Float xMin;

    private Float yMax;

    private Float yMin;

    public ViewMethodGJOL(float a, float b) {
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

        //yp是预报值 欧拉法下的值
        float yp = yk_1 + h * f(xk_1,yk_1);
        //校正值
        float yc = yk_1 + h * f(getX(k),yp);

        //取平均
        float v = 1F / 2F * (yp + yc);
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
