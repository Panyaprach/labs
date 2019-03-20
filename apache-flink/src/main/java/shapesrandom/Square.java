package shapesrandom;

/**
 *
 * Created by Panyaprach Tularak on Mar 18, 2019
 */
public class Square implements Shape {

    private Color color;
    private double side;

    public Square(Color color, double side) {
        this.color = color;
        this.side = side;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public double getArea() {
        return side * side;
    }
    @Override
    public String toString(){
        return String.format("Square[ area: %.2f, color: %s]\n", getArea(), getColor());
    }
}
