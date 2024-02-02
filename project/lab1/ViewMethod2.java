package project.lab1;

import java.util.ArrayList;
import java.util.List;

public class ViewMethod2 implements ViewMethod {
    private Integer n;

    private Float[] x;

    private Float[] y;

    private List<Point> xy;

    private Float xMax;

    private Float xMin;

    private Float yMax;

    private Float yMin;


    public ViewMethod2(Integer n, Float[] x, Float[] y) {
        this.n = n;
        this.x = x;
        this.y = y;
    }

    //2: p
    public float p(float x) {
        float y = 0;
        for (int i = 0; i < n; i++) {
            y += f(i) * pi2(x,i);
        }
        return y;
    }

    //2: pi2
    private float pi2(float x, int u) {
        float y = 1;
        for (int i = 0; i < u; i++) {
            y *= (x - this.x[i]);
        }
        return y;
    }

    //2: f(x0,x1...xn)
    private float f(int u) {
        float y = 0;
        for (int i = 0; i <= u; i++) {
            y += this.y[i] / pi(this.x[i],i,u);
        }
        return y;
    }

    //2: Pi_u(x)
    private Float pi(float x, int r,int u) {
        float ans = 1;
        for (int i = 0; i <= u; i++) {
            if (i != r){
                ans *= (x - this.x[i]);
            }
        }
        return ans;
    }

    public void generate(float xMin, float xMax) {
        this.xMax = xMax;
        this.xMin = xMin;
        xy = new ArrayList();
        yMin = null;
        yMax = null;
        for (float i = xMin; i <= xMax; i += 0.01) {
            float y = p(i);
            Point point = new Point(i, y);
            if (yMax == null || y > yMax) {
                yMax = y;
            }
            if (yMin == null || y < yMin) {
                yMin = y;
            }
            xy.add(point);
        }
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
}
