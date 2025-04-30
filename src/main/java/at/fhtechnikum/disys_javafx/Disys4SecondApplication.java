package at.fhtechnikum.disys_javafx;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

@SpringBootApplication
public class Disys4SecondApplication {

    public static void main(String[] args) {
        SpringApplication.run(Disys4SecondApplication.class, args);
        Application.launch(GUI.class, args); // Start the JavaFX GUI
    }

}
