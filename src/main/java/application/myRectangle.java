package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class myRectangle extends Rectangle implements Shapes {
    private Color color;

    public myRectangle(int x, int y, float height, float width, Color color) {
        super(x, y, width, height);
        ColorUtility.setColor(this, color);
    }

    public myRectangle(int x, int y, float height, float bredde) {
        super(x, y);
        setHeight(height);
        setWidth(bredde);
    }

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

    public double circumference() {
        return 2 * (getHeight() + getWidth());
    }

    @Override
    public String toString() {
        return getShape() + ", x: " + getX() + ", y: " + getY() + ", height: " + getHeight() + ", width: " + getWidth();
    }

    @Override
    public String getShape() {
        return "Rectangle";
    }


}
