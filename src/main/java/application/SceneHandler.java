package application;

import application.SceneHandler.type;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class SceneHandler {
    private Scene scene;
    private BorderPane rootPane;
    private Pane drawerPane;
    private MouseEventHandler mouseHandler;
    public HandlerHandler handlerHandler = new HandlerHandler();
    private VBox shapeInfoPanel;
    private Shape selectedShape;

    enum type {
        LINE,
        CIRCLE,
        RECTANGLE,
        ARC,
        POLYGON,
        POLYLINE,
        ELLIPSE;
    }

    type selctedType;
    public SceneHandler(){};
    public SceneHandler(Scene scene) {
        this.scene = scene;
        this.rootPane = (BorderPane) scene.getRoot();

        shapeInfoPanel = new VBox(10);
        shapeInfoPanel.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 10px;");

        rootPane.setCenter(drawerPane);
        rootPane.setRight(shapeInfoPanel);

        intialize();
    }

    private void intialize() {
        BorderPane bp = (BorderPane) scene.getRoot();

        drawerPane = new Pane();
        drawerPane.setStyle("-fx-background-color: #ffffff;");


        bp.setCenter(drawerPane);
        bp.setLeft(getVBucks());

        drawerPane.setPrefWidth(scene.getWidth() * 3 / 4);

        mouseHandler = new MouseEventHandler(drawerPane, this);

        Shapes[] shapes = new Shapes[10];
    }

    private VBox getVBucks() {
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

        line.setOnAction(e -> selctedType = type.LINE);
        arc.setOnAction(e -> selctedType = type.ARC);
        polyline.setOnAction(e -> selctedType = type.POLYLINE);
        rectangle.setOnAction(e -> selctedType = type.RECTANGLE);
        circle.setOnAction(e -> selctedType = type.CIRCLE);
        ellipse.setOnAction(e -> selctedType = type.ELLIPSE);
        polygon.setOnAction(e -> selctedType = type.POLYGON);

        //For laterrr
        // RadioButton radioButton = (RadioButton) tg.getSelectedToggle();
        vBucks.getChildren().addAll(line, arc, polyline, rectangle, circle, ellipse, polygon);

        return vBucks;
    }

    public void createShape(double startX, double startY, double endX, double endY) {
        if (selctedType != null)
            switch (selctedType) {
                case LINE: lineDraw(startX, startY);
                    break;
                case ARC: arcDraw(startX, startY, startX, startY);
                    break;
                case POLYLINE: polyLineDraw(startX, startY);
                    break;
                case RECTANGLE: rectangleDraw(startX, startY, endX, endY);
                    break;
                case CIRCLE: circleDraw(startX, startY);
                    break;
                case ELLIPSE: ellipseDraw(startX, startY);
                    break;
                case POLYGON: polygonDraw(startX, startY);
                    break;
            }
    }

    private void ellipseDraw(double x, double y) {
    }

    private void polygonDraw(double x, double y) {
    }

    private void lineDraw(double startX, double startY) {

    }

    private void arcDraw(double startX, double startY, double endX, double endY) {
        throw new RuntimeException("BRRRR");
    }

    private void polyLineDraw(double x, double y){
        throw new RuntimeException("SKRRRRT");
    }

    private void rectangleDraw(double startX, double startY, double endX, double endY) {
        //x2 y2 is released cords = width and height
        myRectangle rectangle = new myRectangle(startX, startY, endY, endX);
        drawerPane.getChildren().add(rectangle);
    }

    private void circleDraw(double x, double y){
        myCircle circle = new myCircle(x, y, 20);

        drawerPane.getChildren().add(circle);
    }

    public void showShapeDetails(Shape shape) {
        selectedShape = shape;
        shapeInfoPanel.getChildren().clear();  // Clear previous info

        // Display shape details
        Label shapeTypeLabel = new Label("Shape: " + shape.getClass().getSimpleName());
        Label areaLabel = new Label("Area: " + calculateArea(shape));
        Label circumferenceLabel = new Label("Circumference: " + calculateCircumference(shape));

        // Stroke color
        ColorPicker strokeColorPicker = new ColorPicker();
        strokeColorPicker.setValue((Color) ((Shape) shape).getStroke());
        strokeColorPicker.setOnAction(e -> ((Shape) shape).setStroke(strokeColorPicker.getValue()));

        //Fill color
        ColorPicker fillColorPicker = new ColorPicker();
        fillColorPicker.setValue((Color) ((Shape) shape).getFill());  // Get the current fill color
        fillColorPicker.setOnAction(e -> ((Shape) shape).setFill(fillColorPicker.getValue()));

        shapeInfoPanel.getChildren().addAll(
                shapeTypeLabel,
                areaLabel,
                circumferenceLabel,
                new Label("Stroke Color:"), strokeColorPicker,
                new Label("Fill Color:"), fillColorPicker
        );

        // Add buttons for forward/backward schmovement
        createMoveButtons();
    }

    private double calculateArea(Shape shape) {
        if (shape instanceof Shapes) {
            return ((Shapes) shape).area();
        }
        return 0;
    }
    private double calculateCircumference(Shape shape) {
        if (shape instanceof Shapes) {
            return ((Shapes) shape).circumference();
        }
        return 0;
    }
    private void createMoveButtons() {
        Button forwardButton = new Button("Move Forward");
        Button backButton = new Button("Move Backward");

        forwardButton.setOnAction(e -> moveShapeForward());
        backButton.setOnAction(e -> moveShapeBackward());

        shapeInfoPanel.getChildren().addAll(forwardButton, backButton);
    }

    private void moveShapeForward() {
    }

    private void moveShapeBackward() {
    }

}
