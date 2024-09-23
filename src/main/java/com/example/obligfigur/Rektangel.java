package com.example.obligfigur;
import java.awt.*;

public class Rektangel extends Figur{
    protected float høyde, bredde;
    public Rektangel(int x, int y, float høyde, float bredde, Color color){
        super(x, y, color);
        setHøyde(høyde);
        setBredde(bredde);
    }
    public Rektangel(int x, int y, float høyde, float bredde){
        super(x, y);
        setHøyde(høyde);
        setBredde(bredde);
    }

    public void setHøyde(float høyde) {
        this.høyde = høyde;
    }
    public void setBredde(float bredde) {
        this.bredde = bredde;
    }
    public float getHøyde() {
        return høyde;
    }
    public float getBredde() {
        return bredde;
    }
    @Override
    public String toString() {
        return super.toString() + " Høyde: " + høyde + " Bredde: " + bredde;
    }
}
