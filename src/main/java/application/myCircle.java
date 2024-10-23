package application;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class myCircle extends Circle implements Shapes {
    private Color color;
    private Circle circle;
    private double endX, endY;

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

    public void setEndX(double endX){
        this.endX =  endX;
    }

    public void setEndY(double endY){
        this.endY =  endY;
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
        return Math.PI * getRadius() * getRadius();
    }

    @Override
    public double getCircumference() {
        return 2 * Math.PI * getRadius();
    }

    @Override
    public String getShape() {
        return "Circle";
    }

    @Override
    public String toString() {
        return getShape() + ", x: " + getCenterX() + ", y: " + getCenterY() +
                ", radius: " + getRadius() + ", area: " + getArea() + ", circumference: " + getCircumference() + ", color: " + getColor();
    }

    @Override
    public void createShape(double startX, double startY, double endX, double endY) {
        double radius = Math.hypot(endX - startX, endY - startY) / 2;  // Kem sa Pytagoras læresetning va useless?
        this.setCenterX((startX + endX) / 2);
        this.setCenterY((startY + endY) / 2);
        this.setRadius(radius);
    }

    @Override
    public void setShapeAt(double x ,double y){
    }
}
