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

/**
 * Hauptklasse für das Energie-Dashboard.
 * Baut das UI auf und verbindet es mit REST-Services.
 */
public class GUI extends Application {

    // UI-Komponenten
    private Label communityPoolLabel;
    private Label gridPortionLabel;
    private Label communityProducedLabel;
    private Label communityUsedLabel;
    private Label gridUsedLabel;

    private DatePicker startDatePicker;
    private ComboBox<String> startTimeComboBox;
    private DatePicker endDatePicker;
    private ComboBox<String> endTimeComboBox;

    @Override
    public void start(Stage stage) {
        // Initialisiere UI-Komponenten
        initLabels();
        initDateTimePickers();

        // Baue das UI
        VBox topSection = buildTopSection();
        GridPane dateTimeInputs = buildDateTimeInputs();
        VBox resultsSection = buildResultsSection();
        Button showDataButton = buildShowDataButton();

        VBox sceneRoot = new VBox(20, topSection, dateTimeInputs, showDataButton, resultsSection);
        sceneRoot.setPadding(new Insets(20));
        sceneRoot.setAlignment(Pos.CENTER);

        HBox mainLayout = new HBox(20, buildThunderIcon(), sceneRoot);
        mainLayout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(mainLayout, 950, 650);
        scene.getStylesheets().add(getClass().getResource("/at/fhtechnikum/disys_javafx/style.css").toExternalForm());
        stage.setTitle("Energy Usage Dashboard");
        stage.getIcons().add(new Image(getClass().getResource("/at/fhtechnikum/disys_javafx/pika_sprite.png").toExternalForm()));
        stage.setScene(scene);
        stage.show();
    }

    /** Initialisiert die Label-Komponenten. */
    private void initLabels() {
        communityPoolLabel = new Label("Community Pool: NaN used");
        gridPortionLabel = new Label("Grid Portion: NaN used");
        communityProducedLabel = new Label("Community produced: NaN kWh");
        communityUsedLabel = new Label("Community used: NaN kWh");
        gridUsedLabel = new Label("Grid used: NaN kWh");
    }

    /** Initialisiert die DatePicker und ComboBoxen für Zeitangaben. */
    private void initDateTimePickers() {
        startDatePicker = new DatePicker();
        startTimeComboBox = createHourComboBox();
        endDatePicker = new DatePicker();
        endTimeComboBox = createHourComboBox();
    }

    /** Erstellt eine ComboBox mit Stundenangaben. */
    private ComboBox<String> createHourComboBox() {
        ComboBox<String> comboBox = new ComboBox<>();
        for (int hour = 0; hour < 24; hour++) {
            comboBox.getItems().add(String.format("%02d:00", hour));
        }
        return comboBox;
    }

    /** Baut den oberen Bereich mit aktuellen Werten und Refresh-Button. */
    private VBox buildTopSection() {
        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(e -> fetchCurrentHourData());
        VBox topSection = new VBox(10, communityPoolLabel, gridPortionLabel, refreshButton);
        topSection.setAlignment(Pos.CENTER);
        return topSection;
    }

    /** Baut die Eingabefelder für Start- und Endzeit. */
    private GridPane buildDateTimeInputs() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));
        grid.setAlignment(Pos.CENTER);
        grid.add(new Label("Start:"), 0, 0);
        grid.add(startDatePicker, 1, 0);
        grid.add(startTimeComboBox, 2, 0);
        grid.add(new Label("End:"), 0, 1);
        grid.add(endDatePicker, 1, 1);
        grid.add(endTimeComboBox, 2, 1);
        return grid;
    }

    /** Baut den Button zum Anzeigen der historischen Daten. */
    private Button buildShowDataButton() {
        Button showDataButton = new Button("Show Data");
        showDataButton.setOnAction(e -> fetchHistoricData());
        return showDataButton;
    }

    /** Baut den Bereich für die Ergebnis-Labels. */
    private VBox buildResultsSection() {
        VBox results = new VBox(10, communityProducedLabel, communityUsedLabel, gridUsedLabel);
        results.setAlignment(Pos.CENTER);
        return results;
    }

    /** Baut das Thunder-Icon mit abgerundeten Ecken. */
    private ImageView buildThunderIcon() {
        Image thunderGif = new Image(getClass().getResource("/at/fhtechnikum/disys_javafx/thunder.gif").toExternalForm());
        ImageView thunderIcon = new ImageView(thunderGif);
        thunderIcon.setFitWidth(500);
        thunderIcon.setFitHeight(280);
        Rectangle clip = new Rectangle(thunderIcon.getFitWidth(), thunderIcon.getFitHeight());
        clip.setArcWidth(40);
        clip.setArcHeight(40);
        thunderIcon.setClip(clip);
        return thunderIcon;
    }

    /** Holt aktuelle Daten vom REST-Service. */
    private void fetchCurrentHourData() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8080/api/hour";
            CurrentHourData data = restTemplate.getForObject(url, CurrentHourData.class);
            if (data != null) {
                communityPoolLabel.setText("Community Pool: " + String.format("%.2f", data.getCommunityPool()) + " %");
                gridPortionLabel.setText("Grid Portion: " + String.format("%.2f", data.getGridPortion()) + " %");
            }
        } catch (Exception ex) {
            showError("Fehler beim Laden der aktuellen Daten.");
        }
    }

    /** Holt historische Daten vom REST-Service und zeigt sie an. */
    private void fetchHistoricData() {
        if (startDatePicker.getValue() != null && endDatePicker.getValue() != null &&
                startTimeComboBox.getValue() != null && endTimeComboBox.getValue() != null) {
            String start = startDatePicker.getValue() + "T" + startTimeComboBox.getValue() + ":00";
            String end = endDatePicker.getValue() + "T" + endTimeComboBox.getValue() + ":00";
            try {
                RestTemplate restTemplate = new RestTemplate();
                String url = "http://localhost:8080/api/historic?start=" + start + "&end=" + end;
                HistoricData[] historicData = restTemplate.getForObject(url, HistoricData[].class);
                if (historicData != null) {
                    double totalProduced = 0, totalUsed = 0, totalGridUsed = 0;
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
            } catch (Exception ex) {
                showError("Fehler beim Laden der historischen Daten.");
            }
        } else {
            showError("Bitte Start- und Endzeit angeben.");
        }
    }

    /** Zeigt eine Fehlermeldung an. */
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