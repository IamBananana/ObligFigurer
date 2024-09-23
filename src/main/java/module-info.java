module com.example.obligfigur {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.obligfigur to javafx.fxml;
    exports com.example.obligfigur;
}