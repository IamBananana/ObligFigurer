package application;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import  javafx.scene.paint.Color;

import java.awt.*;

public class SceneHandler{
    private Scene scene;
    private Pane drawerPane;
    public MainController mainController = new MainController();

    public SceneHandler(Scene scene) {
        this.scene = scene;
        intialize();
    }

    private void intialize(){
        BorderPane bp = (BorderPane) scene.getRoot();

        drawerPane = new Pane();
        drawerPane.setStyle("-fx-background-color: #ffffff;");

        bp.setLeft(getVBucks());
        bp.setCenter(drawerPane);

        drawerPane.setPrefWidth(scene.getWidth() * 3 / 4);

        new MouseEventHandler(drawerPane);
        Shapes[] shapes = new Shapes[10];
        shapes[0] = new myCircle(20, 30, 50, Color.RED);
        shapes[1] = new myRectangle(50, 50, 100, 50, Color.BLUE);
        drawerPane.getChildren().add((Node) shapes[0]);
        drawerPane.getChildren().add((Node) shapes[1]);


    }
    private VBox getVBucks(){
        VBox vBucks = new VBox(10);
        vBucks.setPrefWidth(scene.getWidth() / 5);
        vBucks.setAlignment(Pos.TOP_LEFT);
        vBucks.setStyle("-fx-background-color: #cccccc; -fx-padding: 10px;");
        ToggleGroup tg = new ToggleGroup();
        //Line types
        RadioButton line = new RadioButton("Line");
        RadioButton arc = new RadioButton("Arc");
        RadioButton polyline = new RadioButton("Polyline");

        //Surface types
        RadioButton rectangle = new RadioButton("Rectangle");
        //RadioButton triangle = new RadioButton();
        RadioButton circle = new RadioButton("Circle");
        RadioButton ellipse = new RadioButton("Ellipse");
        RadioButton polygon = new RadioButton("Polygon");
        line.setToggleGroup(tg);
        arc.setToggleGroup(tg);
        polyline.setToggleGroup(tg);
        rectangle.setToggleGroup(tg);
        circle.setToggleGroup(tg);
        ellipse.setToggleGroup(tg);
        polygon.setToggleGroup(tg);
        //For laterrr
        // RadioButton radioButton = (RadioButton) tg.getSelectedToggle();
        vBucks.getChildren().addAll(line, arc, polyline, rectangle, circle, ellipse, polygon);

        return vBucks;
    }
}
