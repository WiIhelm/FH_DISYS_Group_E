module at.fhtechnikum.disys_javafx {
    requires spring.context;
    requires spring.boot;
    requires spring.beans;
    requires spring.web;
    requires spring.boot.autoconfigure;
    requires com.fasterxml.jackson.annotation;


    opens at.fhtechnikum.disys_javafx to javafx.fxml;
    exports at.fhtechnikum.disys_javafx;
}