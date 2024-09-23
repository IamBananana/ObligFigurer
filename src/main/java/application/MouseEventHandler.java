package application;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class MouseEventHandler {

    private final Pane drawablePane;

    public MouseEventHandler(Pane drawablePane) {
        this.drawablePane = drawablePane;
        addMouseListeners();
    }

    private void addMouseListeners() {
        drawablePane.setOnMouseClicked(this::handleMouseClick);

        drawablePane.setOnMousePressed(this::handleMousePressed);

        drawablePane.setOnMouseReleased(this::handleMousePressed);
    }



    private void handleMouseClick(MouseEvent event) {
        System.out.println("Mouse Clicked at: " + event.getSceneX() + ", " + event.getSceneY());
    }
    private void handleMousePressed(MouseEvent event) {
        System.out.println("Mouse Pressed at: " + event.getSceneX() + ", " + event.getSceneY());
    }
    private void handleMouseReleased(MouseEvent event) {
        System.out.println("Mouse Released at: " + event.getSceneX() + ", " + event.getSceneY());
    }
}
