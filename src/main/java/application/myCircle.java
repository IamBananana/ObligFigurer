package application;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Paint;

public class myCircle extends Circle implements Shapes {
    private Color color;

    public myCircle(double x, double y, double radius) {
        super(x, y, radius);
    }
    public myCircle(double x, double y, double radius, Color color) {
        super(x, y, radius);
        this.color = color;
        ColorUtility.setColor(this, color);
    }

    public Paint getColor() {
        return color;
    }

    public Color setColor(Color color) {
        return this.color = color;
    }

    @Override
    public double area() {
        return Math.PI*getRadius()*getRadius();
    }

    @Override
    public double circumference() {
        return 2*Math.PI*getRadius();
    }
    @Override
    public String getShape() {
        return "Circle";
    }

    @Override
    public String toString() {
        return getShape() + ", x: " + getCenterX() + ", y: " + getCenterY() +
                ", radius: " + getRadius() + ", area: " + area() + ", circumference: " + circumference();
    }
}
