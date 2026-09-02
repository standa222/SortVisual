package cz.cvut.fel.sortvisual.utils;

import cz.cvut.fel.sortvisual.model.TrackedList;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Shuffler {
    // returns randomly shuffled data
    public void shuffleRandom(TrackedList trackedList) {
        List<Integer> list = Arrays.asList(trackedList.getRawData());
        Collections.shuffle(list);

        trackedList.setRawData(list.toArray(new Integer[0]));
    }

    // returns sorted data
    public TrackedList shuffleBest(TrackedList list) {
        return list;
    }

    // returns reversely sorted data
    public TrackedList shuffleWorst(TrackedList list) {
        return list;
    }
}
