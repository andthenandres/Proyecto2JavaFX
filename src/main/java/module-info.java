module com.example.javafxclase2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafxclase2 to javafx.fxml;
    exports com.example.javafxclase2;
}