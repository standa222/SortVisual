package cz.cvut.fel.sortvisual.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static cz.cvut.fel.sortvisual.model.SortStepType.*;

@Getter
@Setter
public class TrackedList {
    private Integer[] rawData;
    private int size;
    private int active; // index of a "currently important index", like a pivot in quick sort, or current MIN in selection sort
    private List<SortStep> steps = new ArrayList<>();

    public TrackedList(int size) {
        this.size = size;
        rawData = new Integer[size];
        for (int i = 0; i < rawData.length; i++) {
            rawData[i] = i + 1;
        }
    }

    public boolean isGreaterThan(int indexA,  int indexB) {
        steps.add(new SortStep(COMPARE, indexA, indexB));
        return rawData[indexA] > rawData[indexB];
    }

    public boolean isLowerThan(int indexA, int indexB) {
        steps.add(new SortStep(COMPARE, indexA, indexB));
        return rawData[indexA] < rawData[indexB];
    }

    public boolean isLowerOrEqualTo(int indexA, int indexB) {
        steps.add(new SortStep(COMPARE, indexA, indexB));
        return rawData[indexA] <= rawData[indexB];
    }

    public void swap(int indexA, int indexB) {
        steps.add(new SortStep(BEFORE_SWAP, indexA, indexB));
        steps.add(new SortStep(SWAP, indexA, indexB));
        int temp = rawData[indexA];
        rawData[indexA] = rawData[indexB];
        rawData[indexB] = temp;
    }

    public void markSorted(int index) {
        steps.add(new SortStep(MARK_SORTED, index));
    }

    public void doneSorted() {
        steps.add(new SortStep(DONE_SORTED));
    }

    public void insert(int initial, int target) {
        while (initial > target) {
            swap(initial, initial-1);
            initial--;
        }
    }
}
