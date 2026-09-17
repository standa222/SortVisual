package cz.cvut.fel.sortvisual.view;

import cz.cvut.fel.sortvisual.controller.Controller;
import cz.cvut.fel.sortvisual.model.TimeComplexityData;
import cz.cvut.fel.sortvisual.model.sort.SortingAlgoInfo;
import cz.cvut.fel.sortvisual.model.sort.SortingAlgorithm;
import cz.cvut.fel.sortvisual.utils.Colors;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lombok.Getter;

import java.util.List;
import java.util.function.Function;

@Getter
public class View {
    private final Stage stage;
    private final Controller controller;
    private final Canvas canvas = new Canvas(800, 500);
    private final GraphicsContext gc = canvas.getGraphicsContext2D();
    private final PlaybackControls controls;

    public View(Stage stage, Controller controller) {
        this.stage = stage;
        this.controller = controller;
        controls = new PlaybackControls(controller);
        controls.hideAll();
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

        stage.setScene(new Scene(layout, 1040, 600));
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
                    return;
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

        HBox topBar = new HBox(12);
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setStyle("-fx-padding: 12px 16px; -fx-background-color: #1e1e1e;");

        Button backBtn = new Button("Back to Menu");
        backBtn.setOnAction(e -> controller.onBackToMenu());

        Button shuffleBtn = new Button("Shuffle");
        shuffleBtn.setOnAction(e -> controller.onShuffle());

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button sortBtn = new Button("Sort ");
        sortBtn.setOnAction(e -> controller.onStartSort());

        topBar.getChildren().addAll(shuffleBtn, sortBtn, spacer, backBtn);
        root.setTop(topBar);

        root.setCenter(canvas);
        root.setBottom(controls);
        root.setRight(new AlgorithmInfoPane(info, controller));

        stage.setScene(new Scene(root, 1040, 600));
    }

    public void render(Integer[] array, boolean[] sortedArray, Integer activeA, Integer activeB, String activeColor) {
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

            if (sortedArray[i]) {
                gc.setFill(Color.web(Colors.SORTED));
            } else if (i == activeA || i == activeB) {
                gc.setFill(Color.web(activeColor));
            } else {
                gc.setFill(Color.web(Colors.DEFAULT));
            }
            gc.fillRect(x, y, barWidth - 1, barHeight);
        }
    }

    public void render(Integer[] array, boolean[] sortedArray) {
        render(array, sortedArray, -1, -1, null);
    }

    public void showTimeComplexity(TimeComplexityData data) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #121212;");

        HBox topBar = new HBox(12);
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setStyle("-fx-padding: 12px 16px; -fx-background-color: #1e1e1e;");

        Button backBtn = new Button("Back to visualizer");
        backBtn.setOnAction(e -> controller.onBackToVisualizer());

        Canvas chartCanvas = new Canvas(500, 500);
        GraphicsContext chartGc = chartCanvas.getGraphicsContext2D();
        drawComplexityData(chartGc, data);

        root.setCenter(chartCanvas);

        topBar.getChildren().add(backBtn);
        root.setTop(topBar);
        stage.setScene(new Scene(root, 1040, 600));
    }

    private void drawComplexityData(GraphicsContext chartGc, TimeComplexityData data) {
        double width = chartGc.getCanvas().getWidth();
        double height = chartGc.getCanvas().getHeight();
        int padding = 25;

        chartGc.setFill(Color.web("#121212"));
        chartGc.fillRect(0, 0, width, height);

        // Draw axes
        chartGc.setStroke(Color.web("#ffffff"));
        chartGc.strokeLine(padding, height - padding, width - padding, height - padding); // X-axis
        chartGc.strokeLine(padding, padding, padding, height - padding); // Y-axis

        // Draw labels
        chartGc.setFill(Color.web("#ffffff"));
        chartGc.fillText("Input Size (n)", width / 2, height - 10);
        chartGc.fillText("Number of operations", 10, 10);

        // Draw complexity curves
        chartGc.setLineWidth(3);
        chartGc.setStroke(Color.web("#EB27F5"));
        chartGc.strokeLine(padding, height - padding, width - padding, padding);

        chartGc.setStroke(Color.web("#13cf42"));
        drawFunctionCurve(chartGc, width, height, padding, (n) -> n * Math.log(n)); // O(n log n)
        chartGc.setStroke(Color.web("#ff2929"));
        drawFunctionCurve(chartGc, width, height, padding, (n) -> (double) (n * n)); // O(n^2)
    }

    private void drawFunctionCurve(GraphicsContext chartGc, double width, double height, int padding, Function<Integer, Double> function) {
        for (int x = 0; x < width - 2 * padding; x++) {
            double y = function.apply(x);
            double xCord = x + padding;
            double yCord = height - padding - y;

            System.out.println("point: x=" + x + ", y=" + y + ", xCord=" + xCord + ", yCord=" + yCord);

            chartGc.strokeLine(xCord, yCord, padding + x + 1, yCord);
        }
    }
}
