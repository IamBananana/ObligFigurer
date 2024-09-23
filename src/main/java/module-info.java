module com.example.obligfigur {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.obligfigur to javafx.fxml;
    exports com.example.obligfigur;
}