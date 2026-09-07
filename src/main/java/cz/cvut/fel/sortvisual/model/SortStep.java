package cz.cvut.fel.sortvisual.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SortStep {
    private SortStepType type;
    private int indexA = -1;
    private int indexB = -1;

    public SortStep(SortStepType type, int indexA) {
        this.type = type;
        this.indexA = indexA;
    }

    public SortStep(SortStepType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type + "(" + indexA + ", " + indexB + ")";
    }
}
