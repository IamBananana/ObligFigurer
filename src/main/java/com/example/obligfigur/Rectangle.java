package com.example.obligfigur;
import java.awt.*;

public class Rectangle extends Shapes {
    protected float height, width;
    public Rectangle(int x, int y, float høyde, float width, Color color){
        super(x, y, color);
        setHøyde(høyde);
        setWidth(width);
    }
    public Rectangle(int x, int y, float height, float bredde){
        super(x, y);
        setHøyde(height);
        setWidth(bredde);
    }

    public void setHøyde(float height) {
        this.height = height;
    }
    public void setWidth(float width) {
        this.width = width;
    }
    public float getHøyde() {
        return height;
    }
    public float getWidth() {
        return width;
    }
    @Override
    public String toString() {
        return super.toString() + " Høyde: " + height + " Bredde: " + width;
    }
}
