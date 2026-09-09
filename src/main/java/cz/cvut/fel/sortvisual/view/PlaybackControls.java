package cz.cvut.fel.sortvisual.view;

import cz.cvut.fel.sortvisual.controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import lombok.Getter;

@Getter
public class PlaybackControls extends HBox {
    private final Button playPauseBtn;
    private final Button nextStepBtn;
    private final Button previousStepBtn;
    private final Button slowerBtn;
    private final Button fasterBtn;

    public PlaybackControls(Controller controller) {
        super(12);
        setAlignment(Pos.CENTER);
        setStyle("-fx-padding: 14px 16px; -fx-background-color: #1e1e1e;");

        previousStepBtn = new Button("Previous Step");
        previousStepBtn.setOnAction(e -> controller.onPreviousStep());

        nextStepBtn = new Button("Next Step");
        nextStepBtn.setOnAction(e -> controller.onNextStep());

        playPauseBtn = new Button("Play");
        playPauseBtn.setOnAction(e -> controller.togglePlayPause());

        slowerBtn = new Button("Slow down");
        slowerBtn.setOnAction(e -> controller.onSlowDown());

        fasterBtn = new Button("Speed up");
        fasterBtn.setOnAction(e -> controller.onSpeedUp());

        getChildren().addAll(previousStepBtn, slowerBtn, playPauseBtn, fasterBtn, nextStepBtn);
    }

    public void hideAll() {
        previousStepBtn.setVisible(false);
        nextStepBtn.setVisible(false);
        playPauseBtn.setVisible(false);
        slowerBtn.setVisible(false);
        fasterBtn.setVisible(false);
    }

    public void showAll() {
        previousStepBtn.setVisible(true);
        nextStepBtn.setVisible(true);
        playPauseBtn.setVisible(true);
        slowerBtn.setVisible(true);
        fasterBtn.setVisible(true);
    }

    public void disablePreviousStep() {
        previousStepBtn.setDisable(true);
    }

    public void enablePreviousStep() {
        previousStepBtn.setDisable(false);
    }

    public void disableNextAndPlay() {
        nextStepBtn.setDisable(true);
        playPauseBtn.setDisable(true);
    }

    public void enableNextAndPlay() {
        nextStepBtn.setDisable(false);
        playPauseBtn.setDisable(false);
    }

    public void setPlayPauseBtnText(String text) {
        playPauseBtn.setText(text);
    }

    public void enableFasterAndSlower() {
        fasterBtn.setDisable(false);
        slowerBtn.setDisable(false);
    }

    public void disableFasterAndSlower() {
        fasterBtn.setDisable(true);
        slowerBtn.setDisable(true);
    }
}
