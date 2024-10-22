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

        // Add buttons for forward/backward schmovement
        createMoveButtons();
    }

    private double getArea(Shape shape) {
        if (shape instanceof Shapes) {
            return ((Shapes) shape).area();
        }
        return 0;
    }

    private double getCircumference(Shape shape) {
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

    public void addShape(Shapes shape) {
        // Check for duplicates based on unique characteristics, e.g., position and size
        if (!shapesList.contains(shape)) {
            shapesList.add(shape);  // Add shape to the LinkedList
            drawerPane.getChildren().add((Node) shape);  // Add shape to the pane
            updateShapeInfoPanel();  // Update the panel to reflect the new total count
        } else {
            System.out.println("Duplicate shape detected, not adding to the list.");
        }
    }

    private void moveShapeForward() {
        int index = shapesList.indexOf(selectedShape);
        if (index < shapesList.size() - 1) {

            Shapes nextShape = shapesList.get(index + 1);
            shapesList.set(index, nextShape);
            shapesList.set(index + 1, (Shapes) selectedShape);


            refreshPane();
        }
    }

    private void moveShapeBackward() {
        int index = shapesList.indexOf(selectedShape);
        if (index > 0) {

            Shapes previousShape = shapesList.get(index - 1);
            shapesList.set(index, previousShape);
            shapesList.set(index - 1, (Shapes) selectedShape);


            refreshPane();
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
            int currentIndex = shapesList.indexOf(selectedShape) + 1; // 1-based index
            Label shapePositionLabel = new Label("Shape " + currentIndex + " of " + totalShapes);

            // Update the existing shape position label
            if (shapeInfoPanel.getChildren().size() > 4) { // Assuming position label is at index 4
                shapeInfoPanel.getChildren().set(4, shapePositionLabel);
            }
        }
    }
}
