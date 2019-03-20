package shapesrandom;

/**
 *
 * Created by Panyaprach Tularak on Mar 18, 2019
 */
public class Circle implements Shape {

    private Color color;
    private double radius;
    public Circle(Color color, double radius) {
        this.color = color;
        this.radius = radius;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }
    
    @Override
    public String toString(){
        return String.format("Circle[ area: %.2f, color: %s]\n", getArea(), getColor());
    }

}
