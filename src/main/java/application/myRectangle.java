package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class myRectangle extends Rectangle implements Shapes {
    private  double endX, endY;

    public myRectangle(double x, double y, double height, double width, Color color) {
        super(x, y, width, height);
        setFill(color);
    }

    public myRectangle(double x, double y, double height, double width) {
        super(x, y, width, height);
    }

    private myRectangle() {
    }

    public void setEndX(double endX){
        this.endX =  endX;
    }

    public void setEndY(double endY){
        this.endY =  endY;
    }

    @Override
    public double getArea() {
        return getHeight() * getWidth();
    }

    @Override
    public double getCircumference() {
        return 2 * (getHeight() + getWidth());
    }

    @Override
    public String getShape() {
        return "Rectangle";
    }

    @Override
    public String toString() {
        return getShape() + ", x: " + getX() + ", y: " + getY() + ", height: " + getHeight() + ", width: " + getWidth() + ", color: " + getFill();
    }

    @Override
    public void createShape(double startX, double startY, double endX, double endY) {
        this.setX(Math.min(startX, endX));
        this.setY(Math.min(startY, endY));
        this.setWidth(Math.abs(endX - startX));
        this.setHeight(Math.abs(endY - startY));
    }

    @Override
    public void setShapeAt(double x ,double y){
        this.setX(getX()+x);
        this.setY(getY()+y);
    }
}