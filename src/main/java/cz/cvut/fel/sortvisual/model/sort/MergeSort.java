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

    public void mergeSort(TrackedList data, int lo, int hi) {
        if (hi-lo < 1) {
            return;
        }

        int half = lo + (hi-lo) / 2;
        mergeSort(data, lo, half);
        mergeSort(data, half+1, hi);

        merge(data, lo, half, half+1, hi);
    }

    private void merge(TrackedList data, int leftLo, int leftHi, int rightLo, int rightHi) {
        int left = leftLo; // left index
        int right = rightLo; // right index
        int current = leftLo;

        while (left <= leftHi && right <= rightHi) {
            if (data.isLowerOrEqualTo(left, right)) {
                data.insert(left, current);
            } else {
                data.insert(right, current);
                right++;
                leftHi++;
            }
            left++;
            current++;
        }
    }
}
