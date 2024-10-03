package application;

import javafx.scene.control.Button;

public class HandlerHandler {
    private Button myButton;

    public void initialize() {
        myButton.setOnAction(event -> handleButtonClick());
    }

    private void handleButtonClick() {
        System.out.println("Button clicked!");
    }
}
