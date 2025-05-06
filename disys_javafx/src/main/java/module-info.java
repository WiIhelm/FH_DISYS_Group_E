module at.fhtechnikum.disys_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.web;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    opens at.fhtechnikum.disys_javafx to javafx.fxml;
    exports at.fhtechnikum.disys_javafx;
}