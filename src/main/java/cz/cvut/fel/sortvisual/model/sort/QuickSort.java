package cz.cvut.fel.sortvisual.model.sort;

import cz.cvut.fel.sortvisual.model.TrackedList;

public class QuickSort extends SortingAlgorithm {
    public QuickSort(SortingAlgoInfo info) {
        super(info);
    }

    @Override
    public void sort(TrackedList data) {
        sortPart(data, 0, data.getSize()-1);
        data.doneSorted();
    }

    private void sortPart(TrackedList data, int lo, int hi) {
        if (lo >= 0 && hi >= 0 && lo < hi) {
            int p = partition(data, lo, hi);
            sortPart(data, lo, p);
            sortPart(data, p+1, hi);
        } else {
            data.markSorted(lo);
        }
    }

    private int partition(TrackedList data, int lo, int hi) {
        int pivot = lo; // possible todo - improve pivot choice

        int i = lo-1; // left index
        int j = hi+1; // right index

        while (true) {
            do i++;
            while (data.isLowerThan(i, pivot));

            do j--;
            while (data.isGreaterThan(j, pivot));

            if (i >= j) return j;
            data.swap(i, j);
        }
    }
}
