package project.lab3;

import project.lab1.Point;
import project.lab1.ViewMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * 欧拉法
 * 建立在李普希兹条件之下，用了差商代替了导数
 */
public class ViewMethodOL implements ViewMethod {


    private float a;

    private float b;

    private int n;

    //40点，步长为0.05
    private float h = 0.05F;

    private List<Point> xy;

    private Float xMax;

    private Float xMin;

    private Float yMax;

    private Float yMin;

    public ViewMethodOL(float a, float b) {
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

        //40个点
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

    /**
     * 递归获取Y
     * @param k
     * @return
     */
    private float getY(int k) {
        if (k == 0) return 1;
        float yk_1 = getY(k - 1);
        //欧拉法公式
        //y k = y k-1 + f(x k-1 , y k-1 ) * h
        return yk_1 + f(getX(k-1),yk_1) * h;
    }

    //李普希兹条件下的导数 -y -x * y² ( x∈ [0,2])
    private float f(float x, float y) {
        return -y - x * y * y;
    }

    /**
     * 递归获取X
     * @param k
     * @return
     */
    private float getX(int k) {
        if (k == 0) return 0;
        return getX(k-1) + h;
    }


}
