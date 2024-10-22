package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class myRectangle extends Rectangle implements Shapes {
    private Color color;

    public myRectangle(double x, double y, double height, double width, Color color) {
        super(x, y, width, height);
        ColorUtility.setColor(this, color);
    }

    public myRectangle(double x, double y, double height, double width) {
        super(x, y, width, height);
    }

    private myRectangle(){};

    public Color getColor() {
        return color;
    }

    public Color setColor(Color color) {
        return this.color = color;
    }

    @Override
    public double area() {
        return getHeight() * getWidth();
    }

    @Override
    public double circumference() {
        return 2 * (getHeight() + getWidth());
    }

    @Override
    public String getShape() {
        return "Rectangle";
    }

    @Override
    public String toString() {
        return getShape() + ", x: " + getX() + ", y: " + getY() + ", height: " + getHeight() + ", width: " + getWidth();
    }

    @Override
    public void createShape(double startX, double startY, double endX, double endY){
        this.setX(Math.min(startX, endX));
        this.setY(Math.min(startY, endY));
        this.setWidth(Math.abs(endX - startX));
        this.setHeight(Math.abs(endY - startY));
    }
}