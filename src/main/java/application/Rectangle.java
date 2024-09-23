package application;
import java.awt.*;

public class Rectangle extends Shapes {
    protected float height, width;
    public Rectangle(int x, int y, float setHeight, float width, Color color){
        super(x, y, color);
        setHeight(setHeight);
        setWidth(width);
    }
    public Rectangle(int x, int y, float height, float bredde){
        super(x, y);
        setHeight(height);
        setWidth(bredde);
    }

    public void setHeight(float height) {
        this.height = height;
    }
    public void setWidth(float width) {
        this.width = width;
    }
    public float getHeight() {
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
