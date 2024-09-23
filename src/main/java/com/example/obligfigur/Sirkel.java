package com.example.obligfigur;
import java.awt.*;

public class Sirkel extends Figur {
    protected float radius;
    public Sirkel(int x, int y, float radius, Color color) {
        super(x, y, color);
        setRadius(radius);
    }
    public Sirkel(int x, int y, float radius) {
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
