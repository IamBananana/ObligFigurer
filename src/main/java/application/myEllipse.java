package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class myEllipse extends Ellipse implements Shapes {

    public myEllipse(double centerX, double centerY, double radiusX, double radiusY) {
        super(centerX, centerY, radiusX, radiusY);
        setFill(Color.TRANSPARENT);
        setStroke(Color.BLACK);
    }

    @Override
    public double getX() {
        return getCenterX();
    }

    @Override
    public double getY() {
        return getCenterY();
    }

    @Override
    public double area() {
        return Math.PI * getRadiusX() * getRadiusY();
    }

    @Override
    public double circumference() {
        return Math.PI * (3 * (getRadiusX() + getRadiusY()) - Math.sqrt((3 * getRadiusX() + getRadiusY()) * (getRadiusX() + 3 * getRadiusY())));
    }

    @Override
    public String getShape() {
        return "Ellipse";
    }
}
