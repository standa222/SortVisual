package cz.cvut.fel.sortvisual.model.sort;

import cz.cvut.fel.sortvisual.model.TrackedList;

public class SelectionSort extends SortingAlgorithm {
    public SelectionSort(SortingAlgoInfo info) {
        super(info);
    }

    @Override
    public void sort(TrackedList data) {
        for (int i = 0; i < data.getSize(); i++) {
            int minIndex = i;
            for (int j = i+1; j < data.getSize(); j++) {
                if (data.isLowerThan(j, minIndex)) {
                    minIndex = j;
                }
            }

            if (i != minIndex) {
                data.swap(i, minIndex);
            }
            data.markSorted(i);
        }
        data.doneSorted();
    }
}
