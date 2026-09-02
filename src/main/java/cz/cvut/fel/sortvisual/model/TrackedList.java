package cz.cvut.fel.sortvisual.model;

import java.util.ArrayList;

public class TrackedList extends ArrayList<Integer> {
    private int[] data;
//    private List<SortStep> steps = new ArrayList<>();

    public TrackedList() {
        data = new int[100];
        for (int i = 0; i < data.length; i++) {
            data[i] = i + 1;
        }
    }
}
