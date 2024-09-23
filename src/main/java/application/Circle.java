package application;
import java.awt.*;

public class Circle extends Shapes {
    protected float radius;
    public Circle(int x, int y, float radius, Color color) {
        super(x, y, color);
        setRadius(radius);
    }
    public Circle(int x, int y, float radius) {
        super(x, y);
        setRadius(radius);
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
    public float getRadius() {
        return radius;
    }
    @Override
    public String toString() {
        return super.toString() + " Radius: " + radius;
    }
}
