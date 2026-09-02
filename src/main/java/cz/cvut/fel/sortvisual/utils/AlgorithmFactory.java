package cz.cvut.fel.sortvisual.utils;

import cz.cvut.fel.sortvisual.model.sort.BubbleSort;
import cz.cvut.fel.sortvisual.model.sort.SortingAlgoInfo;
import cz.cvut.fel.sortvisual.model.sort.SortingAlgorithm;

import java.util.List;

public class AlgorithmFactory {
    public List<SortingAlgorithm> createAll() {
        return List.of(
                new BubbleSort(new SortingAlgoInfo(
                        "Bubble Sort",
                        "n^2",
                        "n^2",
                        "reverse sorted data",
                        "n",
                        "already sorted"
                ))
        );
    }
}
