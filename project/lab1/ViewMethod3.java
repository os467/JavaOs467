package project.lab1;

import java.util.ArrayList;
import java.util.List;

public class ViewMethod3 implements ViewMethod {

    private Float[] h;

    private Float[] a;

    private Float[] b;

    private Float[] dy;

    private Float[] m;

    private Integer n;

    private Float[] x;

    private Float[] y;

    private List<Point> xy;

    private Float xMax;

    private Float xMin;

    private Float yMax;

    private Float yMin;



    public ViewMethod3(Integer n, Float[] x, Float[] y) {
        this.n = n;
        this.x = x;
        this.y = y;

        dy = new Float[n];
        h =  new Float[n];
        a =  new Float[n];
        b =  new Float[n];
        m = new Float[n];

        for (int i = 0; i < n - 1; i++) {
            h[i] = x[i+1] - x[i];
        }
        for (int i = 1; i < n - 1; i++) {
            a[i] = h[i-1] / (h[i-1] + h[i]);
        }
        for (int i = 1; i < n - 1; i++) {
            b[i] = 3* ((1-a[i])*( (y[i]-y[i-1])/ h[i-1] ) + a[i] * (y[i+1] -y[i]) / h[i]);
        }

      /*  dy[0] = (float)(Math.cos(x[0]));
        dy[n - 1] = (float)(Math.cos(x[n - 1]));*/
        dy[0] = 2F;
        dy[n-1] = -3F;
        m[0] = dy[0];
        m[n - 1] = dy[n - 1];

        Gauss gauss = new Gauss(m);
        gauss.setM();

    }

    private float k0(float x) {
        return (x-1) * (x-1) * (2 * x +1);
    }

    private float k1(float x) {
        return x * x * (3 - 2 * x);
    }

    private float t0(float x) {
        return (x-1)*(x-1)*x;
    }

    private float t1(float x) {
        return x * x * (x-1);
    }


    //1: p
    public float p(float x) {
        //向下取整
        int i = (int)Math.floor(x);
        if (Math.abs(x - i) > 0.9){
            i++;
        }

        return this.y[i] * (
                k0((x - this.x[i]) / h[i])
                        + this.y[i+1] * k1((x-this.x[i])/h[i])
                        + h[i] * m[i] * t0((x-this.x[i])/h[i])
                        + h[i] *m[i+1] * t1((x-this.x[i])/h[i])
        );
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
}
