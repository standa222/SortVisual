package cz.cvut.fel.sortvisual.controller;

import cz.cvut.fel.sortvisual.model.SortStep;
import cz.cvut.fel.sortvisual.model.sort.SortingAlgorithm;
import cz.cvut.fel.sortvisual.utils.Colors;
import cz.cvut.fel.sortvisual.view.View;
import cz.cvut.fel.sortvisual.model.Model;

import java.util.Arrays;
import java.util.List;

public class Controller {
    private View view;
    private final Model model;
    private final List<SortingAlgorithm> algorithms;
    private Integer[] viewArray;
    private boolean[] viewSortedArray;
    private int stepIndex = 0;
    private boolean finished = false;

    public Controller(Model model,  List<SortingAlgorithm> algorithms) {
        this.model = model;
        this.algorithms = algorithms;
    }

    public void onShuffle() {
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
        stepIndex = 0;
        finished = false;
        model.sort();
    }

    public void onBackToMenu() {
        view.showMenu(algorithms);
    }

    public void onNextStep() {
        if (!finished) {
            SortStep current = model.getData().getSteps().get(stepIndex);

            switch (current.getType()) {
                case COMPARE -> handleCompare(current);
                case SWAP -> handleSwap(current);
                case MARK_SORTED -> handleMarkSorted(current);
                case DONE_SORTED -> handleDoneSorted();
            }
            stepIndex++;
        }
    }

    public void onPreviousStep() {

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
    }

}
