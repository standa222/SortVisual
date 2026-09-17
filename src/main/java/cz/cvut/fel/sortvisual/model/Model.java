package cz.cvut.fel.sortvisual.model;

import cz.cvut.fel.sortvisual.model.sort.SortingAlgorithm;
import cz.cvut.fel.sortvisual.utils.Shuffler;
import lombok.Getter;

@Getter
public class Model {
    private SortingAlgorithm sortingAlgo;
    private final Shuffler shuffler;
    private TrackedList data;

    public Model() {
        this.shuffler = new Shuffler();
    }

    public void shuffle() {
        shuffler.shuffleRandom(data);
    }

    public void sort() {
        data.getSteps().clear();
        sortingAlgo.sort(data);
    }

    public void setSortingAlgo(SortingAlgorithm sortingAlgo, int size) {
        this.sortingAlgo = sortingAlgo;
        data = new TrackedList(size);
    }

    public TimeComplexityData computeTimeComplexityData() {
        return new TimeComplexityData();
    }
}
