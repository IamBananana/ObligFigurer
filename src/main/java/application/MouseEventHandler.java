package application;

import javafx.scene.Node;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class MouseEventHandler {
    double startX, startY;
    private final Pane drawablePane;
    private SceneHandler sceneHandler;
    private Shape currentShape;  // Current shape being drawn
    private Shape selectedShape = null;
    double endX, endY;

    public MouseEventHandler(Pane drawablePane, SceneHandler sceneHandler) {
        this.drawablePane = drawablePane;
        setSceneHandler(sceneHandler);
        addMouseListeners();
    }

    public void setSceneHandler(SceneHandler sceneHandler) {
        this.sceneHandler = sceneHandler;
    }

    private void addMouseListeners() {
        drawablePane.setOnMousePressed(this::handleMousePressed);
        drawablePane.setOnMouseDragged(this::handleMouseDragged);
        drawablePane.setOnMouseReleased(this::handleMouseReleased);
        // Double-click for selecting a shape
        drawablePane.setOnMouseClicked(this::handleMouseClick);
        //kanskje en double-click and hold for resizing av en allerede lagd shape? idk
    }

    private void handleMousePressed(MouseEvent event) {
        startX = event.getX();
        startY = event.getY();
        endX = startX;
        endY = startY;

        //Gjorde endX og endY global
        //Da starter vi ikke i hjørnet hele tiden

        //Slipper error og får det bare opp under keyPress
        if(sceneHandler.selectedType == null){
            System.out.println("Ingen type valgt.");
            return;
        }

        switch (sceneHandler.selectedType) {
            case RECTANGLE:
                currentShape = new myRectangle(startX, startY, endX, endY);
                break;
            case CIRCLE:
                currentShape = new myCircle(startX, startY, 0);
                break;
            case ELLIPSE:
                currentShape = new myEllipse(startX, startY, endX, endY);
                break;
            case LINE:
                currentShape = new myLine(startX, startY, endX, endY);
                break;
            case TEXT:
                TextInputDialog dialog = new TextInputDialog("Your Text Here");
                dialog.setTitle("Input Text");
                dialog.setHeaderText("Enter the text you want to display:");
                dialog.setContentText("Text:");

                dialog.showAndWait().ifPresent(content -> {
                    currentShape = new myText(startX, startY, content);
                    drawablePane.getChildren().add(currentShape);  // Add to the pane for visual feedback
                    sceneHandler.addShape((Shapes) currentShape); // Add shape to the handler
                });
                break;
        }

        if (currentShape != null) {
            sceneHandler.addShape((Shapes) currentShape);
        }
    }

    private void handleMouseDragged(MouseEvent event) {
        endX = event.getX();
        endY = event.getY();

        if(sceneHandler.selectedType == null) return;
        switch (sceneHandler.selectedType) {
            case RECTANGLE:
                ((myRectangle) currentShape).createShape(startX, startY, endX, endY);
                break;
            case CIRCLE:
                ((myCircle) currentShape).createShape(startX, startY, endX, endY);
                break;
            case ELLIPSE:
                ((myEllipse) currentShape).createShape(startX, startY, endX, endY);
            case LINE:
                ((myLine) currentShape).createShape(startX, startY, endX, endY);
        }
    }

    private void handleMouseReleased(MouseEvent event) {
        // Resetter current shape
        currentShape = null;
    }

    private void handleMouseClick(MouseEvent event) {
        if (event.getClickCount() == 2) {  // Double-click
            Node clickedShape = findShapeAt(event.getX(), event.getY());
            if (clickedShape != null) {
                selectedShape = (Shape) clickedShape;
                sceneHandler.showShapeDetails(selectedShape);  // Display info about the selected shape
            }
        }
    }

    private Shape findShapeAt(double x, double y) {
        for (Node node : drawablePane.getChildren()) {
            if (node instanceof Shape) {
                Shape shape = (Shape) node;
                if (shape.contains(x, y)) {
                    return shape;
                }
            }
        }
        return null;
    }
}
