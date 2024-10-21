package application;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class MouseEventHandler {
    double startX, startY;
    private final Pane drawablePane;
    private SceneHandler sceneHandler;
    private Shape currentShape;  // Current shape being drawn
    private Shape selectedShape = null;

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


        switch (sceneHandler.selctedType) {
            case RECTANGLE:
                currentShape = new myRectangle(startX, startY, 0, 0);
                break;
            case CIRCLE:
                currentShape = new myCircle(startX, startY, 0);
                break;
            case ELLIPSE:
                currentShape = new myEllipse(startX, startY, 0, 0);
                break;

        }

        if (currentShape != null) {
            drawablePane.getChildren().add(currentShape);  //legger til shapen, kan adde her til datastruktur?
        }
    }

    private void handleMouseDragged(MouseEvent event) {
        double endX = event.getX();
        double endY = event.getY();


        if (currentShape instanceof myRectangle) {
            myRectangle rect = (myRectangle) currentShape;
            rect.setX(Math.min(startX, endX));
            rect.setY(Math.min(startY, endY));
            rect.setWidth(Math.abs(endX - startX));
            rect.setHeight(Math.abs(endY - startY));
        } else if (currentShape instanceof myCircle) {
            myCircle circle = (myCircle) currentShape;
            double radius = Math.hypot(endX - startX, endY - startY) / 2;  // Kem sa Pytagoras læresetning va useless?
            circle.setCenterX((startX + endX) / 2);
            circle.setCenterY((startY + endY) / 2);
            circle.setRadius(radius);
        } else if (currentShape instanceof myEllipse) {
            myEllipse ellipse = (myEllipse) currentShape;
            ellipse.setCenterX((startX + endX) / 2);
            ellipse.setCenterY((startY + endY) / 2);
            ellipse.setRadiusX(Math.abs(endX - startX) / 2);
            ellipse.setRadiusY(Math.abs(endY - startY) / 2);
        }
        // Flere shapes går under her
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
