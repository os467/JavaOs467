package project.lab1;

import java.util.List;

public interface ViewMethod {

    float p(float x);

    Float getXMax();

    Float getXMin();

    Float getYMax();

    Float getYMin();

    List<Point> getXy();

    void generate(float xMin, float xMax);
}
