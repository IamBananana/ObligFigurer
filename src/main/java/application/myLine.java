package application;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class myLine extends Line implements Shapes {
    private Color color;
    private double length;
    private Line line;

    public myLine(double startX, double startY, double endX, double endY){
        super(startX, startY, endX, endY);
    }

    public myLine(double startX, double startY, double endX, double endY, Color color){
        super(startX, startY, endX, endY);
        setColor(color);
    }

    public void setColor(Color color){
        this.color = color;
    }

    private void setLenght(double lenght){
        this.length = length;
    }

    public double getLength(){
        return this.length;
    }

    public Color getColor(){
        return this.color;
    }

    @Override
    public double getX(){
        return getStartX();
    }

    @Override
    public double getY(){
        return getStartX();
    }

    @Override
    public double getArea(){
        return this.getStrokeWidth()*this.length;
    }

    @Override
    public double getCircumference(){
        return this.getStrokeWidth()+this.length;
    }

    @Override
    public String toString(){
        return getShape() + ", startX: " + getStartX() + ", startY: " + getStartY() + ", endX: " + getEndX() + ", endY: " + getEndY() + ", lenght: " +
                getLength() + ", width: " + getStrokeWidth();
    }

    @Override
    public String getShape(){
        return "Line";
    }

    //Endrer bare på verdier til objektet
    @Override
    public void createShape(double startX, double startY, double endX, double endY){
        this.setLenght(Math.hypot(endX-startX, endY-startY));
        this.setStartX(startX);
        this.setStartY(startY);
        this.setEndX(endX);
        this.setEndY(endY);
    }

    @Override
    public Node getShapeNode(){
        return line;
    }
}
