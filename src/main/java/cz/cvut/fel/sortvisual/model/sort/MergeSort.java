package cz.cvut.fel.sortvisual.model.sort;

import cz.cvut.fel.sortvisual.model.TrackedList;

public class MergeSort extends SortingAlgorithm {
    public MergeSort(SortingAlgoInfo info) {
        super(info);
    }

    @Override
    public void sort(TrackedList data) {
        mergeSort(data, 0, data.getSize()-1);
        data.doneSorted();
    }

    private void mergeSort(TrackedList data, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        int mid = lo + (hi - lo) / 2;
        mergeSort(data, lo, mid);
        mergeSort(data, mid + 1, hi);

        merge(data, lo, mid, hi);
    }

    private void merge(TrackedList data, int lo, int mid, int hi) {
        int[] temp = new int[hi - lo + 1];
        int i = lo;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= hi) {
            if (data.isLowerOrEqualTo(i, j)) {
                temp[k++] = data.get(i++);
            } else {
                temp[k++] = data.get(j++);
            }
        }

        while (i <= mid) {
            temp[k++] = data.get(i++);
        }

        while (j <= hi) {
            temp[k++] = data.get(j++);
        }

        for (int m = 0; m < temp.length; m++) {
            data.set(lo + m, temp[m]);
        }
    }
}
