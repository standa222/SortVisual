package cz.cvut.fel.sortvisual.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SortStep {
    private SortStepType type;
    private int indexA = -1;
    private int indexB = -1;
    private final int value;
    private final int previousValue;

    public SortStep(SortStepType type, int indexA, int indexB) {
        this(type, indexA, indexB, -1, -1);
    }

    public SortStep(SortStepType type, int indexA) {
        this(type, indexA, -1, -1, -1);
    }

    public SortStep(SortStepType type) {
        this(type, -1, -1, -1, -1);
    }

    public SortStep(SortStepType type, int index, int value, int previousValue) {
        this.type = type;
        this.indexA = index;
        this.indexB = -1;
        this.value = value;
        this.previousValue = previousValue;
    }
}
