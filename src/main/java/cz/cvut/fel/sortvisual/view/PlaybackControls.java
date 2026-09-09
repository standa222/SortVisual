package cz.cvut.fel.sortvisual.view;

import cz.cvut.fel.sortvisual.controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class PlaybackControls extends HBox {
    private final Button playPauseBtn; // todo
    private final Button nextStepBtn;
    private final Button previousStepBtn;

    public PlaybackControls(Controller controller) {
        super(12);
        setAlignment(Pos.CENTER);
        setStyle("-fx-padding: 14px 16px; -fx-background-color: #1e1e1e;");

        previousStepBtn = new Button("Previous Step");
        previousStepBtn.setOnAction(e -> controller.onPreviousStep());

        nextStepBtn = new Button("Next Step");
        nextStepBtn.setOnAction(e -> controller.onNextStep());

        playPauseBtn =  new Button("Play");
        playPauseBtn.setOnAction(e -> controller.togglePlayPause());

        getChildren().addAll(previousStepBtn, playPauseBtn, nextStepBtn);
    }

    public void hideAll() {
        previousStepBtn.setVisible(false);
        nextStepBtn.setVisible(false);
        playPauseBtn.setVisible(false);
    }

    public void showAll() {
        previousStepBtn.setVisible(true);
        nextStepBtn.setVisible(true);;
        playPauseBtn.setVisible(true);
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

    public void enableNextStep() {
        nextStepBtn.setDisable(false);
    }

    public void setPlayPauseBtnText(String text) {
        playPauseBtn.setText(text);
    }
}
