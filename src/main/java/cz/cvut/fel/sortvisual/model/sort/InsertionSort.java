package cz.cvut.fel.sortvisual.model.sort;

import cz.cvut.fel.sortvisual.model.TrackedList;

public class InsertionSort extends SortingAlgorithm {
    public InsertionSort(SortingAlgoInfo info) {
        super(info);
    }

    @Override
    public void sort(TrackedList data) {
        for (int i = 1; i < data.getSize(); i++) {
            int j = i;
            while (j > 0 && data.isGreaterThan(j-1, j)) {
                data.swap(j-1, j);
                j--;
            }
        }
        data.doneSorted();
    }
}
