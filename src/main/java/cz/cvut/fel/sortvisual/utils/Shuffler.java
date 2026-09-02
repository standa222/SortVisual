package cz.cvut.fel.sortvisual.utils;

import cz.cvut.fel.sortvisual.model.TrackedList;

public class Shuffler {
    // returns randomly shuffled data
    public TrackedList shuffleRandom(TrackedList list) {
        list.add(15);
        return list;
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
