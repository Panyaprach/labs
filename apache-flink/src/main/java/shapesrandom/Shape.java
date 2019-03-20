package shapesrandom;

import java.io.Serializable;

/**
 *
 * Created by Panyaprach Tularak on Mar 18, 2019
 */
public interface Shape extends Serializable {
    public Color getColor();
    public double getArea();
}