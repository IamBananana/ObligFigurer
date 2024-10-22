package application;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.util.LinkedList;

public class SceneHandler {
    private Scene scene;
    private BorderPane rootPane;
    private Pane drawerPane;
    private MouseEventHandler mouseHandler;
    public HandlerHandler handlerHandler = new HandlerHandler();
    private VBox shapeInfoPanel;
    private Shape selectedShape;
    private LinkedList<Shapes> shapesList = new LinkedList<>();

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

    public SceneHandler() {}

    public SceneHandler(Scene scene) {
        this.scene = scene;
        this.rootPane = (BorderPane) scene.getRoot();

        shapeInfoPanel = new VBox(10);
        shapeInfoPanel.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 10px;");

        rootPane.setCenter(drawerPane);
        rootPane.setRight(shapeInfoPanel);
        
        drawerPane = new Pane();
        drawerPane.setStyle("-fx-background-color: #ffffff;");


        rootPane.setCenter(drawerPane);
        rootPane.setLeft(getVBucks());

        drawerPane.setPrefWidth(scene.getWidth() * 3 / 4);

        mouseHandler = new MouseEventHandler(drawerPane, this);

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

    public void showShapeDetails(Shape shape) {
        selectedShape = shape;
        shapeInfoPanel.getChildren().clear();  // Clear previous info

        // Display shape details
        /*
         *Er ikke alle av 'shape' vi lager av 'Shapes' interfacet?
         * Så hvorfor sjekke? Kan vi ikke bare caste her med en gang.
         * + ordet 'calculate' er kanskje misleading siden det ikke skjer noen kalkuleringer i metoden.
         *
         *
         * Enig, endra d te getArea/circumference
         * prøvde å bruke shapes interface for å getShape() men d bugga
         */
//        Label shapeTypeLabel = new Label("Shape: " + shapes.getShape());
        Label shapeTypeLabel = new Label("Shape: " + shape.getClass().getSimpleName());
        Label areaLabel = new Label("Area: " + getArea(shape));
        Label circumferenceLabel = new Label("Circumference: " + getCircumference(shape));

        // Stroke color
        ColorPicker strokeColorPicker = new ColorPicker();
        strokeColorPicker.setValue((Color) shape.getStroke());
        strokeColorPicker.setOnAction(e -> {
            System.out.println("hii");
            shape.setStroke(strokeColorPicker.getValue());
        });

        //Fill color
        ColorPicker fillColorPicker = new ColorPicker();
        fillColorPicker.setValue((Color) shape.getFill());  // Get the current fill color
        fillColorPicker.setOnAction(e -> shape.setFill(fillColorPicker.getValue()));

        shapeInfoPanel.getChildren().addAll(
                shapeTypeLabel,
                areaLabel,
                circumferenceLabel,
                new Label("Stroke Color:"), strokeColorPicker,
                new Label("Fill Color:"), fillColorPicker
        );

        createMoveButtons();
    }

    private double getArea(Shape shape) {
        if (shape instanceof Shapes) {
            return ((Shapes) shape).getArea();
        }
        return 0;
    }

    private double getCircumference(Shape shape) {
        if (shape instanceof Shapes) {
            return ((Shapes) shape).getCircumference();
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
        int index = shapesList.indexOf(selectedShape);
        if (index >= 0 && index < shapesList.size() - 1) {

            Shapes shapeToMove = shapesList.remove(index);
            shapesList.add(index + 1, shapeToMove);
            refreshPane();
        }
    }

    private void moveShapeBackward() {
        int index = shapesList.indexOf(selectedShape);
        if (index > 0) {
            // Move the selected shape to the previous position
            Shapes shapeToMove = shapesList.remove(index);
            shapesList.add(index - 1, shapeToMove);
            refreshPane();  // Refresh the pane to update the display
        }
    }

    private void refreshPane() {

        drawerPane.getChildren().clear();
        for (Shapes shape : shapesList) {
            drawerPane.getChildren().add((Node) shape);
        }
        updateShapeInfoPanel();  // Update position info
    }

    private void updateShapeInfoPanel() {
        if (selectedShape != null) {
            int totalShapes = shapesList.size();
            int currentIndex = shapesList.indexOf(selectedShape) + 1;
            Label shapePositionLabel = new Label("Shape " + currentIndex + " of " + totalShapes);


            if (shapeInfoPanel.getChildren().size() > 4) {
                shapeInfoPanel.getChildren().set(4, shapePositionLabel);
            }
        }
    }
    public void addShape(Shapes shape) {

        boolean isDuplicate = false;

        for (Shapes existingShape : shapesList) {

            if (existingShape.getX() == shape.getX() &&
                    existingShape.getY() == shape.getY() &&
                    existingShape.getClass() == shape.getClass()) {
                isDuplicate = true;
                break;
            }
        }

        if (!isDuplicate) {
            shapesList.add(shape);
            drawerPane.getChildren().add((Node) shape);
            updateShapeInfoPanel();
            System.out.println("Shape added: " + shape);
        } else {
            System.out.println("Duplicate shape detected, not adding to the list: " + shape);
        }
    }

}
