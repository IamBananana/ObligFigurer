package application;
import java.awt.*;

public class Shapes {
    protected int x, y;
    protected Color color;

    public Shapes(int x, int y, Color color) {
        setX(x);
        setY(y);
        setColor(color);
    }
    public Shapes(int x, int y) {
        setX(x);
        setY(y);
    }

    public void setX (int x){
        this.x = x;
    }
    public void setY (int y){
        this.y = y;
    }
    public void setColor(Color color){
        this.color = color;
    }
    public int getX (){
        return x;
    }
    public int getY (){
        return y;
    }
    public Color getColor(){
        return color;
    }
    @Override
    public String toString (){
        return "X: " + x + " Y: " + y;
    }

}

