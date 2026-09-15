package cz.cvut.fel.sortvisual.view;

import cz.cvut.fel.sortvisual.model.sort.SortingAlgoInfo;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class AlgorithmInfoPane extends VBox {

    public AlgorithmInfoPane(SortingAlgoInfo info) {
        setSpacing(10);
        setPadding(new Insets(16));
        setPrefWidth(240);
        setStyle("-fx-background-color: #121212; -fx-border-color: #333333; -fx-border-width: 0 0 0 1;");

        Label title = new Label(info.getName());
        title.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 24px; -fx-font-weight: bold;");

        Label timeComplexityHeader = createSectionHeader("Time Complexity");
        Label averageCase = createDataRow("Average:", info.getAvgTimeComplexity());
        HBox worstCase = createDataRowWithNote("Worst:", info.getWorstTimeComplexity(), info.getWorstTimeComplexityNote());
        HBox bestCase = createDataRowWithNote("Best:", info.getBestTimeComplexity(), info.getBestTimeComplexityNote());

        Label stabilityHeader = createSectionHeader("Properties");
        Label stability = createDataRow("Stable:", info.isStable() ? "Yes" : "No");

        Label spaceComplexityHeader = createSectionHeader("Space Complexity");
        Label spaceComplexity = createDataRow("Memory:", info.getSpaceComplexity());

        getChildren().addAll(
                title,
                timeComplexityHeader,
                averageCase,
                worstCase,
                bestCase,
                stabilityHeader,
                stability,
                spaceComplexityHeader,
                spaceComplexity
        );
    }

    private Label createSectionHeader(String text) {
        Label header = new Label(text);
        header.setStyle("-fx-text-fill: #3498db; -fx-font-size: 20px; -fx-font-weight: bold; -fx-padding: 8 0 0 0;");
        return header;
    }

    private Label createDataRow(String label, String value) {
        Label row = new Label(label + "  " + (value != null ? value : "-"));
        row.setStyle("-fx-text-fill: #ecf0f1; -fx-font-size: 18px;");
        return row;
    }

    private HBox createDataRowWithNote(String label, String value, String note) {
        HBox row = new HBox(6);
        row.setAlignment(Pos.CENTER_LEFT);

        Label text = new Label(label + "  " + (value != null ? value : "-"));
        text.setStyle("-fx-text-fill: #ecf0f1; -fx-font-size: 20px;");
        row.getChildren().add(text);

        if (note != null && !note.isBlank()) {
            Label infoIcon = new Label("ⓘ");
            infoIcon.setStyle("-fx-text-fill: #f39c12; -fx-font-size: 18px; -fx-cursor: hand;");

            Tooltip tooltip = new Tooltip(note);
            tooltip.setShowDelay(Duration.millis(150));
            tooltip.setStyle("-fx-font-size: 12px;");
            Tooltip.install(infoIcon, tooltip);

            row.getChildren().add(infoIcon);
        }

        return row;
    }
}