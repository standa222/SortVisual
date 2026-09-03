package cz.cvut.fel.sortvisual.view;

import cz.cvut.fel.sortvisual.controller.Controller;
import cz.cvut.fel.sortvisual.model.TrackedList;
import cz.cvut.fel.sortvisual.model.sort.SortingAlgoInfo;
import cz.cvut.fel.sortvisual.model.sort.SortingAlgorithm;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;

public class View {
    private final Stage stage;
    private final Controller controller;
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

        Label sizeLabel = new Label("Size of the list:");
        sizeLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
        TextField sizeTextField = new TextField("100");
        HBox hb = new HBox(15);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(sizeLabel, sizeTextField);

        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-size: 14px;");

        layout.getChildren().addAll(title, hb, errorLabel);
        for (SortingAlgorithm algo : algorithms) {
            Button btn = createBtn(algo, sizeTextField, errorLabel);
            layout.getChildren().add(btn);
        }

        stage.setScene(new Scene(layout, 800, 560));
    }

    private Button createBtn(SortingAlgorithm algo, TextField sizeTextField, Label errorLabel) {
        Button btn = new Button(algo.getInfo().getName());
        btn.setPrefWidth(200);
        btn.setStyle("-fx-font-size: 16px; -fx-padding: 8px;");
        btn.setOnAction(e -> {
            try {
                int size = Integer.parseInt(sizeTextField.getText().trim());
                if (size < 5 || size > 200) {
                    errorLabel.setText("Number must be between 5 and 200");
                }
                errorLabel.setText("");
                controller.onSelectAlgorithm(algo, size);
            } catch (NumberFormatException ex) {
                errorLabel.setText("Please enter a valid number");
            }
        });
        return btn;
    }

    public void showVisualizer(SortingAlgoInfo info) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #121212;");

        root.setCenter(canvas);

        HBox controls = new HBox(12);
        controls.setAlignment(Pos.TOP_LEFT);
        controls.setStyle("-fx-padding: 15px; -fx-background-color: #1e1e1e;");

        Button shuffleBtn = new Button("Shuffle");
        shuffleBtn.setOnAction(e -> controller.onShuffle());

        Button sortBtn = new Button("Start " + info.getName());
        sortBtn.setOnAction(e -> controller.onStartSort());

        Button backBtn = new Button("Back to Menu");
        backBtn.setOnAction(e -> controller.onBackToMenu());
        backBtn.setAlignment(Pos.BOTTOM_RIGHT);

        controls.getChildren().addAll(shuffleBtn, sortBtn, backBtn);
        root.setBottom(controls);

        stage.setScene(new Scene(root, 800, 560));
    }

    public void render(TrackedList data) {
        Integer[] array = data.getRawData();
        double width = canvas.getWidth();
        double height = canvas.getHeight();
        double barWidth = width / array.length;
        double maxVal = array.length;

        gc.setFill(Color.web("#121212"));
        gc.fillRect(0, 0, width, height);

        for (int i = 0; i < array.length; i++) {
            double barHeight = (array[i] / maxVal) * (height - 20);
            double x = i * barWidth;
            double y = height - barHeight;

            gc.setFill(Color.web("#9CCDFF"));
            gc.fillRect(x, y, barWidth - 1, barHeight);
        }
    }
}
