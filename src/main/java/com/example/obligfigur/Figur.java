package com.example.obligfigur;


import java.awt.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Figur {
    protected int x, y;
    protected Color farge;

    public Figur (int x, int y, Color farge) {
        this.x = x;
        this.y = y;
        this.farge = farge;
    }
    public Figur (int x, int y) {
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

