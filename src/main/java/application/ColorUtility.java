package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class ColorUtility {
    public static void setColor(Shape shape, Color color) {
        shape.setFill(Color.WHITE);
        shape.setStroke(color);
    }
    public static void setFillColor(Shape shape, Color color) {
        shape.setStroke(color);
        shape.setFill(color);
    }
}
