package at.fhtechnikum.disys_javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.springframework.web.client.RestTemplate;

public class GUI extends Application {

    private Label communityPoolLabel;
    private Label gridPortionLabel;
    private Label communityProducedLabel;
    private Label communityUsedLabel;
    private Label gridUsedLabel;

    @Override
    public void start(Stage stage) {
        communityPoolLabel = new Label("Community Pool: NaN used");
        gridPortionLabel = new Label("Grid Portion: NaN used");

        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(e -> fetchDataFromRestService());

        Label startLabel = new Label("Start:");
        DatePicker startDatePicker = new DatePicker();
        ComboBox<String> startTimeComboBox = new ComboBox<>();

        Label endLabel = new Label("End:");
        DatePicker endDatePicker = new DatePicker();
        ComboBox<String> endTimeComboBox = new ComboBox<>();

        for (int hour = 0; hour < 24; hour++) {
            String time = String.format("%02d:00", hour);
            startTimeComboBox.getItems().add(time);
            endTimeComboBox.getItems().add(time);
        }

        Button showDataButton = new Button("Show Data");
        setupShowDataButton(showDataButton, startDatePicker, startTimeComboBox, endDatePicker, endTimeComboBox);

        communityProducedLabel = new Label("Community produced: NaN kWh");
        communityUsedLabel = new Label("Community used: NaN kWh");
        gridUsedLabel = new Label("Grid used: NaN kWh");

        // Blitzsymbol als GIF hinzufügen
        Image thunderGif = new Image(getClass().getResource("/at/fhtechnikum/disys_javafx/thunder.gif").toExternalForm());
        ImageView thunderIcon = new ImageView(thunderGif);
        thunderIcon.setFitWidth(500); // Größe bleibt gleich
        thunderIcon.setFitHeight(280);

        // Abgerundete Ecken hinzufügen
        Rectangle clip = new Rectangle(thunderIcon.getFitWidth(), thunderIcon.getFitHeight());
        clip.setArcWidth(40); // Rundung der Ecken (entspricht den Buttons)
        clip.setArcHeight(40);
        thunderIcon.setClip(clip);


        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        VBox topSection = new VBox(10, communityPoolLabel, gridPortionLabel, refreshButton);
        topSection.setAlignment(Pos.CENTER);

        GridPane dateTimeInputs = new GridPane();
        dateTimeInputs.setHgap(10);
        dateTimeInputs.setVgap(10);
        dateTimeInputs.setPadding(new Insets(10));
        dateTimeInputs.setAlignment(Pos.CENTER);
        dateTimeInputs.add(startLabel, 0, 0);
        dateTimeInputs.add(startDatePicker, 1, 0);
        dateTimeInputs.add(startTimeComboBox, 2, 0);
        dateTimeInputs.add(endLabel, 0, 1);
        dateTimeInputs.add(endDatePicker, 1, 1);
        dateTimeInputs.add(endTimeComboBox, 2, 1);

        VBox resultsSection = new VBox(10, communityProducedLabel, communityUsedLabel, gridUsedLabel);
        resultsSection.setAlignment(Pos.CENTER);

        // Blitzsymbol links hinzufügen
        VBox sceneRoot = new VBox(20); // Neues VBox-Element für die Scene
        sceneRoot.setPadding(new Insets(20));
        sceneRoot.setAlignment(Pos.CENTER);

        sceneRoot.getChildren().addAll(topSection, dateTimeInputs, showDataButton, resultsSection);

        HBox mainLayout = new HBox(20, thunderIcon, sceneRoot);
        mainLayout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(mainLayout, 950, 650);
        scene.getStylesheets().add(getClass().getResource("/at/fhtechnikum/disys_javafx/style.css").toExternalForm());
        stage.setTitle("Energy Usage Dashboard");
        stage.setScene(scene);
        stage.show();
    }

    private void fetchDataFromRestService() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/api/hour";
        CurrentHourData data = restTemplate.getForObject(url, CurrentHourData.class);

        if (data != null) {
            communityPoolLabel.setText("Community Pool: " + String.format("%.2f", data.getCommunityPool()) + " %");
            gridPortionLabel.setText("Grid Portion: " + String.format("%.2f", data.getGridPortion()) + " %");
        }
    }

    private void setupShowDataButton(Button showDataButton, DatePicker startDatePicker, ComboBox<String> startTimeComboBox, DatePicker endDatePicker, ComboBox<String> endTimeComboBox) {
        showDataButton.setOnAction(e -> {
            if (startDatePicker.getValue() != null && endDatePicker.getValue() != null &&
                    startTimeComboBox.getValue() != null && endTimeComboBox.getValue() != null) {

                String start = startDatePicker.getValue() + "T" + startTimeComboBox.getValue();
                String end = endDatePicker.getValue() + "T" + endTimeComboBox.getValue();

                RestTemplate restTemplate = new RestTemplate();
                String url = "http://localhost:8080/api/historic?start=" + start + "&end=" + end;
                HistoricData[] historicData = restTemplate.getForObject(url, HistoricData[].class);

                if (historicData != null) {
                    double totalProduced = 0;
                    double totalUsed = 0;
                    double totalGridUsed = 0;

                    for (HistoricData data : historicData) {
                        totalProduced += data.getProduced();
                        totalUsed += data.getUsed();
                        totalGridUsed += data.getGridUsed();
                    }

                    communityProducedLabel.setText("Community produced: " + String.format("%.2f", totalProduced) + " kWh");
                    communityUsedLabel.setText("Community used: " + String.format("%.2f", totalUsed) + " kWh");
                    gridUsedLabel.setText("Grid used: " + String.format("%.2f", totalGridUsed) + " kWh");
                } else {
                    showError("Keine Daten gefunden.");
                }
            } else {
                showError("Bitte Start- und Endzeit angeben.");
            }
        });
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fehler");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}