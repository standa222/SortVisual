package cz.cvut.fel.sortvisual.model.sort;

import cz.cvut.fel.sortvisual.model.TrackedList;

public class HeapSort extends SortingAlgorithm {
    public HeapSort(SortingAlgoInfo info) {
        super(info);
    }

    @Override
    public void sort(TrackedList data) {
        heapify(data);

        int end = data.getSize();
        while (end > 1) {
            end--;
            data.swap(end, 0);
            data.markSorted(end);
            siftDown(data, 0, end);
        }

        data.doneSorted();
    }

    private void heapify(TrackedList data) {
        int start = parent(data.getSize()-1) + 1;

        while (start > 0) {
            start--;
            siftDown(data, start, data.getSize());
        }
    }

    private void siftDown(TrackedList data, int root, int end) {
        while (leftChild(root) < end) {
            int child = leftChild(root);
            if (child+1 < end && data.isLowerThan(child, child+1)) {
                child++;
            }

            if (data.isLowerThan(root, child)) {
                data.swap(root, child);
                root = child;
            } else {
                return;
            }
        }
    }

    private int leftChild(int i) {
        return 2*i+1;
    }

    private int rightChild(int i) {
        return 2*i+2;
    }

    private int parent(int i) {
        return (i-1)/2;
    }
}
