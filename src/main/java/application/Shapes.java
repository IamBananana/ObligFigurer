package application;

import javafx.scene.shape.Shape;

public interface Shapes {
    double getX();

    double getY();

    double area();

    double circumference();

    String toString();

    String getShape();

    //Endrer bare på verdier til objektet
    void  createShape(double startX, double startY, double endX, double endY);
}