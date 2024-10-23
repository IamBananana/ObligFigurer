package application;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class myEllipse extends Ellipse implements Shapes {
    private Ellipse ellipse;

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
    public double getArea() {
        return Math.PI * getRadiusX() * getRadiusY();
    }

    @Override
    public double getCircumference() {
        return Math.PI * (3 * (getRadiusX() + getRadiusY()) - Math.sqrt((3 * getRadiusX() + getRadiusY()) * (getRadiusX() + 3 * getRadiusY())));
    }

    @Override
    public String getShape() {
        return "Ellipse";
    }

    @Override
    public void createShape(double startX, double startY, double endX, double endY) {
        this.setCenterX((startX + endX) / 2);
        this.setCenterY((startY + endY) / 2);
        this.setRadiusX(Math.abs(endX - startX) / 2);
        this.setRadiusY(Math.abs(endY - startY) / 2);
    }
}
