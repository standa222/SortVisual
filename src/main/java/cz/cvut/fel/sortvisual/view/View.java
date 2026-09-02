package cz.cvut.fel.sortvisual.view;

import cz.cvut.fel.sortvisual.controller.Controller;
import cz.cvut.fel.sortvisual.model.sort.SortingAlgoInfo;
import cz.cvut.fel.sortvisual.model.sort.SortingAlgorithm;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class View {
    private final Stage stage;
    private Controller controller;
    private final Canvas canvas = new Canvas(800, 500);
    private final GraphicsContext gc = canvas.getGraphicsContext2D();

    public View(Stage stage, Controller controller) {
        this.stage = stage;
        this.controller = controller;
    }

    public void showMenu(List<SortingAlgorithm> algorithms) {
        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #1e1e1e;");

        Label title = new Label("Select a Sorting Algorithm");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 24px; -fx-font-weight: bold;");

        layout.getChildren().add(title);
        for (SortingAlgorithm algo : algorithms) {
            Button btn = new Button(algo.getInfo().getName());
            btn.setPrefWidth(200);
            btn.setStyle("-fx-font-size: 16px; -fx-padding: 8px;");
            btn.setOnAction(e -> controller.onSelectAlgorithm(algo));
            layout.getChildren().add(btn);
        }

        stage.setScene(new Scene(layout, 800, 560));
    }

    public void showVisualizer(SortingAlgoInfo info) {

    }

    private VBox setupButtons() {
        // Create buttons with styling
        Button button1 = new Button("Shuffle");
        button1.setStyle("-fx-text-fill: black; -fx-font-size: 20px; -fx-padding: 10px;");
        button1.setOnAction(t -> controller.shuffle());

        VBox buttonBox = new VBox(10, button1);
        buttonBox.setStyle("-fx-padding: 20px; -fx-background-color: black");
        return buttonBox;
    }
}
