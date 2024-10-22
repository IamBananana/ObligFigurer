package application;

import javafx.scene.Node;

public interface Shapes {
    double getX();

    double getY();

    double getArea();

    double getCircumference();

    String toString();

    String getShape();

    //Endrer bare på verdier til objektet
    void  createShape(double startX, double startY, double endX, double endY);

    Node getShapeNode();
}