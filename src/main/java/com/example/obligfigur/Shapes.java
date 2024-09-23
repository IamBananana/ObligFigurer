package com.example.obligfigur;
import java.awt.*;

public class Shapes {
    protected int x, y;
    protected Color farge;

    public Shapes(int x, int y, Color farge) {
        this.x = x;
        this.y = y;
        this.farge = farge;
    }
    public Shapes(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX (int x){
        this.x = x;
    }
    public void setY (int y){
        this.y = y;
    }
    public void setFarge (Color farge){
        this.farge = farge;
    }
    public int getX (){
        return x;
    }
    public int getY (){
        return y;
    }
    public Color getFarge (){
        return farge;
    }
    @Override
    public String toString (){
        return "X: " + x + " Y: " + y;
    }

}

