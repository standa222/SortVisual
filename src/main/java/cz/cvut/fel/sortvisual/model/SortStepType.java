package cz.cvut.fel.sortvisual.model;

public enum SortStepType {
    COMPARE,
    BEFORE_SWAP, // uses swap color, but already before swap for better visual clarity
    SWAP,
    MARK_SORTED,
    DONE_SORTED,
    OVERWRITE,
}
