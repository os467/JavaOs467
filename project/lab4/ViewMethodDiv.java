package project.lab4;

import project.lab1.Point;
import project.lab1.ViewMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分法
 */
public class ViewMethodDiv implements ViewMethod {

    private Float gen;

    private float a;

    private float b;

    private List<Point> xy;

    private Float xMax;

    private Float xMin;

    private Float yMax;

    private Float yMin;

    public ViewMethodDiv(float a, float b) {
        this.a = a;
        this.b = b;
    }

    public float p(float x) {
        return qg();
    }

    private float qg() {

        float gen = div(a,b);

        return gen;
    }

    private float f(float x) {
        return (float) (x - 2F * Math.sin(x));
    }

    private float div(float a, float b) {
        float fb = f(b);
        if (Math.abs(a - b) < 0.0001){
            return b;
        }

        float mid = ( a + b ) /2F;

        //中间值和右边值乘积
        if ( f(mid) * fb >= 0){
            //同号 说明在中间值右边
            return div(a,mid);
        }else {
            //异号 说明在中间值左边
            return div(mid,b);
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
        gen = p(0);
    }


}
