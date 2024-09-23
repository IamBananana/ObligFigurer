package application;

import javafx.application.Application;
import javafx.stage.Stage;

import java.awt.*;

public class MouseListener extends Application {

    @Override
    public void start(Stage stage) {
        Circle circle = new Circle(50, 50, 30);
        circle.setColor(Color.BLUE);
    }
}
