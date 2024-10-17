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
        System.out.println("Mouse Clicked at: " + event.getX() + ", " + event.getY());

    }

    private void handleMousePressed(MouseEvent event) {
        startX = event.getX();
        startY = event.getY();

        System.out.println("Mouse Pressed at: " + event.getX() + ", " + event.getY());
    }

    private void handleMouseReleased(MouseEvent event) {
        /*
        double endX = event.getX();
        double endY = event.getY();
        System.out.println("Mouse Released at: " + endX + ", " + endY);

        if (sceneHandler != null) {
            sceneHandler.createShape(startX, startY, endX, endY);
        }

        startX = 0;
        startY = 0;

         */
    }

    private void handleMouseDragged(MouseEvent event) {
        //System.out.println("Mouse drag: " + event.getX() + " , "+ event.getY());
        double endX = event.getX();
        double endY = event.getY();
        System.out.println("Mouse drag at: " + endX + ", " + endY);

        if (sceneHandler != null) {
            sceneHandler.createShape(startX, startY, endX, endY);
        }
    }
}
