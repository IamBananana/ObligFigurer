package application;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class myText extends Text implements Shapes {
    private double x, y;
    private Text text;
    private double endX, endY;

    public myText(double x, double y, String content) {
        super(content);
        setStartX(x);
        setStartY(y);

        setX(x);
        setY(y);
        setFont(new Font("Arial", 20));
        setFill(Color.BLACK);
    }

    public void setStartX(double startX){
        this.x = startX;
    }

    public void setStartY(double startY){
        this.y = startY;
    }

    public void setEndX(double endX){
        this.endX =  endX;
    }

    public void setEndY(double endY){
        this.endY =  endY;
    }

    @Override
    public double getArea() {
        return -1;
    }

    @Override
    public double getCircumference() {
        //gi bredden sin bounds? Esje så viktig for text, kan return -1 også
        return getLayoutBounds().getWidth();
    }

    @Override
    public String getShape() {
        return "Text";
    }

    @Override
    public void createShape(double startX, double startY, double endX, double endY) {

    }

    @Override
    public void setShapeAt(double x ,double y){
    }
}
