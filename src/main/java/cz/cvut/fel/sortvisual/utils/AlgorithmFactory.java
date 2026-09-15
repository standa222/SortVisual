package cz.cvut.fel.sortvisual.utils;

import cz.cvut.fel.sortvisual.model.sort.BubbleSort;
import cz.cvut.fel.sortvisual.model.sort.SelectionSort;
import cz.cvut.fel.sortvisual.model.sort.SortingAlgoInfo;
import cz.cvut.fel.sortvisual.model.sort.SortingAlgorithm;

import java.util.List;

public class AlgorithmFactory {
    public List<SortingAlgorithm> createAll() {
        return List.of(
                new BubbleSort(new SortingAlgoInfo(
                        "Bubble Sort",
                        "O(n^2)",
                        true,
                        "O(n^2)",
                        "reverse sorted data",
                        "O(n)",
                        "already sorted",
                        "O(1)"
                )),
                new SelectionSort(new SortingAlgoInfo(
                        "Selection Sort",
                        "O(n^2)",
                        true,
                        "O(n^2)",
                        "",
                        "O(n^2)",
                        "",
                        "O(1)"
                ))
        );
    }
}
