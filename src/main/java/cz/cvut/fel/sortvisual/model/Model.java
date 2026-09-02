package cz.cvut.fel.sortvisual.model;

import cz.cvut.fel.sortvisual.model.sort.SortingAlgorithm;
import cz.cvut.fel.sortvisual.utils.Shuffler;
import lombok.Getter;

public class Model {
    private SortingAlgorithm sortingAlgo;
    private final Shuffler shuffler;
    @Getter
    private TrackedList data;

    public Model() {
        this.shuffler = new Shuffler();
    }

    public void shuffle() {
        shuffler.shuffleRandom(data);
    }

    public void sort() {
        sortingAlgo.sort(data);
    }

    public void setSortingAlgo(SortingAlgorithm sortingAlgo) {
        this.sortingAlgo = sortingAlgo;
        data = new TrackedList();
    }
}
