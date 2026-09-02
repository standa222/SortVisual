package cz.cvut.fel.sortvisual.controller;

import cz.cvut.fel.sortvisual.model.sort.SortingAlgorithm;
import cz.cvut.fel.sortvisual.view.View;
import cz.cvut.fel.sortvisual.model.Model;

import java.util.List;

public class Controller {
    private View view;
    private final Model model;
    private final List<SortingAlgorithm> algorithms;

    public Controller(Model model,  List<SortingAlgorithm> algorithms) {
        this.model = model;
        this.algorithms = algorithms;
    }

    public void shuffle() {
        model.shuffle();
    }

    // called on init - show menu immediately
    public void setView(View view) {
        this.view = view;
        view.showMenu(algorithms);
    }

    public void onSelectAlgorithm(SortingAlgorithm algo) {
        model.setSortingAlgo(algo);
        view.showVisualizer(algo.getInfo());
    }
}
