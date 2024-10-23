package application;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
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
    public Pane drawerPane;
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
        ELLIPSE,
        TEXT
    }

    type selectedType;

    /**
     * Constructor for sceneHandler
     * sceneHandler har som jobb i å håndtere og konstruere alt på scenen
     * Bygger opp scenen og plasserer
     * @param scene
     */
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

        mouseHandler = new MouseEventHandler(this);

    }

    /**
     * Lager en vertikal box som inneholder radioknapper for valg av shapes brukeren kan velge.
     * @return returnerer VBoxen med innhold
     */
    private VBox getVBucks() {
        VBox vBucks = new VBox(10);
        vBucks.setPrefWidth(scene.getWidth() / 5);
        vBucks.setAlignment(Pos.TOP_LEFT);
        vBucks.setStyle("-fx-background-color: #cccccc; -fx-padding: 10px;");
        ToggleGroup tg = new ToggleGroup();
        RadioButton selector = new RadioButton("Selector");
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
        RadioButton text = new RadioButton("Text");

        selector.setToggleGroup(tg);
        line.setToggleGroup(tg);
        arc.setToggleGroup(tg);
        polyline.setToggleGroup(tg);
        rectangle.setToggleGroup(tg);
        circle.setToggleGroup(tg);
        ellipse.setToggleGroup(tg);
        polygon.setToggleGroup(tg);
        text.setToggleGroup(tg);

        selector.setOnAction(e -> {
            selectedType = null;
            drawerPane.setCursor(Cursor.CROSSHAIR); });
        selector.setOnAction(e -> {
            selectedType = null;
            drawerPane.setCursor(Cursor.CROSSHAIR); });
        line.setOnAction(e -> {
            selectedType = type.LINE;
            drawerPane.setCursor(Cursor.DEFAULT); });
        arc.setOnAction(e -> {
            selectedType = type.ARC;
            drawerPane.setCursor(Cursor.DEFAULT); });
        polyline.setOnAction(e -> {
            selectedType = type.POLYLINE;
            drawerPane.setCursor(Cursor.DEFAULT); });
        rectangle.setOnAction(e -> {
            selectedType = type.RECTANGLE;
            drawerPane.setCursor(Cursor.DEFAULT); });
        circle.setOnAction(e -> {
            selectedType = type.CIRCLE;
            drawerPane.setCursor(Cursor.DEFAULT); });
        ellipse.setOnAction(e -> {
            selectedType = type.ELLIPSE;
            drawerPane.setCursor(Cursor.DEFAULT); });
        polygon.setOnAction(e -> {
            selectedType = type.POLYGON;
            drawerPane.setCursor(Cursor.DEFAULT); });
        text.setOnAction(e -> {
            selectedType = type.TEXT;
            drawerPane.setCursor(Cursor.DEFAULT); });

        vBucks.getChildren().addAll(selector, line, arc, polyline, rectangle, circle, ellipse, polygon, text);

        return vBucks;
    }

    public void showShapeDetails(Shape shape) {
        selectedShape = shape;
        shapeInfoPanel.getChildren().clear();  // Clear previous info

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

        if (shape instanceof myText textShape) {
            Label textContentLabel = new Label("Text Content: " + textShape.getText());
            shapeInfoPanel.getChildren().add(textContentLabel);
        }

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
