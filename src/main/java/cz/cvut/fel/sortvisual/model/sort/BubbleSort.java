package cz.cvut.fel.sortvisual.model.sort;

import cz.cvut.fel.sortvisual.model.TrackedList;

public class BubbleSort extends SortingAlgorithm {
    public BubbleSort(SortingAlgoInfo info) {
        super(info);
    }

    public void sort(TrackedList data) {
        Integer[] array = data.getRawData();

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length-1; j++) {
                if (array[j] > array[j+1]) {
                    Integer tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                }
            }
        }

        data.setRawData(array);
    }
}
