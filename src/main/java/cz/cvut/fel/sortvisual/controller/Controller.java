package cz.cvut.fel.sortvisual.controller;

import cz.cvut.fel.sortvisual.model.SortStep;
import cz.cvut.fel.sortvisual.model.sort.SortingAlgorithm;
import cz.cvut.fel.sortvisual.utils.Colors;
import cz.cvut.fel.sortvisual.view.View;
import cz.cvut.fel.sortvisual.model.Model;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.Arrays;
import java.util.List;

import static cz.cvut.fel.sortvisual.model.SortStepType.MARK_SORTED;

public class Controller {
    private View view;
    private final Model model;
    private final List<SortingAlgorithm> algorithms;
    private Integer[] viewArray;
    private boolean[] viewSortedArray;
    private int stepIndex = 0;
    private boolean finished = false;
    private Timeline timeline;
    private boolean isPlaying = false;

    private static final int FRAME_DURATION_MILLIS = 30;

    public Controller(Model model,  List<SortingAlgorithm> algorithms) {
        this.model = model;
        this.algorithms = algorithms;
    }

    public void onShuffle() {
        pause();
        model.shuffle();
        viewArray = model.getData().getRawData().clone();
        Arrays.fill(viewSortedArray, false);
        view.render(viewArray, viewSortedArray);
    }

    // called on init - show menu immediately
    public void setView(View view) {
        this.view = view;
        view.showMenu(algorithms);
    }

    public void onSelectAlgorithm(SortingAlgorithm algo, int size) {
        model.setSortingAlgo(algo, size);
        view.showVisualizer(algo.getInfo());
        viewArray = model.getData().getRawData().clone();
        viewSortedArray = new boolean[viewArray.length];
        view.render(viewArray, viewSortedArray);
    }

    public void onStartSort() {
        pause();
        stepIndex = 0;
        finished = false;
        model.sort();
        initTimeline();
        view.getControls().showAll();
        view.getControls().disablePreviousStep();
    }

    public void onBackToMenu() {
        pause();
        view.showMenu(algorithms);
        view.getControls().hideAll();
    }

    public void onNextStep() {
        pause(); // pause on manual next step button click
        playNextStep();
    }

    public void playNextStep() {
        if (!finished && !model.getData().getSteps().isEmpty()) {
            SortStep current = model.getData().getSteps().get(stepIndex);

            switch (current.getType()) {
                case COMPARE -> handleCompare(current);
                case SWAP -> handleSwap(current);
                case MARK_SORTED -> handleMarkSorted(current);
                case DONE_SORTED -> handleDoneSorted();
            }
            stepIndex++;
            view.getControls().enablePreviousStep();
        }
    }

    public void onPreviousStep() {
        pause(); // switch to manual on button pressed
        if (stepIndex > 0) {
            stepIndex--;

            SortStep undoStep = model.getData().getSteps().get(stepIndex);
            switch (undoStep.getType()) {
                case COMPARE -> handleCompare(undoStep);
                case SWAP -> handleSwap(undoStep);
                case MARK_SORTED -> undoMarkSorted(undoStep);
                case DONE_SORTED -> undoDoneSorted();
            }

            if (stepIndex == 0) {
                view.getControls().disablePreviousStep();
            }
        }
    }

    private void handleCompare(SortStep step) {
        view.render(viewArray, viewSortedArray, step.getIndexA(), step.getIndexB(), Colors.COMPARE);
    }

    private void handleSwap(SortStep step) {
        Integer tmp = viewArray[step.getIndexA()];
        viewArray[step.getIndexA()] = viewArray[step.getIndexB()];
        viewArray[step.getIndexB()] = tmp;

        view.render(viewArray, viewSortedArray, step.getIndexA(), step.getIndexB(), Colors.SWAP);
    }

    private void handleMarkSorted(SortStep step) {
        viewSortedArray[step.getIndexA()] = true;
        view.render(viewArray, viewSortedArray);
    }

    private void handleDoneSorted() {
        Arrays.fill(viewSortedArray, true);
        view.render(viewArray, viewSortedArray);
        finished = true;
        view.getControls().disableNextAndPlay();
    }

    private void undoMarkSorted(SortStep step) {
        viewSortedArray[step.getIndexA()] = false;
        view.render(viewArray, viewSortedArray);
    }

    private void undoDoneSorted() {
        finished = false;
        Arrays.fill(viewSortedArray, false);

        // reconstruct viewSortedArray
        for (SortStep step :  model.getData().getSteps()) {
            if (step.getType().equals(MARK_SORTED)) {
                viewSortedArray[step.getIndexA()] = true;
            }
        }

        view.render(viewArray, viewSortedArray);
        view.getControls().enableNextStep();
    }

    public void togglePlayPause() {
        if (isPlaying) {
            pause();
        } else {
            play();
        }
    }

    private void pause() {
        if (isPlaying) {
            isPlaying = false;
            timeline.pause();
            view.getControls().setPlayPauseBtnText("Play");
        }
    }

    private void play() {
        if (!isPlaying && !finished) {
            isPlaying = true;
            timeline.play();
            view.getControls().setPlayPauseBtnText("Pause");
        }
    }

    private void initTimeline() {
        if (timeline != null) {
            timeline.stop();
        }
        timeline = new Timeline(new KeyFrame(Duration.millis(FRAME_DURATION_MILLIS), e -> playNextStep()));
        timeline.setCycleCount(Animation.INDEFINITE);
    }
}
