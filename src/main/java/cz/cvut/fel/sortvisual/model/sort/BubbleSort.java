package cz.cvut.fel.sortvisual.model.sort;

import cz.cvut.fel.sortvisual.model.TrackedList;

public class BubbleSort extends SortingAlgorithm {
    public BubbleSort(SortingAlgoInfo info) {
        super(info);
    }

    public void sort(TrackedList data) {
        boolean swapped = true;
        int i = 1;
        while (swapped) {
            swapped = false;
            for (int j = 0; j < data.getSize()-i; j++) {
                if (data.isGreaterThan(j, j+1)) {
                    data.swap(j, j+1);
                    swapped = true;
                }
            }
            data.markSorted(data.getSize()-i);
            i++;
        }
        data.doneSorted();
    }
}
