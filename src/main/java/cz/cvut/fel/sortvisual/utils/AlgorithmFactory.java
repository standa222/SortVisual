package cz.cvut.fel.sortvisual.utils;

import cz.cvut.fel.sortvisual.model.sort.*;

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
                        false,
                        "O(n^2)",
                        "",
                        "O(n^2)",
                        "",
                        "O(1)"
                )),
                new InsertionSort(new SortingAlgoInfo(
                        "Insertion Sort",
                        "O(n^2)",
                        true,
                        "O(n^2)",
                        "",
                        "O(n)",
                        "",
                        "O(1)"
                ))
                // TODO - graph comparison for every algorithm with growing n, compared to n^2, n*log n etc.

        );
    }
}
