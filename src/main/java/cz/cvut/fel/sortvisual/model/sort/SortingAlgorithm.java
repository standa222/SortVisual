package cz.cvut.fel.sortvisual.model.sort;

import cz.cvut.fel.sortvisual.model.TrackedList;

public abstract class SortingAlgorithm {
    private final SortingAlgoInfo info;

    public SortingAlgorithm(SortingAlgoInfo info) {
        this.info = info;
    }

    // sort data in the list
    abstract public void sort(TrackedList data);

    public SortingAlgoInfo getInfo() {
        return info;
    }
}
