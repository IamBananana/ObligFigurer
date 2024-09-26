package application;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, screenWidth * 0.8, screenHeight * 0.8);


        SceneHandler sceneHandler = new SceneHandler(scene);

        stage.setTitle("Geometry Application with Mouse Listeners");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}