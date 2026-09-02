package cz.cvut.fel.sortvisual.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrackedList {
    private Integer[] rawData;
//    private List<SortStep> steps = new ArrayList<>();

    public TrackedList() {
        rawData = new Integer[100];
        for (int i = 0; i < rawData.length; i++) {
            rawData[i] = i + 1;
        }
    }
}
