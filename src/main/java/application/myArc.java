package application;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class myArc extends Arc implements Shapes {
    public myArc(double centerX, double centerY, double radiusX, double radiusY, double startAngle, double length) {
        super(centerX, centerY, radiusX, radiusY, startAngle, length);
        setFill(Color.TRANSPARENT);
        setStroke(Color.BLACK);
    }

    public myArc(double centerX, double centerY, double radiusX, double radiusY, double startAngle, double length, Color color) {
        super(centerX, centerY, radiusX, radiusY, startAngle, length);
        //ColorUtility.setColor(this, color);
        setFill(Color.TRANSPARENT);
        setStroke(color);
    }

    public myArc(){}
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
        return getLength()*getStrokeWidth();
    }

    @Override
    public double getCircumference() {
        if(getStrokeWidth() > 1){
            return getLength()*2+getStrokeWidth()*2;
        }

        return getLength();
    }

    @Override
    public String getShape() {
        return "Arc";
    }

    @Override
    public String toString() {
        return getShape() + ", x: " + getX() + ", y: " + getY() +
                ", area: " + getArea() + ", circumference: " + getCircumference() + " arcType: " + getType() + ", color: " + getFill();
    }

    @Override
    public void createShape(double startX, double startY, double endX, double endY) {
        this.setCenterX((startX + endX) / 2);
        this.setCenterY((startY + endY) / 2);
        this.setRadiusX(Math.abs(endX - startX) / 2);
        this.setRadiusY(Math.abs(endY - startY) / 2);
    }

    @Override
    public void setShapeAt(double x ,double y){
        this.setCenterX(getX()+x);
        this.setCenterY(getY()+y);
    }
}
