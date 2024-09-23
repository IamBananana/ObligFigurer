package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

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
        //Left side

        VBox vBucks = new VBox(10);
        vBucks.setPrefWidth(scene.getWidth() / 5);
        vBucks.setAlignment(Pos.CENTER_LEFT);
        vBucks.setStyle("-fx-background-color: #cccccc; -fx-padding: 10px;");

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
        vBucks.getChildren().addAll(line, arc, polyline, rectangle, circle, ellipse, polygon);


        bp.setLeft(vBucks);

        drawerPane = new Pane();
        drawerPane.setPrefWidth(scene.getWidth() * 3 / 4);

        //Right side


    }

}
