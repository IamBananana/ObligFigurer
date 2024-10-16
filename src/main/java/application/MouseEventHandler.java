package application;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class MouseEventHandler {
    double startX, startY;
    private final Pane drawablePane;
    private SceneHandler sceneHandler;

    public MouseEventHandler(Pane drawablePane, SceneHandler sceneHandler) {
        this.drawablePane = drawablePane;
        setSceneHandler(sceneHandler);
        addMouseListeners();
    }
    public void setSceneHandler(SceneHandler sceneHandler) {
        this.sceneHandler = sceneHandler;
    }

    private void addMouseListeners() {
        drawablePane.setOnMouseClicked(this::handleMouseClick);

        drawablePane.setOnMousePressed(this::handleMousePressed);

        drawablePane.setOnMouseReleased(this::handleMouseReleased);

        drawablePane.setOnMouseDragged(this::handleMouseDragged);
    }


    private void handleMouseClick(MouseEvent event) {
        System.out.println("Mouse Clicked at: " + event.getSceneX() + ", " + event.getSceneY());
        startX = event.getSceneX();
        startY = event.getSceneY();
    }

    private void handleMousePressed(MouseEvent event) {

        System.out.println("Mouse Pressed at: " + event.getSceneX() + ", " + event.getSceneY());
    }

    private void handleMouseReleased(MouseEvent event) {
        double endX = event.getSceneX();
        double endY = event.getSceneY();
        System.out.println("Mouse Released at: " + endX + ", " + endY);

        if (sceneHandler != null) {
            sceneHandler.createShape(endX, endY, startX, startY);
        }
    }

    private void handleMouseDragged(MouseEvent event) {

    }
}
